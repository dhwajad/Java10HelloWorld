package lab4;


// In this lab we will create a method, in a Util class,
// that will print the details of the calling method: The name, the class it belongs to, and the line number from which this method was called.
// Then call it from a main method and also from within an instance method.

class Util {

    public void stackWalkerMethod() {


        String details = StackWalker.getInstance()
                .walk(frames ->
                        frames
                                .map(frame -> String.format("%s %s %d", frame.getClassName(), frame.getMethodName(), frame.getLineNumber()))
                                .skip(1)
                                .findFirst()
                                .orElse(""));
        System.out.println(details);
    }

    public void instanceCaller() {
        stackWalkerMethod();
    }
}

public class Sample {

    public static void main(String... args) {
        Util util = new Util();
        util.stackWalkerMethod();
        System.out.println("----------------------------");
        util.instanceCaller();
    }
}
