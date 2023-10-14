package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	
	private boolean [] keys = new boolean[256];
	
	// manejo de las flechas para la nave
	
	public static boolean UP, LEFT, RIGHT; 
	
	public Keyboard(){
		
		UP = false;
		RIGHT = false;
		LEFT = false;
		
	}
	
	
	public void update()
	{
		UP = keys[KeyEvent.VK_UP];
		LEFT = keys[KeyEvent.VK_LEFT];
		RIGHT = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
	keys[e.getKeyCode()] = true;	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}