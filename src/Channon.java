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

	public static Game game;

	static public void init(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		initGL();
		reshape();
		
		Channon.game = new Game();
		Channon.game.init();
		
		Channon.updateFPS = new TimerTask() {
			public void run() {
				Display.setTitle("FPS:"+(Channon.FPS=Channon._frames));
				Channon._frames = 0;
			}
		};
		
		timer.scheduleAtFixedRate(Channon.updateFPS, 1000, 1000);
		timer.scheduleAtFixedRate(Debug.debugMSGTimer, 1000, 1000);
	}
	
	static public void initGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1.0f);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);

		
	}

	static public void ready3D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45.0f, (float)Display.getWidth() / Display.getHeight(), 0.1f, 100.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	static public void ready2D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
	}
	
	static public void reshape() {
		int height = Display.getHeight();
		int width = Display.getWidth();
		GL11.glViewport(0, 0, width, height);
		ready3D();
		ready2D();
	}
	
	static public void start() {

		Debug.initTimer("draw");
		Debug.initTimer("update");
		
		long time, startTime;
		while (!Channon.finished) {
			startTime = System.currentTimeMillis();
			
			if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Channon.finished = true;
			
			Debug.startTimer("update");
			game.pollInput();
			game.update();
			Debug.endTimer("update");

			Debug.startTimer("draw");

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			GL11.glLoadIdentity();
			
			Channon.ready3D();
			game.draw();

			Channon.ready2D();
			HUD.draw();
			Debug.endTimer("draw");
			
			/**/
			Display.update();
//			Display.sync(60);
			
			_t++;
			_frames++;

			time = System.currentTimeMillis() - startTime;
			speedFactor = time / 20.0;
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String[] argv) {
		Channon.init(1024, 768);
		//Renderer.init();
		Channon.start();
	}
}