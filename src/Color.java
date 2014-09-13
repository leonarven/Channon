
public class Color {
	double r, g, b, a;

	public Color(double r, double g, double b, double a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	public Color(double r, double g, double b) {
		this(r, g, b, 1.0);
	}
	public Color(Color color) {
		this(color.r, color.g, color.b, color.a);
	}
}
