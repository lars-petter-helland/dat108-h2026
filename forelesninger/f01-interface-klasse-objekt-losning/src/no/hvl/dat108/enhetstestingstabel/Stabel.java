package no.hvl.dat108.enhetstestingstabel;

public interface Stabel<T> {
	
	/**
	 * Elementet e blir lagt på toppen av stabelen.
	 */
	void push(T e);
	
	/**
	 * Det øverste elementet på stabelen blir fjernet og returnert.
	 * Hvis stabelen er tom returneres null.
	 */
	T pop();
	
	/**
	 * Det øverste elementet på stabelen blir returnert.
	 * Hvis stabelen er tom returneres null.
	 */
	T peek();
}
