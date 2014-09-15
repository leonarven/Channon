import org.lwjgl.opengl.GL11;

public class Force {

	static public final double G =  0.01;
	
	double xPlus;
	double yPlus;

	public Force(double xp, double yp) {
		this.xPlus = xp;
		this.yPlus = yp;
	}
	public Force(double xp, double yp, double a) {
		this(xp*a, yp*a);
	}
	
	public Force plus(Force f) {
		return new Force(f.xPlus * Channon.speedFactor + this.xPlus, f.yPlus * Channon.speedFactor + this.yPlus);
	}
	public Force opposite() {
		return new Force(-this.xPlus, -this.yPlus);
	}
	public Force perpendicular() {
		double a = Math.atan2(this.yPlus, this.xPlus)+Math.PI/2;
		
		return new Force(Math.cos(a) , Math.sin(a));
	}

	
	
	static public Force towards(double x, double y, double a) {
		double d = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
		return new Force(x/d, y/d, a);
	}
	static public Force towards(double x, double y) {
		return Force.towards(x, y, 1);
	}
	static public Force perpendicular(Force f) {
		return Force.perpendicular(f, 1.0);
	}
	static public Force perpendicular(Force f, double k) {
		
		double a = Math.atan2(f.yPlus, f.xPlus)+Math.PI/2;
		
		return new Force(Math.cos(a) * k , Math.sin(a) * k);
	}
	static public Force gravitation(Entity a, Entity b) {
		if (a.mass == 0 || b.mass == 0) return new Force(0,0);
		
		double r = a.distance2D(b);
		double cos = (a.x() - b.x());
		double sin = (a.y() - b.y());

		if (r <= 1) r = 1;
		double F = Force.G * (a.mass * b.mass) / Math.pow(r, 2);
		return new Force((cos / r) * F, (sin / r) * F);
		
	}
	
	

	public void draw(Point p) {
		GL11.glLoadIdentity();
		GL11.glTranslated(p.x()-Camera.x(),p.y()-Camera.y(),Camera.z()-p.z());
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3d(0,0,0);
		GL11.glVertex3d(this.xPlus, this.yPlus, 0);
		GL11.glEnd();
	}
	@Override
	public String toString() {
		return "xp:"+xPlus+",yp:"+yPlus;
	}
}
