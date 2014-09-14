import java.util.Random;

import org.lwjgl.opengl.GL11;


public class SmokeParticle extends Particle {

	public SmokeParticle() {
		super(ParticleType.SMOKE);
	}

	public SmokeParticle(double x, double y, double z) {
		super(x, y, z, ParticleType.SMOKE);
	}
	
	public void draw() {
		Random rand = new Random();
		GL11.glColor4d(this.color.r, this.color.g, this.color.b, this.color.a);
		GL11.glBegin(GL11.GL_POINTS);
		for(int i = 0; i < 10; i++)
			GL11.glVertex3d(this.x() + rand.nextDouble()*(this.size+this.life/20.0)*Math.cos(rand.nextInt()),
					        this.y() + rand.nextDouble()*(this.size+this.life/20.0)*Math.sin(rand.nextInt()),
					        this.z());
		GL11.glEnd();
	}
}
