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
        String[] reportStrings = content.split("\r\n");
        List<List<Integer>> reports = new ArrayList<>();
        for (String reportString : reportStrings){
            reports.add(Arrays.stream(reportString.split(" ")).map(Integer::parseInt).toList());
        }

        int safeReportCount = partOne(reports);
        System.out.println(safeReportCount);
        int newlySafeReportCount = partTwo(getUnsafeReports(reports));
        System.out.println(newlySafeReportCount + safeReportCount);
    }

    private static int partTwo(List<List<Integer>> unsafeReports) {

        int safeReports = 0;
        int previousDifference = 0;
        for (List<Integer> report : unsafeReports){
            for (int i = 0; i < report.size(); i++) {
                List<Integer> subReport = new ArrayList<>();

                if (i == 0){
                    subReport.addAll(report.subList(i+1, report.size()));
                    System.out.println(subReport);
                }
                else if (i == 1){
                    subReport.add(report.getFirst());
                    subReport.addAll(report.subList(i+1, report.size()));
                    System.out.println(subReport);
                }
                else if (i < report.size()-2){
                    subReport.addAll(report.subList(0, i));
                    subReport.addAll(report.subList(i+1, report.size()));
                    System.out.println(subReport);
                }
                else if (i == report.size()-2){
                    subReport.addAll(report.subList(0, i));
                    subReport.add(report.getLast());
                    System.out.println(subReport);
                }
                else {
                    subReport.addAll(report.subList(0, report.size()-1));
                    System.out.println(subReport);
                }

                int safeReportCount = getSafeReport(subReport, previousDifference);
                if (safeReportCount == 1){
                    safeReports++;
                    break;
                }
            }
        }
        return safeReports;
    }

    private static int partOne(List<List<Integer>> reports) {

        int safeReports = 0;
        int previousDifference = 0;
        for (List<Integer> report : reports){
            safeReports += getSafeReport(report, previousDifference);
        }

        return safeReports;
    }

    private static int getSafeReport(List<Integer> report, int previousDifference){
        int safeReports = 0;
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
        return safeReports;
    }

    private static List<List<Integer>> getUnsafeReports(List<List<Integer>> reports) {
        List<List<Integer>> unsafeReports = new ArrayList<>();
        int previousDifference = 0;
        for (List<Integer> report : reports){
            for (int i = 0; i < report.size()-1; i++){
                if (i == 0) {
                    previousDifference = 0;
                }
                int difference = report.get(i+1) - report.get(i);
                if (Math.abs(difference) < 1 || Math.abs(difference) > 3){
                    unsafeReports.add(report);
                    break;
                }
                if ((previousDifference * difference) < 0){
                    unsafeReports.add(report);
                    break;
                }
                previousDifference = difference;
            }
        }
        return unsafeReports;
    }
}