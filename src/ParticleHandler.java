import java.util.ArrayList;
import java.util.Iterator;


public class ParticleHandler {
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public void addParticle(Particle particle) {
		this.particles.add(particle);
	}
	
	public void draw() {
		for(Particle particle : this.particles)
			particle.draw();
	}
	
	public void update() {
		for(Iterator<Particle> i = this.particles.iterator(); i.hasNext();) {
			Particle particle = i.next();
			particle.update();
			if (particle.life > particle.lifetime) i.remove();
		}
	}
}
