import org.lwjgl.opengl.GL11;

public class Star extends PointRGBA implements Drawable  {

	Star() {
		this.r = 1;
		this.g = 1;
		this.b = 1;
		this.a = 1;
	}
	
	public void draw() {
		GL11.glColor4d(this.r, this.g, this.b, this.a);
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex3d(this.x(), this.y(), this.z());
		GL11.glEnd();
	}
}
