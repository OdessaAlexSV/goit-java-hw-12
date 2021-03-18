package main.java.hw.cycle_barrier;

import java.util.Random;
import java.util.StringJoiner;

public class DataPreparation {
    private int countElements;
    private int currentNumberO = 0;
    private int currentNumberH = 0;
    private Random r = new Random();
    private StringJoiner finalStr = new StringJoiner("");

    public DataPreparation(int countElements) {
        this.countElements = countElements;
    }

    public StringJoiner finalStr() {
        String alphabet = "OH";
        int i = 0;
        while (i < Integer.MAX_VALUE) {
            i++;
            char element = alphabet.charAt(r.nextInt(2));
            switch (element) {
                case 'O':
                    if (currentNumberO < (countElements / 3)) {
                        finalStr.add(Character.toString(element));
                        currentNumberO++;
                    } else if (currentNumberH == (countElements / 3 * 2)) {
                        System.out.println("There were " + i + " iterations for creating the required input data:" +
                                "\n length - " + countElements +
                                "\n number of 'O' - " + countElements/3 +
                                "\n number of 'H' - " + countElements/3*2);
                        i = Integer.MAX_VALUE;
                    }
                    break;
                case 'H':
                    if (currentNumberH < (countElements / 3 * 2)) {
                        finalStr.add(Character.toString(element));
                        currentNumberH++;
                    } else if (currentNumberO == (countElements / 3)) {
                        System.out.println("There were " + i + " iterations for creating the required input data:" +
                                "\n length - " + countElements +
                                "\n number of 'O' - " + countElements/3 +
                                "\n number of 'H' - " + countElements/3*2);
                        i = Integer.MAX_VALUE;
                    }
                    break;
            }
        }
        return finalStr;
    }

}
