/*
 * Copyright (c)
 */
package com.soft.fire.util;

import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES对称加密解密工具
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-08 12:08
 */
@Slf4j
public class AESUtil {


    /**
     * AES加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        if (StringUtil.isBlank(content) || StringUtil.isBlank(password)) {
            log.error("aes 加密参数不能为空!!");
            return null;
        }
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = content.getBytes("UTF-8");
            //初始化为加密密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            byte[] encryptByte = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            log.error("AES encryption operation has exception,content:{},password:{}", content, password, e);
        }
        return null;
    }

    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        //生成指定算法密钥的生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(password.getBytes()));
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //转换成AES的密钥
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }


    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     * @param password       解密的密钥
     * @return
     */
    public static String decrypt(String encryptContent, String password) {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            //执行解密操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptContent));
            return new String(result, "UTF-8");
        } catch (Exception e) {
            log.error("AES decryption operation has exception,content:{},password:{}", encryptContent, password, e);
        }
        return null;
    }

}
