import org.lwjgl.opengl.GL11;


public class Bullet extends BulletPrototype {
	
	
	public Bullet(double x, double y, double z, double a) {
		super(0.3, 1, a);
		this.pos(x, y, z);
	}
	public Bullet(Point p, double a) {
		this(p.x(), p.y(), p.z(), a);
	}
	
	@Override
	public void draw() {
		GL11.glColor3d(1, 1, 1);
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex3d(this.x(), this.y(), this.z());
		GL11.glEnd();
	}
}
