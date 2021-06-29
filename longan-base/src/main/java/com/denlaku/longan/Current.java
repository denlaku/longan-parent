package com.denlaku.longan;

import com.denlaku.longan.vo.User;

/**
 * @author tianx
 */
public class Current {

	public static String getAccount() {
		User user = getUser();
		if (user != null) {
			return user.getAccount();
		}
		return null;
	}

	public static String getUserId() {
		User user = getUser();
		if (user != null) {
			return user.getId();
		}
		return null;
	}
	
	public static String getTenantId() {
		return "1000";
	}

	public static User getUser() {
		User user = new User();
		user.setId("1");
		user.setAccount("admin");
		return user;
	}

}
