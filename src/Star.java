import org.lwjgl.opengl.GL11;

public class Star extends Point implements Drawable  {

	Star() {
	}
	
	public void draw() {
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex3d(this.x(), this.y(), this.z());
		GL11.glEnd();
	}
}
