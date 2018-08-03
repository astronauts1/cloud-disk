package com.icourt.clouddisk.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来生成token，解析token
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */
public class JwtUtil {


    private static final byte[] SECRET = "geiwodiangasfdjsikolkjikolkijswe".getBytes();


    public static final String TOKEN = "token";
//
    /**
     * options请求方式
     */
    public static final String OPTIONS = "OPTIONS";

    /**
     * 未认证
     */
    public static final Integer UNAUTHORIZED = 1;

    private static final String EXP = "exp";

    /**
     * 认证成功
     */
    public static final Integer OK = 0;

    /**
     * 登录过期
     */
    public static final Integer EXPIRE = 2;

    /**
     * 创建token
     * @param payloadMap
     * @return
     * @throws JOSEException
     */
    public static String createToken(Map<String,Object> payloadMap) throws JOSEException {
        //定义算法加密规则
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷
        Payload payload = new Payload(new JSONObject(payloadMap));

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        //建立一个秘钥

        JWSSigner jwsSigner = new MACSigner(SECRET);

        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }




    /**
     * 解析token
     * @param token
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public static Map<String,Object> validToken(String token) throws JOSEException, ParseException {
        JWSObject jwsObject = JWSObject.parse(token);

        //获取到载荷

        Payload payload = jwsObject.getPayload();

        JWSVerifier jwsVerifier = new MACVerifier(SECRET);

        Map<String,Object> resultMap = new HashMap<>(2);

        //验证token
        if(jwsObject.verify(jwsVerifier)){
            resultMap.put("result",OK);
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data",jsonObject);

            //判断token是否过期

            Long expTime = Long.valueOf(jsonObject.get(EXP).toString());
            Long nowTime = System.currentTimeMillis();
            //判断是否过期
            if (nowTime > expTime) {
                //已经过期
                resultMap.clear();
                resultMap.put("result", EXPIRE);

            }
        }
        else {
            resultMap.put("result",UNAUTHORIZED);
        }
        return resultMap;
    }


}
