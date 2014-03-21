package nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TransitionFunctions {

	private Map<List<String>, Set<String>> transitionFunctions;

	

	public TransitionFunctions() {
		// TODO Auto-generated constructor stub
		transitionFunctions = new HashMap<List<String>, Set<String>> ();
	}

	public void put(List<String> list, String n) {
		if (this.transitionFunctions.get(list) == null) {
			transitionFunctions.put(list, new LinkedHashSet<String>());
		}
		transitionFunctions.get(list).add(n);
	}

	public Set<String> get(List list) {
		return transitionFunctions.get(list);
	}

	
	
	/*if (memory.get(l.rest().trim()) == null) {
memory.put(l.rest(), new Stack<String>());
}
//memory.get(l.rest().trim()).push("0");
executionStack.push(l.rest().trim());*/

}
