import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class day10 {
    public static void main(String[] args) throws IOException {

        b10();
    }
    public static void a10() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day10a"));
        char[][] field = new char[file.size()][file.get(0).length()];


        for (int i = 0; i < file.size(); i++)  {
            String line = file.get(i);
            char[] chars = line.toCharArray();
            System.arraycopy(chars, 0, field[i], 0, chars.length);

        }

        int currentposi = 0;
        int currentposj = 0;
        outerloop: for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'S') {
                    currentposi = i;
                    currentposj = j;
                    break outerloop;
                }
            }
        }

        int nextposi = currentposi + 1;
        int nextposj = currentposj;
        int sum = 0;
        for (int i = 0; true; i++) {
            if (field[nextposi][nextposj] == 'S') {
                sum = i;
                break;
            }
            System.out.println(i);
            int[] nextnextpos = getNextNextPosition(field[nextposi][nextposj], nextposi, nextposj, currentposi, currentposj);
            currentposi = nextposi;
            currentposj = nextposj;
            nextposi = nextnextpos[0];
            nextposj = nextnextpos[1];
        }
        sum = (sum+1)/2;

        System.out.println(sum);
    }

    public static int[] getNextNextPosition(char field, int nextposi, int nextposj, int currentposi, int currentposj) {
        switch (field) {
            case '|':
                if (nextposi + 1 == currentposi) {
                    return new int[]{nextposi - 1, nextposj};
                } else {
                    return new int[]{nextposi + 1, nextposj};
                }
            case '-':
                if (nextposj + 1 == currentposj) {
                    return new int[]{nextposi, nextposj - 1};
                } else {
                    return new int[]{nextposi, nextposj + 1};
                }
            case 'L':
                if (nextposi - 1 == currentposi) {
                    return new int[]{nextposi, nextposj + 1};
                } else {
                    return new int[]{nextposi - 1, nextposj};
                }
            case 'J':
                if (nextposi - 1 == currentposi) {
                    return new int[]{nextposi, nextposj - 1};
                } else {
                    return new int[]{nextposi - 1, nextposj};
                }
            case '7':
                if (nextposi + 1 == currentposi) {
                    return new int[]{nextposi, nextposj - 1};
                } else {
                    return new int[]{nextposi + 1, nextposj};
                }
            case 'F':
                if (nextposi + 1 == currentposi) {
                    return new int[]{nextposi, nextposj + 1};
                } else {
                    return new int[]{nextposi + 1, nextposj};
                }
        }
        System.exit(-1);
        return new int[0];
    }

    public static void b10() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day10b_test"));
        char[][] field = new char[file.size()][file.get(0).length()];


        for (int i = 0; i < file.size(); i++)  {
            String line = file.get(i);
            char[] chars = line.toCharArray();
            System.arraycopy(chars, 0, field[i], 0, chars.length);

        }

        int currentposi = 0;
        int currentposj = 0;
        outerloop: for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'S') {
                    currentposi = i;
                    currentposj = j;
                    break outerloop;
                }
            }
        }

        int nextposi = currentposi + 1;
        int nextposj = currentposj;
        char[][] loopfield = new char[file.size()][file.get(0).length()];
        for (char[] chars : loopfield) {
            Arrays.fill(chars, '.');
        }
        loopfield[currentposi][currentposj] = 'L';
        loopfield[nextposi][nextposj] = 'L';

        for (int i = 0; true; i++) {
            if (field[nextposi][nextposj] == 'S') {
                break;
            }
            int[] nextnextpos = getNextNextPosition(field[nextposi][nextposj], nextposi, nextposj, currentposi, currentposj);
            currentposi = nextposi;
            currentposj = nextposj;
            nextposi = nextnextpos[0];
            nextposj = nextnextpos[1];
            loopfield[nextposi][nextposj] = 'L';
        }

        for (int i = 0; i < loopfield.length; i++) {
            for (int j = 0; j < loopfield[i].length; j++) {
                System.out.print(loopfield[i][j]);
            }
            System.out.println();
        }


        int sum = 0;
        System.out.println(sum);
    }

}