import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.GL2;


public class Game implements Drawable {
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Star> stars = new ArrayList<Star>();
	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	public Player player;
	public Camera camera;
	
	public double theFactor;
	
	public Game() {
		
		this.theFactor = 1;
		
		double a = new Random().nextDouble();
		
		player = new Player();
		this.entities.add(player);
		player.pos(5*Math.cos(a), 5*Math.sin(a));
		camera = new Camera(0,0,-15);
	}
	
	public void init() {
		Random rand = new Random();
		
		for(int i = 0; i < 100000; i++) {
			Star star = new Star();
			star.pos(rand.nextDouble()*800-400, rand.nextDouble()*800-400, -rand.nextDouble()*10);
			this.stars.add(star);
		}
		for(int i = 0; i < 100; i++) {
			double a = rand.nextDouble()*Math.PI*2;
			Asteroid asteroid = new Asteroid(0.1 + 0.3*rand.nextDouble(), 4+(int)(5*rand.nextDouble()));
			asteroid.pos((30+5*rand.nextDouble())*Math.cos(a), (30+5*rand.nextDouble())*Math.sin(a));
//			asteroid.addForce(new Force((new Random().nextDouble()-0.5), (new Random().nextDouble()-0.5),0.002));

			asteroid.addForce(Force.towards(0, 0));

			
			
			asteroid.update();
			this.asteroids.add(asteroid);
			this.entities.add(asteroid);
		}
	}
	
	public void update() {

		for(Asteroid a : this.asteroids) {
			a.addForceTowards(0, 0 , 0.001);
			a.update();
		}
		
		this.player.addForceTowards(0, 0 , 0.001);
		this.player.update();

		this.camera.x(this.player.x());
		this.camera.y(this.player.y());
		
//		System.out.println("("+this.player.screen2D()[0]+","+this.player.screen2D()[1]+")");
	}
	
	public void draw(GL2 gl) {

		gl.glTranslated(-this.camera.x(), -this.camera.y(), this.camera.z());
		
	//	for(Asteroid asteroid : this.asteroids) {
		//	asteroid.draw(gl);
		//}
		for(Star star : this.stars) {
			star.draw(gl);
		}
		for(Entity entity : this.entities) {
			entity.draw(gl);
		}
//		this.player.draw(gl);
	}
}
