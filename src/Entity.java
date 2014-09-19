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
		this.health = this.healthMax = 0;
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
	
	public boolean hits(Entity e) {
		if (this.distance2D(e) < this.size+e.size) return true;
		return false;
	}
	
	public void draw() {
		GL11.glLoadIdentity();
		GL11.glTranslated(this.x()-Camera.x(),this.y()-Camera.y(),Camera.z()-this.z());	
		
		if (Channon.DEBUG) {
			GL11.glColor3d(1,0,1);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			for(double i = 0; i < 2*Math.PI; i+= Math.PI/100)
				GL11.glVertex2d(Math.cos(i)*this.size, Math.sin(i)*this.size);
			GL11.glEnd();

			GL11.glColor3d(1, 1, 0);
			this.mov.draw(this,10);
			
			for(Force f : this.forces)
				f.draw(this,10);
		}
		
		GL11.glRotated(this.rotation*180.0/Math.PI, 0, 0, 1);	

		GL11.glColor3d(1, 1, 0);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		for(double i = 0; i < 2*Math.PI*(this.health/this.healthMax); i+= Math.PI/100)
			GL11.glVertex2d(Math.cos(i)*(this.size+0.1), Math.sin(i)*(this.size+0.1));
		GL11.glEnd();

	}
}
