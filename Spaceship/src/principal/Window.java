package principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{

	//creacion de objetos
	public static final int WIDTH =800, HEIGHT = 800;
	private Canvas canvas;
	//hilo de vida
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;

	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS; //objetivo de tiempo
	private double delta= 0;
	private int PROMEDIOFPS = FPS; //PROMEDIO == AVERAGE
	public Window()
	{
		//primeros pasos para el juego
		setTitle("Space Ship Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//para el canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		
		
	}
	
	
	
	public static void main(String[] args) {
		new Window().Start();
	}
	
	//para mover lo creado(rectangulo)
	int x = 0;
	//método actualizar
	private void update(){
		x++;
	}
	
	//método dibujar usando el canvas
	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Empezamos a dibujar
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.black);
		
		g.drawString(""+PROMEDIOFPS, 10, 10);
		
		// g.drawRect(x, 0, 100, 100);
		
		//Terminando de dibujar
		
		g.dispose();
		bs.show();
		
		
	}

	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		int time = 0;
		
		
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/ TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1){
				update();
				draw();	
			 	delta --;
			 	frames ++;
			 	//System.out.println(frames);
			}
			if (time >= 1000000000)
			{
				PROMEDIOFPS = frames;				
				frames = 0;
				time = 0;
				
			}
			 
			
		}
		
		Stop();
	}
		//creacion de metodos
		
		private void Start(){
			thread  = new Thread(this);
			thread.start();
			running = true;
		}
		
		private void Stop(){
			try {
				thread.join();
				running = false;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
		}
	

}
