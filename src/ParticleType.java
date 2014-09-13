
public enum ParticleType {
	NOTE(new Color(1, 1, 1, 1), 2, 10000),
	SMOKE(new Color(0.5,0.5,0.5,0.5), 0.1, 40),
	FIRE (new Color(0.8,0.3,0.0), 2, 10);

	public Color color;
	public double size;
	public int lifetime;
	
	private ParticleType(Color color, double size, int lifetime) {
		this.color = color;
		this.size = size;
		this.lifetime = lifetime;
	}
}
