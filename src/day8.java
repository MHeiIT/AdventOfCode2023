import helpers.Pair;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static helpers.helpers.*;

public class day8 {
    public static void main(String[] args) throws IOException {

        //b8();
        calc();
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
        char[] chararr = file.get(0).toCharArray();

        file.remove(0);
        file.remove(0);
        Map<String, Pair<String, String>> mp = new HashMap<>();
        List<String> current_posses_list = new ArrayList<>();
        for (String line: file) {
            String newline = line.replace(")", "").replace(" = (", ",").replace(" ", "");
            String[] splitline = newline.split(",");
            String key = splitline[0];
            if (mp.containsKey(key)) {
                System.out.println("okok");
                System.exit(-1);
            }
            Pair<String, String> p = new Pair<>(splitline[1], splitline[2]);
            mp.put(key, p);

            if (key.toCharArray()[2] == 'A') {
                current_posses_list.add(key);
            }
        }

        int sum = 0;
        String[] current_posses = current_posses_list.toArray(new String[0]);

        //System.out.println(current_posses.length);
        int tt = 5;
        for (int j = 0; true; j++) {
            char c = chararr[j % chararr.length];


            boolean end = true;

            for (int i = 3; i < current_posses.length; i++) {
                if (c == 'R') {
                    current_posses[i] = mp.get(current_posses[i]).getR();
                } else {
                    current_posses[i] = mp.get(current_posses[i]).getL();
                }
                if (current_posses[i].toCharArray()[current_posses[i].toCharArray().length - 1] != 'Z') {
                    end = false;
                }
                break;
            }

            if (end) {
                if (tt != 0) {
                    System.out.println(j+1);
                    tt--;
                } else {
                    sum = j + 1;
                    break;
                }

            }
        }

        System.out.println(sum);
        // (29998-14999)*x+14999 = (40186-20093)*x+20093= (34526-17263)*x+17263= (33394-16697)*x+16697= (24338-12169)*x+12169= (41318-20659)*x+20659
        //(29998-14999)*x+14999 = (40186-20093)*x+20093= (34526-17263)*x+17263= (33394-16697)*x+16697= (41318-20659)*x+20659
        // 14999*x+14999 = 20093*y+20093= 17263*z+17263=16697*v+16697= 20659*w+20659
        // 14999*x = 20093*y= 17263*z=16697*v= 20659*w
        // x mod 14999 = x mod 20093 = x mod 17263 = x mod 16697 = x mod 20659
        // solve x mod 14999 = 0 && x mod 20093 = 0 && x mod 17263 = 0 && x mod 16697 = 0 && x mod 20659
        // Element[x, Integers] && x > 0 && Element[y, Integers] && y > 0 && Element[z, Integers] && z > 0 && Element[v, Integers] && v > 0 && Element[w, Integers] && w > 0
        // 14999*x+14999 = 20093*y+20093= 17263*z+17263=16697*v+16697= 20659*w+20659 && Element[x, Integers]&& Element[y, Integers]&& Element[z, Integers] && Element[v, Integers] && Element[w, Integers]
    }

    public static void calc() {
        BigInteger[] ints = {BigInteger.valueOf(14999), BigInteger.valueOf(20093),
                BigInteger.valueOf(17263), BigInteger.valueOf(16697),
                BigInteger.valueOf(20659)};

        BigInteger lcm = calculateLCM(ints);

        BigInteger test = lcm(ints);

        System.out.println("The least common multiple (LCM) is: " + lcm);
        System.out.println("The least common multiple (LCM) is: " + test);
        BigInteger product = multiplyBigIntegers(ints);

        System.out.println("The product of all BigIntegers is: " + product);
    }

    // to high 311653045202070000
    // to low 279785601383
    public static BigInteger multiplyBigIntegers(BigInteger[] bigIntegers) {
        BigInteger product = BigInteger.ONE;
        for (BigInteger num : bigIntegers) {
            product = product.multiply(num);
        }
        return product;
    }
    public static BigInteger calculateLCM(BigInteger[] moduli) {
        BigInteger lcm = moduli[0];
        for (int i = 1; i < moduli.length; i++) {
            lcm = lcm.multiply(moduli[i]).divide(lcm.gcd(moduli[i]));
        }
        return lcm;
    }

    public static BigInteger lcm(BigInteger... values) {
        if (values.length == 0)
            return BigInteger.ONE;
        BigInteger lcm = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i].signum() != 0) {
                final BigInteger gcd = lcm.gcd(values[i]);
                if (gcd.equals(BigInteger.ONE)) {
                    lcm = lcm.multiply(values[i]);
                } else {
                    if (!values[i].equals(gcd)) {
                        lcm = lcm.multiply(values[i].divide(gcd));
                    }
                }
            }
        }
        return lcm;
    }
}