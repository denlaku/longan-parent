package com.denlaku.longan.vo;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public class DashboardHis extends TenantBase {
    /**
     * 仪表板id
     */
    private String bizId;
    /**
     * 仪表板
     */
    private Dashboard dashboard;
}
