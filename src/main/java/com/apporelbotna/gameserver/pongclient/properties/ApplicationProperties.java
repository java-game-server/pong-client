package com.apporelbotna.gameserver.pongclient.properties;

import java.util.ResourceBundle;

public final class ApplicationProperties
{
	private static String version;
	private static String name;

	private static String serverIp;
	private static int serverSocketPort;

	private ApplicationProperties()
	{
		throw new UnsupportedOperationException("ApplicationProperties must not be instantiated!");
	}

	static
	{
		ResourceBundle bundle = ResourceBundle
				.getBundle("com.apporelbotna.gameserver.pongclient.properties.application");

		version = bundle.getString("version");
		name = bundle.getString("name");

		serverIp = bundle.getString("server.ip");
		serverSocketPort = Integer.valueOf(bundle.getString("server.socketport"));
	}

	public static String getVersion()
	{
		return version;
	}

	public static String getName()
	{
		return name;
	}

	public static String getServerIp()
	{
		return serverIp;
	}

	public static int getServerSocketPort()
	{
		return serverSocketPort;
	}
}
