package com.apporelbotna.gameserver.pongclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

import com.apporelbotna.gameserver.pongclient.view.PongGameView;
import com.apporelbotna.gameserver.pongserver.stubs.Player;
import com.apporelbotna.gameserver.pongserver.stubs.ServerMessage;

// to beeee is to refactor meeee
public class GameFinder
{
	public static void main(String[] args)
	{
		// TODO crear una instancia de Player a traves de las credenciales que ?pasara por comando? el
		// launcher
		// De esta instancia se pasara el username al server y la ref al PongGameView

		try (Socket clientSocket = new Socket("localhost", 5555))
		{
			System.out.println("Client: " + "Connection Established");

			ServerConnection serverConnection = new ServerConnection(clientSocket);
			BufferedReader reader = serverConnection.getReader();
			BufferedWriter writer = serverConnection.getWriter();

			writer.write("palomino\n");
			writer.flush();

			String serverMsg;
			ServerMessage serverMessage = null;
			while((serverMsg = reader.readLine()) != null)
			{
				if(serverMsg.startsWith("{"))
				{
					serverMessage = ServerMessage.fromJson(serverMsg);
					break;
				}
				System.out.println(serverMsg);
			}
			if(serverMessage != null)
			{
				PongGameView game = new PongGameView(new Player("cabronazo"), serverMessage.getEnemy());
				game.launch();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
