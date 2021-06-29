package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Lookup extends TenantBase {
    private String code;
    private String nameCn;
    private String nameEn;
}
