import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;


import com.jogamp.opengl.util.Animator;

public class Channon extends Frame implements KeyListener {

	private static final long serialVersionUID = 7501745434871809453L;

	static GL2 gl;
	static GLU glu;

	static GLCanvas canvas;
	static Animator animator;
	
	static Game game;
	
	public Channon() {

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				animator.stop();
				dispose();
				System.exit(0);
			}
		});
		

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				java.awt.Point point = e.getPoint();
				System.out.println(point);
			}
		});


		Renderer renderer = new Renderer();

		// Luodaan GLCanvas johon voi piirtää ja josta OpenGL tykkää
		canvas = new GLCanvas(new GLCapabilities(GLProfile.getDefault()));
		canvas.addGLEventListener(renderer);
		canvas.addKeyListener(this);

		animator = new Animator(canvas);

		//Sijoittaa canvaksen keskelle Framea
		add(canvas, BorderLayout.CENTER);
	
		animator.start();

		game = new Game();
		game.init();
	}

	/* @link https://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html */
	
	public class Renderer implements GLEventListener/*, MouseListener, MouseMotionListener*/ {

		@Override
		public void display(GLAutoDrawable drawable) {
			gl = drawable.getGL().getGL2();
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

			gl.glClearDepth(1.0f);

			gl.glEnable(GL2.GL_DEPTH_TEST);

			gl.glDepthFunc(GL2.GL_LEQUAL);

			gl.glFrontFace(GL2.GL_CW);
//			gl.glCullFace(GL2.GL_FRONT);
//			gl.glEnable(GL2.GL_CULL_FACE);

			game.update();
			
			gl.glLoadIdentity();

			game.draw(gl);

			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3f(1.0f, 1.0f, -1.0f);
			gl.glVertex3f(-1.0f, 1.0f, -1.0f);
			gl.glVertex3f(-1.0f, 1.0f, 1.0f);
			gl.glVertex3f(1.0f, 1.0f, 1.0f);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(1.0f, 0.5f, 0.0f);
			gl.glVertex3f(1.0f, -1.0f, 1.0f);
			gl.glVertex3f(-1.0f, -1.0f, 1.0f);
			gl.glVertex3f(-1.0f, -1.0f, -1.0f);
			gl.glVertex3f(1.0f, -1.0f, -1.0f);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(1.0f, 0.0f, 0.0f);
			gl.glVertex3f(1.0f, 1.0f, 1.0f);
			gl.glVertex3f(-1.0f, 1.0f, 1.0f);
			gl.glVertex3f(-1.0f, -1.0f, 1.0f);
			gl.glVertex3f(1.0f, -1.0f, 1.0f);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(1.0f, 1.0f, 0.0f);
			gl.glVertex3f(1.0f, -1.0f, -1.0f);
			gl.glVertex3f(-1.0f, -1.0f, -1.0f);
			gl.glVertex3f(-1.0f, 1.0f, -1.0f);
			gl.glVertex3f(1.0f, 1.0f, -1.0f);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 1.0f);  
			gl.glVertex3f(-1.0f, 1.0f, 1.0f);
			gl.glVertex3f(-1.0f, 1.0f, -1.0f);
			gl.glVertex3f(-1.0f, -1.0f, -1.0f);
			gl.glVertex3f(-1.0f, -1.0f, 1.0f);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(1.0f, 0.0f, 1.0f);
			gl.glVertex3f(1.0f, 1.0f, -1.0f);
			gl.glVertex3f(1.0f, 1.0f, 1.0f);
			gl.glVertex3f(1.0f, -1.0f, 1.0f);
			gl.glVertex3f(1.0f, -1.0f, -1.0f);
			gl.glEnd();
		}

		public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

		@Override
		public void init(GLAutoDrawable drawable) {
			gl = drawable.getGL().getGL2();
			glu = new GLU();
			gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			gl.glClearDepth(1.0f);
			gl.glEnable(GL.GL_DEPTH_TEST);
			gl.glDepthFunc(GL.GL_LEQUAL);
			gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
			gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		}

		public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
			GL2 gl = drawable.getGL().getGL2();
			
			if (height == 0) height = 1;
			float aspect = (float)width / height;
			
			gl.glViewport(0, 0, width, height);
			
			gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
			gl.glLoadIdentity();
			glu.gluPerspective(45.0, aspect, 0.1, 100.0);
//			gl.glOrtho(-10.0 * aspect, 10.0 * aspect, -10.0, 10.0, 10.0, -10.0);
			
			gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
			gl.glLoadIdentity();
		}

		public void dispose(GLAutoDrawable drawable) {}
		
		
/*		@Override
		public void mouseClicked(MouseEvent arg0) {  
			System.out.println("Mouse Clicked");
		}
		
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			System.out.println("Mouse Entered Frame");
		}
		
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			System.out.println("Mouse Exited Frame");
		}


		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("Mouse is pressed.");
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Mouse has been released");
		}

		//HERE IS THE PROBLEM, THE DISPLAY WILL NOT UPDATE THIS INPUT
		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("Mouse is being dragged");
			
			System.out.println("x = "+e.getX());
			System.out.println("y = "+e.getY());   
		}


		@Override
		public void mouseMoved(MouseEvent e) {
		
		}*/
	}


	@Override
	public void keyTyped(KeyEvent key) {
		System.out.println("Key " + key.getKeyChar() + " typed");
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Key " + key.getKeyChar() + " pressed");
		switch (key.getKeyCode()) {
			case KeyEvent.VK_Q:
				game.camera.moveZ(0.5);
				break;
			case KeyEvent.VK_E:
				game.camera.moveZ(-0.5);
				break;
			case KeyEvent.VK_D:
				game.player.rotationPlus = 0.05;
				break;
			case KeyEvent.VK_A:
				game.player.rotationPlus = -0.05;
				break;
			case KeyEvent.VK_W:
				game.player.move(0.01);
				break;
			case KeyEvent.VK_S:
				game.player.move(-0.01);
				break;	
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		System.out.println("Key " + key.getKeyChar() + " released");
	}

	public static void main(String[] args) {
		Channon frame = new Channon();
		frame.setTitle("Illuminate Geometry");
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}