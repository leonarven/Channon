import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;


public class Asteroid extends Entity {
	
	public ArrayList<Point> corners;
	
	Asteroid(double size, int cornersCount) {
		this.size = size;
		double a = this.size ;
		cornersCount = Math.max(5, cornersCount);
		
		corners = new ArrayList<Point>(cornersCount);
		for(double rad, i = 0; i < cornersCount; i++) {
			rad = (Math.PI*2/cornersCount) * (i + Math.random());
			corners.add(new Point(Math.cos(rad) * a, Math.sin(rad) * a));
			a += (new Random().nextDouble()-0.5)*0.2;
		}
	}
	
	public void draw() {
		super.draw();
		GL11.glColor3d(1.0, 1.0, 1.0);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for(Point point : corners)
			GL11.glVertex3d(this.x()+point.x(), this.y()+point.y(), this.z()+point.z());
		GL11.glEnd();
	}
	public void update() {
		super.update();
	}
}
