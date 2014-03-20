package nfa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class NFAConverter {
	
	private String[] transitions;
	private Set<String> dfaTransitions;
	private Map<Set<String>, String> dfaStates;

	public NFAConverter(String file) {
		super();
		readNFA(file);
		dfaTransitions = new LinkedHashSet<String>();
		dfaStates = new HashMap<Set<String>, String>();
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

	public String[] getTransitions() {
		return transitions;
	}
	
	
	// TODO Finish this 
	public void generateDFA() {
		String currentState = getStartState();
		int i = 0;
		for (String w : getLanguage()) {
			for (String s : Parser.getNFATransitions(currentState, w)) {
				dfaTransitions.add(s);
				dfaStates.put(dfaTransitions, Integer.toString(i));
			}
		}
	}
}
