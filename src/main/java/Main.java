package main.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import main.java.ballotSystem.Ballot;
import main.java.ballotSystem.Message;
import main.java.universe.Kingdom;
import main.java.universe.Southeros;
import main.java.util.Input;

public class Main {

	public static void main(String[] args) {

		Main main = new Main();
		
		System.out.println("Problem Set 5 : Tame of Thrones\n\n");
		
		main.solution1(Constants.INPUT1_FILEPATH);
		
		main.solution2();
		
		System.exit(1);
		
	}
	
	private boolean solution1(String file) {
		
		System.out.println("Problem 1 : A Golden Crown");
		
		if(Input.getProblem1InputList() != null)
			Input.getProblem1InputList().clear();
		
		Input.readProblem1InputFile(file);
		
		Set<Kingdom> allies = new HashSet<>();
		
		Kingdom from = null;
		
		for(Message msg : Input.getProblem1InputList()) {
			from = msg.getFrom();
			Kingdom to = msg.getTo();
			String message = msg.getContent();
			
			if(Ballot.isValidMessage(message, to.getEmblem())) {
				allies.add(to);
			}
		}
		
		if(allies.size() >= 3) {
			System.out.println("King Shan is the King of Southeros");
			Southeros southeros = new Southeros(from, allies);
			southeros.printRulerAndAllies();
			return true;
		}
		
		
		System.out.println("No King declared \n");
		
		return false;
	}

	private void solution2(){
		
		ArrayList<String> kingdoms = Input.getProblem2InputFromUser();
		
		Set<Kingdom> competingKingdoms = new HashSet<>();
		
		for(String kd : kingdoms) {
			if(Kingdom.isValidKingdom(kd))
				competingKingdoms.add(Constants.KINGDOMS.get(kd));
			
			else
				System.out.println(kd + " is not valid Kingdom, thus excluding it.");
		}
		
		Set<Kingdom> nonCompeting = Input.getNonCompetingKingdoms(competingKingdoms);
		System.out.println("non size : " + nonCompeting.size());
		Input.readProblem2InputFileAndPopulate(competingKingdoms, nonCompeting);
		
		new Ballot().setBallotMessages(Input.getProblem2InputBallotList());
		new Ballot().runBallotAndDeclareWinner(competingKingdoms, nonCompeting, Constants.BALLOT_SIZE);
		
	}

}
