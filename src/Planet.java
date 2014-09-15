import org.lwjgl.opengl.GL11;

public class Planet extends Entity {

	public Planet(double size, double mass) {
		this.size = size;
		this.mass = mass;
	}
	
	public void draw() {		
		GL11.glColor3d(1, 1, 1);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for(double i = 0.0; i < 2*Math.PI; i+= 0.2)
			GL11.glVertex3d(this.size * Math.cos(i), this.size * Math.sin(i), this.z());
		GL11.glEnd();
	}
}
