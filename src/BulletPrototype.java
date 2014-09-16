
abstract class BulletPrototype extends Point implements Drawable {
	double xPlus;
	double yPlus;
	double speed;
	
	public BulletPrototype(double speed) {
		this.speed = speed;
	}

	public void update() {
		this.x(this.x()+this.xPlus);
		this.y(this.y()+this.yPlus);
	}
}
