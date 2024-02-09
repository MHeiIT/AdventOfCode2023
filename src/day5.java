import helpers.RangeMapper;
import org.w3c.dom.ranges.Range;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class day5 {
    public static void main(String[] args) throws IOException {
        b5();
    }

    public static long[] stringArrToIntArr(String numbers) {
        String[] arr = numbers.split(" ");
        long[] nums = new long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            nums[i] = Long.parseLong(arr[i]);
        }

        return nums;
    }


    public static long[] iteration(List<String> lines, long[] seeds) {
        long[] nums = new long[seeds.length];

        for (String line: lines) {
            long[] dest_sor_range = stringArrToIntArr(line);

            long offset = dest_sor_range[0] - dest_sor_range[1];

            for (int i = 0; i < seeds.length; i++) {
                if (seeds[i] >= dest_sor_range[1] && seeds[i] < dest_sor_range[1] + dest_sor_range[2]) {
                    nums[i] = seeds[i] + offset;
                    seeds[i] = -1;
                }
            }
        }

        for (int i = 0; i < seeds.length; i++) {
            if (seeds[i] != -1) {
                nums[i] = seeds[i];
            }
        }


        return nums;
    }

    public static void a5() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day5a"));
        long[] seeds = stringArrToIntArr(file.get(0).split(": ")[1]);

        file.remove(0);
        file.remove(0);

        List<String> mapping = new ArrayList<>();

        while (!file.isEmpty()) {
            if (mapping.isEmpty()) {
                file.remove(0);
            }

            String line = file.get(0);

            if (line.isEmpty()) {
                seeds = iteration(mapping, seeds);
                mapping = new ArrayList<>();
                file.remove(0);
                continue;
            }

            mapping.add(line);
            file.remove(0);
        }
        seeds = iteration(mapping, seeds);

        long lowest_number = Long.MAX_VALUE;

        for (long num: seeds) {
            lowest_number = Long.min(lowest_number, num);
        }

        System.out.println(lowest_number);
    }



    public static RangeMapper iteration(List<String> lines) {
        RangeMapper rm = new RangeMapper();

        for (String line: lines) {
            long[] dest_sor_range = stringArrToIntArr(line);
            rm.add(dest_sor_range);
        }

        return rm;
    }

    //That one is bad terrible and really slow like it takes 365 seconds
    // How todo better:
    // squasch range mapper list into one range mapper.
    // only check the changes should be possible to do in O(1)
    public static void b5() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day5a"));
        long[] seeds = stringArrToIntArr(file.get(0).split(": ")[1]);

        file.remove(0);
        file.remove(0);

        List<RangeMapper> rm_list = new ArrayList<>();

        List<String> mapping = new ArrayList<>();

        while (!file.isEmpty()) {
            if (mapping.isEmpty()) {
                file.remove(0);
            }

            String line = file.get(0);

            if (line.isEmpty()) {
                rm_list.add(iteration(mapping));
                mapping = new ArrayList<>();
                file.remove(0);
                continue;
            }

            mapping.add(line);
            file.remove(0);
        }
        rm_list.add(iteration(mapping));

        long lowest_number = Long.MAX_VALUE;
        long max = 0;
        for (int i = 0; i < seeds.length; i++) {
            max += seeds[i+1];
            i++;
        }
        long startTime = System.nanoTime();

        long counter = 0;
        for (int i = 0; i < seeds.length; i++) {

            for (long j = seeds[i]; j < seeds[i] + seeds[i+1]; j++) {
                long num = j;
                for (RangeMapper rm: rm_list) {
                    num = rm.map(num);
                }
                lowest_number = Long.min(lowest_number, num);
            }
            counter += seeds[i+1];
            double d = (double) counter;
            double dd = (double) max;
            System.out.println(d / dd +"% Done");
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000_000.0; // Calculate the duration in seconds
            System.out.println("Time taken: " + duration + " seconds");

            i++;
        }
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000_000.0; // Calculate the duration in seconds
        System.out.println("Time taken: " + duration + " seconds");
        System.out.println(lowest_number);
    }


}