import java.util.ArrayList;

import org.lwjgl.opengl.GL11;


abstract class Entity extends Point implements Drawable {
	public double size, mass;
	public double rotation, rotationPlus;
	public Force mov;
	public ArrayList<Force> forces = new ArrayList<Force>();
	
	public Entity() {
		this.mov = new Force(0,0);
		this.mass = 0;
	}
	
	public void addForce(Force f) {
		this.mov = this.mov.plus(f);
		this.forces.add(f);
	}

	public void addForce(Force f, double a) {
		addForce(new Force(f.xPlus, f.yPlus, a));
	}

	public void addForceTowards(double x, double y) {
		addForceTowards(x, y, 1);
	}
	
	public void addForceTowards(double x, double y, double a) {
		double d = Math.sqrt(Math.pow(y - this.y(), 2) + Math.pow(x - this.x(), 2));
		addForce(new Force((x - this.x())/d, (y - this.y())/d, a));
	}
	
	public void update() {
		this.moveX(this.mov.xPlus*Channon.speedFactor);
		this.moveY(this.mov.yPlus*Channon.speedFactor);
		this.forces.clear();
	}
	
	public void draw() {
		if (Channon.DEBUG) {
			GL11.glColor3d(1, 1, 0);
			this.mov.draw(this);
			
			for(Force f : this.forces)
				f.draw(this);
		}
	}
}
