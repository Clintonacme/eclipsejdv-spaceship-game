package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import input.Keyboard;
import math.Vector2D;

public class Player extends GameObject{

	public Player(Vector2D position, BufferedImage texture) {
		super(position, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		
		if(Keyboard.RIGHT){
			position.setX(position.getX() + 4);
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
		
	}
	

}
