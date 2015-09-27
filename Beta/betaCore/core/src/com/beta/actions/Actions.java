package com.beta.actions;


import com.beta.chams.Cham;
import com.beta.equipment.Equipment;
import com.beta.game.Constants;


public class Actions{


	private boolean isCham;
	private Cham newCham;
	private String name;
	private char costType;
	private int cost;
	private char targetType;
	private int player;
	private String effect;
	private boolean dontTarget;
	private Equipment equip;
	private int id;


	public void activate(){

	}


	public void setStats(boolean isCham,String name,Cham newCham,char costType,int player,int cost, char targetType,Equipment equip, int id){
		this.isCham=isCham;
		this.name=name;
		this.newCham=newCham;
		this.costType=costType;
		this.player=player;
		this.cost=cost;
		this.targetType=targetType;
		this.equip=equip;
		this.id=id;

		//populate id 
		if(!Constants.actionId.containsKey(id)){
			Constants.actionId.put(id, this.getClass());
		}

	}
	public Equipment getEquip(){
		return equip;
	}
	public String getEffect(){
		return effect;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public int getCost() {
		// TODO Auto-generated method stub
		return cost;
	}

	public char getCostType() {
		// TODO Auto-generated method stub
		return costType;
	}

	public Cham getNewCham() {
		// TODO Auto-generated method stub
		return newCham;
	}

	public char getTargetType() {
		// TODO Auto-generated method stub
		return targetType;
	}

	public boolean isCham() {
		// TODO Auto-generated method stub
		return isCham;
	}


	public boolean dontTarget(){
		return dontTarget;
	}
	public int getPlayer(){
		return player;
	}

	public void setDontTarget(boolean b) {
		this.dontTarget=b;

	}

	public void setEffect(String string) {
		// TODO Auto-generated method stub
		this.effect=string;

	}
	public Cham createNewCham() {

		return null;
	}


	public int getId() {
		return id;
	}

}
