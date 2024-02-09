import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static helpers.helpers.*;

public class day6 {
    public static void main(String[] args) throws IOException {

        b6();
    }

    public static void a6() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day6a"));
        int[] time = stringArrToIntArr(replacedoublespaces(file.get(0)).split(": ")[1]);
        int[] distance = stringArrToIntArr(replacedoublespaces(file.get(1)).split(": ")[1]);

        int sum = 1;
        for (int i = 0; i < time.length; i++) {
            int maxtime = time[i];
            int counter = 0;
            for (int j = 1; j < maxtime; j++) {
                int dist = j * (maxtime - j);
                if (dist > distance[i]) {
                    counter++;
                }
            }
            sum *= counter;
        }

        System.out.println(sum);

    }

    public static void b6() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day6a"));
        long time = Long.parseLong(file.get(0).replace(" ", "").split(":")[1]);
        long distance = Long.parseLong(file.get(1).replace(" ", "").split(":")[1]);


        long counter = 0;
        for (long j = 1; j < time; j++) {
            long dist = j * (time - j);
            if (dist > distance) {
                counter++;
            }
        }


        System.out.println(counter);

    }



}