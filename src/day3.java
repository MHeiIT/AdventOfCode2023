import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class day3 {
    public static void main(String[] args) throws IOException {

        b3();
    }

    public static void a3() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day3a"));
        List<char[]> puzzle = new ArrayList<>();

        for (String line : file) {
            puzzle.add(line.toCharArray());
        }



        int sum = 0;

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).length; j++) {
                if (isSymbol(puzzle.get(i)[j])) {
                    for (int k = i-1; k <= i+1; k++) {
                        if (k < 0 || k >= puzzle.size()) {
                            continue;
                        }
                        for (int l = j-1; l <= j+1; l++) {
                            if (l < 0 || l >= puzzle.get(k).length) {
                                continue;
                            }
                            if (Character.isDigit(puzzle.get(k)[l])) {
                                int pos = l;
                                while (true) {
                                    if (!Character.isDigit(puzzle.get(k)[pos])) {
                                        pos++;
                                        break;
                                    }
                                    if (pos == 0) {
                                        break;
                                    }
                                    pos--;
                                }
                                StringBuilder number = new StringBuilder();

                                while (true) {
                                    if (Character.isDigit(puzzle.get(k)[pos])) {
                                        number.append(puzzle.get(k)[pos]);
                                        puzzle.get(k)[pos] = '.';
                                    } else {
                                        break;
                                    }
                                    pos++;
                                    if (pos >= puzzle.get(k).length) {
                                        break;
                                    }

                                }

                                sum += Integer.parseInt(number.toString());

                            }
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public static boolean isSymbol(char character) {
        char[] chars = "0123456789.".toCharArray();

        for (char c: chars) {
            if (character == c) {
                return false;
            }
        }
        return true;
    }

    public static void b3() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day3a"));
        List<char[]> puzzle = new ArrayList<>();

        for (String line : file) {
            puzzle.add(line.toCharArray());
        }



        int sum = 0;

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).length; j++) {
                if (puzzle.get(i)[j] == '*') {
                    List<Integer> adjacent = new ArrayList<>();
                    for (int k = i-1; k <= i+1; k++) {
                        if (k < 0 || k >= puzzle.size()) {
                            continue;
                        }
                        for (int l = j-1; l <= j+1; l++) {
                            if (l < 0 || l >= puzzle.get(k).length) {
                                continue;
                            }
                            if (Character.isDigit(puzzle.get(k)[l])) {
                                int pos = l;
                                while (true) {
                                    if (!Character.isDigit(puzzle.get(k)[pos])) {
                                        pos++;
                                        break;
                                    }
                                    if (pos == 0) {
                                        break;
                                    }
                                    pos--;
                                }
                                StringBuilder number = new StringBuilder();

                                while (true) {
                                    if (Character.isDigit(puzzle.get(k)[pos])) {
                                        number.append(puzzle.get(k)[pos]);
                                        puzzle.get(k)[pos] = '.';
                                    } else {
                                        break;
                                    }
                                    pos++;
                                    if (pos >= puzzle.get(k).length) {
                                        break;
                                    }

                                }
                                adjacent.add(Integer.parseInt(number.toString()));
                            }
                        }
                    }

                    if (adjacent.size() == 2) {
                        sum += adjacent.get(0) * adjacent.get(1);
                    }

                }
            }
        }
        System.out.println(sum);
    }

}