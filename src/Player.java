import javax.media.opengl.GL;
import javax.media.opengl.GL2;


public class Player extends Spaceship {

	@Override
	public void draw(GL2 gl) {
		super.draw(gl);
		
		double aToOrigo = Math.atan2(-this.y(), -this.x());
		
		if (this.distance2D(new Point(0,0)) > 10) {
			gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glVertex3d(this.x()+0.4*Math.cos(aToOrigo), this.y()+0.4*Math.sin(aToOrigo), this.z());
			 gl.glVertex3d(this.x()+1.0*Math.cos(aToOrigo), this.y()+1.0*Math.sin(aToOrigo), this.z());
			gl.glEnd();
		}
		
		this.mov.draw(gl, (Point)this);
	}
	
}
