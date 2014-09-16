import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class HUD {

	static public void draw() {


		GL11.glColor3d(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		 GL11.glVertex2d(10, Display.getHeight()-10);
		 GL11.glVertex2d(10, 10);
		 GL11.glVertex2d(Display.getWidth()-10, 10);
		 GL11.glVertex2d(Display.getWidth()-10, Display.getHeight()-10);
		GL11.glEnd();
		
		/* ---------- PLAYER ---------- */

		Player p = Channon.game.player;
		Planet planet = Channon.game.planets.get(0);

		if (p.distance2D(planet) > 5) {
			double aToOrigo = Math.atan2(-p.y(), -p.x());
			GL11.glColor3d(1,1,1);
			GL11.glBegin(GL11.GL_LINE_STRIP);
			 GL11.glVertex2d(p.screenX()+40*Math.cos(aToOrigo), p.screenY()+40*Math.sin(aToOrigo));
			 GL11.glVertex2d(p.screenX()+50*Math.cos(aToOrigo), p.screenY()+50*Math.sin(aToOrigo));
			GL11.glEnd();
			
		}
		GL11.glColor3d(1,1,1);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for(double i = 0; i < 2*Math.PI; i+= Math.PI/100)
			GL11.glVertex2d(p.screenX()+40*Math.cos(i), p.screenY()+40*Math.sin(i));
		GL11.glEnd();
	}
}
