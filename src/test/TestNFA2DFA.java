package test;

import static org.junit.Assert.*;
import nfa.NFAConverter;
import nfa.Triples;

import org.junit.Before;
import org.junit.Test;

public class TestNFA2DFA {
	private NFAConverter dfa;
	@Before
	public void setUp() throws Exception {
		dfa = new NFAConverter("nfa.in");
		dfa.generateDFA();
	}


	@Test
	//TODO what do you do when you are doing TDD when you need an ADT?
	// Do you stop and create the ADTs in order to pass the test or
	// did you do something wrong to be having the need to create ADTs so
	// early on?
	public void testDFAStates() {
		assertEquals(3, dfa.numStates());
	}
	
	@Test
	public void testDFALanguage() {
		assertEquals("ab", dfa.getLanguage());
	}
	
	@Test
	public void testDFAAcceptingStates(){
		for (String i: dfa.getAcceptingStates()){
			assertEquals(2, Integer.parseInt(i));
		}
	}
	
	@Test
	public void testDFAStartState() {
		assertEquals(0, Integer.parseInt(dfa.getStartState()));
	}
	@Test
	public void testDFATransitions() {
		String t[] = { "[0] a []",
				"[0] b [0]",
				"[0,1] a [0,1]", 
				"[0,1] b [0,2]",
				"[0,2] a [0,1]",
				"[0,2] b [0]" };
		int count = 0;
		for (Triples i: dfa.getTransitions()) {
			//assertEquals(t[count], i.toString());
			System.out.println(i.toString());
		}
	}
}
