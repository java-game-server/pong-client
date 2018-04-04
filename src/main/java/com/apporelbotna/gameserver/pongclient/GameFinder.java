package com.apporelbotna.gameserver.pongclient;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apporelbotna.gameserver.pongclient.properties.ApplicationProperties;
import com.apporelbotna.gameserver.pongserver.model.GameControllerThread;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.net.GameStatusMessage;
import com.apporelbotna.gameserver.stubs.Token;
import com.apporelbotna.gameserver.stubs.User;
import com.apporelbotna.gameserver.stubs.UserWrapper;

// REFACTOR meeee
public class GameFinder
{
	private static final Logger logger = LoggerFactory.getLogger(GameControllerThread.class);

	public static void main(String[] args)
	{
		String playerEmail = args[0];
		String playerToken = args[1];
		// TODO crear una instancia de Player a traves de las credenciales que ?pasara por comando? el
		// launcher
		// De esta instancia se pasara el username al server y la ref al PongGameView

		try (Socket clientSocket = new Socket(
				ApplicationProperties.getServerIp(),
				ApplicationProperties.getServerSocketPort()))
		{
			logger.info("Connection established!");

			ServerConnection serverConnection = new ServerConnection(clientSocket);

			// TODO uncomment when client ready
			// serverConnection.write(playerEmail);
			// serverConnection.write(playerToken);

			String serverMsg;
			GameStatusMessage gameStatusMessage = null;
			while((serverMsg = serverConnection.readLine()) != null)
			{
				if(GameStatusMessage.canCreateFromJson(serverMsg))
				{
					gameStatusMessage = GameStatusMessage.fromJson(serverMsg);
					break;
				}
				logger.info(serverMsg);
			}
			if(gameStatusMessage != null)
			{
				PongClientController gameController = new PongClientController(
						new Player(new UserWrapper(new User(playerEmail), new Token(playerToken))),
						gameStatusMessage.getEnemy(),
						serverConnection);

				gameController.beginGameLoop();
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}
}
