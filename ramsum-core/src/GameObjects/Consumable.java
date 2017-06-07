package GameObjects;

public class Consumable extends Item {

	private int resHp=10,resMp=10;
	public Consumable(int id, String name,int hp,int mp) {
		super(id, name);
		// TODO Auto-generated constructor stub
		this.resHp=hp;
		this.resMp=mp;
	}
	
	public int getresHP(){
		return resHp;
	}
	public int getresMP(){
		return resMp;
	}
}