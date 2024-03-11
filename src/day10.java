import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        char[][] loopfield = new char[file.size()][file.get(0).length()];
        char[][] original_loopfield = new char[file.size()][file.get(0).length()];
        for (char[] chars : loopfield) {
            Arrays.fill(chars, '.');
        }
        for (char[] chars : original_loopfield) {
            Arrays.fill(chars, '.');
        }
        loopfield[currentposi][currentposj] = 'L';
        loopfield[nextposi][nextposj] = 'L';
        original_loopfield[currentposi][currentposj] = field[currentposi][currentposj];
        original_loopfield[nextposi][nextposj] = field[nextposi][nextposj];

        while (field[nextposi][nextposj] != 'S') {
            int[] nextnextpos = getNextNextPosition(field[nextposi][nextposj], nextposi, nextposj, currentposi, currentposj);
            currentposi = nextposi;
            currentposj = nextposj;
            nextposi = nextnextpos[0];
            nextposj = nextnextpos[1];
            loopfield[nextposi][nextposj] = 'L';
            original_loopfield[nextposi][nextposj] = field[nextposi][nextposj];
        }

        for (int i = 0; i < original_loopfield.length; i++) {
            if (original_loopfield[i][0] == '.') {
                original_loopfield[i][0] = 'O';
            }
            if (original_loopfield[i][original_loopfield[i].length - 1] == '.') {
                original_loopfield[i][original_loopfield[i].length - 1] = 'O';
            }
        }

        for (int i = 0; i < original_loopfield[0].length; i++) {
            if (original_loopfield[0][i] == '.') {
                original_loopfield[0][i] = 'O';
            }
            if (original_loopfield[original_loopfield.length - 1][i] == '.') {
                original_loopfield[original_loopfield.length - 1][i] = 'O';
            }
        }

        boolean again = true;
        while (again) {
            again = false;
            for (int i = 1; i < (original_loopfield.length - 1); i++) {
                for (int j = 1; j < (original_loopfield[i].length - 1); j++) {
                    if (original_loopfield[i][j] == '.') {
                        if (original_loopfield[i-1][j] == 'O' || original_loopfield[i+1][j] == 'O' ||
                                original_loopfield[i][j-1] == 'O' || original_loopfield[i][j+1] == 'O') {
                            original_loopfield[i][j] = 'O';
                            again = true;
                        }
                    }
                }
            }
        }

        String right = "west";
        String left = "east";
        boolean rightisoutside = false;

        currentposi = 0;
        currentposj = 0;
        outerloop: for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'S') {
                    currentposi = i;
                    currentposj = j;
                    break outerloop;
                }
            }
        }

        nextposi = currentposi + 1;
        nextposj = currentposj;

        while (field[nextposi][nextposj] != 'S') {
            int[] nextnextpos = getNextNextPosition(field[nextposi][nextposj], nextposi, nextposj, currentposi, currentposj);
            currentposi = nextposi;
            currentposj = nextposj;
            nextposi = nextnextpos[0];
            nextposj = nextnextpos[1];
            if (Objects.equals(right, "west") || Objects.equals(right, "east")) {
                if (original_loopfield[currentposi][currentposj] == '|') {

                } else if (original_loopfield[currentposi][currentposj] == 'L') {
                    right = "south";
                    left = "north";
                } else if (original_loopfield[currentposi][currentposj] == 'J') {
                    right = "north";
                    left = "south";
                } else if (original_loopfield[currentposi][currentposj] == '7') {
                    right = "north";
                    left = "south";
                } else if (original_loopfield[currentposi][currentposj] == 'F') {
                    right = "south";
                    left = "north";
                } else {
                    System.exit(-1);
                }
            } else {
                if (original_loopfield[currentposi][currentposj] == '-') {

                } else if (original_loopfield[currentposi][currentposj] == 'L') {
                    right = "east";
                    left = "west";
                } else if (original_loopfield[currentposi][currentposj] == 'J') {
                    right = "east";
                    left = "west";
                } else if (original_loopfield[currentposi][currentposj] == '7') {
                    right = "west";
                    left = "east";
                } else if (original_loopfield[currentposi][currentposj] == 'F') {
                    right = "west";
                    left = "east";
                } else {
                    System.exit(-1);
                }
            }

            if (Objects.equals(right, "west")) {
                if (0 <= (currentposj - 1) && original_loopfield[currentposi][currentposj - 1] == 'O') {
                    rightisoutside = true;
                    break;
                } else if (original_loopfield[currentposi].length > currentposj + 1 && original_loopfield[currentposi][currentposj + 1] == 'O') {
                    rightisoutside = false;
                    break;
                }
            } else if (Objects.equals(right, "east")) {
                if (0 <= (currentposj - 1) && original_loopfield[currentposi][currentposj - 1] == 'O') {
                    rightisoutside = false;
                    break;
                } else if (original_loopfield[currentposi].length > currentposj + 1 && original_loopfield[currentposi][currentposj + 1] == 'O') {
                    rightisoutside = true;
                    break;
                }
            } else if (Objects.equals(right, "north")) {
                if (0 <= (currentposi - 1) && original_loopfield[currentposi - 1][currentposj] == 'O') {
                    rightisoutside = true;
                    break;
                } else if (original_loopfield.length > currentposi + 1 && original_loopfield[currentposi + 1][currentposj] == 'O') {
                    rightisoutside = false;
                    break;
                }
            } else {
                if (0 <= (currentposi - 1) && original_loopfield[currentposi - 1][currentposj] == 'O') {
                    rightisoutside = false;
                    break;
                } else if (original_loopfield.length > currentposi + 1 && original_loopfield[currentposi + 1][currentposj] == 'O') {
                    rightisoutside = true;
                    break;
                }
            }
        }
        right = "west";
        left = "east";
        System.out.println(rightisoutside);
        currentposi = 0;
        currentposj = 0;
        outerloop: for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'S') {
                    currentposi = i;
                    currentposj = j;
                    break outerloop;
                }
            }
        }

        nextposi = currentposi + 1;
        nextposj = currentposj;
        while (field[nextposi][nextposj] != 'S') {
            int[] nextnextpos = getNextNextPosition(field[nextposi][nextposj], nextposi, nextposj, currentposi, currentposj);
            currentposi = nextposi;
            currentposj = nextposj;
            nextposi = nextnextpos[0];
            nextposj = nextnextpos[1];
            if (Objects.equals(right, "west") || Objects.equals(right, "east")) {
                if (original_loopfield[currentposi][currentposj] == '|') {

                } else if (original_loopfield[currentposi][currentposj] == 'L') {
                    right = "south";
                } else if (original_loopfield[currentposi][currentposj] == 'J') {
                    right = "north";
                } else if (original_loopfield[currentposi][currentposj] == '7') {
                    right = "north";
                } else if (original_loopfield[currentposi][currentposj] == 'F') {
                    right = "south";
                } else {
                    System.exit(-1);
                }
            } else {
                if (original_loopfield[currentposi][currentposj] == '-') {

                } else if (original_loopfield[currentposi][currentposj] == 'L') {
                    right = "east";
                } else if (original_loopfield[currentposi][currentposj] == 'J') {
                    right = "east";
                } else if (original_loopfield[currentposi][currentposj] == '7') {
                    right = "west";
                } else if (original_loopfield[currentposi][currentposj] == 'F') {
                    right = "west";
                } else {
                    System.exit(-1);
                }
            }

            if (Objects.equals(right, "west")) {
                if (rightisoutside) {
                    if (0 <= (currentposj - 1) && original_loopfield[currentposi][currentposj - 1] == '.') {
                        original_loopfield[currentposi][currentposj - 1] = 'O';
                    }
                } else {
                    if (original_loopfield[currentposi].length > currentposj + 1 && original_loopfield[currentposi][currentposj + 1] == '.') {
                        original_loopfield[currentposi][currentposj + 1] = 'O';
                    }
                }
            } else if (Objects.equals(right, "east")) {
                if (rightisoutside) {
                    if (original_loopfield[currentposi].length > currentposj + 1 && original_loopfield[currentposi][currentposj + 1] == '.') {
                        original_loopfield[currentposi][currentposj + 1] = 'O';
                    }
                } else {
                    if (0 <= (currentposj - 1) && original_loopfield[currentposi][currentposj - 1] == '.') {
                        original_loopfield[currentposi][currentposj - 1] = 'O';
                    }
                }
            } else if (Objects.equals(right, "north")) {
                if (rightisoutside) {
                    if (0 <= (currentposi - 1) && original_loopfield[currentposi - 1][currentposj] == '.') {
                        original_loopfield[currentposi - 1][currentposj] = 'O';
                    }
                } else {
                    if (original_loopfield.length > currentposi + 1 && original_loopfield[currentposi + 1][currentposj] == '.') {
                        original_loopfield[currentposi + 1][currentposj] = 'O';
                    }
                }
            } else {
                if (rightisoutside) {
                    if (original_loopfield.length > currentposi + 1 && original_loopfield[currentposi + 1][currentposj] == '.') {
                        original_loopfield[currentposi + 1][currentposj] = 'O';
                    }
                } else {
                    if (0 <= (currentposi - 1) && original_loopfield[currentposi - 1][currentposj] == '.') {
                        original_loopfield[currentposi - 1][currentposj] = 'O';
                    }
                }
            }
        }

        again = true;
        while (again) {
            again = false;
            for (int i = 1; i < (original_loopfield.length - 1); i++) {
                for (int j = 1; j < (original_loopfield[i].length - 1); j++) {
                    if (original_loopfield[i][j] == '.') {
                        if (original_loopfield[i-1][j] == 'O' || original_loopfield[i+1][j] == 'O' ||
                                original_loopfield[i][j-1] == 'O' || original_loopfield[i][j+1] == 'O') {
                            original_loopfield[i][j] = 'O';
                            again = true;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < original_loopfield.length; i++) {
            for (int j = 0; j < original_loopfield[i].length; j++) {
                System.out.print(original_loopfield[i][j]);
                if (original_loopfield[i][j] == '.') {
                    sum++;
                }
            }
            System.out.println();
        }



        System.out.println(sum);
    }

}