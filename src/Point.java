import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Point extends Point2D {
	private double x;
	private double y;
	private double z;

	Point(double x, double y, double z) {
		pos(x, y, z);
	}
	Point(Point p) {
		this(p.x(), p.y(), p.z());
	}
	Point(Point2D p) {
		this(p.x(), p.y(), 0);
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

	public double z() { return this.z; }
	public void z(double val) { this.z = val; }
	public void moveZ(double val) { this.z += val; }
	

	public double distance2D(Point p) {
		return Math.sqrt(Math.pow(p.x() -this.x, 2) + Math.pow(p.y() -this.y, 2));
	}

	public double angle2D(Point p) {
		double deltaX = p.x() - this.x;
		double deltaY = p.y() - this.y;
		return Math.atan2(deltaY, deltaX);
	}
	
	public Point2D screen2D() {
		FloatBuffer screenCoords = BufferUtils.createFloatBuffer(4);
		IntBuffer viewport = BufferUtils.createIntBuffer(16);
		FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
		FloatBuffer projection = BufferUtils.createFloatBuffer(16);

		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelView);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
		GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
		boolean result = GLU.gluProject((float) x, (float) y, (float) z, modelView, projection, viewport, screenCoords);
		if (result)
			return new Point2D((int)screenCoords.get(0), (int)screenCoords.get(1));
		return null;
	}
	
	@Override
	public String toString() {
		return "("+x()+","+y()+","+z()+")";
	}
}
