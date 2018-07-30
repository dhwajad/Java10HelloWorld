import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(11, 12, 13, 41, 51, 16, 17, 81, 91, 110);

        long count = numbers.stream()
                //.filter(e -> e < 50) //Java 8
                //.limit(3) //Java 8
                //.skip(7) //Java 8
                //.takeWhile(e -> e < 50) //Java 9
                .dropWhile(e -> e < 50)
                .takeWhile(e -> e < 91)
                .count();
                //.forEach(System.out::println);

        System.out.println(count);

    }
}
