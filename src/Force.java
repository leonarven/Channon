import javax.media.opengl.GL2;

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

	public void draw(GL2 gl, Point p) {
		gl.glBegin(GL2.GL_LINE_LOOP);
		 gl.glVertex3d(p.x(), p.y(), p.z());
		 gl.glVertex3d(p.x() + this.xPlus, p.y() + this.yPlus, p.z());
		gl.glEnd();
	}
	
}
