import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;


public class Point {
	private double x;
	private double y;
	private double z;

	Point(double x, double y, double z) {
		pos(x, y, z);
	}
	Point(Point p) {
		this(p.x(), p.y(), p.z());
	}
	Point(double x, double y) {
		this(x, y, 0);
	}
	Point() {
		this(0, 0, 0);
	}
	
	public void pos(double x, double y, double z) {
		x(x);
		y(y);
		z(z);
	}

	public void pos(double x, double y) {
		pos(x, y, 0);
	}

	public double x() { return this.x; }
	public double y() { return this.y; }
	public double z() { return this.z; }
	
	public void x(double val) { this.x = val; }
	public void y(double val) { this.y = val; }
	public void z(double val) { this.z = val; }
	
	public void moveX(double val) { this.x += val; }
	public void moveY(double val) { this.y += val; }
	public void moveZ(double val) { this.z += val; }
	

	public double distance2D(Point p) {
		return Math.sqrt(Math.pow(p.x() -this.x, 2) + Math.pow(p.y() -this.y, 2));
	}

	public double angle2D(Point p) {
		double deltaX = p.x() - this.x;
		double deltaY = p.y() - this.y;
		return Math.atan2(deltaY, deltaX);
	}
	
	public int[] screen2D() {
/*		FloatBuffer screenCoords = BufferUtils.createFloatBuffer(4);
		IntBuffer viewport = BufferUtils.createIntBuffer(16);
		FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
		FloatBuffer projection = BufferUtils.createFloatBuffer(16);
		// int[] screenCoords = new double[4];
		// int[] viewport = new int[4];
		// double[] modelView = new double[16];
		// double[] projection = new double[16];
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelView);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
		GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
		boolean result = Channon.glu.gluProject((float) x, (float) y, (float) z, modelView, projection, viewport, screenCoords);
		if (result) {
		    return new int[] { (int) screenCoords.get(3), (int) screenCoords.get(1) };
		}*/
		return null;
	}
}
