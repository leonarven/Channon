import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;


public class Spaceship extends Entity {
	
	class SpaceshipPoint {
		double rad, r, red, green, blue, alpha;
		SpaceshipPoint(double rad, double r, double red, double green, double blue, double alpha) {
			this.rad = rad;
			this.r = r;
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha;
		}
		SpaceshipPoint(SpaceshipPoint p) {
			this.rad = p.rad;
			this.r = p.r;
			this.red = p.red;
			this.green = p.green;
			this.blue = p.blue;
			this.alpha = p.alpha;
		}
		SpaceshipPoint() {
		}
		public SpaceshipPoint mirror() {
			SpaceshipPoint p = new SpaceshipPoint(this);
			p.rad = -p.rad;
			return p;
		}
	}
	
	public ArrayList<SpaceshipPoint> vpoints = new ArrayList<SpaceshipPoint>();

	public Spaceship() {
		generateShip();
	}
	
	public void update() {
		super.update();

		if (Math.abs(this.rotationPlus) > 0.001) {
			if (this.rotationPlus < 0) this.rotationPlus += 0.001;
			else this.rotationPlus -= 0.001;
		} else this.rotationPlus = 0;

		this.rotation += this.rotationPlus;
		
	}
	
	public void generateShip() {
		Random rand = new Random();
		vpoints.clear();
		for(int i=0; i< 2+ (new Random()).nextInt(4); i++) {
			SpaceshipPoint p = new SpaceshipPoint();
			p = new SpaceshipPoint();
			
			p.red = rand.nextDouble();
			p.green = rand.nextDouble();
			p.blue = rand.nextDouble();
			p.alpha = rand.nextDouble();

			p.r = rand.nextDouble();
			
			p.rad = rand.nextDouble()*2*Math.PI;
			vpoints.add(p);
			vpoints.add(p.mirror());
		}
		SpaceshipPoint p = new SpaceshipPoint();
		p.red = 1;
		p.green = 1;
		p.blue = 1;
		p.alpha = 1;
		p.r = this.size;
		p.rad = 0;
		vpoints.add(p);
	}
	
	public void draw() {
		super.draw();
		GL11.glRotated(this.rotation*180.0/Math.PI, 0, 0 , 1);
		GL11.glBegin(GL11.GL_LINES);
		for(SpaceshipPoint p1 : this.vpoints) {
			GL11.glColor4d(p1.red, p1.green, p1.blue, p1.alpha);
			GL11.glVertex3d(Math.cos(p1.rad) * p1.r, Math.sin(p1.rad) * p1.r, 0);
			GL11.glColor3d(1, 1, 1);
			GL11.glVertex3d(0, 0, 0);

			for(SpaceshipPoint p2 : this.vpoints) {
				GL11.glColor4d(p1.red, p1.green, p1.blue, p1.alpha);
				GL11.glVertex3d(Math.cos(p1.rad) * p1.r, Math.sin(p1.rad) * p1.r, 0);
				GL11.glColor4d(p2.red, p2.green, p2.blue, p2.alpha);
				GL11.glVertex3d(Math.cos(p2.rad) * p2.r, Math.sin(p2.rad) * p2.r, 0);

				GL11.glColor4d(p1.red, p1.green, p1.blue, p1.alpha);
				GL11.glVertex3d(Math.cos(p1.rad) * p1.r, Math.sin(p1.rad) * p1.r, 0);
				GL11.glColor4d(p2.red, p2.green, p2.blue, p2.alpha);
				GL11.glVertex3d(Math.cos(p2.rad) * p2.r, Math.sin(p2.rad) * p2.r, 0);
}
		}
		GL11.glEnd();
		/*		
		GL11.glBegin(GL11.GL_LINES);
		for(SpaceshipPoint p1 : this.vpoints) {
			GL11.glColor4d(p1.red, p1.green, p1.blue, p1.alpha);
			GL11.glVertex3d(this.x()+Math.cos(this.rotation + p1.rad) * p1.r, this.y()+Math.sin(this.rotation + p1.rad) * p1.r, 0);
			GL11.glColor3d(1, 1, 1);
			GL11.glVertex3d(this.x(), this.y(), 0);

			for(SpaceshipPoint p2 : this.vpoints) {
				GL11.glColor4d(p1.red, p1.green, p1.blue, p1.alpha);
				GL11.glVertex3d(this.x()+Math.cos(this.rotation + p1.rad) * p1.r, this.y()+Math.sin(this.rotation + p1.rad) * p1.r, 0);
				GL11.glColor4d(p2.red, p2.green, p2.blue, p2.alpha);
				GL11.glVertex3d(this.x()+Math.cos(this.rotation + p2.rad) * p2.r, this.y()+Math.sin(this.rotation + p2.rad) * p2.r, 0);

				GL11.glColor4d(p1.red, p1.green, p1.blue, p1.alpha);
				GL11.glVertex3d(this.x()+Math.cos(this.rotation - p1.rad) * p1.r, this.y()+Math.sin(this.rotation - p1.rad) * p1.r, 0);
				GL11.glColor4d(p2.red, p2.green, p2.blue, p2.alpha);
				GL11.glVertex3d(this.x()+Math.cos(this.rotation - p2.rad) * p2.r, this.y()+Math.sin(this.rotation - p2.rad) * p2.r, 0);
}
		}
		GL11.glEnd();*/
	}
	
	public void move(double a) {
		
		this.addForceTowards(this.x()+Math.cos(this.rotation), this.y()+Math.sin(this.rotation), a);
		
	}
}
