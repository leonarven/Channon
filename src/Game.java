import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class Game implements Drawable {
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public ArrayList<Star> stars = new ArrayList<Star>();
	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	public ArrayList<Planet> planets = new ArrayList<Planet>();
	
	public Player player;
	public Camera camera;
	public ParticleHandler particleHandler = new ParticleHandler();
	
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
		Planet planet = new Planet(4, 2);
		planet.pos(0, 0);
		this.planets.add(planet);
		this.entities.add(planet);
	}
	
	public void update() {

		for(Asteroid a : this.asteroids) {
			a.addForceTowards(0, 0 , 0.001);
			a.update();
		}
		
		this.player.addForceTowards(0, 0 , 0.001);
		this.player.update();

		this.particleHandler.update();

		this.camera.x(this.player.x());
		this.camera.y(this.player.y());
		
//		System.out.println("("+this.player.screen2D()[0]+","+this.player.screen2D()[1]+")");
	}
	
	public void draw() {

		GL11.glTranslated(-this.camera.x(), -this.camera.y(), this.camera.z());
		
		for(Star star : this.stars) {
			star.draw();
		}
		for(Asteroid asteroid: this.asteroids) {
			asteroid.draw();
		}
		for(Planet planet: this.planets) {
			planet.draw();
		}
		this.player.draw();
		this.particleHandler.draw();
	}
	
	public void pollInput() {

		/* Hiiren vasen nappi */
		if (Mouse.isButtonDown(0)) {
			System.out.println("MOUSE DOWN @ X: " + Mouse.getX() + " Y: " + Mouse.getY());
		}
		
		/* Hiiren oikea nappi */
		if (Mouse.isButtonDown(1)) {
			double xp = 2.0*(double)Mouse.getX()/(double)Display.getWidth()-1.0;
			double yp = 2.0*(double)Mouse.getY()/(double)Display.getHeight()-1.0;
	
			this.player.addForce(Force.towards(xp, yp), 0.002);
			this.player.rotation = Math.atan2(yp, xp);
			this.particleHandler.addParticle(new SmokeParticle(player.x()+Math.cos(player.rotation+Math.PI)*0.2, player.y()+Math.sin(player.rotation+Math.PI)*0.2, player.z()));
			
		}
	
		/* game.player:n liikkuminen */
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) this.player.rotationPlus =  0.005;
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) this.player.rotationPlus = -0.005;
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) this.player.move( 0.001);
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) this.player.move(-0.001);

		
		/* kameran zoomtaso */
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) this.camera.moveZ( 0.5);
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) this.camera.moveZ(-0.5);
			

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Pressed");
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Released");
				}
			}
		}
	}
}
