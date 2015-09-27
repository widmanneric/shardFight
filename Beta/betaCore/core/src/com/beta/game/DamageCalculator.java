package com.beta.game;

import com.beta.abilities.Abilities;
import com.beta.chams.Filler;

public class DamageCalculator {

	static int damage;



	public DamageCalculator() {

	}

	public static void dealDamage(int row, int col, int player, Abilities ab) {
		// TODO Auto-generated method stub


		damage=0;
		char target = Beta.mech.getBoard(player)[row][col].getCharType();
		char atk = Beta.mech.getCurAb().getcostType();
		int tempDam=Beta.mech.getCurAb().getDamage();

		//fire 
		if(atk=='f'){
			if(target=='l'){
				damage+=(tempDam/2);
			}
			if(target=='b'){
				damage-=(tempDam/2);
			}
		}
		//normal
		if(atk=='n'){
			if(target=='g'){
				damage-=(tempDam/2);
			}
		}
		//dark
		if(atk=='d'){
			if(target=='p'){
				damage+=(tempDam/2);
			}
			if(target=='w'){
				damage-=(tempDam/2);
			}
		}
		//ice
		if(atk=='b'){
			if(target=='f'){
				damage+=(tempDam/2);
			}
			if(target=='b'){
				damage-=(tempDam/2);
			}
		}
		//light 
		if(atk=='w'){
			if(target=='d'){
				damage+=(tempDam/2);
			}
			if(target=='p'){
				damage-=(tempDam/2);
			}
		}
		//lightning
		if(atk=='l'){
			if(target=='b'){
				damage+=(tempDam/2);
			}
			if(target=='g'){
				damage-=(tempDam/2);
			}
		}
		//ground
		if(atk=='g'){
			if(target=='n'){
				damage-=(tempDam/2);
			}
		}
		if(atk=='p'){
			if(target=='w'){
				damage+=(tempDam/2);
			}
			if(target=='d'){
				damage-=(tempDam/2);
			}
		}
		if(Beta.mech.getBoard(player)[row][col] instanceof Filler){
			if(player==2){
				Beta.mech.getShardBoard()[1].setActSp(Beta.mech.getShardBoard()[1].getActSp()-(tempDam/10));
			}
			else{
				Beta.mech.getShardBoard()[0].setActSp(Beta.mech.getShardBoard()[0].getActSp()-(tempDam/10));
			}
			Beta.loader.addDamageCham(Beta.mech.getBoard(player)[row][col], tempDam/10);
		}
		else{
			damage+=tempDam;
			Beta.mech.getBoard(player)[row][col].setActHp(Beta.mech.getBoard(player)[row][col].getActHp()-damage);
			Beta.loader.addDamageCham(Beta.mech.getBoard(player)[row][col],damage);
		}
	}//end of method
}//end of class
