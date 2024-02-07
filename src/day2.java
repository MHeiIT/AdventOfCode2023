import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class day2 {
    public static void main(String[] args) throws IOException {

        b2();
    }

    public static void a2() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day2a"));
        int sum = 0;
        for (String line: file) {
            String[] split_line = line.split(":");
            int game_number = Integer.parseInt(split_line[0].split(" ")[1]);
            String new_line = split_line[1];

            String[] cube_subsets = new_line.split(";");
            boolean isPossible = true;

            outerloop: for (String cube_subset: cube_subsets) {
                String[] cubes = cube_subset.split(",");
                for (String one_cube_color: cubes) {
                    int cube_Count = Integer.parseInt(one_cube_color.substring(1).split(" ")[0]);
                    String cube_color = one_cube_color.substring(1).split(" ")[1];
                    if (cube_color.compareTo("red") == 0 && cube_Count > 12) {
                        isPossible = false;
                        break outerloop;
                    } else if (cube_color.compareTo("blue") == 0 && cube_Count > 14) {
                        isPossible = false;
                        break outerloop;
                    } else if (cube_color.compareTo("green") == 0 && cube_Count > 13) {
                        isPossible = false;
                        break outerloop;
                    }
                }
            }

            if (isPossible) {
                sum += game_number;
            }

        }
        System.out.println(sum);
    }

    public static void b2() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day2a"));
        int sum = 0;
        for (String line: file) {
            String[] split_line = line.split(":");
            int game_number = Integer.parseInt(split_line[0].split(" ")[1]);
            String new_line = split_line[1];

            String[] cube_subsets = new_line.split(";");
            int red_cube_count = 0;
            int blue_cube_count = 0;
            int green_cube_count = 0;

            for (String cube_subset: cube_subsets) {
                String[] cubes = cube_subset.split(",");
                for (String one_cube_color: cubes) {
                    int cube_Count = Integer.parseInt(one_cube_color.substring(1).split(" ")[0]);
                    String cube_color = one_cube_color.substring(1).split(" ")[1];
                    if (cube_color.compareTo("red") == 0 && cube_Count > red_cube_count) {
                        red_cube_count = cube_Count;
                    } else if (cube_color.compareTo("blue") == 0 && cube_Count > blue_cube_count) {
                        blue_cube_count = cube_Count;
                    } else if (cube_color.compareTo("green") == 0 && cube_Count > green_cube_count) {
                        green_cube_count = cube_Count;
                    }
                }
            }

            sum += red_cube_count * blue_cube_count * green_cube_count;

        }
        System.out.println(sum);
    }
}