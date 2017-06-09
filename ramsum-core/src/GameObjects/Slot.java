package GameObjects;

import Helper.Assetloader;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Slot {
	
	private Item item;

    private int amount;
    
    private String str="0";
    public Slot(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.str = ""+amount;
    }

    public boolean isEmpty() {
        return item == null || amount <= 0;
    }

    public boolean add(Item item, int amount) {
        if (this.item == item || this.item == null) {
            this.item = item;
            this.amount += amount;
            this.str = ""+amount;
            notifyListeners();
            return true;
        }

        return false;
    }

    public boolean take(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            this.str = ""+amount;
            if (this.amount == 0) {
                item = null;
            }
            notifyListeners();
            return true;
        }

        return false;
    }
    private Array<SlotListener> slotListeners = new Array<SlotListener>();
    public void addListener(SlotListener slotListener) {
        slotListeners.add(slotListener);
    }

    public void removeListener(SlotListener slotListener) {
        slotListeners.removeValue(slotListener, true);
    }

    private void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Slot[" + item + ":" + amount + "]";
    }
    
    public void render(SpriteBatch batcher,float x,float y){
    	
    	if(item != null){
    		batcher.draw(Assetloader.ui[0],x+10,y+5,60,60);
        	Assetloader.font.draw(batcher,str , x + 10, y + 10);
    	}
    }
}
