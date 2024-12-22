import java.util.List;

public class SolutionPartTwo {

    public static void main(String[] args) {
        AoCReader reader = new AoCReader();
        List<String[]> lines = reader.readInput("../my_input.txt");
        List<String[]> transposed = reader.transposeList(lines);

        int result = 0;
        for(String v1: transposed.get(0)) {
            //System.out.println(String.format("%s is %d times in other list", v1, 
            //            countForValue(v1, transposed.get(1))));
            result += Integer.parseInt(v1) * countForValue(v1, transposed.get(1));
        }
        System.out.println(String.format("Result: %d", result));
    }


    static int countForValue(String value, String[] countInValues) {
        int result = 0;
        for(String number : countInValues) {
            if(number.equals(value)) {
                result++;
            }
        }
        return result;
    }

}
