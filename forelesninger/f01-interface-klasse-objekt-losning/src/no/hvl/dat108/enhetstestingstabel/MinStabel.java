package no.hvl.dat108.enhetstestingstabel;

import java.util.ArrayList;
import java.util.List;

public class MinStabel<T> implements Stabel<T> {
	
	private List<T> elementer = new ArrayList<>();

	@Override
	public void push(T element) {
		elementer.add(element);
	}

	@Override
	public T pop() {
		int antall = elementer.size();
		return antall >= 1 ? elementer.remove(antall-1) : null;
	}

	@Override
	public T peek() {
		int antall = elementer.size();
		return antall >= 1 ? elementer.get(antall-1) : null;
	}
}
