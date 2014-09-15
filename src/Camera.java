
public class Camera {
	static private double x;
	static private double y;
	static private double z;

	static public double x() { return Camera.x; }
	static public double y() { return Camera.y; }
	static public double z() { return Camera.z; }

	static public void x(double val) { Camera.x = val; }
	static public void y(double val) { Camera.y = val; }
	static public void z(double val) { Camera.z = val; }
}
