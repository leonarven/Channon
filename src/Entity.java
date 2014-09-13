
abstract class Entity extends Point implements Drawable {
	double size;
	Force mov;
	double mass;
	double rotation, rotationPlus;
	
	public Entity() {
		this.mov = new Force(0,0);
		this.mass = 0;
	}
	
	public void addForce(Force f) {
		this.mov = this.mov.plus(f);
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
		this.moveX(this.mov.xPlus);
		this.moveY(this.mov.yPlus);
	}
	
	public void draw() {
		this.mov.draw(this);
	}
}
