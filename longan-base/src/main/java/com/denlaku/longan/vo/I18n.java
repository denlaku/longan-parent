package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class I18n extends TenantBase {
    private String key;
    private String text;
    private String desc;
    private String language;
}
