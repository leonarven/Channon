
public class Gun extends Entity implements Drawable {

	BulletType bulletType;
	double length;
	
	public Gun(BulletType bulletType) {
		this.bulletType = bulletType;
	}
	
	public void update() {
		
	}
	public void draw() {
		
	}
	public void shoot() {
		switch(this.bulletType) {
			case BULLET:
				BulletHandler.addBullet(new Bullet(this));
				break;
		}
	}
}
