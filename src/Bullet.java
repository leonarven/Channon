import org.lwjgl.opengl.GL11;


public class Bullet extends BulletPrototype {
	
	
	public Bullet(double x, double y, double z) {
		super(0.3);
		this.pos(x, y, z);
	}
	public Bullet(Point p) {
		this(p.x(), p.y(), p.z());
	}
	
	@Override
	public void draw() {
		GL11.glPointSize(10);
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex3d(this.x(), this.y(), this.z());
		GL11.glEnd();
		GL11.glPointSize(1);
	}
}
