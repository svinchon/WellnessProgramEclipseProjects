package com.diy.xpressionhelper;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IdFactory {
	private final String hostName;
	private final long creationTimeMillis;
	private long lastTimeMillis;
	private long discriminator;

	public IdFactory() throws UnknownHostException {
		this.hostName = InetAddress.getLocalHost().getHostAddress();
		this.creationTimeMillis = System.currentTimeMillis();
		this.lastTimeMillis = creationTimeMillis;
	}

	public synchronized Serializable createId() {
		String id;
		long now = System.currentTimeMillis();

		if (now == lastTimeMillis) {
			++discriminator;
		} else {
			discriminator = 0;
		}
		id = String.format("%s-%d-%d-%d", hostName, creationTimeMillis, now, discriminator);
		lastTimeMillis = now;
		return id;
	}
}
