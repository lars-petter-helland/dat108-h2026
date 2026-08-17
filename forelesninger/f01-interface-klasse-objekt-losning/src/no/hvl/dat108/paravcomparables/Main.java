package no.hvl.dat108.paravcomparables;

public class Main {
	
	public static void main(String[] args) {
		
		Par<String> personer = new Par<>("Per", "Arne");
		System.out.println("Storste person: " + personer.storste());
		
		Par<Integer> tall = new Par<>(-999, 0);
		System.out.println("Minste tall: " + tall.minste());
	}
}
