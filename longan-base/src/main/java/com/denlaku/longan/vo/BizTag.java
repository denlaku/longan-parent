package com.denlaku.longan.vo;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public class BizTag extends TenantBase {
    /**
     * tag名称
     */
    private String name;
    /**
     * 业务
     */
    private String biz;
}
