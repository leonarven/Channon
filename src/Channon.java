import java.util.TimerTask;
import java.util.Timer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Channon {
	
	public static boolean DEBUG = true;
	
	public static int _t = 0;
	public static int _frames = 0;
	public static int FPS = 0;
	
	public static TimerTask updateFPS;
	public static Timer timer = new Timer();

	public static double speedFactor = 1;
	
	public static boolean finished = false;

	public Game game;

	public void init(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);
	 
		this.reshape();
		
		game = new Game();
		game.init();
		
		Channon.updateFPS = new TimerTask() {
			public void run() {
				Display.setTitle("FPS:"+(Channon.FPS=Channon._frames));
				Channon._frames = 0;
			}
		};
		
		timer.scheduleAtFixedRate(Channon.updateFPS, 1000, 1000);
	}

	public void reshape() {
		int height = Display.getHeight();
		int width = Display.getWidth();
		if (height == 0) height = 1;
		float aspect = (float)width / height;
		
		GL11.glViewport(0, 0, width, height);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
//		GL11.glOrtho(-10.0 * aspect, 10.0 * aspect, -10.0, 10.0, 1.0, 100.0);
	
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		GL11.glClearDepth(1.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glFrontFace(GL11.GL_CW);
//		GL11.glCullFace(GL11.GL_FRONT);
//		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glLoadIdentity();

	}
	
	public void start() {


		long time, startTime;
		while (!Channon.finished) {
			startTime = System.currentTimeMillis();
			
			if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Channon.finished = true;
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			GL11.glLoadIdentity();
			
			game.pollInput();
			game.update();
			game.draw();
			
			Display.update();
//			Display.sync(60);
			
			Channon._t++;
			Channon._frames++;

			time = System.currentTimeMillis() - startTime;
			Channon.speedFactor = time / 20.0;
			
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String[] argv) {
		Channon channon = new Channon();
		channon.init(1024, 768);
		channon.start();
	}
}