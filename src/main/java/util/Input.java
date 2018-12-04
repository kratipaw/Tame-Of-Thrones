package main.java.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import main.java.Constants;
import main.java.ballotSystem.Message;
import main.java.universe.Kingdom;

public class Input {
	
	private static ArrayList<Message> list = new ArrayList<>();

	private static ArrayList<Message> ballotList = new ArrayList<>();
	
	private static List<String> messages = new ArrayList<>();
	
	static {
		
		try {
			messages = Files.readAllLines(Paths.get(Constants.INPUT2_FILEPATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static ArrayList<Message> getProblem1InputList() {
		return list;
	}
	
	public static ArrayList<Message> getProblem2InputBallotList() {
		return ballotList;
	}
	
	public static void readProblem1InputFile(String filepath) {
		
		File file = new File(filepath);
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file)); 
		  
			String st; 
		
			while ((st = br.readLine()) != null) {
				String[] tok = st.split("\\s+");
				
				Message msg = null;
				
				String kingdom = tok[0];
				String content = tok[1].replaceAll("[^a-zA-Z]", "").toLowerCase();
				
				msg = new Message(new Kingdom("Space", "gorilla"), new Kingdom(tok[0], Constants.KINGDOMS.get(kingdom).getEmblem()), content);
				
				list.add(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ArrayList<String> getProblem2InputFromUser() {
		
		ArrayList<String> competingKingdoms = new ArrayList<>();
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Problem 2 : Breaker of Chains\n");
		
		System.out.println("Space\tIce\tWater\tFire\tLand\tAir\n");
		
		System.out.println("Give competing Kingdom names from above list "
				+ "(Space separated): ");
		
		String competing = in.nextLine();
		String[] tok = competing.split("\\s+");
		
		for(String t : tok) {
			competingKingdoms.add(t);
		}
		
		System.out.println("Taking fixed ballot size i.e. 6");
		
		in.close();
		
		return competingKingdoms;
	}
	
	public static void readProblem2InputFileAndPopulate(Set<Kingdom> competingKingdoms, Set<Kingdom> nonCompeting) {
		System.out.println("non input size : " + nonCompeting.size());
		int size = competingKingdoms.size() * nonCompeting.size();
		
		int i = 0;
		
		List<String> randomMessages = RandomGenerator.getRandomInputMessages(messages, size);
		
		if(ballotList != null)
			ballotList.clear();

		for(Kingdom from : competingKingdoms) {
			
			for(Kingdom to : nonCompeting) {
				
				Message msg = new Message(from, to, randomMessages.get(i));
				
				ballotList.add(msg);
				
				i++;
			}
		}
	}
	
	public static Set<Kingdom> getNonCompetingKingdoms(Set<Kingdom> competingKingdoms){
		
		Set<Kingdom> nonCompeting = new HashSet<>();
		
		List<Map.Entry<String, Kingdom>> list = new LinkedList<Map.Entry<String, Kingdom> >(Constants.KINGDOMS.entrySet());
		
		for(Map.Entry<String, Kingdom> entry : list) {
			if(!competingKingdoms.contains(entry.getValue()))
				nonCompeting.add(entry.getValue());
		}
				
		return nonCompeting;
	}
}
