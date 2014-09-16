import org.lwjgl.opengl.GL11;


public class Player extends Spaceship {

	public Player() {
		super();
		this.mass = 1;
		this.guns.put(SpaceshipGunRank.PRIMARY, new Gun(BulletType.BULLET));
	}
	
	public void shoot(SpaceshipGunRank rank) {
		Gun gun = this.guns.get(rank);

		if (gun == null) return;
		gun.shoot();
	}
	
	public void draw() {
		super.draw();
		GL11.glRotated(this.rotation, 1, 1, 1.0);
		if (this.distance2D(new Point(0,0)) > 10) {
			double aToOrigo = Math.atan2(-this.y(), -this.x());
			GL11.glBegin(GL11.GL_LINE_STRIP);
			 GL11.glVertex3d(0.4*Math.cos(aToOrigo), 0.4*Math.sin(aToOrigo), this.z());
			 GL11.glVertex3d(1.0*Math.cos(aToOrigo), 1.0*Math.sin(aToOrigo), this.z());
			GL11.glEnd();
		}
	}
}
