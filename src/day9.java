import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class day9 {
    public static void main(String[] args) throws IOException {

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
}