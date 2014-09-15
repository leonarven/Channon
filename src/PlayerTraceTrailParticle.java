import org.lwjgl.opengl.GL11;


public class PlayerTraceTrailParticle extends TraceTrailParticle {

	public PlayerTraceTrailParticle(double x, double y, double z) {
		super(x, y, z, ParticleType.PLAYERTRACETRAIL);
	}
	public PlayerTraceTrailParticle(double x, double y, double z, TraceTrailParticle prev) {
		super(x, y, z, prev, ParticleType.PLAYERTRACETRAIL);
	}
	
	public void update() {
		super.update();
		if (this.previous == null) return;
		this.color.r = this.previous.color.r * 0.7;
		this.color.g = this.previous.color.g * 0.7;
		this.color.b = this.previous.color.b * 0.7;
		this.color.a = this.previous.color.a * 0.7;
	}
	
	@Override
	public void draw() {
		if (this.previous == null) return;

		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor4d(this.color.r, this.color.g, this.color.b, this.color.a);
		 GL11.glVertex3d(this.x(), this.y(), this.z());
			GL11.glColor4d(this.previous.color.r, this.previous.color.g, this.previous.color.b, this.previous.color.a);
		 GL11.glVertex3d(this.previous.x(), this.previous.y(), this.previous.z());
		GL11.glEnd();

	}

}
