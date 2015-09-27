package com.beta.shards;


import com.beta.game.TxtLoader;

public class basicRedShard extends Shard {
	
	
	public basicRedShard(int player){
		
		char [] gainType = {'z','n','f'};
		int [] gain = {-5,100,100};
		//super constructor
		super.setStats("Basic_Blue_Shard",player,super.createAni(TxtLoader.basicRedShardTex),"Fire",500,gainType,gain,29);
	}//end of constructor 
}
