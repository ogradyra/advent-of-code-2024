import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get("src/input.txt");
        String content = Files.readString(filePath);

        String[] numbers = content.split("\n");
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();
        for (String number : numbers){
            String[] pair = number.split("   ");
            listOne.add(Integer.parseInt(pair[0]));
            listTwo.add(Integer.parseInt(pair[1]));
        }

        List<Integer> sortedListOne = listOne.stream().sorted().toList();
        List<Integer> sortedListTwo = listTwo.stream().sorted().toList();

        int difference = 0;
        for (int i = 0; i < sortedListTwo.size(); i++){
            difference += Math.abs(sortedListTwo.get(i) - sortedListOne.get(i));
        }
        System.out.println(difference);
    }
}