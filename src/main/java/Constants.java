package main.java;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import main.java.universe.Kingdom;

public class Constants {

	public static final Map<String, Kingdom> KINGDOMS = new HashMap<String, Kingdom>();
	
	static {
		
		KINGDOMS.put("Space", new Kingdom("Space", "gorilla"));
		KINGDOMS.put("Land", new Kingdom("Land", "panda"));
		KINGDOMS.put("Water", new Kingdom("Water", "octopus"));
		KINGDOMS.put("Ice", new Kingdom("Ice", "mammoth"));
		KINGDOMS.put("Air", new Kingdom("Air", "owl"));
		KINGDOMS.put("Fire", new Kingdom("Fire", "dragon"));
		
	}
	
	private static final String CURR_DIR = System.getProperty("user.dir");
	
	public static final String INPUT1_FILEPATH = CURR_DIR + File.separator + "Input1.txt";
	
	public static final String INPUT2_FILEPATH = CURR_DIR + File.separator + "messages.txt";
	
	public static final int MAX_RUN_BALLOT = 10;
	
	public static final int ALPHABET_SIZE = 26;
	
	public static final int BALLOT_SIZE = 6;
	
	
}
