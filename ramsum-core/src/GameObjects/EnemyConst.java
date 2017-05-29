package GameObjects;

public class EnemyConst {
	public static final int health[]={12000,1200,1500,750,1200,18000};
	public static final int attack[]={300,30,60,125,100,250};
	public static final int defense[]={500,20,40,30,120,200};
	public static final int attrange[]={200,150,150,300,50,300};
	public static final int alrtrange[]={660,600,600,650,200,2000};
	public static final int hurtrto[]={10,2,4,5,3,10};
	public static final int minoff[]={0,0,2,1,1,1};
	public static final int bossoff[]={0,0,0,0,0,1};
	public static final int specseq[]={1,0,0,0,0,2};
	public static final float attackTime[]={2.4f,1.0f,2.4f,1.4f,1.2f,2.4f};//hit the player after atttime duration
	public static final float hitTime[][]={{2.4f},{0.6f},{2.4f},{0.4f},{0.3f,0.6f},{0.8f,1.7f,2.3f}};
	public static final float Velv[]={10,120,11,20,100,400};//movement vel
	public static final float Awmul[]={1,1,1,2,1,1};//temp width when attacking
	public static final float hurttm[]={0.36f,0.54f,0.36f,0.30f,0.24f,0.72f};//hurt animation time
	public static final float attackdely[]={3f,2f,2f,2f,1f,2f};//delay between consecutive hits
	public static final String dialogs[]={ "smash!!" , "Face me Coward!!" , "Ha Ha Ha Ha" , "Face My Wrath!!!" , "Meet my Children!!!" };

}
