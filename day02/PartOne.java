/*
 * So, a report only counts as safe if both of the following are true:
 *    The levels are either all increasing or all decreasing.
 *    Any two adjacent levels differ by at least one and at most three.*/

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {

    public static void main(String[] args) {
        AoCReader reader = new AoCReader();
        List<String[]> lines = reader.readInput("example.txt");
        System.out.println(String.format("List contains %d elements", lines.size()));

        for(String[] curr : lines) {
            for(int i = 0; i < curr.length; i++) {
                System.out.print(curr[i]+" ");
            }
            System.out.println();
        }

        String[] results = new String[lines.size()];
        for(int i = 0; i < lines.size(); i++) {
            String[] curr = lines.get(i);
            int gdirection = 0;
            for(int idx = 0; idx < curr.length - 1; idx++) {
                int current = Integer.parseInt(curr[idx]);
                int next = Integer.parseInt(curr[idx+1]);

                int direction = current - next <= 0 ? -1 : 1;
                if(direction != gdirection && idx > 0) {
                    results[i] = "Unsafe";
                    break;
                }
                gdirection = direction;
                if(differsInRange(Integer.parseInt(curr[idx]), Integer.parseInt(curr[idx+1]))) {
                    results[i] = "Safe";
                } else {
                    results[i] = "Unsafe";
                    break;
                }
            }
        }
        int safeElements = Arrays.stream(results)
            .filter(result -> result.equalsIgnoreCase("safe"))
            .collect(Collectors.toList())
            .size();
        System.out.println(String.format("%s elements are safe", safeElements));
    }


    static boolean differsInRange(int v1, int v2) {
        int diff = Math.abs(v1-v2);
        return diff >= 1 && diff <=3;
    }
}
