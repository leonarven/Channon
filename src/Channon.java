import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Channon {
	
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
	}
	
	public void start() {

		while (!Display.isCloseRequested()) {

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			GL11.glClearDepth(1.0f);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GL11.glFrontFace(GL11.GL_CW);
//			GL11.glCullFace(GL11.GL_FRONT);
//			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glLoadIdentity();
			
			game.update();
			
			game.draw();

			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glColor3f(0.0f, 1.0f, 0.0f);
			GL11.glVertex3f(1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
			GL11.glVertex3f(1.0f, 1.0f, 1.0f);
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glColor3f(1.0f, 0.5f, 0.0f);
			GL11.glVertex3f(1.0f, -1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
			GL11.glVertex3f(1.0f, -1.0f, -1.0f);
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
			GL11.glVertex3f(1.0f, 1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
			GL11.glVertex3f(1.0f, -1.0f, 1.0f);
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glColor3f(1.0f, 1.0f, 0.0f);
			GL11.glVertex3f(1.0f, -1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(1.0f, 1.0f, -1.0f);
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glColor3f(0.0f, 0.0f, 1.0f);  
			GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glColor3f(1.0f, 0.0f, 1.0f);
			GL11.glVertex3f(1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(1.0f, 1.0f, 1.0f);
			GL11.glVertex3f(1.0f, -1.0f, 1.0f);
			GL11.glVertex3f(1.0f, -1.0f, -1.0f);
			GL11.glEnd();
			
			game.pollInput();
			
			Display.update();
	
		}
	 
		Display.destroy();
	}
	
	public static void main(String[] argv) {
		Channon channon = new Channon();
		channon.init(800, 600);
		channon.start();
	}
}