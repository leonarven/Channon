import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public class Planet extends Entity {

	Sphere sphere;
	
	public Planet(double size, double mass) {
		this.size = size;
		this.mass = mass;
		this.health = this.healthMax = 100;
		sphere = new Sphere();
		sphere.setDrawStyle(GLU.GLU_LINE);
	}
	
	public void update() {
		super.update();
	}
	
	public void draw() {
		super.draw();
		GL11.glRotated(20, 1, 1, 0);
		GL11.glRotated(Channon._t, 0, 0, 1);
//		GL11.glRotated(Channon._t, 1, 1, 1);
		GL11.glColor3d(1, 1, 1);
		sphere.draw((int)this.size, 20, 20);

/*		GL11.glBegin(GL11.GL_LINE_LOOP);
		for(double i = 0; i < 2*Math.PI; i+= Math.PI/100)
			GL11.glVertex2d(Math.cos(i)*this.size, Math.sin(i)*this.size);
		GL11.glEnd();*/
	}
}
