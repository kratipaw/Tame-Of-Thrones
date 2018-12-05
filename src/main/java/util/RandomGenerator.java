package main.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.ballotSystem.Message;

public class RandomGenerator {

	public static List<String> getRandomInputMessages(List<String> messages, int max){
		
		Collections.shuffle(messages);
		
		return messages.subList(0, max);
	}
	
	public static List<Message> getBallotResultantMessages(ArrayList<Message> ballotMessages, int max){
		Collections.shuffle(ballotMessages);
		
		return ballotMessages.subList(0, max);
	}
}
