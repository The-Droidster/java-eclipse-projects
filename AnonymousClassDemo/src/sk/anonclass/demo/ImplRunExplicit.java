package sk.anonclass.demo;

public class ImplRunExplicit implements Runnable {

	public void run(){
		System.out.println("Running");
	}
	public static void main(String[] args) {
		new ImplRunExplicit().run();

	}

}
