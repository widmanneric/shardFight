package com.beta.game;

import java.io.File;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Constants{


	@SuppressWarnings("rawtypes")
	public static HashMap<Integer,Class>actionId = new HashMap<Integer,Class>();
	@SuppressWarnings("rawtypes")
	public static HashMap<Integer, Class> equipId = new HashMap<Integer,Class>();
	public static ArrayList<Integer> unlockedCham = new ArrayList<Integer>();
	public static ArrayList<Profile> profiles= new ArrayList<Profile>();
	public static ArrayList<Profile> cpuProfiles = new ArrayList<Profile>();

	//location constants
	public static final int leftHudBorder = 810;
	public static final int equipmentUpperBound=675;
	public static final int equipmentLowerBount=635;
	public static final int equipmentRightBound = 1050;
	//saving idMap
	FileOutputStream out;
	ObjectOutputStream oss;

	public void save(){
		//save idMaps 
		File newFile = new File("profiles/c.prm");
		try {
			out = new FileOutputStream(newFile);
			oss = new ObjectOutputStream(out);
			oss.writeObject(Constants.actionId);
			oss.writeObject(Constants.equipId);
			oss.close();
		} catch (Exception e) {
			System.out.println("Constants not Saved");
			e.printStackTrace();
		}
	}

}
