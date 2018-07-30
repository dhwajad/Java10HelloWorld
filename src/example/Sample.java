package example;

import java.util.*;

public class Sample {  
  public static void f2() {
    StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    
    System.out.println(walker.getCallerClass());
  } 
  
  public static void f1() {
    f2();
  }     
  
  public static void main(String[] args) {
    f1();
  }
}