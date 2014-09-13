import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.GL2;


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
	
	public void draw(GL2 gl) {
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin(GL2.GL_POLYGON);
		for(Point point : corners) {
			gl.glVertex3d(this.x()+point.x(), this.y()+point.y(), this.z()+point.z());
		}
		gl.glEnd();
	}
}
