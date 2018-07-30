package lab1;

import java.util.*;
import java.awt.Color;
import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Pencil {
    private final int id;
    private final Color color;

    public Pencil(int theId, Color theColor) {
        id = theId;
        color = theColor;
    }

    public int getId() { return id; }
    public Color getColor() { return color; }
}

public class Sample {

    public static Color getColorFromField(Field field) {
        try {
            return (Color)(field.get(null));
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Pencil> createPencils() throws Exception {
        //Let's convert this function to functional style

        /*List<Color> colors = new ArrayList<>();

       Arrays.stream(Color.class.getFields()).filter(field -> field.getType().equals(Color.class)).forEach(field -> {
            try {
                colors.add((Color)(field.get(null)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });*/

        List<Color> colors = Arrays.stream(Color.class.getFields())
                .filter(field -> field.getType().equals(Color.class))
                .map(Sample::getColorFromField)
                .collect(Collectors.toList());

        /*for(Field field : Color.class.getFields()) {
            if(field.getType().equals(Color.class)) {
                colors.add((Color)(field.get(null)));
            }
        }*/

        List<Pencil> pencils = new ArrayList<>();
        /*for(int i = 0; i < 100; i++) {
            pencils.add(new Pencil(i, colors.get(i % colors.size())));
        }*/

        IntStream.range(0, 100).forEach(i -> pencils.add(new Pencil(i, colors.get(i % colors.size()))));

        return pencils;
    }

    public static void main(String[] args) throws Exception {
        List<Pencil> pencils = createPencils();

        //Let's refactor this code to functional style
        int count = 0;
        boolean foundGreen = false;


        //counting sum between first green and first yellow after that
        long longCount = pencils.stream()
                            .dropWhile(pencil -> pencil.getColor() != Color.GREEN)
                            .takeWhile(pencil -> pencil.getColor() != Color.YELLOW)
                            .skip(1)
                            .count();

        /*for(Pencil pencil : pencils) {
            if(!foundGreen && pencil.getColor() == Color.GREEN) {
                foundGreen = true;
                continue;
            }

            if(!foundGreen) {
                continue;
            }

            if(pencil.getColor() == Color.YELLOW) {
                break;
            }

            count++;
        }*/

        System.out.println(count);
        System.out.println(longCount);
    }
}
