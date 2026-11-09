package no.hvl.dat108.f25c;

public class Bruker {
	
	private String brukernavn;
	private String passordhash;
	private String salt;
	
	public Bruker(String brukernavn, String passordhash, String salt) {
		this.brukernavn = brukernavn;
		this.passordhash = passordhash;
		this.salt = salt;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public String getSalt() {
		return salt;
	}

	public String getPassordhash() {
		return passordhash;
	}

	@Override
	public String toString() {
		return "Bruker:"
				+ "\n\tbrukernavn=" + brukernavn 
				+ "\n\tpassordhash[32byte]=" + passordhash 
				+ "\n\tsalt[16byte]=" + salt;
	}

	
}
