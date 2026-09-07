package dat108.eksv21.oppg5;

import javax.swing.JOptionPane;

public class MeldingsboksTraad extends Thread {

	private UtskriftsloopTraad printLoop;
	
	public MeldingsboksTraad(UtskriftsloopTraad printLoop) {
		this.printLoop = printLoop;
	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, "Trykk OK for å avbryte utskriftstråden", 
				"Avbryt", JOptionPane.PLAIN_MESSAGE);
		printLoop.stopp();
	}
}
