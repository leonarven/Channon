public class Point2D {
	private double x;
	private double y;

	Point2D(double x, double y) {
		pos(x, y);
	}
	Point2D(Point p) {
		this(p.x(), p.y());
	}
	Point2D(Point2D p) {
		this(p.x(), p.y());
	}
	Point2D() {
		this(0, 0);
	}
	
	public void pos(double x, double y) {
		x(x);
		y(y);
	}

	public double x() { return this.x; }
	public double y() { return this.y; }
	
	public void x(double val) { this.x = val; }
	public void y(double val) { this.y = val; }
	
	public void moveX(double val) { this.x += val; }
	public void moveY(double val) { this.y += val; }
	

	public double distance(Point2D p) {
		return Math.sqrt(Math.pow(p.x() -this.x, 2) + Math.pow(p.y() -this.y, 2));
	}

	public double angle(Point2D p) {
		double deltaX = p.x() - this.x;
		double deltaY = p.y() - this.y;
		return Math.atan2(deltaY, deltaX);
	}
	
	@Override
	public String toString() {
		return "("+x()+","+y()+")";
	}
}
