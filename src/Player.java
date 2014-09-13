import org.lwjgl.opengl.GL11;


public class Player extends Spaceship {

	public void draw() {
		super.draw();
		
		double aToOrigo = Math.atan2(-this.y(), -this.x());
		
		if (this.distance2D(new Point(0,0)) > 10) {
			GL11.glBegin(GL11.GL_LINE_STRIP);
			GL11.glVertex3d(this.x()+0.4*Math.cos(aToOrigo), this.y()+0.4*Math.sin(aToOrigo), this.z());
			GL11.glVertex3d(this.x()+1.0*Math.cos(aToOrigo), this.y()+1.0*Math.sin(aToOrigo), this.z());
			GL11.glEnd();
		}
	}
}
