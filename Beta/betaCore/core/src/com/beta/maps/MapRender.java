package com.beta.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beta.game.Beta;

public class MapRender {

	SpriteBatch batch;
	//Flag 
	boolean drawMap;
	boolean drawMainHud;
	boolean loop=true;

	//file i/o 
	Scanner scan;
	File mapFile;
	int [][] map;

	int currMap;

	//Map1
	Texture grassTile;

	//map 2
	Texture woodTile;

	//map 3 
	Texture spaceBack;

	//grid
	Texture grid;



	//Main Hud 
	Texture mainHud;

	public MapRender(SpriteBatch batch){

		this.batch = batch;
		map = new int [9][9];
		grid = new Texture("tiles/grid.png");

	}

	public void loadMap(int mapNumber){
		drawMap=true;
		switch(mapNumber){
		case 1: 
			grassTile = new Texture("tiles/grassTile.png");
			mapFile= new File("maps/map1.txt");
			break;
		case 3: 
			woodTile = new Texture("tiles/wood1.jpg");
			mapFile = new File ("maps/map2.txt");
			break;
		case 2:	
			this.spaceBack= new Texture("tiles/7.jpg");
			loop=false;
			break;
		}
		if(this.mapFile!=null){
			this.createMapArray();
		}
		this.currMap=mapNumber;
	}
	
	public void loadRandomMap(){
		int random = 1+(int)(Math.random()*2);
		loadMap(random);
	}

	private void createMapArray(){
		try {
			scan = new Scanner(mapFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int x=0;
		int y=0;
		while(scan.hasNextInt()){
			int temp = scan.nextInt();
			map[x][y]=temp;
			if(x==8){
				x=0;
				y+=1;
				continue;
			}
			x+=1;
		}//end of while loop 
		scan.close();
	}

	public void loadMainHud() {
		// TODO Auto-generated method stub
		mainHud = new Texture("hud/mainHud.png");
		drawMainHud=true;

	}

	public void drawMap(){
		if(Beta.loader.step==1){
			if(drawMap&&loop){
				for(int i =0; i<9; i+=1){
					for(int j=0; j<9; j+=1){
						switch(map[i][j]){
						case 1: batch.draw(grassTile,i*90,j*90,90,90);
						break;
						case 2: batch.draw(woodTile, i*90, j*90,90,90);
						break;
						}
					}//end of loop
				}//end of loop
				batch.draw(this.grid, 0, 0,810,810);
			}
			else if(drawMap){
				switch(this.currMap){
				case 2: batch.draw(this.spaceBack,0,0,810,810);
				break;
				}
			}
			if(drawMainHud){
				batch.draw(mainHud,810,0);
			}
		}

	}//end of method 
}//end of class 




