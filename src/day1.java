import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class day1 {
    public static void main(String[] args) throws IOException {

        b1();
    }

    public static void a1() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day1a"));
        int sum = 0;
        for (String line: file) {
            char[] chars = line.toCharArray();
            int first = -1;
            int last = -1;
            for (char c: chars) {
                if (Character.isDigit(c)) {
                    if (first == -1) {
                        first = (int)(c - '0') * 10;
                        last = (int)(c - '0');
                    } else {
                        last = (int)(c - '0');
                    }
                }
            }
            sum += first + last;
        }
        System.out.println(sum);
    }

    public static void b1() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day1b"));
        int sum = 0;
        for (String line: file) {
            line = replaceSpelledOutDigits(line);
            char[] chars = line.toCharArray();
            int first = -1;
            int last = -1;
            for (char c: chars) {
                if (Character.isDigit(c)) {
                    if (first == -1) {
                        first = (int)(c - '0') * 10;
                        last = (int)(c - '0');
                    } else {
                        last = (int)(c - '0');
                    }
                }
            }
            System.out.println(first + last);
            sum += first + last;
        }
        System.out.println(sum);
    }

    public static String replaceSpelledOutDigits(String line) {
        String[][] numbers = {{"one", "on1e"}, {"two", "tw2o"}, {"three", "thre3e"}, {"four", "fou4r"},
                {"five", "fiv5e"}, {"six", "si6x"}, {"seven", "seve7n"}, {"eight", "eigh8t"}, {"nine", "ni9ne"}};

        for (String[] number: numbers) {
            line = line.replace(number[0], number[1]);
        }

        return line;
    }
}