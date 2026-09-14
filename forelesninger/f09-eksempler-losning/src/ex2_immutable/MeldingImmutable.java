package ex2_immutable;

public final class MeldingImmutable {
	
	private final String innhold;

	public MeldingImmutable(String innhold) {
		this.innhold = innhold; //OK siden String er immutable
	}

	public String getInnhold() {
		return innhold; //OK siden String er immutable
	}

	public MeldingImmutable leggTil(String merTekst) {
		return new MeldingImmutable(innhold + merTekst);
	}
	
	@Override
	public String toString() {
		return "MeldingImmutable [innhold=" + innhold + "]";
	}
	
}
