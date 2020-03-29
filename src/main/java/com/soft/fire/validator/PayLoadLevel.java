/*
 * Copyright (c)
 */
package com.soft.fire.validator;

import javax.validation.Payload;

/**
 * 效验问题级别
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-22 15:27
 */
public class PayLoadLevel {
     //信息提示级别
     public static interface INFO extends Payload {}
     // 警告级别
     public static interface WARN extends  Payload{}
}
