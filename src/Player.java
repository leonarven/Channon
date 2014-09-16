import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public class Player extends Spaceship {

	public Player() {
		super(0.4, 1, 100);
		this.guns.put(SpaceshipGunRank.PRIMARY, new Gun(0, 0, 0, BulletType.BULLET));
	}
	
	public void update() {
		super.update();
		Channon.ready3D();
		Debug.println(this.screenCoordinates+"-"+Channon.game.planets.get(0).screenCoordinates+"-"+this.screen2D()+"-"+Channon.game.planets.get(0).screen2D());
//		this.screenCoordinates = new Point2D(Display.getWidth()/2, Display.getHeight()/2);
	}
	
	public void draw() {
		super.draw();
	}
}