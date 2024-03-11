import helpers.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day11 {
    public static void main(String[] args) throws IOException {

        b11();
    }
    public static void a11() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day11a"));
        char[][] field = new char[file.size()][file.get(0).length()];

        for (int i = 0; i < file.size(); i++)  {
            String line = file.get(i);
            char[] chars = line.toCharArray();
            System.arraycopy(chars, 0, field[i], 0, chars.length);
        }
        int addrows = 0;
        for (int i = 0; i < field.length; i++) {
            boolean containsnogalaxy = true;
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '#') {
                    containsnogalaxy = false;
                    break;
                }
            }
            if (containsnogalaxy) {
                addrows++;
            }
        }
        char[][] new_field = new char[field.length + addrows][field[0].length];
        int oldi = 0;
        for (int i = 0; i < new_field.length; i++) {
            boolean containsnogalaxy = true;
            for (int j = 0; j < new_field[i].length; j++) {
                new_field[i][j] = field[oldi][j];
                if (field[oldi][j] == '#') {
                    containsnogalaxy = false;
                }
            }
            if (containsnogalaxy) {
                i++;
                Arrays.fill(new_field[i], '.');
            }
            oldi++;
        }

        int addcols = 0;
        for (int j = 0; j < new_field[0].length; j++) {
            boolean containsnogalaxy = true;
            for (int i = 0; i < new_field.length; i++) {
                if (new_field[i][j] == '#') {
                    containsnogalaxy = false;
                    break;
                }
            }
            if (containsnogalaxy) {
                addcols++;
            }
        }

        char[][] new_new_field = new char[new_field.length][new_field[0].length + addcols];
        int oldj = 0;
        for (int j = 0; j < new_new_field[0].length; j++) {
            boolean containsnogalaxy = true;
            for (int i = 0; i < new_new_field.length; i++) {
                new_new_field[i][j] = new_field[i][oldj];
                if (new_field[i][oldj] == '#') {
                    containsnogalaxy = false;
                }
            }
            if (containsnogalaxy) {
                j++;
                for (int i = 0; i < new_new_field.length; i++) {
                    new_new_field[i][j] = '.';
                }
            }
            oldj++;
        }


        /*for (int i = 0; i < new_new_field.length; i++) {
            for (int j = 0; j < new_new_field[0].length; j++) {
                System.out.print(new_new_field[i][j]);
            }
            System.out.println();
        }
        System.out.println(new_new_field.length + " " + new_new_field[0].length);*/
        List<Pair<Integer, Integer>> l = new ArrayList<>();
        for (int i = 0; i < new_new_field.length; i++) {
            for (int j = 0; j < new_new_field[0].length; j++) {
                if (new_new_field[i][j] == '#') {
                    l.add(new Pair<>(i, j));
                }
            }
        }
        int sum = 0;
        while (!l.isEmpty()) {
            Pair<Integer, Integer> p = l.get(0);
            l.remove(0);

            for (Pair<Integer, Integer> op : l) {
                sum += Math.abs(p.getL()-op.getL()) + Math.abs(p.getR() - op.getR());
            }


        }


        System.out.println(sum);
    }

    public static void b11() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day11a"));
        char[][] field = new char[file.size()][file.get(0).length()];
        //int larger = 10;
        int larger = 1000000;

        for (int i = 0; i < file.size(); i++)  {
            String line = file.get(i);
            char[] chars = line.toCharArray();
            System.arraycopy(chars, 0, field[i], 0, chars.length);
        }

        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            boolean containsnogalaxy = true;
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '#') {
                    containsnogalaxy = false;
                    break;
                }
            }
            if (containsnogalaxy) {
                rows.add(i);
            }
        }


        List<Integer> cols = new ArrayList<>();
        for (int j = 0; j < field[0].length; j++) {
            boolean containsnogalaxy = true;
            for (int i = 0; i < field.length; i++) {
                if (field[i][j] == '#') {
                    containsnogalaxy = false;
                    break;
                }
            }
            if (containsnogalaxy) {
                cols.add(j);
            }
        }

        List<Pair<Integer, Integer>> l = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == '#') {
                    l.add(new Pair<>(i, j));
                }
            }
        }
        int sum = 0;
        while (!l.isEmpty()) {
            Pair<Integer, Integer> p = l.get(0);
            l.remove(0);

            for (Pair<Integer, Integer> op : l) {
                int bigger_i = p.getL() > op.getL() ? p.getL() : op.getL();
                int smaller_i = p.getL() < op.getL() ? p.getL() : op.getL();
                int bigger_j = p.getR() > op.getR() ? p.getR() : op.getR();
                int smaller_j = p.getR() < op.getR() ? p.getR() : op.getR();

                int number_of_empty_lines = 0;

                for (int row : rows) {
                    if (row < bigger_i) {
                        if (row > smaller_i) {
                            number_of_empty_lines++;
                        }
                    } else {
                        break;
                    }
                }

                for (int col : cols) {
                    if (col < bigger_j) {
                        if (col > smaller_j) {
                            number_of_empty_lines++;
                        }
                    } else {
                        break;
                    }
                }


                sum += (bigger_i - smaller_i) + (bigger_j - smaller_j) + (number_of_empty_lines * (larger - 1));
            }
        }


        System.out.println(sum);
    }
}