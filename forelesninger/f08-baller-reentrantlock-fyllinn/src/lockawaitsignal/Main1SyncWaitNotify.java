package lockawaitsignal;

public class Main1SyncWaitNotify {

	public static void main(String[] args) {

		Melding melding = new Melding();

		Thread printlnTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(melding.getTekst());
			}
		});

		Thread giVerdiTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				melding.setTekst("Hallo");
			}
		});

		printlnTraad.start();
		giVerdiTraad.start();
	}

}
