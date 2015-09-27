package com.beta.game;


import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beta.maps.MapRender;


public class Beta extends ApplicationAdapter {
	//frame timer
	static float elapsedTime;
	//controls
	Controls control;
	//Menu 
	public static Mech mech;
	//sprite batch
	SpriteBatch batch;
	//sprite loader 
	public static SpriteRender loader;
	//Combat map drawer 
	public static MapRender map;
	//damage calculator
	DamageCalculator calc;
	public static int playerTurn;
	TxtLoader sprites;
	//constants 
	Constants constants= new Constants();
	//profile related
	FileInputStream read;
	ObjectInputStream ob;




	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void create () {

		//load save game		
		this.generateProfiles();
		this.generateCpu(); 
		for(int i=0; i<Constants.profiles.size();i+=1){
			System.out.println("User: "+Constants.profiles.get(i).getUserName());
			System.out.println();
			System.out.println("SetList: "+Constants.profiles.get(i).getP().getSetList().toString());
			System.out.println("---------------------------------------------------------------------------------------------------------------------\n");
		}


		//load constants 
		try {
			read = new FileInputStream("profiles/c.prm");
			ob = new ObjectInputStream(read);
			Constants.actionId= (HashMap<Integer, Class>) ob.readObject();
			Constants.equipId= (HashMap<Integer, Class>) ob.readObject();
			ob.close();

		} catch (Exception e1) {
			System.out.println("Constant File not Found");
		}


		//_____________________________________________________________________________________________________________________________________________________________
		//Initialization
		sprites= new TxtLoader();
		batch = new SpriteBatch();
		mech = new Mech();
		loader = new SpriteRender(batch,this,mech);
		map = new MapRender(batch);
		calc = new DamageCalculator();
		control = new Controls(mech);
		Gdx.input.setInputProcessor(control);


		//+_ ____________________________________________________________________________________________________________________________________________________________
		constants.save();






	}//end of create
	@Override
	public void render () {
		//core
		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//batch begin
		batch.begin();

		//drawMap
		map.drawMap();

		//drawSprites
		loader.drawSprites();

		//batch end
		batch.end();
	}

	
	
	
	
	
	
	
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@SuppressWarnings("unchecked")
	public void generateProfiles() {
		File[] savFiles = new File("profiles").listFiles();
		for(File file: savFiles){
			if(file.isFile()){
				P tempP = new P();
				String temp;
				try {
					read = new FileInputStream(file);
					ob = new ObjectInputStream(read);
					tempP.setSetList((ArrayList<ArrayList<Pair<Integer,Integer>>>)ob.readObject());
					temp = (String) ob.readObject();
					ob.close();
					Constants.profiles.add(new Profile(tempP,temp));

				} catch (Exception e) {
					continue;
				}


			}
		}
	}

	@SuppressWarnings("unchecked")
	private void generateCpu(){
		File[] savFiles = new File("cpu").listFiles();
		for(File file: savFiles){
			if(file.isFile()){
				P tempP = new P();
				String temp;
				try {
					read = new FileInputStream(file);
					ob = new ObjectInputStream(read);
					tempP.setSetList((ArrayList<ArrayList<Pair<Integer,Integer>>>)ob.readObject());
					temp = (String) ob.readObject();
					ob.close();
					Constants.cpuProfiles.add(new Profile(tempP,temp));

				} catch (Exception e) {
					continue;
				}


			}
		}
	}//end of cpu
}//end of class
