import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;


public class Asteroid extends Entity {
	
	public ArrayList<Point> corners;
	public double xr;
	public double yr;
	public double zr;
	public double rr;
	
	Asteroid(double size, int cornersCount) {
		Random rand = new Random();
		this.size = size;
		this.health = this.healthMax = this.size*100;
		double a = this.size ;
		cornersCount = Math.max(5, cornersCount);
		
		corners = new ArrayList<Point>(cornersCount);
		for(double rad, i = 0; i < cornersCount; i++) {
			rad = (Math.PI*2/cornersCount) * (i + Math.random());
			corners.add(new Point(Math.cos(rad) * a, Math.sin(rad) * a));
			a += (new Random().nextDouble()-0.5)*0.2;
		}
		this.rotationPlus = new Random().nextDouble();
		
		this.xr = rand.nextDouble();
		this.yr = rand.nextDouble();
		this.zr = rand.nextDouble();
		this.rr = rand.nextDouble();
	}
	
	public void draw() {
		GL11.glRotated(Channon._t, this.xr, this.yr, this.zr);
		super.draw();
		GL11.glColor3d(1.0, 1.0, 1.0);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex3d(0, 0, -this.size);
		for(Point point : corners)
			GL11.glVertex3d(point.x(), point.y(), point.z());
		GL11.glEnd();
		GL11.glVertex3d(corners.get(0).x(), corners.get(0).y(), corners.get(0).z());
		
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex3d(0, 0, this.size);
		for(Point point : corners)
			GL11.glVertex3d(point.x(), point.y(), point.z());
		GL11.glVertex3d(corners.get(0).x(), corners.get(0).y(), corners.get(0).z());
		GL11.glEnd();
		
		if (Channon.DEBUG) {
			GL11.glColor3d(1,0,1);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			for(double i = 0; i < 2*Math.PI; i+= Math.PI/100)
				GL11.glVertex2d(Math.cos(i)*this.size, Math.sin(i)*this.size);
			GL11.glEnd();
		}
	}
	public void update() {
		super.update();
		this.rotation = Channon._t/100.0;
	}
}
