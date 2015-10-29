package sk.anonclass.demo;

public class ImplRunImplicitInline extends Thread {

	public static void main(String[] args) {
		new Thread(new Runnable() { // Another valid way of doing it, anonymous
									// class implementing Runnable is created
									// internally by JVM

			@Override
			public void run() {
				System.out.println("Running");
				System.out.println(this.getClass().getName());
				// We haven't yet created an object of our class so we cannot
				// print the name of our class using 'this'
				// conclusion : this should be the name of the class internally
				// created by JVM

			}
		}).start(); // thread start, note the semicolon

	}

}

// this way of implementing an anonymous class only works if the constructor of
// the class allows passing Runnable as an argument as is the case with Thread
// class here