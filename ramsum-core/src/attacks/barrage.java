package attacks;

import com.badlogic.gdx.math.Rectangle;

public class barrage {

	public int type = 1;
	public Rectangle rect;
	
	private float vely, blasttimer = 0;
	private enum State{ active, blasted, obsolette};
	private State state;
	private static int velys = 700;
	private static float blastT = 0.6f;
	
	public barrage(float x,float y, int width, int height,int type){
		rect = new Rectangle(x , y, width, height);
		this.vely = velys;
		this.type = type;
		this.state = State.active;
		this.blasttimer=blastT;
	}
	public void update(float delta){
		if(isActive())
			rect.y += vely * delta;
		if(state == State.blasted){
			
			blasttimer -= delta;
			
			if(blasttimer<0)
				state=State.obsolette;
			
		}
	}
	
	public boolean collision(Rectangle r)
	{
		if(isActive()){
			if(rect.overlaps(r))
				return true;
		}
		return false;
	}
	
	public void blast(){
		state=State.blasted;
	}
	
	public boolean isActive(){
		return state == State.active;
	}
	
	public boolean isBlasted(){
		return state == State.blasted;
	}
	
	public boolean isobsolette(){
		return state == State.obsolette;
	}
}

