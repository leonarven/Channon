import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class ParticleHandler {
	static public ArrayList<Particle> particles = new ArrayList<Particle>();
	static public HashMap<String, TraceTrailParticle> traceTrails = new HashMap<String, TraceTrailParticle>();
	
	static public void addParticle(Particle particle) {
		particles.add(particle);
	}
	static public void addParticle(TraceTrailParticle particle) {
		
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
