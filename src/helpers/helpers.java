package helpers;

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
}
