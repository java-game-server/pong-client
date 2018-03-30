package com.apporelbotna.gameserver.pongclient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.apporelbotna.gameserver.pongclient.view.PongGameView;
import com.apporelbotna.gameserver.pongserver.stubs.Player;
import com.apporelbotna.gameserver.pongserver.stubs.PlayerMovementMessage;
import com.apporelbotna.gameserver.pongserver.stubs.ServerMessage;

public class PongClientController
{
	private ServerConnection serverConnection;
	private PongGameView pongGameView;

	public PongClientController(Player player, Player enemy, ServerConnection serverConnection)
	{
		this.serverConnection = serverConnection;

		pongGameView = new PongGameView(player, enemy);
		pongGameView.launch();
		addKeyListenerToView();
	}

	public void beginGameLoop()
	{
		ServerMessage serverMessage = ServerMessage.fromJson(serverConnection.readLine());
		while( ! serverMessage.isGameFinished())
		{
			pongGameView.updateActorLocations(
					serverMessage.getPlayer().getPosition(),
					serverMessage.getEnemy().getPosition(),
					serverMessage.getBall().getPosition().X,
					serverMessage.getBall().getPosition().Y);
			pongGameView.repaint();
			serverMessage = ServerMessage.fromJson(serverConnection.readLine());
		}
	}

	private void addKeyListenerToView()
	{
		pongGameView.addKeyListener(new KeyListener()
		{
			@Override	public void keyTyped(KeyEvent e) { return; }
			@Override	public void keyReleased(KeyEvent e)	{	return; }

			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_UP)
					serverConnection.sendMessage(PlayerMovementMessage.GO_UP);
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					serverConnection.sendMessage(PlayerMovementMessage.GO_DOWN);
			}
		});
	}
}
