
abstract public class Particle extends Point implements Drawable {
	
	public Color color;
	public double size;
	
	public Particle(Particle particle) {
		this.color = new Color(particle.color);
		this.size = particle.size;
	}
	
}
