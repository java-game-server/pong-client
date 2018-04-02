package com.apporelbotna.gameserver.pongclient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.apporelbotna.gameserver.pongclient.view.PongGameView;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.net.GameStatusMessage;
import com.apporelbotna.gameserver.pongserver.stubs.net.PlayerMovementMessage;

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
		GameStatusMessage gameStatusMessage = GameStatusMessage.fromJson(serverConnection.readLine());
		while( ! gameStatusMessage.isGameFinished())
		{
			pongGameView.updateActorLocations(
					gameStatusMessage.getPlayer().getPosition(),
					gameStatusMessage.getEnemy().getPosition(),
					gameStatusMessage.getBall().getPosition().X,
					gameStatusMessage.getBall().getPosition().Y);
			pongGameView.repaint();
			gameStatusMessage = GameStatusMessage.fromJson(serverConnection.readLine());
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
