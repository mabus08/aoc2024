import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AoCReader {

    public List<String[]> transposeList(List<String[]> inputs) {
        int numRows = inputs.size();
        int numCols = inputs.getFirst().length;
        List<String[]> transposed = new ArrayList<>();
        for (int i = 0; i < numCols; i++) {
            transposed.add(new String[numRows]);
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                transposed.get(j)[i] = inputs.get(i)[j];
            }
        }
        return transposed;
    }

    public List<String[]> readInput(String filename) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filename));
            List<String[]> result = new ArrayList<>();
            for(int i = 0; i < lines.size(); i++) {
                String currentLine = lines.get(i);
                result.add(currentLine.split(" "));
            }
            return result;
        } catch(Exception e ) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
