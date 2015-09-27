package com.beta.game;

import java.util.ArrayList;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.beta.abilities.Abilities;
import com.beta.actions.Actions;
import com.beta.ap.ApCounters;
import com.beta.chams.Cham;
import com.beta.chams.Filler;
import com.beta.equipment.Equipment;
import com.beta.shards.Shard;


public class SpriteRender {

	//_______________________________________________________________________________________________________________________________________________________________
	//initialization
	Beta beta;
	P p1;
	P p2;
	Mech mech;

	//glyph
	private GlyphLayout layout= new GlyphLayout();

	//gui flags
	boolean drawCursorSur;
	private boolean drawCursorEnd;
	boolean drawGains;
	boolean drawAbHud;
	boolean drawNoAp;
	boolean drawNotActive;
	boolean drawDeductAp;
	boolean drawActionGain;
	boolean drawDamage;
	boolean drawApHud;
	boolean drawCursorAc;
	boolean drawActionHud;
	boolean drawActionList;
	boolean drawActionCast;
	boolean oppDrawGain;
	public boolean canChangeTarget =true;
	boolean drawTarget;
	boolean drawSelectedTarget;
	boolean drawHoverAb;
	boolean drawEquipmentInfo;

	//batch 
	protected Batch batch;

	//fonts 
	protected BitmapFont mainFont;
	protected BitmapFont subFont;
	protected BitmapFont titleFont;

	//_____________________________________________________________________________________________________________________________________________
	//combat
	//Ap counter textures
	ApCounters counters;

	//cursor texture 
	Texture cursor;
	Texture cursorLeft;
	Texture apHud;
	Texture actionHud;

	//gain animation
	int gainActions=0;
	int gainFrame =0;
	int gainAniOffset=0;

	//ability stuff
	int abSlot;

	//draw ability name
	Texture abHud;
	Abilities selectedAb;
	public Abilities currAb;

	//no mp stuff
	int apFrame;

	//no activeStuff
	int notActiveFrame;

	//deduct AP stuff
	int deductApFrame;
	Abilities deductApAb;
	Actions deductApAc;
	boolean isAbil;
	int deductCost;
	char deductType;

	//deduct action stuff
	int actionsFrame;
	int actionGain;
	int actionOffset;

	//damage stuff 
	int damageFrame; 
	int damageOffset;
	int damage;
	Cham damageCham;
	ArrayList<Cham> damageChams;
	ArrayList<Integer> damageNum;

	//mode in which the game is in
	public int step;

	//splashScreen
	Texture splashScreen;
	int splashScreenFrames;
	float splashScreenFadeValue;
	boolean splashReverse;

	//insufficent funds
	int unableFrames;

	//music 
	//Sound song;

	//gain animation
	int oppGainActions=0;
	int oppGainFrame =0;
	int oppGainAniOffset=0;

	//targetstuff
	Texture targetTex;
	TextureRegion [] targetReg;
	Animation targetAni;
	Cham specTargetCham;

	//hoverAb 
	int hoverAb;
	//_____________________________________________________________________________________________________________________________________
	//menu
	//hover Menu 
	int hoverMenu=-1;

	//hover Save 
	int hoverSave =-2;

	//constanst
	private final char [] charArray= {'b','f','d','n','p','g','l','w'};
	private final char [] charArrayTab={'n','d','f','b','w','l','g','p'};
	private final int actionHudX;
	private final int actionHudY;
	private final int abHudX=(810/2)-(163/2);
	private final int apHudX;
	private final int apHudY;
	private final int apHudTextX;
	private final int apHudTextY;
	private final int apConstantX_One;
	private final int apConstantX_Two;
	private final int apConstantY;
	private final int actionTextX_One=((3*810)/8)-10;
	private final int actionTextX_Two=((5*810)/8)+10;
	private final int actionCursorX_One=((3*810)/8)-35;
	private final int actionCursorX_Two=(5*810)/8-15;

	protected SpriteRender(Batch batch,Beta beta,Mech mech){
		//initialized 
		step =0;
		this.batch=batch;
		this.mech=mech;
		counters = new ApCounters();
		this.beta=beta;

		//font
		mainFont = new BitmapFont(Gdx.files.internal("fonts/main.fnt"));
		subFont = new BitmapFont(Gdx.files.internal("fonts/sub.fnt"));
		titleFont = new BitmapFont(Gdx.files.internal("fonts/title.fnt"));
		titleFont.setColor(Color.RED);

		//cursor 
		cursor = new Texture("cursor/cursor.png");
		cursorLeft = new Texture("cursor/cursorLeft.png");

		//abHud
		abHud = new Texture("hud/abilityHud.png");

		//aphud
		apHud = new Texture("hud/apHud.png");
		this.apHudTextX=(810/2)-(this.apHud.getWidth()/2)+7;
		this.apHudTextY=(810/2)-(this.apHud.getWidth()/2)+apHud.getHeight()-8;
		this.apHudX=(810/2)-(this.apHud.getWidth()/2);
		this.apHudY=(810/2)-(this.apHud.getWidth()/2);
		this.apConstantX_One=(810/2)-(this.apHud.getWidth()/2)+7+40;
		this.apConstantX_Two=(810/2)-(this.apHud.getWidth()/2)+7+160;
		this.apConstantY=(810/2)-(this.apHud.getWidth()/2)+apHud.getHeight()-8-39;

		//splashScreen
		splashScreen = new Texture("blue.png");

		//actionHud
		actionHud = new Texture("hud/actionHud.png");
		actionHudX=  (810/2)-(actionHud.getWidth()/2);
		actionHudY = (810/2)-(actionHud.getHeight()/2);


		//damage varibles 
		damageNum = new ArrayList<Integer>();
		damageChams = new ArrayList<Cham>();

		//song = Gdx.audio.newSound(Gdx.files.internal("song/franz.mp3"));

		//animation of target
		targetTex = new Texture("target.png");
		TextureRegion[][] tmpFrames = TextureRegion.split(targetTex, 90, 90);
		int index =0;
		targetReg = new TextureRegion[2];
		for(int i=0; i<tmpFrames.length;i+=1){
			for(int j=0;j<tmpFrames[0].length;j+=1){
				targetReg[index]=tmpFrames[i][j];
				index+=1;
			}
		}
		targetAni = new Animation(1f,targetReg);

	}//end of method 
	protected void drawSprites(){

		//step 0 splash 
		//step 1 combat 
		//step 2 menu 
		//step 3 save
		//splash screen
		if(step==0){
			if(splashScreenFrames==0){
				Controls.mode=-1;
				//song.play(.25f); 
			}
			if(this.splashScreenFadeValue<.95f&&splashReverse==false){
				this.splashScreenFadeValue+=.007f;

			}
			if(this.splashScreenFadeValue>=.95f){
				this.splashReverse=true;
			}
			if(splashReverse){
				this.splashScreenFadeValue-=.007f;
			}
			this.splashScreenFrames+=1;
			batch.setColor(1.0f, 1.0f, 1.0f, this.splashScreenFadeValue);
			batch.draw(splashScreen, 0,0,1200,810);
			if(this.splashScreenFrames>270){
				step=3;
				batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
				Beta.playerTurn=1;
				Controls.mode=0;
			}
		}
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//pick save 
		if(step==3){
			if(this.hoverSave==-1){
				titleFont.setColor(Color.GREEN);
			}
			titleFont.draw(batch, "+ Create New Save", 5, 800);
			titleFont.setColor(Color.RED);
			mainFont.draw(batch, "Press Escape to Login As a Guest", 935, 25);
			for(int i =0; i<Constants.profiles.size();i+=1){
				if(this.hoverSave==i){
					titleFont.setColor(Color.GREEN);
				}
				titleFont.draw(batch, Constants.profiles.get(i).getUserName(), 50, 720-(i*80));
				titleFont.setColor(Color.RED);
			}

		}
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//main menu
		if(step==2){
			p1 = mech.getPlayer(1);
			p2 = mech.getPlayer(2);
			//main menu options
			for(int i =0; i<4; i+=1){
				if(this.hoverMenu==i){
					titleFont.setColor(Color.GREEN);
				}
				switch(i){
				case 0: titleFont.draw(batch, "Demo", 533.5f, 297.25f);
				break;
				case 1: titleFont.draw(batch, "Multiplayer", 465.5f, 230.5f);
				break;
				case 2: titleFont.draw(batch, "Actionary", 485.5f, 163.75f);
				break;
				case 3: titleFont.draw(batch, "Quit", 550.5f, 97f);
				break;
				}
				titleFont.setColor(Color.RED);
			}

			mainFont.draw(batch, "Press F12 to open options", 995, 25);

		}

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//combat
		if(step==1){

			//draw players turn
			if(Beta.playerTurn==1){
				batch.draw(cursorLeft, 839,349);
			}
			else{
				batch.draw(cursorLeft,1035,349);
			}

			//drawShard
			for(int i=0; i<2;i+=1){
				if(mech.getShardBoard()[i]!=null){
					Shard temp = mech.getShardBoard()[i];
					if(i==0){
						batch.draw(temp.getAnimation().getKeyFrame(Beta.elapsedTime,true),temp.getOffset(),360+temp.getOffset());
					}
					else{
						batch.draw(temp.getAnimation().getKeyFrame(Beta.elapsedTime,true),720+temp.getOffset(),360+temp.getOffset());
					}
				}
			}

			//draw chams
			for(int i=1;i<3;i+=1){
				for(int j=0;j<2;j+=1){
					for(int k=0; k<9;k+=1){
						if(mech.getBoard(i)[j][k]!=null&&mech.getBoard(i)[j][k] instanceof Filler == false){
							mech.getBoard(i)[j][k].getSprite().draw(batch);
						}
					}
				}
			}

			//_______________________________________________________________________________________________________________________________________
			//draw selected target 
			if(this.drawSelectedTarget){
				mainFont.setColor(Color.RED);
				layout.setText(mainFont, "Select A Target.");
				mainFont.draw(batch, layout, (810/2)-(layout.width/2),(810/2)-(layout.height/2));
				mainFont.setColor(Color.WHITE);
			}
			//_______________________________________________________________________________________________________________________________________
			//draw Action Hud 
			if(this.drawActionHud){
				batch.draw(this.actionHud, this.actionHudX,this.actionHudY);
			}
			//_______________________________________________________________________________________________________________________________________
			//drawAbHud 

			if(drawAbHud){
				batch.draw(this.abHud, this.abHudX,776);
				layout.setText(mainFont, this.selectedAb.getName());
				mainFont.draw(batch, layout, (810/2)-(layout.width/2), 803);

			}
			//______________________________________________________________________________________________________________________________________
			if(this.drawApHud){
				batch.draw(this.apHud, this.apHudX, this.apHudY);
				mainFont.draw(batch, "Action Points",this.apHudTextX ,this.apHudTextY );
				P tempP;
				if(Beta.playerTurn==1){
					tempP=mech.getPlayer(2);
				}
				else{
					tempP = mech.getPlayer(1);
				}
				for(int i=0;i<8;i+=1){
					char temp = this.charArrayTab[i];
					if(i<4){
						batch.draw(counters.getCounterTex(temp),this.apConstantX_One-25,this.apConstantY-(i*40)-20);
						subFont.draw(batch, "x"+tempP.getCounters(temp), this.apConstantX_One,this.apConstantY-(i*40));
					}
					else{
						batch.draw(counters.getCounterTex(temp),this.apConstantX_Two-25,this.apConstantY-((i-4)*40)-20);
						subFont.draw(batch, "x"+tempP.getCounters(temp), this.apConstantX_Two,this.apConstantY-((i-4)*40));
					}
				}
			}
			//_______________________________________________________________________________________________________________________________________
			//actions
			if(this.drawActionList){
				P tempP= mech.getPlayer();
				for(int i=0; i<mech.getPlayer().getActionList().size();i+=1){
					if(tempP.getActionList().get(i)!=null){
						layout.setText(subFont, "x"+tempP.getActionList().get(i).getCost()+" "+tempP.getActionList().get(i).getName());
						if(i<10){
							subFont.draw(batch, layout, this.actionTextX_One-((3*layout.width)/8), 40*i+235);
							batch.draw(counters.getCounterTex(tempP.getActionList().get(i).getCostType()), (this.actionCursorX_One-((3*layout.width)/8)),(40*i+215));
						}
						else{
							subFont.draw(batch, layout, (this.actionTextX_Two-((5*layout.width)/8)), (40*(i-10))+235);
							batch.draw(counters.getCounterTex(tempP.getActionList().get(i).getCostType()), (this.actionCursorX_Two-((5*layout.width)/8)),(40*(i-10)+215));
						}
					}
				}
			}
			//___________________________________________________________________________________________________________________________________________
			//draw damage
			if(this.drawDamage){
				if(this.damageFrame==1){
					Controls.mode=-1;
				}
				mainFont.setColor(Color.RED);
				for(int i= 0; i<this.damageChams.size();i+=1){
					this.damageCham=damageChams.get(i);
					this.damage=this.damageNum.get(i);
					if(damageCham.getPlayer()==1){
						mainFont.draw(batch, "-"+damage, this.damageCham.getPosX()*90-45, this.damageCham.getPosY()*90+this.damageOffset);
					}
					else{
						mainFont.draw(batch, "-"+damage, (this.damageCham.getPosX()+7)*90-45, this.damageCham.getPosY()*90+this.damageOffset);
					}
					if(this.damageFrame>90){
						this.damageFrame=0;
						this.damage=0;
						this.drawDamage=false;
						this.damageOffset=0;
						Beta.mech.checkDestruction(damageCham.getPlayer());
						this.damageCham=null;
						Controls.mode=0;
						this.setCurrAb(null);
						this.setDrawAbHud(false);
						this.damageChams.clear();
						this.damageNum.clear();
					}
					if(damageFrame<20){
						damageOffset+=1;
					}
					this.damageFrame+=1;
				}
				mainFont.setColor(Color.WHITE);
			}
			//_______________________________________________________________________________________________________________________________________

			//draw gains animation
			if(this.drawGains){
				subFont.setColor(Color.GREEN);
				mainFont.setColor(Color.GREEN);
				if(gainFrame==1){
					Controls.mode=-1;
				}
				//draw gains animation
				for(int i=0; i<mech.gainsType.size();i+=1){

					switch(mech.gainsType.get(i)){
					case 'b': subFont.draw(batch,"+"+this.mech.gainsAmount.get(i), 1050, 40+this.gainAniOffset);
					break;
					case 'f': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i), 1050, 80+this.gainAniOffset);
					break;
					case 'n': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i),1050,160+this.gainAniOffset);
					break;
					case 'd': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i), 1050, 120+this.gainAniOffset);
					break;
					case 'w': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i), 1160, 160+this.gainAniOffset);
					break;
					case 'l': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i), 1160, 120+this.gainAniOffset);
					break;
					case 'g': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i), 1160, 80+this.gainAniOffset);
					break;
					case 'p': subFont.draw(batch, "+"+this.mech.gainsAmount.get(i), 1160, 40+this.gainAniOffset);
					break;
					}
				}
				if(gainFrame>90){
					drawGains=false;
					gainFrame=0;
					gainAniOffset=0;
					mech.addGains();
					Controls.mode=0;
				}
				if(gainFrame<10){
					gainAniOffset+=1;
				}
				gainFrame+=1;
			}
			subFont.setColor(Color.WHITE);
			mainFont.setColor(Color.WHITE);
			//_____________________________________________________________________________________________________________________________
			//draw gains animation
			if(this.oppDrawGain){
				subFont.setColor(Color.GREEN);
				mainFont.setColor(Color.GREEN);

				if(oppGainFrame==1){
					Controls.mode=-1;
					Beta.loader.setApHud(true);
				}
				if(oppGainFrame==45){
					mech.oppAddGains();
				}
				//draw gains animation
				for(int i=0; i<mech.oppGainsType.size();i+=1){

					switch(mech.oppGainsType.get(i)){
					case 'b': subFont.draw(batch,"+"+this.mech.oppGainsAmount.get(i), (810/2)-(this.apHud.getWidth()/2)+7+15+70,(810/2)-(this.apHud.getWidth()/2)+apHud.getHeight()-8-179+20+oppGainAniOffset);
					break;
					case 'f': subFont.draw(batch, "+"+this.mech.oppGainsAmount.get(i), (810/2)-(this.apHud.getWidth()/2)+7+15+70,(810/2)-(this.apHud.getWidth()/2)+apHud.getHeight()-8-139+20+oppGainAniOffset);
					break;
					case 'n': subFont.draw(batch, "+"+this.mech.oppGainsAmount.get(i),(810/2)-(this.apHud.getWidth()/2)+7+15+70,(810/2)-(this.apHud.getWidth()/2)+apHud.getHeight()-8-59+20+oppGainAniOffset);
					break;
					case 'd': subFont.draw(batch, "+"+this.mech.oppGainsAmount.get(i), (810/2)-(this.apHud.getWidth()/2)+7+15+70,(810/2)-(this.apHud.getWidth()/2)+apHud.getHeight()-8-99+20+oppGainAniOffset);
					break;
					}
				}
				if(oppGainFrame>120){
					oppDrawGain=false;
					oppGainFrame=0;
					oppGainAniOffset=0;
					Beta.loader.setApHud(false);
					Controls.mode=0;

				}
				if(oppGainFrame<10){
					oppGainAniOffset+=1;
				}
				oppGainFrame+=1;
			}
			subFont.setColor(Color.WHITE);
			mainFont.setColor(Color.WHITE);
			//______________________________________________________________________________________________________________________________
			//draw action gain
			if(this.drawActionGain){

				if(actionGain<0){
					mainFont.setColor(Color.RED);
					if(Beta.playerTurn==1){
						mainFont.draw(batch, ""+this.actionGain, 955, 330+actionOffset);
					}
					else{
						mainFont.draw(batch, ""+this.actionGain, 1150, 330+actionOffset);
					}
				}
				if(actionGain>0){
					mainFont.setColor(Color.GREEN);
					if(Beta.playerTurn==1){
						mainFont.draw(batch, "+"+this.actionGain, 955, 330+actionOffset);
					}
					else{
						mainFont.draw(batch, "+"+this.actionGain, 1150, 330+actionOffset);
					}
				}
				if(this.actionsFrame>90){
					mech.actionGain(actionGain);
					this.drawActionGain=false;
					this.actionsFrame=0;
					this.actionGain=0;
					actionOffset=0;

				}
				if(this.actionsFrame<10){
					actionOffset+=1;
				}
				actionsFrame+=1;
				mainFont.setColor(Color.WHITE);
			}
			//_____________________________________________________________________________________________________________________________
			//deduct AP animation
			if(this.drawDeductAp){


				if(deductApFrame==1){
					Controls.mode=-1;
				}

				subFont.setColor(Color.RED);
				switch(this.deductType){
				case 'b': subFont.draw(batch,"-"+this.deductCost, 1050, 40+this.gainAniOffset);
				break;
				case 'f': subFont.draw(batch, "-"+this.deductCost, 1050, 80+this.gainAniOffset);
				break;
				case 'n': subFont.draw(batch, "-"+this.deductCost,1050,160+this.gainAniOffset);
				break;
				case 'd': subFont.draw(batch, "-"+this.deductCost, 1050, 120+this.gainAniOffset);
				break;
				case 'w': subFont.draw(batch, "-"+this.deductCost, 1160, 160+this.gainAniOffset);
				break;
				case 'l': subFont.draw(batch, "-"+this.deductCost, 1160, 120+this.gainAniOffset);
				break;
				case 'g': subFont.draw(batch, "-"+this.deductCost, 1160, 80+this.gainAniOffset);
				break;
				case 'p': subFont.draw(batch, "-"+this.deductCost, 1160, 40+this.gainAniOffset);
				break;
				}


				if(this.deductApFrame>90){
					this.drawDeductAp=false;
					deductApFrame=0;
					gainAniOffset=0;
					mech.deductAP(this.deductCost,deductType);
					Controls.mode=0;
					if(Beta.mech.gainsAmount.isEmpty()==false){
						Beta.loader.drawGains=true;
					}
				}
				if(deductApFrame<10){
					gainAniOffset+=1;
				}
				deductApFrame+=1;
				subFont.setColor(Color.WHITE);
			}

			//______________________________________________________________________________________________________________________________
			//draw no mp 
			if(this.drawNoAp){
				if(apFrame>90){
					this.drawNoAp=false;
					this.apFrame=0;
				}
				layout.setText(mainFont, "Insufficient Funds");
				mainFont.draw(batch, layout, (810/2)-(layout.width/2), 30);
				this.apFrame+=1;
			}
			//______________________________________________________________________________________________________________________________
			//draw not active
			if(this.drawNotActive){
				if(notActiveFrame>90){
					this.drawNotActive=false;
					this.notActiveFrame=0;
				}
				layout.setText(mainFont, "Champion Is Not Active");
				mainFont.draw(batch, layout, (810/2)-(layout.width/2), 405);
				this.notActiveFrame+=1;
			}
			//______________________________________________________________________________________________________________________________
			//Hover stuff
			if(drawCursorSur){
				batch.draw(cursor, 815, 39);
			}
			if (drawCursorEnd){
				batch.draw(cursor, 816,95);
			}
			if(this.drawCursorAc){
				batch.draw(cursor,820,150);
			}
			//______________________________________________________________________________________________________________________________
			//HUD 
			//p1 stuff
			mainFont.setColor(Color.RED);
			mainFont.draw(batch, "P1", 820, 370);
			mainFont.setColor(Color.WHITE);
			mainFont.draw(batch, "Actions: " + p1.getActions(), 865, 330);
			mainFont.draw(batch, "SP: " + mech.getShardBoard()[0].getActSp()+"/"+mech.getShardBoard()[0].getSp(), 860, 285);
			//p2 stuff
			mainFont.setColor(Color.GREEN);
			mainFont.draw(batch, "P2", 1014, 370);
			mainFont.setColor(Color.WHITE);
			mainFont.draw(batch, "Actions: " + p2.getActions(), 1060, 330);
			mainFont.draw(batch, "SP: " + mech.getShardBoard()[1].getActSp()+"/"+mech.getShardBoard()[1].getSp(), 1055, 285);
			//options 
			mainFont.draw(batch, "Actions", 846, 170);
			mainFont.draw(batch, "End Turn", 844, 116);
			mainFont.draw(batch, "Surrender", 840, 60);
			//draw Abilities
			mainFont.draw(batch, "Abilities", 877, 600);
			//____________________________________________________________________________________________________________________________________
			//target shard
			if(mech.getTargetShard()!=null){
				if(this.canChangeTarget){
					if(mech.getTargetShard().getPlayer()==1){
						//drawTarget
						batch.draw(targetAni.getKeyFrame(Beta.elapsedTime,true), 0, 4*90);
					}
					else{
						batch.draw(targetAni.getKeyFrame(Beta.elapsedTime,true), (8)*90, (4)*90);
					}
				}
				batch.draw(mech.getTargetShard().getAnimation().getKeyFrame(Beta.elapsedTime,true),850,680);
				subFont.draw(batch, mech.getTargetShard().getName(), 930, 802);
				subFont.draw(batch, "SP: "+mech.getTargetShard().getActSp()+"/"+mech.getTargetShard().getSp(), 818, 800);
				subFont.draw(batch, "Type: "+mech.getTargetShard().getType(), 818, 780);
				//draw gains 
				for(int i=0; i<3; i+=1){
					if(mech.getTargetShard().getGainType()[i]!='z'){
						if(i==1){
							batch.draw(counters.getCounterTex(mech.getTargetShard().getGainType()[i]),1069,620);
							subFont.draw(batch, "+"+mech.getTargetShard().getGain()[i], 1092, 639);
						}
						if(i==2){
							batch.draw(counters.getCounterTex(mech.getTargetShard().getGainType()[i]),1133,620);
							subFont.draw(batch, "+"+mech.getTargetShard().getGain()[i], 1156, 639);
						}
					}
				}
			}
			//_____________________________________________________________________________________________________________________________________
			//draw special target 
			if(this.drawTarget){
				if(this.specTargetCham.getPlayer()==1){
					batch.draw(targetAni.getKeyFrame(Beta.elapsedTime,true), (this.specTargetCham.getPosX()-1)*90, (this.specTargetCham.getPosY()-1)*90);
				}
				else{
					batch.draw(targetAni.getKeyFrame(Beta.elapsedTime,true), (this.specTargetCham.getPosX()+6)*90, (this.specTargetCham.getPosY()-1)*90);
				}
			}
			//_____________________________________________________________________________________________________________________________________
			//target Ability
			if(mech.getTargetAction()!=null){
				mainFont.draw(batch, mech.getTargetAction().getName(), 930, 802);
				subFont.draw(batch, mech.getTargetAction().getEffect(), 935, 762);
				subFont.draw(batch,"x"+mech.getTargetAction().getCost(), 1135, 801);
				batch.draw(counters.getCounterTex(mech.getTargetAction().getCostType()),1111,782);
			}
			//______________________________________________________________________________________________________________________________________
			//targeted cham.
			if(mech.getTargetCham()!=null){
				Cham tempCham= mech.getTargetCham();
				if(this.canChangeTarget){
					if(tempCham.getPlayer()==1){
						//drawTarget
						batch.draw(targetAni.getKeyFrame(Beta.elapsedTime,true), (tempCham.getPosX()-1)*90, (tempCham.getPosY()-1)*90);
					}
					else{
						batch.draw(targetAni.getKeyFrame(Beta.elapsedTime,true), (tempCham.getPosX()+6)*90, (tempCham.getPosY()-1)*90);
					}
				}
				
				//equipment hover 
				if(this.drawEquipmentInfo){
					//TODO
				}

				//use for hover of ability
				if(Beta.playerTurn==tempCham.getPlayer()&&this.drawHoverAb){
					//hover stuff 
					switch(this.hoverAb){
					case 2:
						if(tempCham.getAb()[0]!=null){ 
							batch.draw(this.cursor, 790,525); 
						}
						break;
					case 1:
						if(tempCham.getAb()[1]!=null){ 
							batch.draw(this.cursor, 790,465);
						}
						break;
					case 0: 
						if(tempCham.getAb()[2]!=null){
							batch.draw(this.cursor,790,405);
						}
						break;
					}

				}


				//draw texture of targeted cham 
				batch.draw(tempCham.getTexture(), 840+tempCham.getOffsetX(),640+tempCham.getOffsetY());

				//draw gains
				for(int i= 0; i<2; i+=1){
					if(tempCham.getTypeGain()[i]!='z'){
						if(i==0){
							batch.draw(counters.getCounterTex(tempCham.getTypeGain()[i]), 1059,620);
						}
						else{
							batch.draw(counters.getCounterTex(tempCham.getTypeGain()[i]), 1128, 620);
						}
					}
					if(tempCham.getGain()[i]>0){
						if(i==0){
							subFont.draw(batch, "+"+tempCham.getGain()[i], 1082, 639);
						}
						else{
							subFont.draw(batch, "+"+tempCham.getGain()[i], 1151, 639);
						}
					}
				}

				//draw targets name 
				mainFont.draw(batch, tempCham.getName(), 930, 802);

				//draw hp 
				subFont.draw(batch,"HP: "+tempCham.getActHp()+"/"+tempCham.getHP(), 818, 800);

				//draw targets effect
				if(tempCham.getTriggerType()!='z'){
					subFont.draw(batch, tempCham.getEffect(), 935, 762);
				}

				//draw type
				subFont.draw(batch, "Type: "+tempCham.getType(), 818, 780);

				//draw abilities
				for(int i=0;i<3;i+=1){
					if(tempCham.getAb()[i]!=null){
						switch(i){
						case 0: subFont.draw(batch, tempCham.getAb()[i].getName()+ " :", 820, 560);
						subFont.draw(batch, tempCham.getAb()[i].getEffect(), 925, 560);
						batch.draw(counters.getCounterTex(tempCham.getAb()[i].getcostType()), 820, 515);
						subFont.draw(batch, "x"+tempCham.getAb()[i].getCost(), 845, 535);
						if(tempCham.getAb()[i].getDamage()!=0)
							subFont.draw(batch, ""+tempCham.getAb()[i].getDamage(), 1150, 560);
						break;
						case 1: subFont.draw(batch, tempCham.getAb()[i].getName()+" :", 820, 500);
						subFont.draw(batch, tempCham.getAb()[i].getEffect(), 925, 500);
						batch.draw(counters.getCounterTex(tempCham.getAb()[i].getcostType()), 820, 455);
						subFont.draw(batch, "x"+tempCham.getAb()[i].getCost(), 845, 475);
						if(tempCham.getAb()[i].getDamage()!=0)
							subFont.draw(batch, ""+tempCham.getAb()[i].getDamage(), 1150, 500);
						break;
						case 2: subFont.draw(batch, tempCham.getAb()[i].getName()+" :", 820, 440);
						subFont.draw(batch, tempCham.getAb()[i].getEffect(), 925, 440);
						batch.draw(counters.getCounterTex(tempCham.getAb()[i].getcostType()), 820, 395);
						subFont.draw(batch, "x"+tempCham.getAb()[i].getCost(), 845, 415);
						if(tempCham.getAb()[i].getDamage()!=0)
							subFont.draw(batch, ""+tempCham.getAb()[i].getDamage(), 1150, 440);
						break;
						}
					}
				}

				//draw cast cost 
				subFont.draw(batch,"x"+tempCham.getCastCost(), 1135, 801);
				batch.draw(counters.getCounterTex(tempCham.getCastCostType()),1111,782);

				//draw status 
				subFont.draw(batch, "Status: ", 818, 635);

				//draw equipment
				subFont.draw(batch, "Equipment: ", 818, 655);
				if(tempCham.getEquipment()!=null){
					Equipment tempEquip = tempCham.getEquipment();
					//set font color based on rarity 
					switch(tempEquip.getRarity()){
					case 'l':subFont.setColor(Color.ORANGE);
					break;
					case 'r':subFont.setColor(Color.YELLOW);
					break;
					case 's':subFont.setColor(Color.RED);
					break;
					case 'u': subFont.setColor(Color.CYAN);
					}

					subFont.draw(batch, tempCham.getEquipment().getName(), 906, 655);
					subFont.setColor(Color.WHITE);
				}
				else{
					subFont.draw(batch, "None", 906, 655);
				}

				//draw equip 
				//subFont.draw(batch, "Equip: ", 918, 635);

			}//end of if statement 
			//____________________________________________________________________________________________________________________________________
			//draw actions points 
			mainFont.draw(batch, "Action Points", 965, 199);
			P tempP = mech.getPlayer();
			for(int i =0; i<8;i+=1){
				char temp = charArray[i];
				if(i<4){
					subFont.draw(batch, "x"+tempP.getCounters(temp), 1005, (i*40)+40);
					batch.draw(counters.getCounterTex(temp),980,(i*40)+20);
				}
				else{
					subFont.draw(batch, "x"+tempP.getCounters(temp), 1115, ((i-4)*40)+40);
					batch.draw(counters.getCounterTex(temp),1090,((i-4)*40)+20);
				}

			}

			//_______________________________________________________________________________________________________________________________________


			if(drawActionCast){
				batch.draw(this.abHud, this.abHudX,776);
				layout.setText(mainFont, mech.currAction.getName());
				mainFont.draw(batch, layout, (810/2)-(layout.width/2), 803);

			}
		}//end of step
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	}//end of draw sprites 

	//______________________________________________________________________________________________________________________________________________________
	//game mechanics 


	public void drawCursorSur(boolean b) {
		this.drawCursorSur =b;
	}
	public void drawCursorEnd(boolean b) {
		this.drawCursorEnd=b;
	}
	public void drawAbCursor(int abSlot) {
		this.abSlot=abSlot;
	}
	public void drawSelectedAbility(int abPressed) {
		if(abPressed==-1){
			this.drawAbHud=false;
			mech.targetCham=null;
			this.drawTarget(mech.abCham, false);
			return;
		}
		this.drawAbHud=true;
		selectedAb = mech.abCham.getAb()[abPressed];
		this.drawTarget(mech.abCham, true);
		this.currAb=mech.abCham.getAb()[abPressed];
	}


	public void drawNoAP() {
		this.drawNoAp=true;		
	}
	public void setCurrAb(Abilities ab) {
		this.currAb=ab;
	}
	public void setDrawAbHud(boolean b) {
		this.drawAbHud=b;
	}
	public void drawNotActive() {
		this.drawNotActive=true;
	}
	public void drawDeductAp(boolean isAbli) {
		if(isAbli){
			this.deductApAb=this.currAb;
			this.deductCost=mech.currAb.getCost();
			this.deductType=mech.currAb.getcostType();
			this.drawDeductAp=true;
		}
		else{

			this.deductCost=mech.currAction.getCost();
			this.deductType=mech.currAction.getCostType();
			this.drawDeductAp=true;

		}
	}
	public void drawActionGain() {
		this.drawActionGain=true;
	}
	public void setActionGain(int actions){
		this.actionGain=actions;
	}
	public void drawDamage() {
	
		this.drawDamage=true;
	}

	public int getActionGain(){
		return actionGain;
	}
	public void setApHud(boolean b){
		this.drawApHud= b;
	}
	public void drawCursorAc(boolean b) {

		this.drawCursorAc=b;

	}
	public void addDamageCham(Cham cham, int damage) {

		this.damageChams.add(cham);
		this.damageNum.add(damage);
	}

	public void drawActionCast(boolean b){
		if(b){
			this.drawActionCast=true;
		}
		else{
			this.drawActionCast=false;
		}
	}
	public void drawTarget(Cham cham,boolean b) {

		this.drawTarget=b;
		this.specTargetCham=cham;
		if(b)
			this.canChangeTarget=false;
		else
			this.canChangeTarget=true;
	}
	public void drawSelectTarget(boolean b){
		this.drawSelectedTarget=b;
	}
	public void drawHoverAb(int i) {
		if(i==-1){
			this.drawHoverAb=false;
			return;
		}
		this.hoverAb=i;
		this.drawHoverAb=true;

	}
	public void drawHoverMenu(int i) {

		this.hoverMenu=i;
	}
	public void drawHoverSave(int i) {

		this.hoverSave=i;

	}
	public void drawEquipmentInfo(boolean b) {

		if(mech.getTargetCham()==null||b==false){
			this.drawEquipmentInfo=false;
		}
		
		else if(mech.getTargetCham().getEquipment()!=null){
			this.drawEquipmentInfo=true;
		}

	}

}//end of class
