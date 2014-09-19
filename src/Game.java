import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class Game implements Drawable {

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	public ArrayList<Planet> planets = new ArrayList<Planet>();
	
	public Player player;
	public Field  field;
	public ParticleHandler particleHandler = new ParticleHandler();
	
	public double theFactor = 1;
	
	public void init() {
		Random rand = new Random();

		{
			double a = rand.nextInt();
			double r = 7;
			player = new Player();
			player.pos(r*Math.cos(a), r*Math.sin(a));
		}

		this.entities.add(player);

		Camera.z(-10);
		field = new Field(-40, -40, 40, 40);

		Planet planet1 = new Planet(4, 2);

		planet1.pos(0,0);
		
		this.planets.add(planet1);
		this.entities.add(planet1);
		

		for(int i = 0; i < 100; i++) {
			Asteroid asteroid = new Asteroid(0.1 + 0.3*rand.nextDouble(), 4+(int)(5*rand.nextDouble()));

			double a = rand.nextDouble()*Math.PI*2;
			double x = (10+5*rand.nextDouble())*Math.cos(a);
			double y = (10+5*rand.nextDouble())*Math.sin(a);
			double z = 0.0+rand.nextDouble();
			asteroid.pos(x, y, z);
			asteroid.mass = 0.4;

			for(Planet planet : this.planets) {
				asteroid.addForce(Force.gravitation(planet, asteroid).perpendicular(),0.025);
			}
			this.asteroids.add(asteroid);
			this.entities.add(asteroid);
			
		}
		
	}
	
	public void update() {
		{
			ArrayList<Asteroid> newAsteroidsBuffer = new ArrayList<Asteroid>();
			newAsteroidsBuffer.clear();
			Iterator<Asteroid> it = this.asteroids.iterator();
			while(it.hasNext()) {
				Asteroid asteroid = it.next();
				for(Planet planet : this.planets) {
					asteroid.addForce(Force.gravitation(planet, asteroid));
				}
				asteroid.update();

				BulletPrototype bulletHit = BulletHandler.checkHit(asteroid, true);
				
				if (bulletHit != null) {
					/* Ammus osuu asteroidiin */
					asteroid.health -= bulletHit.damage;
					// TODO: asteroidi ottaa voimaa ammuksen osumasta
					
					/* Jos on aika asteroidi tappaa(hajoittaa) */
					if (asteroid.health < 0) {
						newAsteroidsBuffer.addAll(asteroid.disintegrate());
						it.remove();
						continue;
					}
				}
				
				if (field.outOfField(asteroid)) {
					it.remove();
					continue;
				}

				for(Planet planet : this.planets)
					if (asteroid.hits(planet)) {
						it.remove();
						planet.health -= asteroid.damage;
						continue;
					}
				
			}

			for(Asteroid a : newAsteroidsBuffer) {
				this.asteroids.add(a);
				this.entities.add(a);
			}
		}

		{
			for(Planet planet : this.planets) {
				this.player.addForce(Force.gravitation(planet, player));
				planet.update();
				
				//if (player.hits(planet)) this.GameOver();
				if (planet.health <= 0) this.GameOver();
			}
		}

		{
			this.player.update();
			
			if (field.outOfField(player)) this.GameOver();
		}

		ParticleHandler.update();
		BulletHandler.update();

		Camera.x(this.player.x());
		Camera.y(this.player.y());
	}
	
	public void draw() {

		GL11.glTranslated(-Camera.x(), -Camera.y(), Camera.z());

		field.draw();
		
		ParticleHandler.draw();
		BulletHandler.draw();

		/*  -------------------------------  */

		{
			Iterator<Planet> it = this.planets.iterator();
			while(it.hasNext()) {
				Planet planet = it.next();
				GL11.glLoadIdentity();
				GL11.glTranslated(planet.x()-Camera.x(),planet.y()-Camera.y(),Camera.z()-planet.z());
				planet.draw();
			}
		}
		{
			Iterator<Asteroid> it = this.asteroids.iterator();
			while(it.hasNext()) {
				Asteroid asteroid = it.next();
				GL11.glLoadIdentity();
				GL11.glTranslated(asteroid.x()-Camera.x(),asteroid.y()-Camera.y(),Camera.z()-asteroid.z());
				asteroid.draw();
			}
		}
		GL11.glLoadIdentity();
		GL11.glTranslated(player.x()-Camera.x(),player.y()-Camera.y(),Camera.z()-player.z());
		this.player.draw();
	}
	
	public void pollInput() {
		Point2D mouse = new Point2D(Mouse.getX(), Mouse.getY());
		double xp = 2.0*(double)Mouse.getX()/(double)Display.getWidth()-1.0,
		       yp = 2.0*(double)Mouse.getY()/(double)Display.getHeight()-1.0,
		       ap = Math.atan2(yp, xp);
		
		/* Hiiren vasen nappi */
		if (Mouse.isButtonDown(0)) {
		//	System.out.println("MOUSE DOWN @ X: " + Mouse.getX() + " Y: " + Mouse.getY());
			this.player.shoot(SpaceshipGunRank.PRIMARY, ap);
		}
		
		/* Hiiren oikea nappi */
		if (Mouse.isButtonDown(1)) {
			this.player.rotation = ap;
//			this.player.rotation -= (-ap+this.player.rotation)/10.0;
			this.player.addForce(new Force(Math.cos(this.player.rotation), Math.sin(this.player.rotation), 0.002));
			
			this.particleHandler.addParticle(new SmokeParticle(player.x()+Math.cos(player.rotation+Math.PI)*0.2, player.y()+Math.sin(player.rotation+Math.PI)*0.2, player.z()));
			this.particleHandler.addParticle(new PlayerTraceTrailParticle(player.x()+Math.cos(player.rotation+Math.PI)*0.2, player.y()+Math.sin(player.rotation+Math.PI)*0.2, player.z()));
		}
	
		/* game.player:n liikkuminen */
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) this.player.rotationPlus =  0.02;
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) this.player.rotationPlus = -0.02;
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) this.player.move( 0.001);
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) this.player.move(-0.001);

		
		/* kameran zoomtaso */
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) Camera.z(Camera.z()+0.05);
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) Camera.z(Camera.z()-0.05);
		{
			int dWheel = Mouse.getDWheel();
			if (dWheel < 0)      Camera.z(Camera.z()+0.3);
			else if (dWheel > 0) Camera.z(Camera.z()-0.3);
			
		}
			
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) player.generateShip();


		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					Channon.DEBUG = true;
					System.out.println("DEBUG ON");
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					Channon.DEBUG = false;
					System.out.println("DEBUG OFF");
				}
			}
		}
	}
	
	public void GameOver() {
		System.out.println("GAME OVER");
		Channon.finished = true;
	}
}
