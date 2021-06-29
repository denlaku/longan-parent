package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Server extends Base {

	/** 服务器IP */
	private String ip;
	/** 服务器平台: linux,unix,window,mac */
	private String platform;
	/** 机器编号 */
	private int machineId;
}
