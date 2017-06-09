package level;

public class Level {
	public int level = 1, NOE = 0, NOP = 0, NOD = 0, NOC = 1, NODC[], CCS = 0, NOO = 0;
	public Enemy_data enemy[];
	public Platform_data plats[];
	public Dialog_data dialogs[];
	public object_data objd[];
	public int px, py, g, grassg, grass, back, hurtg = 600, upwallid = 0;
	public int maxdist, mdadjust = 92;
	public boolean water = false;
	public boolean upwall = false;
	public float intensity = 1.0f;
	public float backscroll = 0.25f;

	public Level() {

	}

	public void loadNextCS() {
		if (CCS < NOC)
			CCS += 1;
	}

	public void loadLevel(int lvl, int gameWidth, int gameHeight) {
		switch (lvl) {
		case 1:
			px = gameWidth / 2 - 25;
			py = 214;
			// g=220+80;
			// grassg=g+58;
			g = 220 + 160;
			grassg = gameHeight - 45;
			intensity = 0.5f;
			NOE = 0;
			NOP = 3;
			NOD = 4;
			NOC = 2;
			NODC = new int[NOC];
			NODC[0] = 3;
			NODC[1] = 4;
			NOO = 10;
			back = 2;
			maxdist = 1280;
			mdadjust = -90;
			grass = 3;
			upwall = true;
			upwallid = 0;
			backscroll = 0.8f;

			enemy = new Enemy_data[NOE];
			plats = new Platform_data[NOP];
			dialogs = new Dialog_data[NOD];
			objd = new object_data[NOO];

			dialogs[0] = new Dialog_data("System", "booting up...");
			dialogs[1] = new Dialog_data("System", "All module up and running!!!");
			dialogs[2] = new Dialog_data("Ramsum", "Where am I, I was... Dead.");
			dialogs[3] = new Dialog_data("System", "Picked up Tool-box!! might come in handy.");
			plats[0] = new Platform_data(30, grassg - 193, 100, 200, 3, false, 0);
			plats[1] = new Platform_data(180, grassg - 60, 140, 70, 4, false, 0);
			plats[2] = new Platform_data(maxdist - 90, 0, 170, 220, 5, true, gameHeight - 160);
			objd[0] = new object_data(1, 210, grassg - 100, 50, 50, 1);
			objd[1] = new object_data(2, 150, grassg - 60, 50, 65, 1);
			objd[2] = new object_data(3, 400, grassg - 150, 50, 50, backscroll);
			objd[3] = new object_data(4, 470, grassg - 130, 50, 100, backscroll);
			objd[4] = new object_data(7, 100, grassg - 250, 160, 100, backscroll);
			objd[5] = new object_data(6, 400, grassg - 310, 300, 160, backscroll);
			objd[6] = new object_data(8, 800, grassg - 158, 100, 160, backscroll);
			objd[7] = new object_data(9, 550, grassg - 158, 100, 160, backscroll);
			objd[8] = new object_data(10, 1040, grassg - 158, 100, 160, backscroll);
			objd[9] = new object_data(-1, 500, g, 65, 65, backscroll, true);// cutscene-trigger
			// objd[10]=new object_data(-4, 750, g, 65,
			// 65,backscroll,true);//level-trigger
			break;

		case 2:
			px = gameWidth / 2 - 25;
			py = 214;
			g = 220 + 160;
			grassg = gameHeight - 45;
			NOE = 12;
			NOP = 5;
			NOD = 4;
			NOC = 1;
			NODC = new int[NOC];
			NODC[0] = 4;
			NOO = 0;
			back = 0;
			maxdist = 8096;
			grass = 0;

			enemy = new Enemy_data[NOE];
			plats = new Platform_data[NOP];
			dialogs = new Dialog_data[NOD];
			objd = new object_data[NOO];

			dialogs[0] = new Dialog_data("Foot-Bot A", "Intruder Alert!!! Unknown Bionic Detected!!!");
			dialogs[1] = new Dialog_data("Foot-Bot B", "Informing Sergeant!!!");
			dialogs[2] = new Dialog_data("Foot-Bot A", "Receiving Orders!!!");
			dialogs[3] = new Dialog_data("Foot-Bot B", "Engage and Destroy!!! Hostile Bionic!!!");

			plats[0] = new Platform_data(1420, 280, 180, 60, 0, true, 60);
			plats[1] = new Platform_data(3200, 280, 180, 60, 0, true, 40);
			plats[2] = new Platform_data(4600, 270, 180, 60, 0, true, 30);
			plats[3] = new Platform_data(5900, 230, 180, 60, 0, true);
			plats[4] = new Platform_data(7000, 210, 180, 60, 0, true, 10);

			enemy[0] = new Enemy_data(4, 600, g - 2, 85, 65, g, false, level, false);
			enemy[1] = new Enemy_data(4, 650, g - 2, 85, 65, g, false, level, false);
			enemy[2] = new Enemy_data(4, 1950, g - 2, 85, 65, g, false, level, false);
			enemy[3] = new Enemy_data(3, 3010, g - 13, 76, 80, g - 11, false, level, false);
			enemy[4] = new Enemy_data(4, 2890, g - 2, 85, 65, g, false, level, false);
			enemy[5] = new Enemy_data(4, 3290, g - 2, 85, 65, g, false, level, false);
			enemy[6] = new Enemy_data(3, 5000, g - 13, 76, 80, g - 11, false, level, false);
			enemy[7] = new Enemy_data(4, 4800, g - 2, 85, 65, g, false, level, false);
			enemy[8] = new Enemy_data(4, 5100, g - 2, 85, 65, g, false, level, false);
			enemy[9] = new Enemy_data(4, 4900, g - 2, 85, 65, g, false, level, false);
			enemy[10] = new Enemy_data(3, 6900, g - 13, 76, 80, g - 11, false, level, false);
			enemy[11] = new Enemy_data(3, 7000, g - 13, 76, 80, g - 11, false, level, false);
			break;

		case 3:
			px = gameWidth / 2 - 25;
			py = 0;
			g = 220 + 160;
			grassg = gameHeight - 45;
			NOE = 10;
			NOP = 6;
			NOD = 3;
			NOC = 1;
			NODC = new int[NOC];
			NODC[0] = 3;
			NOO = 0;
			back = 0;
			maxdist = 6000;
			grass = 1;

			enemy = new Enemy_data[NOE];
			plats = new Platform_data[NOP];
			dialogs = new Dialog_data[NOD];
			objd = new object_data[NOO];

			dialogs[0] = new Dialog_data("Sweeper-Bot", "Intruder Sighted!!!");
			dialogs[1] = new Dialog_data("Sergeant Cluster", "WHO DARES DISTURB ME!!! DESTROY HIM!!!");
			dialogs[2] = new Dialog_data("Sergeant Cluster", "FEEL MY WRATH AND DIE PEST!!!");

			plats[0] = new Platform_data(150, g - 220, 220, 60, 0, true, 50);
			plats[1] = new Platform_data(1500, g - 160, 180, 60, 0, true);
			plats[2] = new Platform_data(2550, g - 180, 220, 60, 0, true, 60);
			plats[3] = new Platform_data(3800, g - 200, 220, 60, 0, true, 90);
			plats[4] = new Platform_data(4730, g - 240, 220, 60, 0, true, 50);
			plats[5] = new Platform_data(5790, 330, 320, 150, 1, false);
			// plats[6]=new Platform_data(6000, 335, 200, 60,2,false);

			enemy[0] = new Enemy_data(0, 4500, g - 93, 152, 160, g - 91, false, 3, true);
			enemy[1] = new Enemy_data(3, 650, g - 13, 76, 80, g - 11, false, level, false);
			enemy[2] = new Enemy_data(4, 900, g - 2, 85, 65, g, false, level, false);
			enemy[3] = new Enemy_data(3, 1190, g - 13, 76, 80, g - 11, false, level, false);
			enemy[4] = new Enemy_data(3, 1590, g - 13, 76, 80, g - 11, false, level, false);
			enemy[5] = new Enemy_data(4, 2000, g - 2, 85, 65, g, false, level, false);
			enemy[6] = new Enemy_data(3, 2500, g - 13, 76, 80, g - 11, false, level, false);
			enemy[7] = new Enemy_data(4, 2800, g - 2, 85, 65, g, false, level, false);
			enemy[8] = new Enemy_data(3, 3300, g - 17, 76, 80, g - 15, false, level, false);
			enemy[9] = new Enemy_data(3, 3300, g - 13, 76, 80, g - 11, false, level, false);
			break;

		case 4:
			px = gameWidth / 2 - 25;
			py = 64;
			g = 330 + 160;
			grassg = gameHeight - 45;
			hurtg = g - 10;
			NOE = 1;
			NOP = 20;
			NOD = 1;
			NOC = 1;
			NODC = new int[NOC];
			NODC[0] = 1;
			NOO = 0;
			back = 0;
			maxdist = 4600;
			grass = 2;
			water = true;

			enemy = new Enemy_data[NOE];
			plats = new Platform_data[NOP];
			dialogs = new Dialog_data[NOD];
			objd = new object_data[NOO];

			dialogs[0] = new Dialog_data("System", "Detecting vast Amount of Nanogen");
			/*
			 * dialogs[1]=new Dialog_data("", "I am called CHRONIC but you might know me as 'John Valence'."); dialogs[2]=new Dialog_data("Ramsum",
			 * "Yooouuuu!!! blooody murderer !!! I will kill you."); dialogs[3]=new Dialog_data("Chronic", "kill me? I saved you. It was lensher he alone is responsible for what" ); dialogs[4]=new
			 * Dialog_data("Chronic", "happended to you, your wife, and ME!!!"); dialogs[5]=new Dialog_data("Chronic", "I have given you this strength to match him and destroy him."); dialogs[6]=new
			 * Dialog_data("Ramsum", "SHUT UP!!! he will meet his end but you are not innocent, you wont be living either!!!" ); dialogs[7]=new
			 * Dialog_data("Ramsum","Atone!!! for your sins with your life."); dialogs[8]=new Dialog_data("Chronic", "Idiot!!! I had a hunch you were a useless tool."); dialogs[9]=new
			 * Dialog_data("Chronic", "So you see i made some enhancements on myself too."); dialogs[10]=new Dialog_data("Chronic", "Come and be a milestone in my growth!!");
			 */

			plats[0] = new Platform_data(-200, 335, 200, 60, 2, false);
			plats[1] = new Platform_data(0, 335, 200, 60, 2, false);
			plats[2] = new Platform_data(200, 335, 200, 60, 2, false);
			plats[3] = new Platform_data(400, 335, 200, 60, 2, false);
			plats[4] = new Platform_data(800, 335, 200, 60, 2, false);
			plats[5] = new Platform_data(1200, 335, 200, 60, 2, false);
			plats[6] = new Platform_data(1400, 335, 200, 60, 2, false);
			plats[7] = new Platform_data(1600, 335, 200, 60, 2, false);
			plats[8] = new Platform_data(1800, 335, 200, 60, 2, false);
			plats[9] = new Platform_data(2000, 335, 200, 60, 2, false);
			plats[10] = new Platform_data(2400, 335, 200, 60, 2, false);
			plats[11] = new Platform_data(2800, 335, 200, 60, 2, false);
			plats[12] = new Platform_data(3000, 335, 200, 60, 2, false);
			plats[13] = new Platform_data(3200, 335, 200, 60, 2, false);
			plats[14] = new Platform_data(3400, 335, 200, 60, 2, false);
			plats[15] = new Platform_data(3600, 335, 200, 60, 2, false);
			plats[16] = new Platform_data(3800, 335, 200, 60, 2, false);
			plats[17] = new Platform_data(4200, 335, 200, 60, 2, false);
			plats[18] = new Platform_data(4400, 335, 200, 60, 2, false);
			plats[19] = new Platform_data(4600, 335, 200, 60, 2, false);

			enemy[0] = new Enemy_data(5, 4500, 334, 120, 100, 234, false, 3, true);
			enemy[0].setWalk(false);
			break;

		default:
			px = gameWidth / 2 - 25;
			py = 214;
			g = 220 + 160;
			grassg = gameHeight - 45;
			NOE = 10;
			NOP = 5;
			NOD = 1;
			NOC = 1;
			NODC = new int[NOC];
			NODC[0] = 1;
			NOO = 0;
			back = 0;
			maxdist = 6000;
			grass = 0;

			enemy = new Enemy_data[NOE];
			plats = new Platform_data[NOP];
			dialogs = new Dialog_data[NOD];
			objd = new object_data[NOO];

			dialogs[0] = new Dialog_data("System", "Dimension Shattered!!!");

			plats[0] = new Platform_data(150, g - 220, 220, 60, 0, true, 50);
			plats[1] = new Platform_data(1500, g - 160, 180, 60, 0, true);
			plats[2] = new Platform_data(2550, g - 180, 220, 60, 0, true, 60);
			plats[3] = new Platform_data(3800, g - 200, 220, 60, 0, true, 90);
			plats[4] = new Platform_data(4730, g - 240, 220, 60, 0, true, 50);
			plats[5] = new Platform_data(5790, 330, 320, 150, 1, false);
			// plats[6]=new Platform_data(6000, 335, 200, 60,2,false);

			enemy[0] = new Enemy_data(0, 4500, g - 93, 152, 160, g - 91, false, 3, true);
			enemy[1] = new Enemy_data(3, 650, g - 13, 76, 80, g - 11, false, level, false);
			enemy[2] = new Enemy_data(4, 900, g - 2, 85, 65, g, false, level, false);
			enemy[3] = new Enemy_data(3, 1190, g - 13, 76, 80, g - 11, false, level, false);
			enemy[4] = new Enemy_data(3, 1590, g - 13, 76, 80, g - 11, false, level, false);
			enemy[5] = new Enemy_data(4, 2000, g - 2, 85, 65, g, false, level, false);
			enemy[6] = new Enemy_data(3, 2500, g - 13, 76, 80, g - 11, false, level, false);
			enemy[7] = new Enemy_data(4, 2800, g - 2, 85, 65, g, false, level, false);
			enemy[8] = new Enemy_data(3, 3300, g - 17, 76, 80, g - 15, false, level, false);
			enemy[9] = new Enemy_data(3, 3300, g - 13, 76, 80, g - 11, false, level, false);
			break;
		}
	}

	public class Enemy_data {
		public int type, x, y, w, h, g, lvl;
		public boolean fly, isboss, canWalk = true;

		public Enemy_data(int type, int x, int y, int w, int h, int g, boolean fly, int lvl, boolean isboss) {
			this.type = type;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.g = g;
			this.fly = fly;
			this.lvl = lvl;
			this.isboss = isboss;
		}

		public void setWalk(Boolean walk) {
			this.canWalk = walk;
		}

	}

	public class Platform_data {
		public int x, y, w, h, type;
		public float maxdist = 24;
		public boolean dync;

		public Platform_data(int x, int y, int w, int h, int type, boolean dync) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.type = type;
			this.dync = dync;
		}

		public Platform_data(int x, int y, int w, int h, int type, boolean dync, float maxdist) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.type = type;
			this.dync = dync;
			this.maxdist = maxdist;
		}
	}

	public class Dialog_data {
		public String speaker, dialog;

		public Dialog_data(String speaker, String dialog) {
			this.speaker = speaker;
			this.dialog = dialog;
		}

	}

	public class object_data {
		public int x, y, w, h, type;
		public float speedMUL;
		public boolean isTrigger = false;

		public object_data(int type, int x, int y, int width, int height, float speedMUL) {
			this.x = x;
			this.y = y;
			this.w = width;
			this.h = height;
			this.type = type;
			this.speedMUL = speedMUL;
		}

		public object_data(int type, int x, int y, int width, int height, float speedMUL, boolean isTrigger) {
			this.x = x;
			this.y = y;
			this.w = width;
			this.h = height;
			this.type = type;
			this.speedMUL = speedMUL;
			this.isTrigger = isTrigger;
		}

	}

}
