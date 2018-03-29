package com.apporelbotna.gameserver.pongclient.view;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import com.apporelbotna.gameserver.pongserver.stubs.Drawable;
import com.apporelbotna.gameserver.pongserver.stubs.PongGame;

public class PongGameScene extends JPanel
{
	private static final long serialVersionUID = 335639977548893422L;

	private transient List<Drawable> drawables;

	public PongGameScene(int windowWidth, int windowHeight, Drawable... drawables)
	{
		setWindowWidth(windowWidth);
		setWindowHeight(windowHeight);
		this.drawables = Arrays.asList(drawables);
	}

	public PongGameScene(PongGame pongGame)
	{
		this(
				PongGame.WINDOW_WIDTH,
				PongGame.WINDOW_HEIGHT,
				pongGame.getBall(),
				pongGame.getPlayer1().getPawn(),
				pongGame.getPlayer2().getPawn()
		);
	}

	public int getWindowWidth()
	{
		return (int)this.getSize().getWidth();
	}

	public void setWindowWidth(int windowWidth)
	{
		this.setSize(windowWidth, getWindowHeight());
	}

	public int getWindowHeight()
	{
		return (int)this.getSize().getHeight();
	}

	public void setWindowHeight(int windowHeight)
	{
		this.setSize(getWindowWidth(), windowHeight);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		for(Drawable drawable : drawables)
			drawable.draw(g);
	}
}
