import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class day4 {
    public static void main(String[] args) throws IOException {

        b4();
    }
    public static void a4() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day4a"));
        int sum = 0;
        for (String line: file) {
            String[] twolists = line.split(": ")[1].split(" \\| ");
            String[] winning_numbers = twolists[0].replace("  ", " ").split(" ");
            String[] my_numbers = twolists[1].replace("  ", " ").split(" ");

            int counter = -1;

            for (String winning_number: winning_numbers) {
                for (String my_number: my_numbers) {
                    if (my_number.equals("") || winning_number.equals("")) {
                        continue;
                    }
                    if (Integer.parseInt(winning_number) == Integer.parseInt(my_number)) {
                        counter++;
                        break;
                    }
                }
            }

            if (counter != -1) {
                sum += Math.pow(2, counter);
            }
        }
        System.out.println(sum);
    }
    public static void b4() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day4a"));

        List<Integer> cardcount = new ArrayList<>();
        for (String line: file) {
            cardcount.add(1);
        }


        for (int i = 0; i < file.size(); i++) {
            String line = file.get(i);
            String[] twolists = line.split(": ")[1].split(" \\| ");
            String[] winning_numbers = twolists[0].replace("  ", " ").split(" ");
            String[] my_numbers = twolists[1].replace("  ", " ").split(" ");

            int counter = 0;

            for (String winning_number: winning_numbers) {
                for (String my_number: my_numbers) {
                    if (my_number.equals("") || winning_number.equals("")) {
                        continue;
                    }
                    if (Integer.parseInt(winning_number) == Integer.parseInt(my_number)) {
                        counter++;
                        break;
                    }
                }
            }
            //System.out.println(counter);
            if (counter != 0) {
                for (int j = i+1; j < i + 1 + counter; j++) {
                    cardcount.set(j, cardcount.get(j) + cardcount.get(i));
                    //System.out.println(j);
                }
            }
        }
        int sum = 0;
        for (int c : cardcount) {
            sum += c;
        }
        System.out.println(sum);
    }
}