package dat108.eksv21.oppg5;

public class UtskriftsloopTraad extends Thread {
	
	private boolean fortsette = true;
	
	public void stopp() {
		fortsette = false;
	}
	
	@Override
	public void run() {
		while (fortsette) {
			System.out.println("Dette er en gjentagende melding !! :)");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
			}
		}
	}

}
