package com.apporelbotna.gameserver.pongclient.view;

import java.awt.Color;

import javax.swing.JFrame;

import com.apporelbotna.gameserver.pongserver.stubs.Player;
import com.apporelbotna.gameserver.pongserver.stubs.PongGame;

public class PongGameView
{
	private PongGameScene scene;

	public PongGameView(Player player1, Player player2)
	{
		scene = new PongGameScene(new PongGame(player1, player2));
	}

	public void launch()
	{
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.BLACK);
		frame.add(scene);
		frame.setSize(scene.getWindowWidth(), scene.getWindowHeight());
		frame.setVisible(true);
	}
}
