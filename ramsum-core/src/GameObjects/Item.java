package GameObjects;

public class Item {
	private int id;
	private String name;
	
	public Item(int id,String name){
		this.id=id;
		this.name=name;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}
