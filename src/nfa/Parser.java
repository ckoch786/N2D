package nfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Parser {
	
	private static int numStates;
	private static String language;
	private static int numAcceptingStates;
	private static int statesRead = 0;
	private static List<String> acceptingStates;
	private static String startState;
	private static TransitionFunctions transitionFunctions = new TransitionFunctions();
	private static boolean startStateFound = false;
	
	private enum NFA {
		STATES (0),
		LANGUAGE (1),
		CARDINALITY (2);
		
		@SuppressWarnings("unused")
		private final int lineNum;
		NFA(int lineNum) {
			this.lineNum = lineNum;
		}
		public int lineNum() {
			return lineNum;
		}
	}
	
	public static void parse(String t, int lineNum) {	
		acceptingStates = new ArrayList<String>();
		if (lineNum == NFA.STATES.lineNum()) {
			numStates = Integer.parseInt(t);
		} else if (lineNum == NFA.LANGUAGE.lineNum()) {
			language = t;
		} else if (lineNum == NFA.CARDINALITY.lineNum()) {
			numAcceptingStates = Integer.parseInt(t);
		} else if (lineNum > NFA.CARDINALITY.lineNum() && 
				isMoreAcceptingStates()) {
			acceptingStates.add(t);
			statesRead++;
		} else if (lineNum > NFA.CARDINALITY.lineNum() &&
				!isMoreAcceptingStates() &&
				!startStateFound()) {
			startState = t;
			startStateFound = true;
		} else if (lineNum > (NFA.CARDINALITY.lineNum() + numStates) +1) {
			String[] transition = new String[3];
			transition = t.split(" ");
			String c = transition[0];
			String w = transition[1];
			String n = transition[2];
			transitionFunctions.put(Collections.unmodifiableList(Arrays.asList(c,w)), n);
		}
				
	}
	
	private static boolean startStateFound() {
		return startStateFound;
	}

	private static boolean isMoreAcceptingStates() {
		return (statesRead < numAcceptingStates) ? true : false;
	}

	public static int getNumStates() {
		return numStates;
	}

	public static String getLanguage() {
		return language;
	}

	public static List<String> getAcceptingStates() {
		return acceptingStates;
	}

	public static String getStartState() {
		return startState;
	}

	public static Set<String> getNFATransitions(String currentState, String w) {
		return transitionFunctions.get(Arrays.asList(currentState, w));
	}

	
	
}
