package Helper;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assetloader {
	public static AssetManager manager;
	public static Texture enemy[], ramt, ramwt, ramht, ramupT, jmpt, attT, slideT, hurricaneT, platformT, levelT;
	public static TextureRegion logo, cloud[], ramtr, grass[], specilaeff[][], ramidle[], ramwalk[], ramhurt[], ramup[], ramko[], jump[], att[], wasp, slide[], hurricane[], enemywalk[][],
			enemyatt[][], enemyhit[][], enemydie[][], ui[], platform[], objs[], upwall[];
	private static TextureParameter param;
	public static Animation idle, walk, jmp, attAnim, slideA, hurricaneA, enemystandA[], enemyattackA[], enemyhitA[], enemywalkA[], ramhurtA, ramkoA, ramgetupA, ramupA, specialeffA[], bulletA;
	public static BitmapFont font;
	private static Random rand;
	private static Preferences prefs;
	private static TextureAtlas shaderidle, shaderatt, shaderhit, shaderwalk, env_Lab, UI, spec;

	public static void init() {
		// enemy arrays initalize for n=5 enemies
		// 1-bigbot
		// 2-wasp
		// 3-hencher
		// 4-gaso
		// 5-shader //in work
		int n = 4;
		enemy = new Texture[n];
		enemywalk = new TextureRegion[n][];
		enemyatt = new TextureRegion[n][];
		enemyhit = new TextureRegion[n][];
		enemydie = new TextureRegion[n][];
		n = 5;
		enemystandA = new Animation[n];
		enemyattackA = new Animation[n];
		enemyhitA = new Animation[n];
		enemywalkA = new Animation[n];

		specialeffA = new Animation[4];
		// player
		ramidle = new TextureRegion[11];
		ramwalk = new TextureRegion[11];
		jump = new TextureRegion[3];
		att = new TextureRegion[26];
		slide = new TextureRegion[6];
		hurricane = new TextureRegion[11];
		ui = new TextureRegion[20];
		ramhurt = new TextureRegion[17];
		ramko = new TextureRegion[13];
		ramup = new TextureRegion[13];

		// enemy
		enemywalk[0] = new TextureRegion[11];
		enemyatt[0] = new TextureRegion[13];
		enemyhit[0] = new TextureRegion[6];
		enemywalk[1] = new TextureRegion[12];
		enemyhit[1] = new TextureRegion[9];
		enemyatt[1] = new TextureRegion[6];
		enemywalk[2] = new TextureRegion[14];
		enemyatt[2] = new TextureRegion[8];
		enemyhit[2] = new TextureRegion[9];
		enemywalk[3] = new TextureRegion[6];
		enemyatt[3] = new TextureRegion[6];
		enemyhit[3] = new TextureRegion[3];

		// specialeffects
		specilaeff = new TextureRegion[4][];
		specilaeff[0] = new TextureRegion[4];// blastground
		specilaeff[1] = new TextureRegion[7];// flight
		specilaeff[2] = new TextureRegion[2];// hurricane
		specilaeff[3] = new TextureRegion[4];// blast

		grass = new TextureRegion[4];
		upwall = new TextureRegion[1];
		cloud = new TextureRegion[3];
		platform = new TextureRegion[6];
		objs = new TextureRegion[12];
	}

	public static void load() {

		float time = System.currentTimeMillis();
		rand = new Random();
		manager = new AssetManager();
		param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		// texture atlas
		shaderidle = new TextureAtlas(Gdx.files.internal("shader/idle.atlas"), true);
		shaderatt = new TextureAtlas(Gdx.files.internal("shader/att.atlas"), true);
		shaderhit = new TextureAtlas(Gdx.files.internal("shader/hit.atlas"), true);
		shaderwalk = new TextureAtlas(Gdx.files.internal("shader/run.atlas"), true);
		env_Lab = new TextureAtlas(Gdx.files.internal("environment/lab.atlas"), true);
		UI = new TextureAtlas(Gdx.files.internal("ui/ui.atlas"), true);
		spec = new TextureAtlas(Gdx.files.internal("attacks/spec.atlas"), true);

		// load texture images
		manager.load("logo.png", Texture.class, param);
		manager.load("cloud.png", Texture.class, param);
		manager.load("cloud2.png", Texture.class, param);
		manager.load("night.png", Texture.class, param);
		manager.load("ramidle.png", Texture.class, param);
		manager.load("ramwalk.png", Texture.class, param);
		manager.load("jump.png", Texture.class, param);
		manager.load("wasp.png", Texture.class, param);
		manager.load("ramatt.png", Texture.class, param);
		manager.load("slide.png", Texture.class, param);
		manager.load("hurricane.png", Texture.class, param);
		manager.load("bigron.png", Texture.class, param);
		manager.load("ram_hurt.png", Texture.class, param);
		manager.load("ramupatt.png", Texture.class, param);
		manager.load("hencher.png", Texture.class, param);
		// manager.load("specialeffects1.png",Texture.class,param);
		manager.load("platform2.png", Texture.class, param);
		manager.load("level.png", Texture.class, param);
		manager.load("gaso.png", Texture.class, param);
		manager.load("fog.png", Texture.class, param);
		manager.finishLoading();

		// initialize textures
		ramt = manager.get("ramidle.png", Texture.class);
		ramwt = manager.get("ramwalk.png", Texture.class);
		attT = manager.get("ramatt.png", Texture.class);
		jmpt = manager.get("jump.png", Texture.class);
		slideT = manager.get("slide.png", Texture.class);
		hurricaneT = manager.get("hurricane.png", Texture.class);
		enemy[0] = manager.get("bigron.png", Texture.class);
		enemy[1] = manager.get("wasp.png", Texture.class);
		enemy[2] = manager.get("hencher.png", Texture.class);
		enemy[3] = manager.get("gaso.png", Texture.class);
		ramht = manager.get("ram_hurt.png", Texture.class);
		ramupT = manager.get("ramupatt.png", Texture.class);
		platformT = manager.get("platform2.png", Texture.class);
		// specialeffT=manager.get("specialeffects1.png", Texture.class);
		levelT = manager.get("level.png", Texture.class);

		// environment and extras
		objs[0] = new TextureRegion(manager.get("fog.png", Texture.class));
		objs[0].flip(false, true);
		objs[1] = env_Lab.findRegion("comp");
		objs[2] = env_Lab.findRegion("chair");
		objs[3] = env_Lab.findRegion("circuits");
		objs[4] = env_Lab.findRegion("circuits2");
		objs[5] = env_Lab.findRegion("pipe");
		objs[6] = env_Lab.findRegion("panel");
		objs[7] = env_Lab.findRegion("panel2");
		objs[8] = env_Lab.findRegion("door");
		objs[9] = env_Lab.findRegion("shelf");
		objs[10] = env_Lab.findRegion("shelf2");
		objs[11] = env_Lab.findRegion("ladder");
		logo = new TextureRegion(manager.get("logo.png", Texture.class), 0, 0, 512, 512);
		cloud[0] = new TextureRegion(manager.get("cloud.png", Texture.class), 2, 2, 512, 256);
		cloud[0].flip(false, true);
		cloud[1] = new TextureRegion(manager.get("night.png", Texture.class), 0, 0, 512, 256);
		cloud[1].flip(false, true);
		cloud[2] = env_Lab.findRegion("walln");
		// cloud[2].flip(false, true);
		platform[0] = new TextureRegion(platformT, 0, 4, 256, 82);
		platform[0].flip(false, true);
		platform[1] = new TextureRegion(levelT, 2, 134, 512, 256);
		platform[1].flip(false, true);
		platform[2] = new TextureRegion(levelT, 516, 3, 392, 128);
		platform[2].flip(false, true);
		platform[3] = env_Lab.findRegion("tankbrokenA");
		platform[4] = env_Lab.findRegion("table");
		platform[5] = env_Lab.findRegion("elevator");
		// ramtr= new TextureRegion(ramt, 2, 306, 130, 150);
		// ramtr.flip(false, true);
		grass[0] = new TextureRegion(levelT, 2, 68, 512, 64);
		grass[0].flip(false, true);
		grass[1] = new TextureRegion(levelT, 516, 295, 500, 95);
		grass[1].flip(false, true);
		grass[2] = new TextureRegion(levelT, 2, 2, 500, 64);
		grass[2].flip(false, true);
		grass[3] = new TextureRegion(levelT, 516, 226, 508, 64);
		grass[3].flip(false, true);

		upwall[0] = env_Lab.findRegion("upwall");

		ui[0] = UI.findRegion("potionicon");// potion
		ui[1] = UI.findRegion("uibaseatt");// baseatt
		ui[2] = UI.findRegion("uibaseatt2");// baseatt2
		ui[3] = UI.findRegion("ring");// ui-ring
		ui[4] = UI.findRegion("bullet3");// bullet1
		ui[5] = UI.findRegion("bullet2");// bullet2
		ui[6] = UI.findRegion("fire");// mage-att
		ui[7] = UI.findRegion("huricane");// hurricane-att
		ui[8] = UI.findRegion("jumpatt");// airpunch-att
		ui[9] = UI.findRegion("slide");// slide-att
		ui[10] = UI.findRegion("slotback");// item-slot
		ui[11] = UI.findRegion("inventory");// inventory
		ui[12] = UI.findRegion("textbubble");// text-board
		ui[13] = UI.findRegion("switchoff3");// switch-offA
		ui[14] = UI.findRegion("switchoff4");// switch-offB
		ui[15] = UI.findRegion("nextlvl");// next-levelA
		ui[16] = UI.findRegion("nextlvl2");// next-levelB
		ui[17] = UI.findRegion("hand");// handA
		ui[18] = UI.findRegion("hand2");// handB
		ui[19] = UI.findRegion("statbar");// stat-bar

		// special effect

		// player
		ramidle[0] = new TextureRegion(ramt, 2, 306, 130, 150);
		ramidle[0].flip(false, true);
		ramidle[1] = new TextureRegion(ramt, 2, 154, 130, 150);
		ramidle[1].flip(false, true);
		ramidle[2] = new TextureRegion(ramt, 134, 306, 130, 150);
		ramidle[2].flip(false, true);
		ramidle[3] = new TextureRegion(ramt, 2, 2, 130, 150);
		ramidle[3].flip(false, true);
		ramidle[4] = new TextureRegion(ramt, 134, 154, 130, 150);
		ramidle[4].flip(false, true);
		ramidle[5] = new TextureRegion(ramt, 266, 306, 130, 150);
		ramidle[5].flip(false, true);
		ramidle[6] = new TextureRegion(ramt, 134, 2, 130, 150);
		ramidle[6].flip(false, true);
		ramidle[7] = new TextureRegion(ramt, 266, 154, 130, 150);
		ramidle[7].flip(false, true);
		ramidle[8] = new TextureRegion(ramt, 398, 306, 130, 150);
		ramidle[8].flip(false, true);
		ramidle[9] = new TextureRegion(ramt, 266, 2, 130, 150);
		ramidle[9].flip(false, true);
		ramidle[10] = new TextureRegion(ramt, 398, 154, 130, 150);
		ramidle[10].flip(false, true);

		ramwalk[0] = new TextureRegion(ramwt, 2, 306, 130, 150);
		ramwalk[0].flip(false, true);
		ramwalk[1] = new TextureRegion(ramwt, 2, 154, 130, 150);
		ramwalk[1].flip(false, true);
		ramwalk[2] = new TextureRegion(ramwt, 134, 306, 130, 150);
		ramwalk[2].flip(false, true);
		ramwalk[3] = new TextureRegion(ramwt, 2, 2, 130, 150);
		ramwalk[3].flip(false, true);
		ramwalk[4] = new TextureRegion(ramwt, 134, 154, 130, 150);
		ramwalk[4].flip(false, true);
		ramwalk[5] = new TextureRegion(ramwt, 266, 306, 130, 150);
		ramwalk[5].flip(false, true);
		ramwalk[6] = new TextureRegion(ramwt, 134, 2, 130, 150);
		ramwalk[6].flip(false, true);
		ramwalk[7] = new TextureRegion(ramwt, 266, 154, 130, 150);
		ramwalk[7].flip(false, true);
		ramwalk[8] = new TextureRegion(ramwt, 398, 306, 130, 150);
		ramwalk[8].flip(false, true);
		ramwalk[9] = new TextureRegion(ramwt, 266, 2, 130, 150);
		ramwalk[9].flip(false, true);
		ramwalk[10] = new TextureRegion(ramwt, 398, 154, 130, 150);
		ramwalk[10].flip(false, true);

		ramhurt[0] = new TextureRegion(ramht, 2, 762, 141, 150);
		ramhurt[0].flip(false, true);
		ramhurt[1] = new TextureRegion(ramht, 2, 610, 141, 150);
		ramhurt[1].flip(false, true);
		ramhurt[2] = new TextureRegion(ramht, 145, 762, 141, 150);
		ramhurt[2].flip(false, true);
		ramhurt[3] = new TextureRegion(ramht, 2, 458, 141, 150);
		ramhurt[3].flip(false, true);
		ramhurt[4] = new TextureRegion(ramht, 145, 610, 141, 150);
		ramhurt[4].flip(false, true);
		ramhurt[5] = new TextureRegion(ramht, 288, 762, 141, 150);
		ramhurt[5].flip(false, true);
		ramhurt[6] = new TextureRegion(ramht, 2, 306, 141, 150);
		ramhurt[6].flip(false, true);
		ramhurt[7] = new TextureRegion(ramht, 145, 458, 141, 150);
		ramhurt[7].flip(false, true);
		ramhurt[8] = new TextureRegion(ramht, 288, 610, 141, 150);
		ramhurt[8].flip(false, true);
		ramhurt[9] = new TextureRegion(ramht, 431, 762, 141, 150);
		ramhurt[9].flip(false, true);
		ramhurt[10] = new TextureRegion(ramht, 2, 154, 141, 150);
		ramhurt[10].flip(false, true);
		ramhurt[11] = new TextureRegion(ramht, 145, 306, 141, 150);
		ramhurt[11].flip(false, true);
		ramhurt[12] = new TextureRegion(ramht, 288, 458, 141, 150);
		ramhurt[12].flip(false, true);
		ramhurt[13] = new TextureRegion(ramht, 431, 610, 141, 150);
		ramhurt[13].flip(false, true);
		ramhurt[14] = new TextureRegion(ramht, 574, 762, 141, 150);
		ramhurt[14].flip(false, true);
		ramhurt[15] = new TextureRegion(ramht, 2, 2, 141, 150);
		ramhurt[15].flip(false, true);
		ramhurt[16] = new TextureRegion(ramht, 145, 154, 141, 150);
		ramhurt[16].flip(false, true);

		ramko[0] = new TextureRegion(ramht, 440, 377, 150, 113);
		ramko[0].flip(false, true);
		ramko[1] = new TextureRegion(ramht, 440, 262, 150, 113);
		ramko[1].flip(false, true);
		ramko[2] = new TextureRegion(ramht, 583, 529, 150, 113);
		ramko[2].flip(false, true);
		ramko[3] = new TextureRegion(ramht, 735, 563, 150, 113);
		ramko[3].flip(false, true);
		ramko[4] = new TextureRegion(ramht, 592, 414, 150, 113);
		ramko[4].flip(false, true);
		ramko[5] = new TextureRegion(ramht, 592, 299, 150, 113);
		ramko[5].flip(false, true);
		ramko[6] = new TextureRegion(ramht, 288, 340, 150, 116);
		ramko[6].flip(false, true);
		ramko[7] = new TextureRegion(ramht, 288, 222, 150, 116);
		ramko[7].flip(false, true);
		ramko[8] = new TextureRegion(ramht, 574, 644, 150, 116);
		ramko[8].flip(false, true);
		ramko[9] = new TextureRegion(ramht, 145, 36, 150, 116);
		ramko[9].flip(false, true);
		ramko[10] = new TextureRegion(ramht, 717, 796, 150, 116);
		ramko[10].flip(false, true);
		ramko[11] = new TextureRegion(ramht, 431, 492, 150, 116);
		ramko[11].flip(false, true);
		ramko[12] = new TextureRegion(ramht, 726, 678, 150, 116);
		ramko[12].flip(false, true);

		ramup[0] = new TextureRegion(ramupT, 431, 3, 141, 277);
		ramup[0].flip(false, true);
		ramup[1] = new TextureRegion(ramupT, 2, 562, 141, 277);
		ramup[1].flip(false, true);
		ramup[2] = new TextureRegion(ramupT, 2, 282, 141, 277);
		ramup[2].flip(false, true);
		ramup[3] = new TextureRegion(ramupT, 145, 562, 141, 277);
		ramup[3].flip(false, true);
		ramup[4] = new TextureRegion(ramupT, 2, 2, 141, 277);
		ramup[4].flip(false, true);
		ramup[5] = new TextureRegion(ramupT, 145, 282, 141, 277);
		ramup[5].flip(false, true);
		ramup[6] = new TextureRegion(ramupT, 288, 562, 141, 277);
		ramup[6].flip(false, true);
		ramup[7] = new TextureRegion(ramupT, 145, 2, 141, 277);
		ramup[7].flip(false, true);
		ramup[8] = new TextureRegion(ramupT, 288, 282, 141, 277);
		ramup[8].flip(false, true);
		ramup[9] = new TextureRegion(ramupT, 431, 562, 141, 277);
		ramup[9].flip(false, true);
		ramup[10] = new TextureRegion(ramupT, 288, 2, 141, 277);
		ramup[10].flip(false, true);
		ramup[11] = new TextureRegion(ramupT, 431, 282, 141, 277);
		ramup[11].flip(false, true);
		ramup[12] = new TextureRegion(ramupT, 574, 562, 141, 277);
		ramup[12].flip(false, true);

		jump[0] = new TextureRegion(jmpt, 2, 2, 130, 157);
		jump[0].flip(false, true);
		jump[1] = new TextureRegion(jmpt, 134, 2, 130, 157);
		jump[1].flip(false, true);
		jump[2] = new TextureRegion(jmpt, 266, 2, 130, 157);
		jump[2].flip(false, true);

		att[0] = new TextureRegion(attT, 2, 787, 173, 157);
		att[0].flip(false, true);
		att[1] = new TextureRegion(attT, 2, 630, 173, 157);
		att[1].flip(false, true);
		att[2] = new TextureRegion(attT, 177, 787, 173, 157);
		att[2].flip(false, true);
		att[3] = new TextureRegion(attT, 2, 473, 173, 157);
		att[3].flip(false, true);
		att[4] = new TextureRegion(attT, 177, 630, 173, 157);
		att[4].flip(false, true);
		att[5] = new TextureRegion(attT, 352, 787, 173, 157);
		att[5].flip(false, true);
		att[6] = new TextureRegion(attT, 2, 316, 173, 157);
		att[6].flip(false, true);
		att[7] = new TextureRegion(attT, 177, 473, 173, 157);
		att[7].flip(false, true);
		att[8] = new TextureRegion(attT, 352, 630, 173, 157);
		att[8].flip(false, true);
		att[9] = new TextureRegion(attT, 527, 787, 173, 157);
		att[9].flip(false, true);
		att[10] = new TextureRegion(attT, 2, 159, 173, 157);
		att[10].flip(false, true);
		att[11] = new TextureRegion(attT, 177, 316, 173, 157);
		att[11].flip(false, true);
		att[12] = new TextureRegion(attT, 352, 473, 173, 157);
		att[12].flip(false, true);
		att[13] = new TextureRegion(attT, 527, 630, 173, 157);
		att[13].flip(false, true);
		att[14] = new TextureRegion(attT, 702, 787, 173, 157);
		att[14].flip(false, true);
		att[15] = new TextureRegion(attT, 2, 2, 173, 157);
		att[15].flip(false, true);
		att[16] = new TextureRegion(attT, 177, 159, 173, 157);
		att[16].flip(false, true);
		att[17] = new TextureRegion(attT, 352, 316, 173, 157);
		att[17].flip(false, true);
		att[18] = new TextureRegion(attT, 527, 473, 173, 157);
		att[18].flip(false, true);
		att[19] = new TextureRegion(attT, 702, 630, 173, 157);
		att[19].flip(false, true);
		att[20] = new TextureRegion(attT, 177, 2, 173, 157);
		att[20].flip(false, true);
		att[21] = new TextureRegion(attT, 352, 159, 173, 157);
		att[21].flip(false, true);
		att[22] = new TextureRegion(attT, 527, 316, 173, 157);
		att[22].flip(false, true);
		att[23] = new TextureRegion(attT, 702, 473, 173, 157);
		att[23].flip(false, true);
		att[24] = new TextureRegion(attT, 352, 2, 173, 157);
		att[24].flip(false, true);
		att[25] = new TextureRegion(attT, 527, 159, 173, 157);
		att[25].flip(false, true);

		slide[0] = new TextureRegion(slideT, 2, 306, 236, 150);
		slide[0].flip(false, true);
		slide[1] = new TextureRegion(slideT, 2, 154, 236, 150);
		slide[1].flip(false, true);
		slide[2] = new TextureRegion(slideT, 240, 306, 236, 150);
		slide[2].flip(false, true);
		slide[3] = new TextureRegion(slideT, 2, 2, 236, 150);
		slide[3].flip(false, true);
		slide[4] = new TextureRegion(slideT, 240, 154, 236, 150);
		slide[4].flip(false, true);
		slide[5] = new TextureRegion(slideT, 240, 2, 236, 150);
		slide[5].flip(false, true);

		hurricane[0] = new TextureRegion(hurricaneT, 2, 306, 224, 150);
		hurricane[0].flip(false, true);
		hurricane[1] = new TextureRegion(hurricaneT, 2, 154, 224, 150);
		hurricane[1].flip(false, true);
		hurricane[2] = new TextureRegion(hurricaneT, 228, 306, 224, 150);
		hurricane[2].flip(false, true);
		hurricane[3] = new TextureRegion(hurricaneT, 2, 2, 224, 150);
		hurricane[3].flip(false, true);
		hurricane[4] = new TextureRegion(hurricaneT, 228, 154, 224, 150);
		hurricane[4].flip(false, true);
		hurricane[5] = new TextureRegion(hurricaneT, 454, 306, 224, 150);
		hurricane[5].flip(false, true);
		hurricane[6] = new TextureRegion(hurricaneT, 228, 2, 224, 150);
		hurricane[6].flip(false, true);
		hurricane[7] = new TextureRegion(hurricaneT, 454, 154, 224, 150);
		hurricane[7].flip(false, true);
		hurricane[8] = new TextureRegion(hurricaneT, 680, 306, 224, 150);
		hurricane[8].flip(false, true);
		hurricane[9] = new TextureRegion(hurricaneT, 454, 2, 224, 150);
		hurricane[9].flip(false, true);
		hurricane[10] = new TextureRegion(hurricaneT, 680, 154, 224, 150);
		hurricane[10].flip(false, true);

		// enemy
		// enemy 0-walk
		enemywalk[0][0] = new TextureRegion(enemy[0], 134, 248, 107, 116);
		enemywalk[0][0].flip(true, true);
		enemywalk[0][1] = new TextureRegion(enemy[0], 266, 366, 107, 116);
		enemywalk[0][1].flip(true, true);
		enemywalk[0][2] = new TextureRegion(enemy[0], 243, 248, 107, 116);
		enemywalk[0][2].flip(true, true);
		enemywalk[0][3] = new TextureRegion(enemy[0], 375, 366, 107, 116);
		enemywalk[0][3].flip(true, true);
		enemywalk[0][4] = new TextureRegion(enemy[0], 352, 248, 107, 116);
		enemywalk[0][4].flip(true, true);
		enemywalk[0][5] = new TextureRegion(enemy[0], 329, 130, 107, 116);
		enemywalk[0][5].flip(true, true);
		enemywalk[0][6] = new TextureRegion(enemy[0], 484, 366, 107, 116);
		enemywalk[0][6].flip(true, true);
		enemywalk[0][7] = new TextureRegion(enemy[0], 461, 248, 107, 116);
		enemywalk[0][7].flip(true, true);
		enemywalk[0][8] = new TextureRegion(enemy[0], 329, 12, 107, 116);
		enemywalk[0][8].flip(true, true);
		enemywalk[0][9] = new TextureRegion(enemy[0], 438, 130, 107, 116);
		enemywalk[0][9].flip(true, true);
		enemywalk[0][10] = new TextureRegion(enemy[0], 593, 366, 107, 116);
		enemywalk[0][10].flip(true, true);

		// enemy 0-attack
		enemyatt[0][0] = new TextureRegion(enemy[0], 570, 248, 107, 116);
		enemyatt[0][0].flip(true, true);
		enemyatt[0][1] = new TextureRegion(enemy[0], 438, 12, 107, 116);
		enemyatt[0][1].flip(true, true);
		enemyatt[0][2] = new TextureRegion(enemy[0], 547, 130, 107, 116);
		enemyatt[0][2].flip(true, true);
		enemyatt[0][3] = new TextureRegion(enemy[0], 702, 366, 107, 116);
		enemyatt[0][3].flip(true, true);
		enemyatt[0][4] = new TextureRegion(enemy[0], 679, 248, 107, 116);
		enemyatt[0][4].flip(true, true);
		enemyatt[0][5] = new TextureRegion(enemy[0], 547, 12, 107, 116);
		enemyatt[0][5].flip(true, true);
		enemyatt[0][6] = new TextureRegion(enemy[0], 656, 130, 107, 116);
		enemyatt[0][6].flip(true, true);
		enemyatt[0][7] = new TextureRegion(enemy[0], 811, 366, 107, 116);
		enemyatt[0][7].flip(true, true);
		enemyatt[0][8] = new TextureRegion(enemy[0], 788, 248, 107, 116);
		enemyatt[0][8].flip(true, true);
		enemyatt[0][9] = new TextureRegion(enemy[0], 656, 12, 107, 116);
		enemyatt[0][9].flip(true, true);
		enemyatt[0][10] = new TextureRegion(enemy[0], 2, 366, 107, 116);
		enemyatt[0][10].flip(true, true);
		enemyatt[0][11] = new TextureRegion(enemy[0], 2, 248, 130, 116);
		enemyatt[0][11].flip(true, true);
		enemyatt[0][12] = new TextureRegion(enemy[0], 134, 366, 130, 116);
		enemyatt[0][12].flip(true, true);

		// enemy 0-hurt
		enemyhit[0][0] = new TextureRegion(enemy[0], 2, 125, 107, 121);
		enemyhit[0][0].flip(true, true);
		enemyhit[0][1] = new TextureRegion(enemy[0], 2, 2, 107, 121);
		enemyhit[0][1].flip(true, true);
		enemyhit[0][2] = new TextureRegion(enemy[0], 111, 125, 107, 121);
		enemyhit[0][2].flip(true, true);
		enemyhit[0][3] = new TextureRegion(enemy[0], 111, 2, 107, 121);
		enemyhit[0][3].flip(true, true);
		enemyhit[0][4] = new TextureRegion(enemy[0], 220, 125, 107, 121);
		enemyhit[0][4].flip(true, true);
		enemyhit[0][5] = new TextureRegion(enemy[0], 220, 2, 107, 121);
		enemyhit[0][5].flip(true, true);

		// enemy 1-walk
		enemywalk[1][0] = new TextureRegion(enemy[1], 817, 426, 75, 80);
		enemywalk[1][0].flip(true, true);
		enemywalk[1][1] = new TextureRegion(enemy[1], 894, 426, 75, 80);
		enemywalk[1][1].flip(true, true);
		enemywalk[1][2] = new TextureRegion(enemy[1], 132, 214, 75, 80);
		enemywalk[1][2].flip(true, true);
		enemywalk[1][3] = new TextureRegion(enemy[1], 113, 132, 75, 80);
		enemywalk[1][3].flip(true, true);
		enemywalk[1][4] = new TextureRegion(enemy[1], 113, 50, 75, 80);
		enemywalk[1][4].flip(true, true);
		enemywalk[1][5] = new TextureRegion(enemy[1], 262, 344, 75, 80);
		enemywalk[1][5].flip(true, true);
		enemywalk[1][6] = new TextureRegion(enemy[1], 339, 344, 75, 80);
		enemywalk[1][6].flip(true, true);
		enemywalk[1][7] = new TextureRegion(enemy[1], 416, 344, 75, 80);
		enemywalk[1][7].flip(true, true);
		enemywalk[1][8] = new TextureRegion(enemy[1], 493, 344, 75, 80);
		enemywalk[1][8].flip(true, true);
		enemywalk[1][9] = new TextureRegion(enemy[1], 570, 344, 75, 80);
		enemywalk[1][9].flip(true, true);
		enemywalk[1][10] = new TextureRegion(enemy[1], 647, 344, 75, 80);
		enemywalk[1][10].flip(true, true);
		enemywalk[1][11] = new TextureRegion(enemy[1], 724, 344, 75, 80);
		enemywalk[1][11].flip(true, true);

		// enemy 1-hit
		enemyhit[1][0] = new TextureRegion(enemy[1], 2, 166, 109, 80);
		enemyhit[1][0].flip(true, true);
		enemyhit[1][1] = new TextureRegion(enemy[1], 132, 296, 109, 80);
		enemyhit[1][1].flip(true, true);
		enemyhit[1][2] = new TextureRegion(enemy[1], 262, 426, 109, 80);
		enemyhit[1][2].flip(true, true);
		enemyhit[1][3] = new TextureRegion(enemy[1], 2, 84, 109, 80);
		enemyhit[1][3].flip(true, true);
		enemyhit[1][4] = new TextureRegion(enemy[1], 373, 426, 109, 80);
		enemyhit[1][4].flip(true, true);
		enemyhit[1][5] = new TextureRegion(enemy[1], 2, 2, 109, 80);
		enemyhit[1][5].flip(true, true);
		enemyhit[1][6] = new TextureRegion(enemy[1], 484, 426, 109, 80);
		enemyhit[1][6].flip(true, true);
		enemyhit[1][7] = new TextureRegion(enemy[1], 595, 426, 109, 80);
		enemyhit[1][7].flip(true, true);
		enemyhit[1][8] = new TextureRegion(enemy[1], 706, 426, 109, 80);
		enemyhit[1][8].flip(true, true);

		// enemy 1-attack
		enemyatt[1][0] = new TextureRegion(enemy[1], 801, 344, 72, 80);
		enemyatt[1][0].flip(true, true);
		enemyatt[1][1] = new TextureRegion(enemy[1], 875, 344, 72, 80);
		enemyatt[1][1].flip(true, true);
		enemyatt[1][2] = new TextureRegion(enemy[1], 209, 214, 72, 80);
		enemyatt[1][2].flip(true, true);
		enemyatt[1][3] = new TextureRegion(enemy[1], 190, 132, 72, 80);
		enemyatt[1][3].flip(true, true);
		enemyatt[1][4] = new TextureRegion(enemy[1], 190, 50, 72, 80);
		enemyatt[1][4].flip(true, true);
		enemyatt[1][5] = new TextureRegion(enemy[1], 283, 262, 72, 80);
		enemyatt[1][5].flip(true, true);

		// enemy 1-stamd

		/*
		 * //enemy-2-idle Atlas = new TextureAtlas("henchidle.atlas"); enemydie[2]=new TextureRegion[9]; enemydie[2][0]=Atlas.findRegion("hencher_stance0005");
		 * enemydie[2][1]=Atlas.findRegion("hencher_stance0008"); enemydie[2][2]=Atlas.findRegion("hencher_stance0010"); enemydie[2][3]=Atlas.findRegion("hencher_stance0012");
		 * enemydie[2][4]=Atlas.findRegion("hencher_stance0015"); enemydie[2][5]=Atlas.findRegion("hencher_stance0019"); enemydie[2][6]=Atlas.findRegion("hencher_stance0022");
		 * enemydie[2][7]=Atlas.findRegion("hencher_stance0024"); enemydie[2][8]=Atlas.findRegion("hencher_stance0026");
		 */

		// enemy 2-walk
		enemywalk[2][0] = new TextureRegion(enemy[2], 330, 106, 90, 80);
		enemywalk[2][0].flip(false, true);
		enemywalk[2][1] = new TextureRegion(enemy[2], 551, 232, 90, 80);
		enemywalk[2][1].flip(false, true);
		enemywalk[2][2] = new TextureRegion(enemy[2], 879, 418, 90, 80);
		enemywalk[2][2].flip(false, true);
		enemywalk[2][3] = new TextureRegion(enemy[2], 444, 150, 90, 80);
		enemywalk[2][3].flip(false, true);
		enemywalk[2][4] = new TextureRegion(enemy[2], 536, 150, 90, 80);
		enemywalk[2][4].flip(false, true);
		enemywalk[2][5] = new TextureRegion(enemy[2], 665, 336, 90, 80);
		enemywalk[2][5].flip(false, true);
		enemywalk[2][6] = new TextureRegion(enemy[2], 757, 336, 90, 80);
		enemywalk[2][6].flip(false, true);
		enemywalk[2][7] = new TextureRegion(enemy[2], 849, 336, 90, 80);
		enemywalk[2][7].flip(false, true);
		enemywalk[2][8] = new TextureRegion(enemy[2], 223, 24, 90, 80);
		enemywalk[2][8].flip(false, true);
		enemywalk[2][9] = new TextureRegion(enemy[2], 315, 24, 90, 80);
		enemywalk[2][9].flip(false, true);
		enemywalk[2][10] = new TextureRegion(enemy[2], 422, 68, 90, 80);
		enemywalk[2][10].flip(false, true);
		enemywalk[2][11] = new TextureRegion(enemy[2], 514, 68, 90, 80);
		enemywalk[2][11].flip(false, true);
		enemywalk[2][12] = new TextureRegion(enemy[2], 658, 254, 90, 80);
		enemywalk[2][12].flip(false, true);
		enemywalk[2][13] = new TextureRegion(enemy[2], 750, 254, 90, 80);
		enemywalk[2][13].flip(false, true);

		// enemy 2-attack
		enemyatt[2][0] = new TextureRegion(enemy[2], 2, 396, 219, 102);
		enemyatt[2][0].flip(false, true);
		enemyatt[2][1] = new TextureRegion(enemy[2], 2, 292, 219, 102);
		enemyatt[2][1].flip(false, true);
		enemyatt[2][2] = new TextureRegion(enemy[2], 223, 396, 219, 102);
		enemyatt[2][2].flip(false, true);
		enemyatt[2][3] = new TextureRegion(enemy[2], 2, 188, 219, 102);
		enemyatt[2][3].flip(false, true);
		enemyatt[2][4] = new TextureRegion(enemy[2], 223, 292, 219, 102);
		enemyatt[2][4].flip(false, true);
		enemyatt[2][5] = new TextureRegion(enemy[2], 444, 396, 219, 102);
		enemyatt[2][5].flip(false, true);
		enemyatt[2][6] = new TextureRegion(enemy[2], 2, 84, 219, 102);
		enemyatt[2][6].flip(false, true);
		enemyatt[2][7] = new TextureRegion(enemy[2], 223, 188, 9219, 102);
		enemyatt[2][7].flip(false, true);

		// enemy 2-hit
		enemyhit[2][0] = new TextureRegion(enemy[2], 444, 314, 105, 80);
		enemyhit[2][0].flip(false, true);
		enemyhit[2][1] = new TextureRegion(enemy[2], 665, 418, 105, 80);
		enemyhit[2][1].flip(false, true);
		enemyhit[2][2] = new TextureRegion(enemy[2], 2, 2, 105, 80);
		enemyhit[2][2].flip(false, true);
		enemyhit[2][3] = new TextureRegion(enemy[2], 223, 106, 105, 80);
		enemyhit[2][3].flip(false, true);
		enemyhit[2][4] = new TextureRegion(enemy[2], 444, 232, 105, 80);
		enemyhit[2][4].flip(false, true);
		enemyhit[2][5] = new TextureRegion(enemy[2], 551, 314, 105, 80);
		enemyhit[2][5].flip(false, true);
		enemyhit[2][6] = new TextureRegion(enemy[2], 444, 314, 105, 80);
		enemyhit[2][6].flip(false, true);
		enemyhit[2][7] = new TextureRegion(enemy[2], 772, 418, 105, 80);
		enemyhit[2][7].flip(false, true);
		enemyhit[2][8] = new TextureRegion(enemy[2], 109, 2, 105, 80);
		enemyhit[2][8].flip(false, true);

		// enemy 3-walk
		enemywalk[3][0] = new TextureRegion(enemy[3], 620, 398, 204, 130);
		enemywalk[3][0].flip(false, true);
		enemywalk[3][1] = new TextureRegion(enemy[3], 208, 2, 204, 130);
		enemywalk[3][1].flip(false, true);
		enemywalk[3][2] = new TextureRegion(enemy[3], 414, 134, 204, 130);
		enemywalk[3][2].flip(false, true);
		enemywalk[3][3] = new TextureRegion(enemy[3], 620, 266, 204, 130);
		enemywalk[3][3].flip(false, true);
		enemywalk[3][4] = new TextureRegion(enemy[3], 414, 2, 204, 130);
		enemywalk[3][4].flip(false, true);
		enemywalk[3][5] = new TextureRegion(enemy[3], 620, 134, 204, 130);
		enemywalk[3][5].flip(false, true);

		// enemy 3-attack
		enemyatt[3][0] = new TextureRegion(enemy[3], 414, 398, 204, 130);
		enemyatt[3][0].flip(false, true);
		enemyatt[3][1] = new TextureRegion(enemy[3], 620, 530, 204, 130);
		enemyatt[3][1].flip(false, true);
		enemyatt[3][2] = new TextureRegion(enemy[3], 2, 2, 204, 130);
		enemyatt[3][2].flip(false, true);
		enemyatt[3][3] = new TextureRegion(enemy[3], 414, 398, 204, 130);
		enemyatt[3][3].flip(false, true);
		enemyatt[3][4] = new TextureRegion(enemy[3], 208, 134, 204, 130);
		enemyatt[3][4].flip(false, true);
		enemyatt[3][5] = new TextureRegion(enemy[3], 414, 266, 204, 130);
		enemyatt[3][5].flip(false, true);

		// enemy 3-hit
		enemyhit[3][0] = new TextureRegion(enemy[3], 2, 794, 204, 130);
		enemyhit[3][0].flip(false, true);
		enemyhit[3][1] = new TextureRegion(enemy[3], 2, 662, 204, 130);
		enemyhit[3][1].flip(false, true);
		enemyhit[3][2] = new TextureRegion(enemy[3], 208, 794, 204, 130);
		enemyhit[3][2].flip(false, true);

		specilaeff[0][0] = spec.findRegion("bigbotblade0");
		specilaeff[0][1] = spec.findRegion("bigbotblade1");
		specilaeff[0][2] = spec.findRegion("bigbotblade2");
		specilaeff[0][3] = spec.findRegion("bigbotblade3");

		specilaeff[1][0] = spec.findRegion("bigbot_poof0");
		specilaeff[1][1] = spec.findRegion("bigbot_poof1");
		specilaeff[1][2] = spec.findRegion("bigbot_poof2");
		specilaeff[1][3] = spec.findRegion("bigbot_poof3");
		specilaeff[1][4] = spec.findRegion("bigbot_poof4");
		specilaeff[1][5] = spec.findRegion("bigbot_poof5");
		specilaeff[1][6] = spec.findRegion("bigbot_poof6");

		specilaeff[2][0] = spec.findRegion("stormeff001");
		specilaeff[2][1] = spec.findRegion("stormeff002");

		specilaeff[3][0] = spec.findRegion("explosion0");
		specilaeff[3][1] = spec.findRegion("explosion1");
		specilaeff[3][2] = spec.findRegion("explosion2");
		specilaeff[3][3] = spec.findRegion("explosion3");

		specialeffA[0] = new Animation(0.06f, specilaeff[0]);
		specialeffA[0].setPlayMode(Animation.PlayMode.LOOP);
		specialeffA[1] = new Animation(0.12f, specilaeff[1]);
		specialeffA[1].setPlayMode(Animation.PlayMode.LOOP);
		specialeffA[2] = new Animation(0.06f, specilaeff[2]);
		specialeffA[2].setPlayMode(Animation.PlayMode.LOOP);
		specialeffA[3] = new Animation(0.15f, specilaeff[3]);
		specialeffA[3].setPlayMode(Animation.PlayMode.REVERSED);

		idle = new Animation(0.06f, ramidle);
		idle.setPlayMode(Animation.PlayMode.LOOP);

		walk = new Animation(0.04f, ramwalk);
		walk.setPlayMode(Animation.PlayMode.LOOP);

		ramhurtA = new Animation(0.15f, ramhurt);
		ramhurtA.setPlayMode(Animation.PlayMode.LOOP);

		ramkoA = new Animation(0.2f, ramko);
		ramkoA.setPlayMode(Animation.PlayMode.NORMAL);

		ramupA = new Animation(0.06f, ramup);
		ramupA.setPlayMode(Animation.PlayMode.LOOP);

		attAnim = new Animation(0.06f, att);
		attAnim.setPlayMode(Animation.PlayMode.LOOP);

		slideA = new Animation(0.06f, slide);
		slideA.setPlayMode(Animation.PlayMode.LOOP);

		hurricaneA = new Animation(0.02f, hurricane);
		hurricaneA.setPlayMode(Animation.PlayMode.LOOP);

		enemystandA[0] = new Animation(0.2f, enemywalk[0]);
		enemystandA[0].setPlayMode(Animation.PlayMode.LOOP);

		enemystandA[1] = new Animation(0.2f, enemywalk[1]);
		enemystandA[1].setPlayMode(Animation.PlayMode.LOOP);

		enemystandA[2] = new Animation(0.2f, enemywalk[2]);
		enemystandA[2].setPlayMode(Animation.PlayMode.LOOP);

		enemystandA[3] = new Animation(0.2f, enemywalk[3]);
		enemystandA[3].setPlayMode(Animation.PlayMode.LOOP);

		enemystandA[4] = new Animation(0.2f, shaderidle.getRegions());
		enemystandA[4].setPlayMode(Animation.PlayMode.LOOP);

		enemyattackA[0] = new Animation(0.2f, enemyatt[0]);
		enemyattackA[0].setPlayMode(Animation.PlayMode.LOOP);

		enemyattackA[1] = new Animation(0.2f, enemyatt[1]);
		enemyattackA[1].setPlayMode(Animation.PlayMode.LOOP);

		enemyattackA[2] = new Animation(0.2f, enemyatt[2]);
		enemyattackA[2].setPlayMode(Animation.PlayMode.LOOP);

		enemyattackA[3] = new Animation(0.2f, enemyatt[3]);
		enemyattackA[3].setPlayMode(Animation.PlayMode.LOOP);

		enemyattackA[4] = new Animation(0.2f, shaderatt.getRegions());
		enemyattackA[4].setPlayMode(Animation.PlayMode.LOOP);

		enemyhitA[0] = new Animation(0.06f, enemyhit[0]);
		enemyhitA[0].setPlayMode(Animation.PlayMode.LOOP);

		enemyhitA[1] = new Animation(0.06f, enemyhit[1]);
		enemyhitA[1].setPlayMode(Animation.PlayMode.LOOP);

		enemyhitA[2] = new Animation(0.06f, enemyhit[2]);
		enemyhitA[2].setPlayMode(Animation.PlayMode.LOOP);

		enemyhitA[3] = new Animation(0.06f, enemyhit[3]);
		enemyhitA[3].setPlayMode(Animation.PlayMode.LOOP);

		enemyhitA[4] = new Animation(0.06f, shaderhit.getRegions());
		enemyhitA[4].setPlayMode(Animation.PlayMode.LOOP);

		enemywalkA[0] = enemystandA[0];
		enemywalkA[1] = enemystandA[1];
		enemywalkA[2] = enemystandA[2];
		enemywalkA[3] = enemystandA[3];

		enemywalkA[4] = new Animation(0.02f, shaderwalk.getRegions());
		enemywalkA[4].setPlayMode(Animation.PlayMode.LOOP);

		bulletA = new Animation(0.06f, ui[4], ui[5]);
		bulletA.setPlayMode(Animation.PlayMode.LOOP);

		// font=new BitmapFont(true);
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		font.setScale(0.75f, -0.75f);

		prefs = Gdx.app.getPreferences("Ramsum");
		if (!prefs.contains("xp")) {
			prefs.putInteger("xp", 0);
		}
		System.out.println("time taken to load" + (time - System.currentTimeMillis()));

		// setXP(0);
	}

	public static float getrandom(int f) {
		return rand.nextInt(f);
	}

	public static TextureRegion getUI(int i) {
		return ui[i];
	}

	public static void setXP(int val) {
		prefs.putInteger("XP", val);
		prefs.flush();
	}

	public static int getXP() {
		return prefs.getInteger("XP");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		manager.dispose();
		ramt.dispose();
		ramwt.dispose();
		ramht.dispose();
		ramupT.dispose();
		jmpt.dispose();
		attT.dispose();
		slideT.dispose();
		hurricaneT.dispose();
		shaderidle.dispose();
		shaderatt.dispose();
		shaderhit.dispose();
		shaderwalk.dispose();
		env_Lab.dispose();
		UI.dispose();
		spec.dispose();
		for (int i = 0; i < enemy.length; i++)
			enemy[i].dispose();
		platformT.dispose();
		// specialeffT.dispose();
		levelT.dispose();
		font.dispose();
	}
}
