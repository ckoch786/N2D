package nfa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Before;

public class NFAConverter {
	
	private Queue<Triples> dfaTransitions;
	private Set<LinkedHashSet<String>> dfaStates;
	private Set<String> dfaTransition;

	public NFAConverter(String file) {
		super();
		readNFA(file);
		dfaTransitions = new LinkedList<Triples>();
		dfaStates = new LinkedHashSet<LinkedHashSet<String>>();
	}

	private void readNFA(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String t;
			int lineNum = 0;
			while ((t = br.readLine()) != null) {
				// TODO replace NFA parser with antlr grammar
				Parser.parse(t.trim(), lineNum);
				lineNum++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public int numStates() {
		return Parser.getNumStates();
	}

	public String getLanguage() {
		return Parser.getLanguage();
	}

	public List<String> getAcceptingStates() {
		return Parser.getAcceptingStates();
	}

	public String getStartState() {
		return Parser.getStartState();
	}

	public Queue<Triples> getTransitions() {
		return dfaTransitions;
	}
	
	
	/**
	 * <pre>
	 * The Subset Construction Algorithm
	 * 
	 * 1.  -- Create the start state of the DFA by taking the $\varepsilon$-closure of the start state of the NFA.
	 * 2.  Perform the following for the new DFA state:
	 *     For each possible input symbol:
	 *        a. Apply move to the newly-created state and the input symbol; this will return a set of states.
	 *        b. -- Apply the $\varepsilon$-closure to this set of states, possibly resulting in a new set.
	 *     This set of NFA states will be a single state in the DFA.
	 * 3.  Each time we generate a new DFA state, we must apply step 2 to it. The process is complete when applying step 2 
	 *     does not yield any new states.
	 * 4.  The finish states of the DFA are those which contain any of the finish states of the NFA.
	 * </pre>
	 */					
	public void generateDFA() {
		dfaStatesAddInitialState();
		int i = 0;
		for (char w: getLanguage().toCharArray()) {
			while (i < dfaStates.size()) {
				applyMove(Character.toString(w), (Set<String>) dfaStates.toArray() [i]);
				i++;
			}
	
//			for (Set<String> s: dfaStates) {
//				
//			}
		}
	}

	private void applyMove(String w, Set<String> nfaStates) {
		dfaTransition = new LinkedHashSet<String>();
		for (String currentState: nfaStates) {
			Set<String> states = Parser.getNFATransitions(currentState, w);
			if (states != null) {
				dfaTransition.addAll(states);
			}
		}
		// TODO output this to file when we complete generation
		dfaTransitions.add(new Triples(nfaStates, w, dfaTransition));
		dfaStates.add((LinkedHashSet<String>) dfaTransition);
	}

	private void dfaStatesAddInitialState() {
		String currentState = getStartState();
		LinkedHashSet<String> foo = new LinkedHashSet<String>();
		foo.add(currentState);
		dfaStates.add(foo);
	}

	public static void main (String[] args) {
		NFAConverter dfa;
		if (args.length == 0) {
			System.out.println("Need to enter name of file");
		}
		dfa = new NFAConverter(args[0]);
		dfa.generateDFA();
		
		int count = 0;
		for (Triples i: dfa.getTransitions()) {
			//assertEquals(t[count], i.toString());
			System.out.println(i.toString());
		}
	}
	
}