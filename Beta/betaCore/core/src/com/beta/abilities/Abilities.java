package com.beta.abilities;




import com.beta.game.DamageCalculator;

public class Abilities{

	private int damage;
	private int cost;
	private char costType;
	private String effect;
	private String name;
	private char targetType;
	private boolean canShard;
	private boolean isMultiTarget;
	public void setStats(String name,int damage, char costType, int cost, String effect,char targetType,boolean canShard) {
		this.damage=damage;
		this.costType=costType;
		this.effect=effect;
		this.name=name;
		this.cost=cost;
		this.targetType=targetType;
		this.canShard=canShard;
	}//end of method 
	public int getDamage(){
		return damage;
	}
	public int getCost(){
		return cost;
	}
	public char getcostType(){
		return costType;
	}
	public String getEffect(){
		return effect;
	}
	public String getName(){
		return name;
	}
	public char getTargetType() {
		return targetType;
	}
	public void activate(int row, int col, int player) {
		DamageCalculator.dealDamage(row, col, player, this);
	}
	public void canShard(boolean b){
		this.canShard=b;
	}
	public boolean getCanShard(){
		return this.canShard;
	}
	public void targetsConfirmed(){

	}
	public boolean getIsMultiTarget() {
		// TODO Auto-generated method stub
		return this.isMultiTarget;
	}
	public void reset(){

	}
	public void activate() {

	}
	public void setMultiTarget(boolean b) {
		this.isMultiTarget=b;
	}
}//end of class 
