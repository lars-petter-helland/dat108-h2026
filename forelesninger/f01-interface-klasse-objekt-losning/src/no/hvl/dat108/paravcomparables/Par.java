package no.hvl.dat108.paravcomparables;

/**
 * Et par av to elementer av samme type <T>.
 * Elementene i paret kan sammenlignes med compareTo() 
 * for å bestemme minste og største.
 */
public class Par<T extends Comparable<? super T>> {
	
	private final T e1;
	private final T e2;
	
	public Par(T e1, T e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public T storste() {
		return e1.compareTo(e2) >= 0 ? e1 : e2;
	}

	public T minste() {
		return e1.compareTo(e2) < 0 ? e1 : e2;
	}
}
