package com.apporelbotna.gameserver.pongclient;

import com.apporelbotna.gameserver.pongserver.stubs.model.Player;

public class AuthenticatedPlayer
{
	private Player player;
	private static AuthenticatedPlayer instance;

	private AuthenticatedPlayer() { }

	public static AuthenticatedPlayer getInstance()
	{
		if(instance == null)
			return new AuthenticatedPlayer();
		return instance;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
