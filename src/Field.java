import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public class Field implements Drawable {
	public ArrayList<Star> stars = new ArrayList<Star>();	
	
	public final double startX, startY, endX, endY;
	public final double width, height;
	
	public Field(double startX, double startY, double endX, double endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.width = endX-startX;
		this.height = endY-startY;
		
		Random rand = new Random();
		for(int i = 0; i < 200; i++) {
			Star star = new Star();
			star.pos(this.startX+rand.nextDouble()*this.width, this.startY+rand.nextDouble()*this.height, -rand.nextDouble()*10);
			this.stars.add(star);
		}
	}
	
	public void update() {
		
	}
	
	public boolean outOfField(Point e) {
		if (e.x() < this.startX) return true;
		if (e.x() > this.endX) return true;
		if (e.y() < this.startY) return true;
		if (e.y() > this.endY) return true;
		return false;
	}
	
	public void draw() {
		for(Star star : this.stars) {
			star.draw();
		}
		
		GL11.glColor3d(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		 GL11.glVertex2d(this.startX, this.startY);
		 GL11.glVertex2d(this.startX, this.endY);
		 GL11.glVertex2d(this.endX, this.endY);
		 GL11.glVertex2d(this.endX, this.startY);
		GL11.glEnd();
	}
}
