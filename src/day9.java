import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static helpers.helpers.stringArrToIntList;

public class day9 {
    public static void main(String[] args) throws IOException {
        b9();
    }

    public static void a9() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day9a"));
        int sum = 0;
        for (String line: file) {
            List<Integer> temp_list = stringArrToIntList(line);

            List<ArrayList<Integer>> list_list = new ArrayList<>();
            list_list.add((ArrayList<Integer>) temp_list);

            boolean isnotzeroes = true;

            while (isnotzeroes) {
                ArrayList<Integer> current_list = list_list.get(list_list.size() - 1);
                ArrayList<Integer> next_list = new ArrayList<>();

                int previous_element = current_list.get(0);

                for (int i = 1; i < current_list.size(); i++) {
                    next_list.add(current_list.get(i) - previous_element);
                    previous_element = current_list.get(i);
                }
                boolean containsnotzero = false;
                for (Integer i : next_list) {
                    if (i != 0) {
                        containsnotzero = true;
                        break;
                    }
                }
                if (!containsnotzero) {
                    isnotzeroes = false;
                }
                list_list.add(next_list);
            }

            list_list.get(list_list.size() - 1).add(0);

            for (int i = list_list.size() - 2; i >= 0; i--) {
                list_list.get(i).add(list_list.get(i).get(list_list.get(i).size() - 1) + list_list.get(i + 1).get(list_list.get(i + 1).size() - 1));
            }
            sum += list_list.get(0).get(list_list.get(0).size() - 1);
        }
        System.out.println(sum);
    }

    public static void b9() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day9a"));
        int sum = 0;
        for (String line: file) {
            List<Integer> temp_list = stringArrToIntList(line);

            List<ArrayList<Integer>> list_list = new ArrayList<>();
            list_list.add((ArrayList<Integer>) temp_list);

            boolean isnotzeroes = true;

            while (isnotzeroes) {
                ArrayList<Integer> current_list = list_list.get(list_list.size() - 1);
                ArrayList<Integer> next_list = new ArrayList<>();

                int previous_element = current_list.get(0);

                for (int i = 1; i < current_list.size(); i++) {
                    next_list.add(current_list.get(i) - previous_element);
                    previous_element = current_list.get(i);
                }
                boolean containsnotzero = false;
                for (Integer i : next_list) {
                    if (i != 0) {
                        containsnotzero = true;
                        break;
                    }
                }
                if (!containsnotzero) {
                    isnotzeroes = false;
                }
                list_list.add(next_list);
            }

            list_list.get(list_list.size() - 1).add(0, 0);

            for (int i = list_list.size() - 2; i >= 0; i--) {
                list_list.get(i).add(0, list_list.get(i).get(0) - list_list.get(i + 1).get(0));
            }
            sum += list_list.get(0).get(0);
        }
        System.out.println(sum);
    }

}