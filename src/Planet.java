import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public class Planet extends Entity {

	Sphere sphere;
	
	public Planet(double size, double mass) {
		this.size = size;
		this.mass = mass;
		sphere = new Sphere();
		sphere.setDrawStyle(GLU.GLU_LINE);
	}
	
	public void update() {
		super.update();
	}
	
	public void draw() {		
		GL11.glRotated(Channon._t, 1, 1, 1);
		
		GL11.glColor3d(1, 1, 1);
		sphere.draw(2, 20, 20);
	}
}
