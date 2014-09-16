import org.lwjgl.opengl.GL11;


public class Gun extends Entity implements Drawable {

	BulletType bulletType;
	double length;
	
	public Gun(double x, double y, double z, BulletType bulletType) {
		this.pos(x, y, z);
		this.bulletType = bulletType;
	}
	
	public void update() {
		
	}
	public void draw() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3d(this.x(), this.y(), this.z());
		GL11.glVertex3d(this.x()+this.length*Math.cos(this.rotation), this.y()+this.length*Math.sin(this.rotation), this.z());
		GL11.glEnd();
	}
	public void shoot(Point p, double a) {
		switch(this.bulletType) {
			case BULLET:
				BulletHandler.addBullet(new Bullet(p, a));
				break;
		}
	}
	public void shoot(Point p) {
		this.shoot(p, this.rotation);
	}
}
