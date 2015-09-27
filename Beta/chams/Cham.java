package com.beta.chams;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.beta.abilities.Abilities;
import com.beta.equipment.Equipment;



public class Cham {


	//variables
	private int spriteOffsetX;
	private int spriteOffsetY;
	private int castCost;
	private int player;
	transient private Sprite sprite;
	transient private TextureRegion txt;
	private char castCostType;
	private int posX;
	private int posY;
	private int offsetX;
	private int offsetY;
	private String name;
	private String effect; 
	private String type;
	private Abilities [] abil;
	private int hp;
	private int actHp;
	private char [] typeGain;
	private int [] gain;
	private char charType;
	private boolean active;
	private char triggerType;
	private boolean isDestroyed;
	private Equipment currEquip;
		



	public Cham(){

	}
	protected void setStats(int player,String name,String effect, String type,char charType,char triggerType, int offsetX, int offsetY,
			Abilities [] abil, int hp,char [] typeGain, int [] gain,int castCost,char castCostType,int spriteOffsetX,int spriteOffsetY,Equipment equip){
		this.player= player;  
		this.name=name;
		this.offsetX=offsetX;
		this.offsetY=offsetY;
		this.effect = effect;
		this.abil = abil;
		this.hp=hp;
		this.type=type;
		actHp=hp;
		this.typeGain=typeGain;
		this.gain=gain;
		this.charType=charType;
		this.castCost=castCost;
		this.castCostType=castCostType;
		this.triggerType=triggerType;
		this.spriteOffsetX=spriteOffsetX;
		this.spriteOffsetY=spriteOffsetY;
		this.currEquip=equip;
	

		
	}//end of method 

	public void setEquip(Equipment equip){
		this.currEquip=equip;
	}
	public Equipment getEquipment(){
		return this.currEquip;
	}
	public int getPlayer(){
		return player; 
	}

	public Sprite getSprite() {
		return sprite; 
	}

	public int getPosX(){
		return posX;
	}

	public int getPosY(){
		return posY;
	}

	public int getOffsetX(){
		return offsetX;
	}

	public int getOffsetY(){
		return offsetY;
	}
	public String getName(){
		return name;
	}
	public String getEffect(){
		return effect;
	}
	public String getType(){
		return type;
	}
	public TextureRegion getTexture(){
		return txt;
	}
	public Abilities [] getAb(){
		return abil;
	}
	public int getHP(){
		return hp;
	}
	public int getActHp(){
		return actHp;
	}
	public void setActHp(int hp){
		actHp=hp;
	}
	public char[] getTypeGain() {
		return typeGain;
	}
	public void setTypeGain(char[] typeGain) {
		this.typeGain = typeGain;
	}
	public int[] getGain() {
		return gain;
	}
	public void setGain(int[] gain) {
		this.gain = gain;
	}
	public char getCharType() {
		return charType;
	}

	public void setCharType(char charType) {
		this.charType = charType;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
		if(active){
			this.sprite.setColor(1.0f, 1.0f, 1.0f, 1.0f);		
		}
		else{
			this.sprite.setColor(.5f, .5f, .5f, 1.0f);
		}
	}
	public char getTriggerType(){
		return triggerType;
	}

	public void activate() {
		// TODO Auto-generated method stub

	}
	public void setPosition(int row, int col){
		this.posX=row;
		this.posY=col;
		if(this instanceof Filler==false){
			if(this.player==1){
				sprite.setPosition(((posX-1)*90)+this.spriteOffsetX,((posY-1)*90)+this.spriteOffsetY);
			}
			else{
				sprite.setPosition(((posX+6)*90)+this.spriteOffsetX,((posY-1)*90)+this.spriteOffsetY);
			}
		}
	}
	public int getCastCost() {
		// TODO Auto-generated method stub
		return this.castCost;
	}
	public char getCastCostType() {
		// TODO Auto-generated method stub
		return this.castCostType;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
	public void setDestoryed(boolean isDestoryed) {
		this.isDestroyed = isDestoryed;
	}
	public void setTriggerType(char c) {
		// TODO Auto-generated method stub
		this.triggerType=c;
	}
	public void targetsConfirmed() {
		// TODO Auto-generated method stub

	}
	private Sprite createSprite(TextureRegion txt,int player){
		TextureRegion txtReg;
		if(player==1){
			txtReg = new TextureRegion(txt,0,0,90,90);
			sprite= new Sprite(txtReg);
			sprite.setSize(90f, 90f);
		}
		else{
			txtReg = new TextureRegion(txt,90,0,90,90);
			sprite= new Sprite(txtReg);
			sprite.setSize(90f, 90f);
		}
		return sprite;

	}
	private TextureRegion createReg(TextureRegion txt){
		return new TextureRegion(txt,0,0,90,90);
	}

	public void createGraphics(TextureRegion txt){

		this.txt=this.createReg(txt);
		this.sprite=this.createSprite(txt, player);
		if(this instanceof Filler==false){
			this.setActive(false);
		}
	}
	public void setSprite(Sprite sprite) {
		this.sprite=sprite;
		
	}
	public void setTxtReg(TextureRegion txtReg) {
		this.txt=txtReg;
		
	}




}//end of class 
