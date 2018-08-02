package com.icourt.clouddisk.filter;

import com.icourt.clouddisk.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * 用来验证用户的token
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */

@Component
@Slf4j
public class TokenVerifyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //获取请求头中的authorization信息
        String authHeader = httpServletRequest.getHeader(JwtUtil.TOKEN);
        if(JwtUtil.OPTIONS.equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //如果验证用户没有token,既当前用户属于未认证状态
            if (StringUtils.isBlank(authHeader)){
                log.debug("未认证状态");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.addHeader("tokenValid", "false");

            }else{
                //解析token

                try {
                    Map<String, Object> stringObjectMap = JwtUtil.validToken(authHeader);
                    int i = (int)stringObjectMap.get("result");

                    if(i == JwtUtil.OK){
                        //如果用户带有token，并且没有过期
                        log.debug("token解析成功");
                        JSONObject jsonObject = (JSONObject)stringObjectMap.get("data");
                        log.debug("uid" + jsonObject.get("uid"));
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                        filterChain.doFilter(servletRequest, servletResponse);
                    }else if(i == JwtUtil.EXPIRE){
                        log.debug("token过期");
                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        httpServletResponse.addHeader("tokenValid", "false");
                    }else if(i== JwtUtil.UNAUTHORIZED){
                        //用户修改了token，导致token不合法
                        log.debug("token不合法");
                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        httpServletResponse.addHeader("tokenValid", "false");
                    }

                } catch (JOSEException e) {
                    log.debug("内部错误",e);
                    //todo : 全局拦截器、、暂未实现
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    log.debug("转换错误",e);
                    throw new RuntimeException(e);
                }
            }


        }

    }

    @Override
    public void destroy() {

    }
}
