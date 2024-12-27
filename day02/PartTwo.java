/*
 * So, a report only counts as safe if both of the following are true:
 *    The levels are either all increasing or all decreasing.
 *    Any two adjacent levels differ by at least one and at most three.*/

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) {
        AoCReader reader = new AoCReader();
        List<String[]> lines = reader.readInput("input.txt");

        Boolean[] results = verifyAll(lines);

        int safeElements = Arrays.stream(results)
            .filter(result -> result == true)
            .collect(Collectors.toList())
            .size();
        System.out.println(String.format("%s elements are safe", safeElements));

        //Arrays.stream(results).forEach(System.out::println);
    }

    static Boolean[] verifyAll(List<String[]> lines) {
        Boolean[] results = new Boolean[lines.size()];
        for(int i = 0; i < lines.size(); i++) {
            results[i] = verifySingleLine(lines.get(i));
        }
        return results;
    }

    static boolean verifySingleLine(String[] line) {
        String val = Arrays.stream(line).collect(Collectors.joining(" "));
        System.out.print(val);
        boolean isSafe = true;
        int len = line.length;
        boolean inRange = true;
        int i = 0;
        int vorzeichen = Integer.parseInt(line[0]) - Integer.parseInt(line[1]);
        if(vorzeichen == 0) {
            vorzeichen = 1;
        };
        boolean keepVorzeichen = true;
        int correction = 0;
        while(inRange && keepVorzeichen && correction <= 1 && i < len -1) {
            int current = Integer.parseInt(line[i]);
            int next = Integer.parseInt(line[i+1]);
            int diff = current - next;
            keepVorzeichen = (vorzeichen > 0 && diff > 0) || (vorzeichen < 0 && diff < 0);
            inRange = Math.abs(diff) >= 1 && Math.abs(diff) <= 3;
            if((!keepVorzeichen || current == next)) {
                correction++;
                keepVorzeichen = true;
                inRange = true;
            }
            i++;
        }
        isSafe = inRange && keepVorzeichen && correction <= 1;
        System.out.println(" - " + inRange + " - " + keepVorzeichen + " - " + correction + " - " + isSafe);
        return inRange && keepVorzeichen && correction <= 1; 
    }

}
