package com.beta.chams;


import com.beta.abilities.Abilities;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class Golem extends Cham {


	private String effect;
	@SuppressWarnings("unused")
	private final int id =10;
	
	public Golem(int player,Equipment equip) {

		effect="When this champion is destroyed,\nsummon a Flame Golem and\na Shadow Golem.";

		Abilities [] ab = {null,null, null};
		char [] typeGain={'g','n'};
		int [] gain={100,300};
		super.setStats(player, "Golem", effect, "Normal", 'n', 'd', -20, 25, ab, 830, typeGain, gain,700,'n',0,0,equip);
		super.createGraphics(TxtLoader.chams[5]);
	}

	public void activate(){
		int counter =0;
		for(int i =0; i<2;i+=1){
			for(int j=0; j<9;j+=1){
				if(Beta.mech.getBoard(super.getPlayer())[i][j]==null){
					if(counter==0){
						Cham newCham = new FlameGolem(super.getPlayer(),null);
						newCham.setPosition(i+1, j+1);
						Beta.mech.getBoard(super.getPlayer())[i][j]= newCham;
						counter+=1;
						continue;
					}
					else{
						Cham newCham = new ShadowGolem(super.getPlayer(),null);
						newCham.setPosition(i+1, j+1);
						Beta.mech.getBoard(super.getPlayer())[i][j]= newCham;
						return;
					}
				}
			}
		}
	}

}//end of class
