package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LookupItem extends TenantBase {
    private String lookupCode;
    private String code;
    private String name;
    private String desc;
    private String language;
    private String order;
}
