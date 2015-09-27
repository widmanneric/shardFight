package com.beta.equipment;

import com.beta.game.Constants;


public class Equipment {


	private char rarity;
	private String effect;
	private String name;
	private char triggerType;
	private int id;
	
	public void setStats(char rarity, String effect, String name,char trigger, int id){
		this.rarity=rarity;
		this.effect=effect;
		this.name=name;
		this.setTriggerType(trigger);
		this.id=id;
		
		if(!Constants.equipId.containsKey(id)){
			Constants.equipId.put(id, this.getClass());
		}
	}//end of stats 
	
	public char getRarity(){
		return rarity;
	}
	public String getEffect(){
		return effect;
	}
	public String getName(){
		return name;
	}

	public char getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(char triggerType) {
		this.triggerType = triggerType;
	}

	public int getId() {
		return id;
	}


}
