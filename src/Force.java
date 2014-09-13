import org.lwjgl.opengl.GL11;

public class Force {

	double xPlus;
	double yPlus;

	public Force(double x, double y) {
		this.xPlus = x;
		this.yPlus = y;
	}
	public Force(double x, double y, double a) {
		this(x*a, y*a);
	}
	
	public Force plus(Force f) {
		return new Force(f.xPlus + this.xPlus, f.yPlus + this.yPlus);
	}
	public Force opposite() {
		return new Force(-this.xPlus, -this.yPlus);
	}
	public Force perpendicular() {
		double a = 1;
		return new Force(this.yPlus * a , this.xPlus * a);
	}

	
	
	static public Force towards(double x, double y) {
		double d = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
		return new Force(x/d, y/d);
	}
	static public Force perpendicular(Force f) {
		return Force.perpendicular(f, 1.0);
	}
	static public Force perpendicular(Force f, double a) {
		return new Force(f.yPlus * a , f.xPlus * a);
	}
	
	

	public void draw(Point p) {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3d(p.x(), p.y(), p.z());
		GL11.glVertex3d(p.x() + this.xPlus, p.y() + this.yPlus, p.z());
		GL11.glEnd();
	}
	@Override
	public String toString() {
		return "xp:"+xPlus+",yp:"+yPlus;
	}
}
