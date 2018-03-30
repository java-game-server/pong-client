package com.apporelbotna.gameserver.pongclient.view;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.apporelbotna.gameserver.pongserver.stubs.Player;
import com.apporelbotna.gameserver.pongserver.stubs.PongGame;
import com.apporelbotna.gameserver.pongserver.stubs.Vector2;

public class PongGameView
{
	private PongGame pongGame;
	private PongGameScene scene;
	private JFrame frame;

	public PongGameView(Player player, Player enemy)
	{
		player.setControlledPlayer(true);
		pongGame = new PongGame(player, enemy);
		scene = new PongGameScene(pongGame);
		frame = new JFrame("Pong");
	}

	public void launch()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.BLACK);
		frame.add(scene);
		frame.setSize(scene.getWindowWidth(), scene.getWindowHeight());
		frame.setVisible(true);
	}

	public void addKeyListener(KeyListener keyListener)
	{
		frame.addKeyListener(keyListener);
	}

	public void updateActorLocations(int playerHeight, int enemyHeight, int ballX, int ballY)
	{
		pongGame.getPlayer1().setPosition(playerHeight);
		pongGame.getPlayer2().setPosition(enemyHeight);
		pongGame.getBall().setPosition(Vector2.of(ballX, ballY));
	}

	public void repaint()
	{
		frame.repaint();
	}
}
