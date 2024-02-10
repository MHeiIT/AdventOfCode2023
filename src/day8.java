import helpers.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static helpers.helpers.*;

public class day8 {
    public static void main(String[] args) throws IOException {

        b8();
    }
    public static void a8() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day8a"));
        Queue<Character> queue = charArrayToQueue(file.get(0).toCharArray());

        file.remove(0);
        file.remove(0);

        Map<String, Pair<String, String>> mp = new HashMap<>();
        for (String line: file) {
            String newline = line.replace(")", "").replace(" = (", ",").replace(" ", "");
            String[] splitline = newline.split(",");
            String key = splitline[0];

            Pair<String, String> p = new Pair<>(splitline[1], splitline[2]);
            mp.put(key, p);
        }

        int sum = 0;
        String current_pos = "AAA";

        while (queue.size() != 0) { // is always true
            char c = queue.poll();

            if (c == 'R') {
                current_pos = mp.get(current_pos).getR();
            } else {
                current_pos = mp.get(current_pos).getL();
            }

            sum++;

            if (Objects.equals(current_pos, "ZZZ")) {
                break;
            }

            queue.offer(c);
        }


        System.out.println(sum);
    }

    public static void b8() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day8b"));
        Queue<Character> queue = charArrayToQueue(file.get(0).toCharArray());

        file.remove(0);
        file.remove(0);
        Map<String, Pair<String, String>> mp = new HashMap<>();
        List<String> current_posses_list = new ArrayList<>();
        for (String line: file) {
            String newline = line.replace(")", "").replace(" = (", ",").replace(" ", "");
            String[] splitline = newline.split(",");
            String key = splitline[0];

            Pair<String, String> p = new Pair<>(splitline[1], splitline[2]);
            mp.put(key, p);

            if (key.toCharArray()[2] == 'A') {
                current_posses_list.add(key);
            }
        }

        int sum = 0;
        String[] current_posses = current_posses_list.toArray(new String[0]);

        //System.out.println(current_posses.length);

        while (queue.size() != 0) { // is always true
            char c = queue.poll();

            sum++;
            boolean end = true;

            for (int i = 0; i < current_posses.length; i++) {
                if (c == 'R') {
                    current_posses[i] = mp.get(current_posses[i]).getR();
                } else {
                    current_posses[i] = mp.get(current_posses[i]).getL();
                }
                if (current_posses[i].toCharArray()[2] != 'Z') {
                    end = false;
                }
            }

            if (end) {
                break;
            }

            queue.offer(c);
        }

        System.out.println(sum);
        // (29998-14999)*x+14999 = (40186-20093)*x+20093= (34526-17263)*x+17263= (33394-16697)*x+16697= (24338-12169)*x+12169= (41318-20659)*x+20659
        //(29998-14999)*x+14999 = (40186-20093)*x+20093= (34526-17263)*x+17263= (33394-16697)*x+16697= (41318-20659)*x+20659
    }
}