import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get("src/input.txt");
        String content = Files.readString(filePath);
        String[] reportStrings = content.split("\n");
        List<List<Integer>> reports = new ArrayList<>();
        for (String reportString : reportStrings){
            reports.add(Arrays.stream(reportString.split(" ")).map(Integer::parseInt).toList());
        }

        System.out.println(partOne(reports));
    }

    private static int partOne(List<List<Integer>> reports) {

        int safeReports = 0;
        int previousDifference = 0;
        for (List<Integer> report : reports){
            for (int i = 0; i < report.size()-1; i++){
                if (i == 0) {
                    previousDifference = 0;
                }
                int difference = report.get(i+1) - report.get(i);
                if (Math.abs(difference) < 1 || Math.abs(difference) > 3){
                    break;
                }
                if ((previousDifference * difference) < 0){
                    break;
                }
                if (i == report.size()-2) {
                    safeReports++;
                }
                previousDifference = difference;
            }
        }

        return safeReports;
    }
}