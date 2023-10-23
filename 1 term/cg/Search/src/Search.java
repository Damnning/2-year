import java.util.Arrays;

/**
 * Returns the index of the key in the array and the number of steps
 */
public class Search {
    public static int[] Linear(int[] arr, int key) {
        int steps = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return new int[]{i, steps};
            } else {
                steps++;
            }
        }
        return new int[]{-1, steps};
    }

    public static int[] Binary(int[] arr, int key) {
        Arrays.sort(arr);
        return Binary(arr, key, 0, 0, arr.length - 1);
    }
    private static int[] Binary(int[] arr, int key, int steps, int left, int right) {
        if (left > right) {
            return new int[]{-1, steps};
        }
        int mid = (left + right) / 2;
        if (arr[mid] == key) {
            return new int[]{mid, steps};
        } else if (arr[mid] > key) {
            steps++;
            return Binary(arr, key, steps, left, mid - 1);
        } else {
            steps++;
            return Binary(arr, key, steps, mid + 1, right);
        }

    }
}
