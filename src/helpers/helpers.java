package helpers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class helpers {
    public static void printintarray(int[] arr) {
        for (int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static String replacedoublespaces(String str) {
        while (str.contains("  ")) {
            str = str.replace("  ", " ");
        }
        return str;
    }

    public static int[] stringArrToIntArr(String numbers) {
        String[] arr = numbers.split(" ");
        int[] nums = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }

        return nums;
    }
    public static int countDifferentCharacters(String str) {
        Set<Character> uniqueCharacters = new HashSet<>();

        for (char c : str.toCharArray()) {
            uniqueCharacters.add(c);
        }

        return uniqueCharacters.size();
    }
    public static Queue<Character> charArrayToQueue(char[] charArray) {
        Queue<Character> queue = new LinkedList<>();

        // Add each character from the array to the queue
        for (char c : charArray) {
            queue.offer(c); // or queue.add(c);
        }

        return queue;
    }
}
