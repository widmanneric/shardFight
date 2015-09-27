package com.beta.abilities;

import com.beta.chams.Cham;
import com.beta.chams.RatKing;
import com.beta.game.Beta;

public class Multiply extends Abilities {

	public Multiply() {
		super.setStats("Multiply", 0, 'd', 400, "Summon an additional copy\nof Rat King.", 'n',false);
	}

	public void activate(){

		boolean breakNow=false;
		for(int i=0;i<2;i+=1){
			for(int j=0;j<9;j+=1){
				if(Beta.mech.getBoard(Beta.playerTurn)[i][j]==null){
					Cham temp = new RatKing(Beta.playerTurn,null);
					Beta.mech.getBoard(Beta.playerTurn)[i][j]=temp;
					temp.setPosition(i+1, j+1);
					breakNow=true;
					break;
				}
			}
			if(breakNow){
				break;
			}
		}

		int counter=0;
		for(int i=0;i<2;i+=1){
			for(int j=0;j<9;j+=1){
				if(Beta.mech.getBoard(Beta.playerTurn)[i][j]instanceof RatKing){
					counter+=1;
				}
			}
		}
		Beta.mech.setGain('d', counter*50);
	}
}
