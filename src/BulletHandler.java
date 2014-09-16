import java.util.ArrayList;


public class BulletHandler {
	static public ArrayList<BulletPrototype> bullets = new ArrayList<BulletPrototype>();

	static public void update() {
		for(BulletPrototype bullet : bullets) {
			bullet.update();
		}
	}
	static public void draw() {
		for(BulletPrototype bullet : bullets) {
			bullet.draw();
		}
	}
	
	static public void addBullet(BulletPrototype bullet) {
		bullets.add(bullet);
	}
}
