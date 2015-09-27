package com.beta.game;

import java.util.ArrayList;






























import com.badlogic.gdx.Gdx;
import com.beta.abilities.Abilities;
import com.beta.actions.Actions;
import com.beta.chams.Cham;
import com.beta.chams.Filler;
import com.beta.shards.Shard;
import com.beta.shards.basicBlackShard;
import com.beta.shards.basicBlueShard;

public class Mech {



	//initialization
	P p1;
	P p2;
	int abPressed;
	char targetType;
	DamageCalculator calc;

	//target variables 
	int clickX;
	int clickY;
	int clickPlayer;

	//curraction
	public Actions currAction;


	//board 
	Cham [] [] boardOne;
	Cham [] [] boardTwo;
	Shard [] shardBoard;

	//targets

	//menu drawing 
	Cham targetCham;
	Shard targetShard;
	public Abilities currAb;


	//gains
	ArrayList<Character> gainsType = new ArrayList<Character>();
	ArrayList<Integer> gainsAmount = new ArrayList<Integer>();

	ArrayList<Character> oppGainsType = new ArrayList<Character>();
	ArrayList<Integer> oppGainsAmount = new ArrayList<Integer>();


	//misc
	public ArrayList<Cham>targets = new ArrayList<Cham>();
	public char legalTargetType;
	public int numberOfTargets;
	public Cham currCham;
	public Cham abCham;
	public Actions targetAction;





	public Mech(){
		//initializations 
		boardOne = new Cham [2][9];
		boardTwo = new Cham [2][9];
		shardBoard = new Shard[2];



		p1 = new P();
		p1.setPlayer(1);
		//Demo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		p2 = Constants.cpuProfiles.get(0).getP();
		p2.setPlayer(2);
		p2.populateActionList(0);
		
//		p2.addAction(new AcBloodMoonImp(2, null));
//		p2.addAction(new AcDarkPuddle(2,null));
//		p2.addAction(new AcNormalStump(2,null));
//		p2.addAction(new AcDarkStump(2,null));
//		p2.addAction(new AcRebootRobot(2,null));
//		p2.addAction(new AcShadowMage(2,null));
//		p2.addAction(new AcDarkRamp(2,null));
//		p2.addAction(new AcWitch(2,null));
//		p2.addAction(new AcNormalRamp(2,null));
//		p2.addAction(new AcDarkAvian(2,new FirmRing()));
//		p2.addAction(new AcGolem(2,null));
//		p2.addAction(new AcDarkRoot(2,null));
//		p2.addAction(new AcDeathBringer(2,null));
//		p2.saveCurrList();
//		Profile tmp = new Profile(p2,"Dark1");
//		tmp.saveCPU();
		
		
		





		clickX=-1;
		clickY=-1;
		clickPlayer=-1;




	}
	public void setTarget(int row, int col,int player){
		//untarget shard
		this.setTargetShard(-1);
		this.targetAction=null;
		if(this.getBoard(player)[row][col]!=null&&this.getBoard(player)[row][col] instanceof Filler ==false){
			targetCham= this.getBoard(player)[row][col] ;

		}
	}

	public void abPressed(int abPressed) {

		if(abPressed==-1||targetCham==null){
			Beta.loader.drawSelectedAbility(-1);
			return;
		}

		this.abPressed=abPressed;
		abCham=targetCham;

		if(abCham.isActive()==false){
			Beta.loader.drawNotActive();
			return;
		}
		if(Beta.playerTurn==abCham.getPlayer()&&checkCost(abCham.getAb()[abPressed].getCost(),abCham.getAb()[abPressed].getcostType())){

			this.currAb=abCham.getAb()[abPressed];
			if(currAb.getTargetType()=='n'){
				Beta.loader.setDrawAbHud(false);
				Beta.loader.drawDeductAp(true);
				currAb.activate();
				Controls.mode=0;
				Beta.loader.setCurrAb(null);
				abCham.setActive(false);
				this.checkTrigger('a');
				this.currAb=null;
				return;
			}
			if(currAb.getIsMultiTarget()==false){
				Controls.mode=1;
				targetType=abCham.getAb()[abPressed].getTargetType();
			}
			else{
				Controls.mode=5;
				currAb.activate();
				Beta.loader.drawTarget(abCham, true);
			}
			Beta.loader.drawSelectedAbility(abPressed);
			Beta.loader.drawSelectTarget(true);
		}

	}
	public void activateAb(int row, int col, int player) {

		if(this.getBoard(player)[row][col]!=null){
			if(this.getBoard(player)[row][col].getTriggerType()=='i'){
				return;
			}
			if(this.getBoard(player)[row][col] instanceof Filler&&this.currAb.getDamage()>0){
				Controls.mode=2;
				if(this.currAb.getCanShard()){
					Beta.loader.drawDeductAp(true);
					currAb.activate(row,col,player);
					if(Beta.loader.damageChams.isEmpty()==false){
						Beta.loader.drawDamage();
					}
					else{
						Controls.mode=0;
						Beta.loader.setCurrAb(null);
						Beta.loader.setDrawAbHud(false);
					}
					abCham.setActive(false);
					this.checkTrigger('a');
					this.currAb=null;
					Beta.loader.drawTarget(abCham, false);
					this.abCham=null;
					Beta.loader.drawSelectTarget(false);
					return;
				}
				else
					return;

			}
			Beta.loader.setDrawAbHud(false);
			Beta.loader.drawDeductAp(true);
			currAb.activate(row,col,player);
			if(Beta.loader.damageChams.isEmpty()==false){
				Beta.loader.drawDamage();
			}
			else{
				Controls.mode=0;
				Beta.loader.setCurrAb(null);
			}
			abCham.setActive(false);
			this.checkTrigger('a');
			Beta.loader.drawTarget(abCham, false);
			this.abCham=null;
			this.currAb=null;
			Beta.loader.drawSelectTarget(false);
			if(targetCham.getActHp()<=0||targetCham.isDestroyed()){
				this.targetCham=null;
			}

		}
	}


	public void drawCursorSur(boolean b) {
		Beta.loader.drawCursorSur(b);
	}
	public void drawCursorEnd(boolean b) {
		Beta.loader.drawCursorEnd(b);	
	}
	public char getTargetType() {
		return targetType;
	}
	private boolean checkCost(int counters,char type) {
		if(this.getPlayer().getCounters(type)-counters>=0){
			return true;	
		}
		Beta.loader.drawNoAP();
		return false;
	}
	void deductAP(int cost,char type) {

		this.getPlayer().setCounters(type,this.getPlayer().getCounters(type)-cost);
	}
	protected void actionGain(int actions) {

		this.getPlayer().setActions(this.getPlayer().getActions()+actions);
	}
	protected void addShard(Shard shard) {
		if(shard.getPlayer()==1){
			shardBoard[0]=shard;
			Filler temp = new Filler(1);
			temp.setPosition(1, 5);
			this.addCham(temp);
		}
		else{
			shardBoard[1]=shard;
			Filler temp = new Filler(2);
			temp.setPosition(2, 5);
			this.addCham(temp);
		}
	}
	public void addCham(Cham cham){
		if(cham.getPlayer()==1){
			if(boardOne[cham.getPosX()-1][cham.getPosY()-1]==null){
				boardOne[cham.getPosX()-1][cham.getPosY()-1]=cham;
			}
		}
		else{
			if(boardTwo[cham.getPosX()-1][cham.getPosY()-1]==null){
				boardTwo[cham.getPosX()-1][cham.getPosY()-1]=cham;
			}
		}
	}
	public void setTargetShard(int shard) {
		if(shard==-1){
			targetShard=null;
			return;
		}
		targetShard=shardBoard[shard];
		targetCham=null;
		targetAction=null;
	}
	public Cham [][] getBoard(int i){
		if(i==1){
			return this.boardOne;
		}
		else{
			return this.boardTwo;
		}
	}
	public void resolveStartTurn(int player) {
		Beta.loader.gainActions=0;
		Beta.playerTurn=player;
		targetCham=null;
		this.drawCursorEnd(false);
		this.setTargetShard(player-1);
		Beta.loader.gainActions+=1;
		//iterate through champs and shards add gains 
		for(int i =0; i<this.getBoard(player).length;i+=1){
			for(int j=0; j<this.getBoard(player)[0].length;j+=1){
				if(this.getBoard(player)[i][j]!=null&&this.getBoard(player)[i][j] instanceof Filler==false){
					this.getBoard(player)[i][j].setActive(true);
					for(int z =0;z<this.getBoard(player)[i][j].getTypeGain().length;z+=1){
						if(this.getBoard(player)[i][j].getTypeGain()[z]!='z'){
							//for animation
							if(!gainsType.contains(this.getBoard(player)[i][j].getTypeGain()[z])){
								gainsType.add(this.getBoard(player)[i][j].getTypeGain()[z]);
								gainsAmount.add(this.getBoard(player)[i][j].getGain()[z]);
							}
							else{
								for(int k =0; k<gainsType.size();k+=1){
									if(gainsType.get(k)==this.getBoard(player)[i][j].getTypeGain()[z]){
										gainsAmount.set(k, gainsAmount.get(k)+this.getBoard(player)[i][j].getGain()[z]);
									}
								}
							}
						}
					}
				}
			}
		}
		for(int i =0; i<3;i+=1){
			if(shardBoard[player-1].getGainType()[i]!='z'){
				//for animation
				if(!gainsType.contains(shardBoard[player-1].getGainType()[i])){
					gainsType.add(shardBoard[player-1].getGainType()[i]);
					gainsAmount.add(shardBoard[player-1].getGain()[i]);
				}
				else{
					for(int k =0; k<gainsType.size();k+=1){
						if(gainsType.get(k)==shardBoard[player-1].getGainType()[i]){
							gainsAmount.set(k, gainsAmount.get(k)+shardBoard[player-1].getGain()[i]);
						}
					}
				}
			}
		}
		for(int i=0; i<2;i+=1){
			for(int j =0; j<9;j+=1){
				if(this.getBoard(player-1)[i][j]!=null&&this.getBoard(player-1)[i][j] instanceof Filler==false){
					this.getBoard(player-1)[i][j].setActive(false);
				}
			}
		}


		Beta.loader.drawGains=true;
		Beta.loader.setActionGain(1);
		Beta.loader.drawActionGain();
	}
	public Cham getTargetCham() {
		// TODO Auto-generated method stub
		return this.targetCham;
	}
	public Shard getTargetShard() {
		// TODO Auto-generated method stub
		return this.targetShard;
	}
	public Shard[] getShardBoard() {
		// TODO Auto-generated method stub
		return shardBoard;
	}
	public Abilities getCurAb() {
		// TODO Auto-generated method stub
		return currAb;
	}
	public P getPlayer(int p){
		switch(p){
		case 1: return this.p1;
		case 2: return this.p2;
		}
		return p1;
	}
	public void addGains(){
		for(int i =0 ; i<gainsType.size();i+=1){
			this.getPlayer().addGains(gainsType.get(i), gainsAmount.get(i));
		}
		this.gainsAmount.clear();
		this.gainsType.clear();
	}
	public void checkDestruction(int player) {
		//iterate through board one 
		for(int i=0;i<2;i+=1){
			for(int j=0; j<9;j+=1){
				if(this.getBoard(player)[i][j]!=null&&this.getBoard(player)[i][j] instanceof Filler == false&&this.getBoard(player)[i][j].getActHp()<=0){
					this.destroy(player,i,j);
				}
				if(this.getBoard(player)[i][j] instanceof Filler&&this.getShardBoard()[player-1].getActSp()<=0){
					System.out.println("win condition");
				}
			}
		}
	}
	public void setGain(char type, int amount){
		if(!(gainsType.contains(type))){
			gainsType.add(type);
			gainsAmount.add(amount);
		}
		else{
			for(int i =0; i < this.gainsType.size();i+=1){
				if(gainsType.get(i)==type){
					gainsAmount.set(i, gainsAmount.get(i)+amount);
				}
			}
		}
	}
	public void checkTrigger(char c) {
		// TODO Auto-generated method stub
		//d trigger
		if(c=='d'){
			for(int k=1; k<3;k+=1){
				//System.out.println(k);
				for(int i=0; i<2; i+=1){
					for(int j=0; j<9;j+=1){
						if(this.getBoard(k)[i][j]!=null&&this.getBoard(k)[i][j].getTriggerType()==c){
							if(this.getBoard(k)[i][j].isDestroyed()){
								this.getBoard(k)[i][j].activate();
							}
						}
					}
				}
			}
		}
		else{
			for(int i=0; i<2; i+=1){
				for(int j=0; j<9;j+=1){
					if(this.getBoard(Beta.playerTurn)[i][j]!=null&&this.getBoard(Beta.playerTurn)[i][j].getTriggerType()==c){
						this.getBoard(Beta.playerTurn)[i][j].activate();
					}
				}
			}
		}
		if(Beta.loader.actionGain!=0){
			Beta.loader.drawActionGain();
		}
	}
	public void drawCursorAc(boolean b) {
		// TODO Auto-generated method stub
		Beta.loader.drawCursorAc(b);
	}

	public void destroy(int player, int row, int col){
		this.getBoard(player)[row][col].setDestoryed(true);
		this.checkTrigger('d');
		this.getBoard(player)[row][col]=null;
		if(Beta.mech.oppGainsAmount.isEmpty()==false){
			Beta.loader.oppDrawGain=true;
		}
	}
	public void destroy(Cham cham) {
		// TODO Auto-generated method stub
		cham.setDestoryed(true);
		this.checkTrigger('d');
		cham=null;
	}
	public void setTargetCham(Cham newCham) {
		// TODO Auto-generated method stub
		if(newCham!=null){
			targetCham=newCham;
			this.targetAction=null;
			targetShard=null;
		}
	}
	public void activateAction(Actions action) {

		if(this.checkCost(action.getCost(), action.getCostType())&&this.getPlayer().getActions()-1>=0){
			if(action.dontTarget()){
				Beta.loader.drawActionHud=false;
				Beta.loader.drawActionList=false;
				currAction=action;
				Beta.loader.setActionGain(-1);
				action.activate();
				Controls.mode=0;
				Beta.loader.drawDeductAp(false);
				Beta.loader.drawActionGain();
				Beta.loader.drawActionCast(false);
				this.checkTrigger('a');
				this.currAction=null;
				return;
			}
			Controls.mode=4;
			Beta.loader.drawActionHud=false;
			Beta.loader.drawActionList=false;
			action.activate();
			Beta.loader.drawActionCast(true);
			currAction=action;
		}
		else{
			Beta.loader.drawNoAP();;
		}


	}


	P getPlayer() {
		// TODO Auto-generated method stub
		if(Beta.playerTurn==1){
			return p1;
		}
		else{
			return p2;
		}
	}
	public void setLastClick(int x, int y, int player){
		this.clickX=x;
		this.clickY=y;
		this.clickPlayer=player;


		if(Controls.mode==4){
			checkLegalActionTarget();
			return;
		}

		if(this.getBoard(clickPlayer)[clickX][clickY]!=null){
			if(this.getBoard(clickPlayer)[clickX][clickY].getTriggerType()!='i'){
				if(Controls.mode==5){

					if(clickPlayer==Beta.playerTurn&&(this.legalTargetType=='a'||this.legalTargetType=='b')){
						if(this.numberOfTargets>0&&this.getBoard(clickPlayer)[clickX][clickY] instanceof Filler ==false){
							this.targets.add(this.getBoard(clickPlayer)[clickX][clickY]);
							this.numberOfTargets-=1;
						}
					}
					else if(clickPlayer!=Beta.playerTurn&&(this.legalTargetType=='e'||this.legalTargetType=='b')){
						if(this.numberOfTargets>0){

							this.targets.add(this.getBoard(clickPlayer)[clickX][clickY]);
							this.numberOfTargets-=1;
						}
					}
					if(this.numberOfTargets==0){
						this.currAb.targetsConfirmed();
					}
				}

				if(Controls.mode==6){
					if(clickPlayer==Beta.playerTurn&&(this.legalTargetType=='a'||this.legalTargetType=='b')){
						if(this.numberOfTargets>0&&this.getBoard(clickPlayer)[clickX][clickY] instanceof Filler ==false){
							this.targets.add(this.getBoard(clickPlayer)[clickX][clickY]);
							this.numberOfTargets-=1;

						}
					}
					else if(clickPlayer!=Beta.playerTurn&&(this.legalTargetType=='e'||this.legalTargetType=='b')){
						if(this.numberOfTargets>0&&this.getBoard(clickPlayer)[clickX][clickY] instanceof Filler ==false){

							this.targets.add(this.getBoard(clickPlayer)[clickX][clickY]);
							this.numberOfTargets-=1;
						}
					}
					if(this.numberOfTargets==0){
						this.currCham.targetsConfirmed();
						Controls.mode=0;
						Beta.loader.drawDeductAp(false);
						Beta.loader.setActionGain(-1);
						Beta.loader.drawActionGain();
						Beta.loader.drawActionCast(false);
						this.checkTrigger('a');
						this.currAction=null;
					}
				}
			}
		}
	}
	private void checkLegalActionTarget() {
		if((this.currAction.getTargetType()=='a'||this.currAction.getTargetType()=='b')&&Beta.playerTurn==this.clickPlayer){
			if(currAction.isCham()&&this.getBoard(this.clickPlayer)[clickX][clickY]==null){
				Cham temp = this.currAction.createNewCham();
				this.getBoard(this.clickPlayer)[this.clickX][this.clickY]=temp;
				temp.setPosition(this.clickX+1, this.clickY+1);
				if(this.getBoard(this.clickPlayer)[this.clickX][this.clickY].getTriggerType()=='n'){
					this.getBoard(this.clickPlayer)[this.clickX][this.clickY].activate();
				}
				else{
					Controls.mode=0;
					Beta.loader.drawDeductAp(false);
					Beta.loader.setActionGain(-1);
					Beta.loader.drawActionGain();
					Beta.loader.drawActionCast(false);
					this.checkTrigger('a');
					this.currAction=null;
				}

			}
		}
		this.clickPlayer=-1;
		this.clickX=-1;
		this.clickY=-1;
	}
	public void oppAddGains() {
		// TODO Auto-generated method stub
		for(int i =0 ; i<oppGainsType.size();i+=1){
			if(Beta.playerTurn==1){
				p2.addGains(oppGainsType.get(i), oppGainsAmount.get(i));
			}
			if(Beta.playerTurn==2){
				p1.addGains(oppGainsType.get(i), oppGainsAmount.get(i));
			}
		}
		this.oppGainsAmount.clear();
		this.oppGainsType.clear();
	}
	public void oppSetGain(char type, int amount) {
		// TODO Auto-generated method stub
		if(!(oppGainsType.contains(type))){
			oppGainsType.add(type);
			oppGainsAmount.add(amount);
		}
		else{
			for(int i =0; i < this.oppGainsType.size();i+=1){
				if(oppGainsType.get(i)==type){
					oppGainsAmount.set(i, oppGainsAmount.get(i)+amount);
				}
			}
		}
	}
	public void setTargetAction(Actions action) {
		this.targetAction=action;
		this.targetShard=null;
		this.targetCham=null;
	}

	public Actions getTargetAction(){
		return targetAction;
	}
	public void activateMenuButton() {

		switch(Beta.loader.hoverMenu){
		case 0: 
			loadCombat();
			Beta.loader.step=1;
			break;
		case 3: Gdx.app.exit();
		}

	}
	public void loadCombat(){
		//load combat
		//_______________________________________________________________________________________________________________________________
		//load map
		Beta.map.loadRandomMap();
		//load hud 
		Beta.map.loadMainHud();
		//load shards 
		addShard(new basicBlueShard(1));
		addShard(new basicBlackShard(2));
		resolveStartTurn(1);
	}
	public void loadSave() {
		int tmpSave = Beta.loader.hoverSave;
		if(tmpSave>=0){
			p1=Constants.profiles.get(tmpSave).getP();
			p1.setPlayer(1);
		
			Beta.loader.step=2;
			System.out.println("User "+Constants.profiles.get(tmpSave).getUserName()+" logged in succesfully!\n");
			p1.populateActionList(0);
		}

	}



}//end of class
