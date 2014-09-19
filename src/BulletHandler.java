import java.util.ArrayList;
import java.util.Iterator;


public class BulletHandler {
	static public ArrayList<BulletPrototype> bullets = new ArrayList<BulletPrototype>();

	static public void update() {
		Iterator<BulletPrototype> it = bullets.iterator();
		while(it.hasNext()) {
			BulletPrototype bullet = it.next();
			bullet.update();
			if (Channon.game.field.outOfField(bullet)) it.remove();
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
	
	static BulletPrototype checkHit(Entity e, boolean remove) {
		Iterator<BulletPrototype> it = bullets.iterator();
		while(it.hasNext()) {
			BulletPrototype bullet = it.next();
			if (bullet.distance2D(e) > 1) continue;
			if (bullet.distance2D(e) < e.size+bullet.speed/2.0) {
				if (remove) it.remove();
				return bullet;
			}
		}
		return null;
	}
	static BulletPrototype checkHit(Entity e) {
		return checkHit(e, true);
	}
}
