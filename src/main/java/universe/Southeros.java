package main.java.universe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Southeros {

	private Kingdom ruler = null;
	
	private Set<Kingdom> allies = null;

	public Southeros(Kingdom ruler, Set<Kingdom> allies) {
		
		this.ruler = ruler;
		this.allies = new HashSet<>(allies);
	}
	
	public void printRulerAndAllies() {
		
		if(allies == null || allies.size() == 0) {
			printNoRuler();
			return;
		}
		
		System.out.println("\nWho is the ruler of Southeros?\n" + ruler);
		
		ArrayList<String> alliesName = new ArrayList<>();
		
		for(Kingdom kd : allies) {
			alliesName.add(kd.getName());
		}
		
		System.out.println("Allies of Ruler?\n" + alliesName.toString()+ "\n");
		
	}
	
	private void printNoRuler() {
		System.out.println("\nWho is the ruler of Southeros?\nNone");
		System.out.println("Allies of Ruler?\nNone\n");
	}
}
