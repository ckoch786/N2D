package nfa;

public class Tuple {

	private String w;
	private String c;

	public Tuple(String c, String w) {
		this.c = c;
		this.w = w;
	}
	
	public String toString() {
		return "("+c+ " " + w +")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + w.hashCode();
		
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} 
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tuple tuple = (Tuple)obj;
		if (c != tuple.c && w != tuple.w) {
			return false;
		}
		return true;
	}
	

}
