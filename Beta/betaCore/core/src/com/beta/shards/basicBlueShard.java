package com.beta.shards;

import com.beta.game.TxtLoader;

public class basicBlueShard extends Shard {

	public basicBlueShard(int player){

		char [] gainType = {'z','n','b'};
		int [] gain = {-5,100,200};
		//super constructor
		super.setStats("Basic Blue Shard",player,super.createAni(TxtLoader.basicBlueShardTex),"Ice",500,gainType,gain,29);
	}//end of constructor 
}//end of class 
