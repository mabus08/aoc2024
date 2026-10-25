/*
 * So, a report only counts as safe if both of the following are true:
 *    The levels are either all increasing or all decreasing.
 *    Any two adjacent levels differ by at least one and at most three.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) {
        AoCReader reader = new AoCReader();
        List<String[]> lines = reader.readInput("input.txt");
        
        //String[] s = {"92", "94", "97", "98", "104"};
        String[] s = {"1", "1", "2", "3", "4"};
        List<String[]> f = new ArrayList<>();
        f.add(s);
        Boolean[] results = verifyAll(lines);
        int safeElements = Arrays.stream(results)
            .filter(result -> result == true)
            .collect(Collectors.toList())
            .size();
        System.out.println(String.format("%s elements are safe", safeElements));
    }

    static Boolean[] verifyAll(List<String[]> lines) {
        Boolean[] results = new Boolean[lines.size()];
        for(int i = 0; i < lines.size(); i++) {
            boolean isSafe = verifySingleLine(lines.get(i));
            if(!isSafe) {
                System.out.print(Arrays.stream(lines.get(i)).collect(Collectors.joining(" ")));
                System.out.println();
            }
            results[i] = isSafe;
        }
        return results;
    }

    static boolean verifySingleLine(String[] line) {
        int len = line.length;
        int i = 0;
        int vorzeichen = Integer.parseInt(line[0]) - Integer.parseInt(line[1]);
        boolean safe = true;
        int corrected = 0;
        while(i < len -1 && safe) { 
            safe = safeNumbers(Integer.parseInt(line[i]), Integer.parseInt(line[i+1]), vorzeichen);
            if(!safe && corrected == 0 && i != len - 2) {
                int p = i == 0 ? 1 : i - 1;
                int n = i == 0 ? p + 1 : i + 1;
                if(vorzeichen == 0) {
                   vorzeichen = Integer.parseInt(line[p]) - Integer.parseInt(line[n]);
                }
                safe = safeNumbers(Integer.parseInt(line[p]), Integer.parseInt(line[n]), vorzeichen);
                if(safe) {
                    i += 2;
                }
                corrected++;
            } else if (!safe && i == len - 2) {
                safe = corrected <= 1;
                corrected++;
                i += 2;
            } else {
                i++;
            }
            System.out.println("Index:" + i + " - " + corrected + " - " + safe);
        }
        return safe && corrected <= 1;
    }

    static boolean safeNumbers(int current, int next, int vorzeichen) {
            int diff = current - next;
            boolean keepVorzeichen = (vorzeichen > 0 && diff > 0) || (vorzeichen < 0 && diff < 0);
            boolean inRange = Math.abs(diff) >= 1 && Math.abs(diff) <= 3;
            System.out.println(current + " || "+ next + "In Range: "+inRange + "vorzeichen "+vorzeichen);
            return inRange && keepVorzeichen;
    }

    static boolean differsInRange(int v1, int v2) {
        int diff = Math.abs(v1-v2);
        return diff >= 1 && diff <=3;
    }
}
