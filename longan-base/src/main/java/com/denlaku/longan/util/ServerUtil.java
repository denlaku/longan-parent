package com.denlaku.longan.util;

import com.denlaku.longan.Platform;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author tianx
 */
@Slf4j
public class ServerUtil {

	public static List<String> getIps() {
		List<String> ips = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = addresses.nextElement();
					if (ip.isSiteLocalAddress()) {
						ips.add(ip.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (ips.size() > 2) {
			ips.remove("127.0.0.1");
		}
		return ips;
	}

	public static String getIp() {
		List<String> ips = getIps();
		Collections.sort(ips);
		return String.join(",", ips);
	}

	public static String getPlatform() {
		String platform = Platform.UNKNOWN.getName();
		Platform[] platforms = Platform.values();
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName != null && !osName.isEmpty()) {
			for (Platform pf : platforms) {
				if (osName.contains(pf.getName())) {
					platform = pf.getName();
					break;
				}
			}
		}
		return platform;
	}

	private ServerUtil() {
	}

}
