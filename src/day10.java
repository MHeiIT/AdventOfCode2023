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
                if (loopfield[i][j] == 'L') {
                    loopfield[i][j] = field[i][j];
                }
                //System.out.print(loopfield[i][j]);
            }
            //System.out.println();
        }

        /*System.out.println("---------------------------");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");*/

        char[][] newarr = double_size(loopfield); //double_size(double_size(loopfield));
        /*for (int i = 0; i < newarr.length; i++) {
            for (int j = 0; j < newarr[i].length; j++) {
                System.out.print(newarr[i][j]);
            }
            System.out.println();
        }*/

        for (int i = 0; i < newarr.length; i++) {
            if (newarr[i][0] == '.') {
                newarr[i][0] = 'O';
            }
            if (newarr[i][newarr[i].length-1] == '.') {
                newarr[i][newarr[i].length-1] = 'O';
            }
        }
        for (int i = 0; i < newarr[0].length; i++) {
            if (newarr[0][i] == '.') {
                newarr[0][i] = 'O';
            }
            if (newarr[newarr.length-1][i] == '.') {
                newarr[newarr.length-1][i] = 'O';
            }
        }

        boolean t = true;

        while (t) {
            t = false;
            for (int i = 0; i < newarr.length; i++) {
                for (int j = 0; j < newarr[i].length; j++) {
                    if (newarr[i][j] == '.') {
                        if ((i != 0) && (newarr[i - 1][j] == 'O')) {
                            t = true;
                            newarr[i][j] = 'O';
                        } else if ((i != (newarr.length - 1)) && (newarr[i + 1][j] == 'O')) {
                            t = true;
                            newarr[i][j] = 'O';
                        } else if ((j != 0) && (newarr[i][j - 1] == 'O')) {
                            t = true;
                            newarr[i][j] = 'O';
                        } else if ((j != (newarr[i].length - 1)) && (newarr[i][j + 1] == 'O')) {
                            t = true;
                            newarr[i][j] = 'O';
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < newarr.length; i++) {
            for (int j = 0; j < newarr[i].length; j++) {
                if (newarr[i][j] == '.') {
                    if (i % 2 == 0 && j % 2 == 0) {
                        sum++;
                    }
                }
            }
        }



        System.out.println(sum);
    }

    public static char[][] double_size(char[][] arr) {
        char[][] field = new char[arr.length*2][arr[0].length*2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '.';
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                field[i*2][j*2] = arr[i][j];
            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '.') {
                    if ((i != 0) && ((field[i - 1][j] == 'F') || (field[i - 1][j] == '|') || (field[i - 1][j] == '7'))) {
                        field[i][j] = '|';
                    } else if ((i != (field.length - 1)) && ((field[i + 1][j] == 'J') || (field[i + 1][j] == '|') || (field[i + 1][j] == 'L'))) {
                        field[i][j] = '|';
                    } else if ((j != 0) && ((field[i][j - 1] == 'F') || (field[i][j - 1] == '-') || (field[i][j - 1] == 'L'))) {
                        field[i][j] = '-';
                    } else if ((j != (field[i].length - 1)) && ((field[i][j + 1] == 'J') || (field[i][j + 1] == '-') || (field[i][j + 1] == '7'))) {
                        field[i][j] = '-';
                    }
                }
            }
        }



        return field;
    }
}