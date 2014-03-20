package nfa;

import java.util.Set;

public class Triples {
	private Set<String> currentState;
	private Set<String> transitingState;
	private String inputSymbol;
	
	public Triples(Set<String> s, String w, Set<String> dfaTransition) {
		currentState = s;
		inputSymbol = w;
		transitingState = dfaTransition;
	}

	/**
	 * return string of the form [0,1] a [0]
	 */
	public String toString() {
		return currentState.toString() + " " + inputSymbol + " " + transitingState.toString();
	}
}
