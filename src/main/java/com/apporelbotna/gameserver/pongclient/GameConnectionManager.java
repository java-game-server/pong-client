package com.apporelbotna.gameserver.pongclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

// to beeee is to refactor meeee
public class GameConnectionManager
{
	public static void main(String[] args)
	{
		try
		{
			Socket clientSocket = new Socket("localhost", 5555);
			System.out.println("Client: " + "Connection Established");

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(clientSocket.getOutputStream()));

			writer.write("palomino\n");
			writer.flush();

			String serverMsg;
			while((serverMsg = reader.readLine()) != null)
			{
				System.out.println("PONG SERVER SAYS: " + serverMsg);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
