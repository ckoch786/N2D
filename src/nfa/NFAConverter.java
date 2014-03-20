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

	public String[] getLanguage() {
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
		for (String w: getLanguage()) {
			for (Set<String> s: dfaStates) {
				applyMove(w, s);
			}
		}
	}

	private void applyMove(String w, Set<String> s) {
		dfaTransition = new LinkedHashSet<String>();
		for (String t: s) {
			
			dfaTransition.addAll(Parser.getNFATransitions(t, w));
		}
		// TODO output this to file when we complete generation
		dfaTransitions.add(new Triples(s, w, dfaTransition));
		dfaStates.add((LinkedHashSet<String>) dfaTransition);
	}

	private void dfaStatesAddInitialState() {
		String currentState = getStartState();
		LinkedHashSet<String> foo = new LinkedHashSet<String>();
		foo.add(currentState);
		dfaStates.add(foo);
	}

/*	for (String s : dfaStates) {
		for (String w : getLanguage()) {
			applyMove(currentState, w);
			currentState = "";
		}
	}
*//*	private void applyMove(String currentState, String w) {
		
		//dfaEnqueueTransition();
	}*/
}
/*int i = 0;

for (String s : Parser.getNFATransitions(currentState, w)) {
	dfaTransitions.add(s); // Populate set of states 
	//dfaStates.put(dfaTransitions, Integer.toString(i)); // Add to states 
}
*/