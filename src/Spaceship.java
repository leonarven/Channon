import javax.media.opengl.GL;
import javax.media.opengl.GL2;


public class Spaceship extends Entity {

	public void update() {

		super.update();

		if (Math.abs(this.rotationPlus) > 0.001) {
			if (this.rotationPlus < 0) this.rotationPlus += 0.001;
			else this.rotationPlus -= 0.001;
		} else this.rotationPlus = 0;

		this.rotation += this.rotationPlus;
		
	}
	
	public void draw(GL2 gl) {
		double a = 2.3;
		double r = 0.3;
		gl.glBegin(GL.GL_LINE_LOOP);
		 gl.glVertex3d(this.x()+Math.cos(this.rotation)*r, this.y()+Math.sin(this.rotation)*r, 0);
		 gl.glVertex3d(this.x()+Math.cos(this.rotation+a)*r, this.y()+Math.sin(this.rotation+a)*r, 0);
		 gl.glVertex3d(this.x()+Math.cos(this.rotation-a)*r, this.y()+Math.sin(this.rotation-a)*r, 0);
		gl.glEnd();
	}
	
	public void move(double a) {
		
		this.addForceTowards(this.x()+Math.cos(this.rotation), this.y()+Math.sin(this.rotation), a);
		
	}
}
