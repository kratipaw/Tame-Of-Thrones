package main.java.ballotSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.java.Constants;
import main.java.universe.Kingdom;
import main.java.universe.Southeros;
import main.java.util.Input;
import main.java.util.RandomGenerator;
import main.java.util.Utility;

public class Ballot {

	private static int BALLOT_SIZE = 0;
	
	private Set<Kingdom> competing = null;
	
	private Set<Kingdom> nonCompeting = null;
	
	private ArrayList<Message> ballotMessages = new ArrayList<>();
	
	private Map<Kingdom, Set<Kingdom>> ballotVotingResults = null;
	
	private void setCompeting(Set<Kingdom> competing) {
		this.competing = new HashSet<>();
		this.competing.addAll(competing);
	}
	
	private void setNonCompeting(Set<Kingdom> nonCompeting) {
		System.out.println("non 1 size : " + nonCompeting.size());
		this.nonCompeting = new HashSet<>();
		this.nonCompeting.addAll(nonCompeting);
	}
	
	public void setBallotMessages(ArrayList<Message> ballotMessages) {
		System.out.println("ballot msgs size " + ballotMessages.size());
		//this.ballotMessages = new ArrayList<>();
		this.ballotMessages.addAll(ballotMessages);
		System.out.println("ballot msgs size " + this.ballotMessages.size());
	}
	
	public Map<Kingdom, Set<Kingdom>> getBallotVotingResults() {
		return ballotVotingResults;
	}
	
	public boolean runBallotAndDeclareWinner(Set<Kingdom> competing, Set<Kingdom> noncompeting, int ballotSize) {
		
		setCompeting(competing);
		
		setNonCompeting(noncompeting);
		
		BALLOT_SIZE = ballotSize;
		
		runBallot();
		
		if(ballotVotingResults == null || ballotVotingResults.size() == 0)
			return false;
		
		Map<Kingdom, Set<Kingdom>> ballotVotingResultsSorted = Utility.sortMap(ballotVotingResults);
		
		ballotVotingResults.clear();
		ballotVotingResults.putAll(ballotVotingResultsSorted);
		
		if(!getBallotWinner()) {
			
			Southeros southeros = handleTie(ballotVotingResults);

			if(southeros == null)
				return false;
			
			southeros.printRulerAndAllies();
			
		}
		
		List<Map.Entry<Kingdom, Set<Kingdom>>> list = new LinkedList<Map.Entry<Kingdom, Set<Kingdom>> >(ballotVotingResults.entrySet());
		
		int n = list.size() - 1;
		
		Southeros ss = new Southeros(list.get(n).getKey(), list.get(n).getValue());
		
		ss.printRulerAndAllies();
		
		return true;
	}
	
	private void runBallot() {
		
		boolean result = false;
		
		ballotVotingResults = new HashMap<Kingdom, Set<Kingdom>>();
		
		int count = 0;
		
		do {
			result = runBallotHelper();
			
			count++;
		}while(!result && count < Constants.MAX_RUN_BALLOT);
		
		if(count == Constants.MAX_RUN_BALLOT && !result) {
			System.out.println("Ballot has run " + Constants.MAX_RUN_BALLOT + " times, still no King could be declared.");
			return;
		}
		
	}
	
	private boolean runBallotHelper() {
		
		List<Message> ballotResults = null;
		System.out.println("runballot helper : " + ballotMessages.size());
		if(competing.size() == 1)
			ballotResults = RandomGenerator.getBallotResultantMessages(ballotMessages, nonCompeting.size());
		
		else
			ballotResults = RandomGenerator.getBallotResultantMessages(ballotMessages, BALLOT_SIZE);
		
		for(Message msg : ballotResults) {
			Kingdom from = msg.getFrom();
			Kingdom to = msg.getTo();
			String message = msg.getContent();
			//System.out.println("Balot result : " + msg.toString());
			Set<Kingdom> allies = null;
			
			if(isValidMessage(message.replaceAll("[^a-zA-Z]", "").toLowerCase(), to.getEmblem())) {
				if(ballotVotingResults.containsKey(from))
					allies = ballotVotingResults.get(from);
				
				else
					allies = new HashSet<>();
				
				allies.add(to);
				ballotVotingResults.put(from, allies);
			}
		}
		
		if(ballotVotingResults.size() == 0)
			return false;
		
		return true;
	}
	
	private boolean getBallotWinner() {
		
		if(ballotVotingResults.size() == 1)
			return true;
		
		List<Map.Entry<Kingdom, Set<Kingdom>>> list = new LinkedList<Map.Entry<Kingdom, Set<Kingdom>> >(ballotVotingResults.entrySet());
		
		int n = list.size() - 1;
		if(list.get(n - 1).getValue().size() != list.get(n).getValue().size()) 
			return true;
		
		return false;
	}
	
	private Southeros handleTie(Map<Kingdom, Set<Kingdom>> sortedMap){
		
		boolean result = false;
		
		int count = 0;
		
		do {
			
			Set<Kingdom> newCompeting = getNewCompetingKingdoms(competing, sortedMap);
			
			Input.readProblem2InputFileAndPopulate(newCompeting, nonCompeting);
			
			competing.clear();
			setCompeting(newCompeting);
			newCompeting.clear();
			
			ballotVotingResults.clear();
			runBallot();
			
			if(ballotVotingResults == null)
				return null;
			
			sortedMap.clear();
			sortedMap = Utility.sortMap(ballotVotingResults);
			ballotVotingResults.clear();
			ballotVotingResults.putAll(sortedMap);
			
			result = getBallotWinner();
			
			count++;
			
		}while(!result && count < Constants.MAX_RUN_BALLOT );
		
		if(count == Constants.MAX_RUN_BALLOT && !result) {
			System.out.println("Ballot has run " + Constants.MAX_RUN_BALLOT + " times, still no King could be declared.");
			return null;
		}
		
		List<Map.Entry<Kingdom, Set<Kingdom>>> list = new LinkedList<Map.Entry<Kingdom, Set<Kingdom>> >(sortedMap.entrySet());
		
		int n = list.size() - 1;

		Southeros southeros = new Southeros(list.get(n).getKey(), list.get(n).getValue());

		return southeros;
	}
	
	private Set<Kingdom> getNewCompetingKingdoms(Set<Kingdom> competingKingdoms, Map<Kingdom, Set<Kingdom>> map) {
		
		Set<Kingdom> newCompeting = new HashSet<>();
		
		List<Map.Entry<Kingdom, Set<Kingdom>>> list = new LinkedList<Map.Entry<Kingdom, Set<Kingdom>> >(map.entrySet());
		
		int n = list.size() - 1;
		
		int size = list.get(n).getValue().size();
		newCompeting.add(list.get(n).getKey());
		
		for(int i = n - 1; i >= 0; i--) {
			
			if(list.get(i).getValue().size() == size)
				newCompeting.add(list.get(i).getKey());
			
			else
				break;
		}
		
		return newCompeting;
	}
	
	public static boolean isValidMessage(String message, String emblem) {
		
		int[] freq = new int[Constants.ALPHABET_SIZE];
		Arrays.fill(freq, 0);
		
		for (int i = 0; i < message.length(); i++) {
			freq[message.charAt(i) - 'a']++;
		}
		
		int[] emblemFreq = new int[Constants.ALPHABET_SIZE];
		Arrays.fill(emblemFreq, 0);
		
		for (int i = 0; i < emblem.length(); i++) {
			emblemFreq[emblem.charAt(i) - 'a']++;
		}
		
		for(int i = 0; i < Constants.ALPHABET_SIZE; i++) {
			if(emblemFreq[i] > freq[i])
				return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Ballot [competing=" + competing + ", nonCompeting=" + nonCompeting + ", ballotMessages="
				+ ballotMessages.toString() + "]";
	}

}
