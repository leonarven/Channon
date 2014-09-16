import java.util.ArrayList;

import org.lwjgl.opengl.GL11;


abstract class Entity extends Point implements Drawable {
	public double health;
	public double healthMax;
	public double size, mass;
	public double rotation, rotationPlus;
	public Force mov;
	public ArrayList<Force> forces = new ArrayList<Force>();
	public Point2D screenCoordinates;
	
	public Entity() {
		this.mov = new Force(0,0);
		this.mass = 0;
		this.health = 0;
		this.healthMax = 0;
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

	public double screenX() {
		if (this.screenCoordinates == null) return 0;
		return this.screenCoordinates.x();
	}
	public double screenY() {
		if (this.screenCoordinates == null) return 0;
		return this.screenCoordinates.y();
	}
	
	public void update() {
		this.rotation += this.rotationPlus*Channon.speedFactor;
		this.moveX(this.mov.xPlus*Channon.speedFactor);
		this.moveY(this.mov.yPlus*Channon.speedFactor);
		this.forces.clear();
		
		screenCoordinates = this.screen2D();
	}
	
	public void draw() {
		GL11.glLoadIdentity();
		GL11.glTranslated(this.x()-Camera.x(),this.y()-Camera.y(),Camera.z()-this.z());	
		GL11.glRotated(this.rotation*180.0/Math.PI, 0, 0, 1);	

		if (Channon.DEBUG) {
			GL11.glColor3d(1, 1, 0);
			this.mov.draw(this);
			
			for(Force f : this.forces)
				f.draw(this);
		}
	}
}
