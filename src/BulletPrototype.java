
abstract class BulletPrototype extends Point implements Drawable {
	double xPlus;
	double yPlus;
	double speed;
	double damage;
	
	public BulletPrototype(double speed, double damage, double a) {
		this.speed = speed;
		this.damage = damage;
		this.xPlus = Math.cos(a) * this.speed;
		this.yPlus = Math.sin(a) * this.speed;
	}

	public void update() {
		this.x(this.x()+this.xPlus);
		this.y(this.y()+this.yPlus);
	}
}
