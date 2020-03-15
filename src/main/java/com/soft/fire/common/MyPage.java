/*
 * Copyright (c)
 */
package com.soft.fire.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义分页对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-14 23:14
 */
@Getter
@Setter
public class MyPage<T> extends Page<T> {
    private static final long serialVersionUID = 5194933845448697148L;
    /**
     * 默认每页显示10条
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    /**
     *
     */
    private String name;

    public MyPage(long current, long size) {
        super(current, size);
    }
}
