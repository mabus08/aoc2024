import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    
    public static void main(String[] args) {
        String[][] result = readFile();
        List<List<Integer>> inputList = new ArrayList<>();
        for(int i = 0; i < result[0].length; i++) {
            String[] row = new String[result.length];
            for(int r =0; r < result.length; r++) {
               row[r] = result[r][i];
            }
            int[] inta = sortedIntArray(row);
            List<Integer> v = Arrays.stream(inta).boxed().collect(Collectors.toList());
            inputList.add(v);
        }

        for(int i = 0; i < inputList.size() - 1; i++) {
            List<Integer> v1 = inputList.get(i);
            List<Integer> v2 = inputList.get(i+1);
            int diff = 0;
            for(int n = 0; n < result.length; n++) {
                int val1 = v1.get(n);
                int val2 = v2.get(n);
                diff += Math.abs(val1 - val2);
            }
            System.out.println(diff);
        }
    }


   static String[][] readFile() {
        try {
            List<String> lines = Files.readAllLines(Path.of("my_input.txt"));
            int rows = lines.size();
            int cols = lines.get(0).split("    ").length;
            String[][] result = new String[rows][cols];
            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < cols; c++) {
                    String[] column = lines.get(r).split("   ");
                    result[r] = column; 
                }
            }
            return result;
        } catch(Exception e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }

   static int[] sortedIntArray(String[] array) {
       int[] res = new int[array.length];
       for(int i = 0; i < array.length; i++) {
           res[i] = Integer.parseInt(array[i]);
       }
       Arrays.sort(res);
       return res;
   }
            
}
