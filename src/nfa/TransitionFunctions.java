package nfa;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TransitionFunctions {

	private Map<Tuple, Stack<String>> transitionFunctions;

	

	public TransitionFunctions() {
		// TODO Auto-generated constructor stub
		transitionFunctions = new HashMap<Tuple, Stack<String>> ();
	}

	public void put(Tuple t, String n) {
		if (this.transitionFunctions.get(t) == null) {
			transitionFunctions.put(t, new Stack<String>());
		}
		transitionFunctions.get(t).push(n);
	}

	public Stack<String> get(Tuple tuple) {
		return transitionFunctions.get(tuple);
	}

	
	
	/*if (memory.get(l.rest().trim()) == null) {
memory.put(l.rest(), new Stack<String>());
}
//memory.get(l.rest().trim()).push("0");
executionStack.push(l.rest().trim());*/

}
