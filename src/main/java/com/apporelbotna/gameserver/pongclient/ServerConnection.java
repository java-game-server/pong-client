package com.apporelbotna.gameserver.pongclient;

import java.io.IOException;
import java.net.Socket;

import com.apporelbotna.gameserver.pongserver.stubs.PlayerMovementMessage;
import com.apporelbotna.gameserver.pongserver.stubs.SocketConnection;

public class ServerConnection extends SocketConnection
{
	public ServerConnection(Socket socket) throws IOException
	{
		super(socket);
	}

	public boolean sendMessage(PlayerMovementMessage msg)
	{
		return write(msg.getText());
	}
}
