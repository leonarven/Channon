import java.util.ArrayList;
import java.util.Iterator;


public class ParticleHandler {
	static public ArrayList<Particle> particles = new ArrayList<Particle>();
	static public ArrayList<TraceTrailParticle> traceTrails = new ArrayList<TraceTrailParticle>();
	
	static public void addParticle(Particle particle) {
		particles.add(particle);
	}
	
	static public void draw() {
		for(Particle particle : particles)
			particle.draw();
	}
	
	static public void update() {
		for(Iterator<Particle> i = particles.iterator(); i.hasNext();) {
			Particle particle = i.next();
			particle.update();
			if (particle.life > particle.lifetime) i.remove();
		}
	}
}
