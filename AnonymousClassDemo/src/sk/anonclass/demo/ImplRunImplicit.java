package sk.anonclass.demo;

public class ImplRunImplicit { // Does not explicitly implement the Runnable
								// Interface

	public static void main(String[] args) {

		Runnable r = new Runnable() { // this does not create the object of the
										// interface, JVM internally creates an
										// anonymous class implementing the
										// Runnable interface and then creates
										// an instance of that class.
			@Override
			public void run() {
				System.out.println("Running");
			}
		}; // note the position of the semicolon
		r.run();

		System.out.println(r.getClass().getName()); // print the name of the
													// anonymous class created
													// by JVM internally
		new ImplRunExplicit().run(); // This is valid too
	}

}

// The "anonymous" class generated will actually have a name, based on the name
// of the class in which this code appears, for instance YourClass$1 or similar
// The object reference 'r' actually refers to the object of that anonymous
// class
// Consider the block after Runnable() to be the definition of that anonymous
// class