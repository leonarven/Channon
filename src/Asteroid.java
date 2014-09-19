import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;


public class Asteroid extends Entity {
	
	public ArrayList<Point> corners;
	public double xr;
	public double yr;
	public double zr;
	public double rr;
	public double damage;
	
	Asteroid(double size, int cornersCount) {
		Random rand = new Random();
		this.size = size;
		this.health = this.healthMax = 100;
		double a = this.size ;
		this.damage = this.size*10.0;
		cornersCount = Math.max(5, cornersCount);
		
		corners = new ArrayList<Point>(cornersCount);
		for(double rad, i = 0; i < cornersCount; i++) {
			rad = (Math.PI*2/cornersCount) * (i + Math.random());
			corners.add(new Point(Math.cos(rad) * a, Math.sin(rad) * a));
		//	a += (new Random().nextDouble()-0.5)*0.2;
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
	}
	public void update() {
		super.update();
		this.rotation = Channon._t/100.0;
	}
	public ArrayList<Asteroid> disintegrate() {
		Random rand = new Random();
		ArrayList<Asteroid> nas = new ArrayList<Asteroid>();
		for(int i = 0; i < this.size*10; i++) {
			double size = this.size*rand.nextDouble();
			if (size < 0.1) continue;
			Asteroid asteroid = new Asteroid(size, 4+(int)(5*rand.nextDouble()));

			double a = rand.nextInt();
			double x = this.x();
			double y = this.y();
			double z = this.z();
			asteroid.pos(x, y, z);
			asteroid.mass = asteroid.size;
			asteroid.addForce(new Force(Math.cos(a), Math.sin(a)), 0.1);
			nas.add(asteroid);
		}
		return nas;
	}
}
