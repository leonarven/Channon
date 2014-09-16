
abstract class BulletPrototype extends Point implements Drawable {
	double xPlus;
	double yPlus;
	double speed;
	
	public BulletPrototype(double speed, double a) {
		this.speed = speed;
		this.xPlus = Math.cos(a) * this.speed;
		this.yPlus = Math.sin(a) * this.speed;
	}

	public void update() {
		this.x(this.x()+this.xPlus);
		this.y(this.y()+this.yPlus);
	}
}
