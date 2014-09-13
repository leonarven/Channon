
abstract public class Particle extends Point implements Drawable {
	
	public Color color;
	public double size;
	public int lifetime;
	public int life;
	
	public Particle(ParticleType type) {
		this.color = type.color;
		this.size = type.size;
		this.lifetime = type.lifetime;	
		this.life = 0;	
	}
	public Particle(double x, double y, double z, ParticleType type) {
		this(type);
		this.pos(x, y, z);
	}
	public void update() {
		this.life++;
	}
	
}
