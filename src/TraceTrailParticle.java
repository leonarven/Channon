
abstract class TraceTrailParticle extends Particle {

	public TraceTrailParticle previous;
	public TraceTrailParticle next;

	public TraceTrailParticle(double x, double y, double z, TraceTrailParticle prev, ParticleType type) {
		super(x, y, z, type);
		this.previous = prev;
		this.next = null;
		
		if (this.previous != null) this.previous.next = this;
	}
	public TraceTrailParticle(double x, double y, double z, TraceTrailParticle prev) {
		this(x, y, z, prev, ParticleType.TRACETRAIL);
	}
	public TraceTrailParticle(double x, double y, double z) {
		this(x, y, z, ParticleType.TRACETRAIL);
	}
	public TraceTrailParticle(double x, double y, double z, ParticleType type) {
		this(x, y, z, null, type);
	}
	public TraceTrailParticle(TraceTrailParticle prev) {
		this(0, 0, 0, prev);
	}
}
