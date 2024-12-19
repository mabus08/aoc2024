import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionPartOne {

    
    public static void main(String[] args) {
        AoCReader reader = new AoCReader();
        List<String[]> input = reader.readInput("my_input.txt");
        List<String[]> transposed = reader.transposeList(input);
        List<List<Integer>> inputList = new ArrayList<>();
        for(String[] s: transposed) {
            List<Integer> sorted = Arrays.stream(sortedIntArray(s)).boxed()
                .collect(Collectors.toList());
            inputList.add(sorted);
        }

         for(int i = 0; i < inputList.size() - 1; i++) {
             List<Integer> v1 = inputList.get(i);
             List<Integer> v2 = inputList.get(i+1);
             int diff = 0;
             for(int n = 0; n < v1.size(); n++) {
                 int val1 = v1.get(n);
                 int val2 = v2.get(n);
                 diff += Math.abs(val1 - val2);
             }
             System.out.println(diff);
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
