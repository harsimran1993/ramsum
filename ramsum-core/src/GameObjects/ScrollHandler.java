package GameObjects;

public class ScrollHandler {

	 private Grass frontGrass, backGrass, backGrass2,waterf,waterb,waterb2;
	 private Background bg1,bg2;
	 public float SCROLL_SPEED = -250;
	 public final int SPEED_LEFT= -250;
	 public final int SPEED_RIGHT= 250;
	 private float backgroundSpeed=0.25f;
	 boolean layer2;
	 boolean moveback=true;
	 public ScrollHandler(float yPos, int back,boolean layer2,int gamewidth,int gameheight, float backscroll) {
		 this.layer2=layer2;
		 this.backgroundSpeed=backscroll;
		 bg1=new Background(0, 0, gamewidth ,(int) yPos, SCROLL_SPEED * backscroll,gamewidth);
		 bg2=new Background(bg1.getTailLX(), 0, gamewidth,(int) yPos, SCROLL_SPEED * backscroll,gamewidth);
		 frontGrass=new Grass(-384, yPos, 512, 64,SCROLL_SPEED,gamewidth,0.1f);
	     backGrass = new Grass(frontGrass.getTailLX(), yPos, 512, 64,SCROLL_SPEED,gamewidth,0.1f);
	     backGrass2 = new Grass(backGrass.getTailLX(), yPos, 512, 64,SCROLL_SPEED,gamewidth,0.1f);
	     if(layer2){
	     waterf=new Grass(-384, yPos, 512, 64,SCROLL_SPEED,gamewidth,0.2f);
	     waterb=new Grass(waterf.getTailLX(), yPos, 512, 64,SCROLL_SPEED,gamewidth,0.2f);
	     waterb2=new Grass(waterb.getTailLX(), yPos, 512, 64,SCROLL_SPEED,gamewidth,0.2f);
	     }
	    if(back==1)
	    	moveback=false;
	 }
	 public void updatewater(float delta)
	 {
	     if (frontGrass.isScrolledLeft()) {
	            frontGrass.reset(backGrass2.getTailLX());
	        }
	     if (backGrass.isScrolledLeft()) {
	            backGrass.reset(frontGrass.getTailLX());
	        }
	     if (backGrass2.isScrolledLeft()) {
	            backGrass2.reset(backGrass.getTailLX());
	        }
	 //    System.out.println("front:"+frontGrass.getX()+"back:"+backGrass.getX()+"back2:"+backGrass2.getX());
	     frontGrass.updatewater(delta);
	     backGrass.updatewater(delta);
	     backGrass2.updatewater(delta);
	     if(layer2){
	     if (waterf.isScrolledLeft()) {
	    	 	waterf.reset(waterb2.getTailLX());
	        }
	     if (waterb.isScrolledLeft()) {
	    	 	waterb.reset(waterf.getTailLX());
	        }
	     if (waterb2.isScrolledLeft()) {
	    	 	waterb2.reset(waterb.getTailLX());
	        }
	     waterf.updatewater(delta);
	     waterb.updatewater(delta);
	     waterb2.updatewater(delta);
	     }
	 }
	 public void update(float delta) {
		 
	     if (frontGrass.isScrolledLeft()) {
	            frontGrass.reset(backGrass2.getTailLX());
	     	}
	     if (backGrass.isScrolledLeft()) {
	            backGrass.reset(frontGrass.getTailLX());
	     	}
	     if (backGrass2.isScrolledLeft()) {
	            backGrass2.reset(backGrass.getTailLX());
	        }
	     if (frontGrass.isScrolledright()) {
	            frontGrass.reset(backGrass.getHeadX());
	        }
	     if (backGrass.isScrolledright()) {
	            backGrass.reset(backGrass2.getHeadX());
	        }
	     if (backGrass2.isScrolledright()) {
	            backGrass2.reset(frontGrass.getHeadX());
	        }
	     if (bg1.isScrolledLeft()) {
	            bg1.reset(bg2.getTailLX());
	        }
	     if (bg2.isScrolledLeft()) {
	            bg2.reset(bg1.getTailLX());
	        }
	     if (bg1.isScrolledright()) {
	            bg1.reset(bg2.getHeadX());
	        }
	     if (bg2.isScrolledright()) {
	            bg2.reset(bg1.getHeadX());
	        }
	    // System.out.println("front:"+frontGrass.getX()+"back:"+backGrass.getX()+"back2:"+backGrass2.getX());

	     if(moveback)
	     {
	     bg1.update(delta);
	     bg2.update(delta);
	     }
		 frontGrass.update(delta);
	     backGrass.update(delta);
	     backGrass2.update(delta);
	     if(layer2){
		     if (waterf.isScrolledLeft()) {
		    	 	waterf.reset(waterb2.getTailLX());
		        }
		     if (waterb.isScrolledLeft()) {
		    	 	waterb.reset(waterf.getTailLX());
		        }
		     if (waterb2.isScrolledLeft()) {
		    	 	waterb2.reset(waterb.getTailLX());
		        }
		     if (waterf.isScrolledright()) {
		    	 	waterf.reset(waterb.getHeadX());
		        }
		     if (waterb.isScrolledright()) {
		    	 	waterb.reset(waterb2.getHeadX());
		        }
		     if (waterb2.isScrolledright()) {
		    	 	waterb2.reset(waterf.getHeadX());
		        }  
		     waterf.update(delta);
			 waterb.update(delta);
			 waterb2.update(delta);
	     }
	     
     }
	 public void stop() {
		 bg1.stop();
		 bg2.stop();
		 frontGrass.stop();
	     backGrass.stop();
	     backGrass2.stop();
	     if(layer2){
		     waterf.stop();
			 waterb.stop();
			 waterb2.stop();
	     }
	 }
	 public void onRestart() {
		 if(SCROLL_SPEED>0)
		 {
	    	bg1.onRestart(0, SCROLL_SPEED * backgroundSpeed);
	    	bg2.onRestart(bg1.getTailLX(), SCROLL_SPEED * backgroundSpeed);
	    	frontGrass.onRestart(0, SCROLL_SPEED);
	        backGrass.onRestart(frontGrass.getTailLX(), SCROLL_SPEED);
	        backGrass2.onRestart(backGrass.getTailLX(), SCROLL_SPEED);
	        if(layer2){
			    waterf.onRestart(0, SCROLL_SPEED/1.2f);
				waterb.onRestart(waterf.getTailLX(), SCROLL_SPEED/1.2f);
				waterb2.onRestart(waterb.getTailLX(), SCROLL_SPEED/1.2f);
	        }
		 }
		 else
		 {
			bg1.onRestart(0, SCROLL_SPEED * backgroundSpeed);
		    bg2.onRestart(bg1.getHeadX(), SCROLL_SPEED * backgroundSpeed);
		    frontGrass.onRestart(0, SCROLL_SPEED);
		    backGrass.onRestart(backGrass.getHeadX(), SCROLL_SPEED);
		    backGrass2.onRestart(frontGrass.getHeadX(), SCROLL_SPEED);
		    if(layer2){
		    	waterf.onRestart(0, SCROLL_SPEED/1.2f);
		    	waterb.onRestart(waterb.getHeadX(), SCROLL_SPEED/1.2f);
		    	waterb2.onRestart(waterf.getHeadX(), SCROLL_SPEED/1.2f);
		    }
		 }
	 }
	 public void direct(float speed) {
		 SCROLL_SPEED=speed;
		 bg1.setSpeed(SCROLL_SPEED * backgroundSpeed);
		 bg2.setSpeed(SCROLL_SPEED * backgroundSpeed);
		 frontGrass.setSpeed(SCROLL_SPEED);
		 backGrass.setSpeed(SCROLL_SPEED);
		 backGrass2.setSpeed(SCROLL_SPEED);
		 if(layer2){
		     waterf.setSpeed(SCROLL_SPEED/1.2f);
			 waterb.setSpeed(SCROLL_SPEED/1.2f);
			 waterb2.setSpeed(SCROLL_SPEED/1.2f);
		 }
		 //onRestart();
	 }
	 public Background getBg1() {
			return bg1;
		}

		public Background getBg2() {
			return bg2;
		}
	 public Grass getFrontGrass() {
	        return frontGrass;
	    }

	    public Grass getBackGrass() {
	        return backGrass;
	    }
	    
	    public Grass getBackGrass2() {
	        return backGrass2;
	    }
	    
	    public Grass getwaterf() {
	        return waterf;
	    }
	    
	    public Grass getwaterb() {
	        return waterb;
	    }
	    
	    public Grass getwaterb2() {
	        return waterb2;
	    }
}