import helpers.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static helpers.helpers.countDifferentCharacters;


public class day7 {
    public static void main(String[] args) throws IOException {

        b7();
    }
    public static void a7() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day7a"));
        List<Pair<String, Integer>> five = new ArrayList<>();
        List<Pair<String, Integer>> four = new ArrayList<>();
        List<Pair<String, Integer>> house = new ArrayList<>();
        List<Pair<String, Integer>> three = new ArrayList<>();
        List<Pair<String, Integer>> twop = new ArrayList<>();
        List<Pair<String, Integer>> onep = new ArrayList<>();
        List<Pair<String, Integer>> high = new ArrayList<>();

        for (String line: file) {
            Pair<String, Integer> p = new Pair<>(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
            int number_of_difchars = countDifferentCharacters(p.getKey());
            if (number_of_difchars == 1) {
                five.add(p);
            } else if (number_of_difchars == 2) {
                if (isFourOfAKind(p.getKey())) {
                    four.add(p);
                } else {
                    house.add(p);
                }
            } else if (number_of_difchars == 3) {
                if (isThreeOfAKind(p.getKey())) {
                    three.add(p);
                } else {
                    twop.add(p);
                }
            } else if (number_of_difchars == 4) {
                onep.add(p);
            } else if (number_of_difchars == 5) {
                high.add(p);
            } else {
                throw new IOException();
            }
        }

        sortPairsByWeight(five);
        sortPairsByWeight(four);
        sortPairsByWeight(house);
        sortPairsByWeight(three);
        sortPairsByWeight(twop);
        sortPairsByWeight(onep);
        sortPairsByWeight(high);
        List<List<Pair<String, Integer>>> l = new ArrayList<>();
        l.add(five);
        l.add(four);
        l.add(house);
        l.add(three);
        l.add(twop);
        l.add(onep);
        l.add(high);
        int sum = 0;
        int counter = file.size();
        for (List<Pair<String, Integer>> lp: l) {
            for (Pair<String, Integer> p:lp) {
                sum += counter * p.getValue();
                counter--;
            }
        }
        System.out.println(sum);
    }
    public static void sortPairsByWeight(List<Pair<String, Integer>> pairs) {
        Collections.sort(pairs, (pair1, pair2) -> Long.compare(getWeight(pair2), getWeight(pair1)));
    }

    public static long getWeight(Pair<String, Integer> pair) {
        String str = pair.getKey();

        str = str.replace("A", "e");
        str = str.replace("T", "a");
        str = str.replace("J", "b");
        str = str.replace("Q", "c");
        str = str.replace("K", "d");
        return Long.parseLong(str, 16);
    }

    private static boolean isThreeOfAKind(String key) {
        char[] arr = key.toCharArray();
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            int tcounter = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == arr[i]) {
                    tcounter++;
                }
            }
            counter = Integer.max(tcounter, counter);
        }
        return counter == 3;
    }

    private static boolean isFourOfAKind(String key) {
        char[] arr = key.toCharArray();
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            int tcounter = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == arr[i]) {
                    tcounter++;
                }
            }
            counter = Integer.max(tcounter, counter);
        }
        return counter == 4;
    }

    public static void b7() throws IOException {
        List<String> file = Files.readAllLines(Path.of("./vendor/day7a"));
        List<Pair<String, Integer>> five = new ArrayList<>();
        List<Pair<String, Integer>> four = new ArrayList<>();
        List<Pair<String, Integer>> house = new ArrayList<>();
        List<Pair<String, Integer>> three = new ArrayList<>();
        List<Pair<String, Integer>> twop = new ArrayList<>();
        List<Pair<String, Integer>> onep = new ArrayList<>();
        List<Pair<String, Integer>> high = new ArrayList<>();

        for (String line: file) {
            Pair<String, Integer> p = new Pair<>(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
            int number_of_difchars = countDifferentCharactersJoker(p.getKey());
            if (number_of_difchars == 1) {
                five.add(p);
            } else if (number_of_difchars == 2) {
                if (isFourOfAKindJoker(p.getKey())) {
                    four.add(p);
                } else {
                    house.add(p);
                }
            } else if (number_of_difchars == 3) {
                if (isThreeOfAKindJoker(p.getKey())) {
                    three.add(p);
                } else {
                    twop.add(p);
                }
            } else if (number_of_difchars == 4) {
                onep.add(p);
            } else if (number_of_difchars == 5) {
                high.add(p);
            } else {
                throw new IOException();
            }
        }

        sortPairsByWeightJoker(five);
        sortPairsByWeightJoker(four);
        sortPairsByWeightJoker(house);
        sortPairsByWeightJoker(three);
        sortPairsByWeightJoker(twop);
        sortPairsByWeightJoker(onep);
        sortPairsByWeightJoker(high);
        List<List<Pair<String, Integer>>> l = new ArrayList<>();
        l.add(five);
        l.add(four);
        l.add(house);
        l.add(three);
        l.add(twop);
        l.add(onep);
        l.add(high);
        int sum = 0;
        int counter = file.size();
        for (List<Pair<String, Integer>> lp: l) {
            for (Pair<String, Integer> p:lp) {
                sum += counter * p.getValue();
                counter--;
            }
        }
        System.out.println(sum);
    }

    public static void sortPairsByWeightJoker(List<Pair<String, Integer>> pairs) {
        Collections.sort(pairs, (pair1, pair2) -> Long.compare(getWeightJoker(pair2), getWeightJoker(pair1)));
    }

    public static long getWeightJoker(Pair<String, Integer> pair) {
        String str = pair.getKey();

        str = str.replace("A", "e");
        str = str.replace("T", "a");
        str = str.replace("J", "1");
        str = str.replace("Q", "c");
        str = str.replace("K", "d");
        return Long.parseLong(str, 16);
    }

    private static boolean isThreeOfAKindJoker(String key) {
        if (!key.contains("J")) {
            return isThreeOfAKind(key);
        }
        char[] possibles = "23456789TQKA".toCharArray();
        for (char c: possibles) {
            if(isThreeOfAKind(key.replace('J', c))) {
                return true;
            }
            if (!key.contains("J")) {
                System.exit(-10);
            }
        }
        return isThreeOfAKind(key);
    }

    private static boolean isFourOfAKindJoker(String key) {
        if (!key.contains("J")) {
            return isFourOfAKind(key);
        }
        char[] possibles = "23456789TQKA".toCharArray();
        for (char c: possibles) {
            if(isFourOfAKind(key.replace('J', c))) {
                return true;
            }
            if (!key.contains("J")) {
                System.exit(-10);
            }
        }
        return isFourOfAKind(key);
    }

    private static int countDifferentCharactersJoker(String key) {
        if (!key.contains("J")) {
            return countDifferentCharacters(key);
        }
        char[] possibles = "23456789TQKA".toCharArray();
        int counter = 5;
        for (char c: possibles) {
            counter = Integer.min(countDifferentCharacters(key.replace('J', c)), counter);
            if (!key.contains("J")) {
                System.exit(-10);
            }
        }
        return counter;
    }

}