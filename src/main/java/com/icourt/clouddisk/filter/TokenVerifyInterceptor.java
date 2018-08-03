package com.icourt.clouddisk.filter;

import com.icourt.clouddisk.common.Logical;
import com.icourt.clouddisk.annotation.RequiredRole;
import com.icourt.clouddisk.exception.UnRoleAuthorizedException;
import com.icourt.clouddisk.service.IRoleService;
import com.icourt.clouddisk.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * token拦截器
 *
 * @author jianglu
 * Created 2018 - 08 - 02 - TIME
 */
@Slf4j
public class TokenVerifyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的token信息
        String authHeader = request.getHeader(JwtUtil.TOKEN);
        if(JwtUtil.OPTIONS.equals(request.getMethod())){
            return true;
        }else{
            //如果验证用户没有token,既当前用户属于未认证状态
            if (StringUtils.isBlank(authHeader)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.addHeader("tokenValid", "false");
            }else{
                //解析token
                Map<String, Object> stringObjectMap;
                try {
                    stringObjectMap = JwtUtil.validToken(authHeader);
                } catch (JOSEException e) {
                    log.debug("内部错误",e);
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    log.debug("转换错误",e);
                    throw new RuntimeException(e);
                }

                int i = (int)stringObjectMap.get("result");

                if(i == JwtUtil.OK){
                    //如果用户带有token，并且没有过期
                    JSONObject jsonObject = (JSONObject)stringObjectMap.get("data");
                    Long uid = (Long)jsonObject.get("uid");

                    Integer userId = uid.intValue();

                    if(annotationResolver(handler,userId)){
                        return true;
                    }
                    //如果用户不具有该角色
                    throw new UnRoleAuthorizedException("用户不具有能够访问该资源的角色");
                }else if(i == JwtUtil.EXPIRE){
                    log.debug("token过期");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.addHeader("tokenValid", "false");
                }else if(i== JwtUtil.UNAUTHORIZED){
                    //用户修改了token，导致token不合法
                    log.debug("token不合法");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.addHeader("tokenValid", "false");
                }
            }
            return false;
        }
    }


    @Resource
    IRoleService roleService;

    /**
     * 注解处理
     * @param handler
     * @param userId
     * @return
     */
    private boolean annotationResolver(Object handler,Integer userId){
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            //获取方法上的注解，如果包含身份验证
            RequiredRole requiredRole = handlerMethod.getMethod().getAnnotation(RequiredRole.class);
            //如果上面用了身份注解
            if(requiredRole!=null){
                //判断该用户是否有该身份
                Set<String> roles = roleService.listRoleNameByUserId(userId);
                Logical logical = requiredRole.logical();
                //两种验证方式，一种是包含所有的
                String[] roleValues = requiredRole.value();
                switch (logical){
                    case OR:
                        for(String role:roleValues){
                            if(roles.contains(role)){
                                return true;
                            }
                        }
                        //如果该用户不包含该任意一个角色，无法进入
                        return false;
                    case AND:
                        List<String> roleList = Arrays.asList(roleValues);
                        return roles.containsAll(roleList);
                    default:

                }


            }
        }
        return true;
    }

}
