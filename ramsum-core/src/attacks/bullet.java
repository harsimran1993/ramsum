package attacks;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class bullet {
	float vel;
	public Rectangle rect;
	private boolean isleft = false;
	private static int rw = 15, rh = 15, vels = 450;
	private int damage = 300, type = 0;

	public bullet(float x, float y, int damage, boolean isleft) {
		rect = new Rectangle(x, y - rh, isleft ? -(rw * 2) : (rw * 2), rh * 2);
		this.vel = isleft ? -vels : vels;
		this.damage = damage;
		this.type = 0;
	}

	public bullet(int type, float x, float y, float width, float height, int damage, boolean isleft) {
		this.type = type;
		rect = new Rectangle(x, y, width, height);
		this.vel = isleft ? -200 : 200;
		this.damage = damage;
		this.setIsleft(isleft);
	}

	public void update(float delta) {
		rect.x += vel * delta;
		vel += vel * 0.01f;
	}

	public boolean collision(Rectangle r) {
		if (rect.overlaps(r) || rect.contains(r)){
			return true;
		}
		return false;
	}

	public int getDMG() {
		return damage;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public boolean isIsleft() {
		return isleft;
	}

	public void setIsleft(boolean isleft) {
		this.isleft = isleft;
	}
}
