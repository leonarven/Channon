import javax.media.opengl.GL2;

public class Star extends Point implements Drawable  {

	Star() {
	}
	
	public void draw(GL2 gl) {
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin(GL2.GL_POINTS);
		 gl.glVertex3d(this.x(), this.y(), this.z());
/*		 gl.glVertex3d(this.x()+0.00, this.y()-0.02, this.z());
		 gl.glVertex3d(this.x()+0.02, this.y()+0.01, this.z());
		 gl.glVertex3d(this.x()-0.02, this.y()+0.01, this.z());

		 gl.glVertex3d(this.x()+0.00, this.y()+0.02, this.z());
		 gl.glVertex3d(this.x()-0.02, this.y()-0.01, this.z());
		 gl.glVertex3d(this.x()+0.02, this.y()-0.01, this.z());*/
		gl.glEnd();
	}
}
