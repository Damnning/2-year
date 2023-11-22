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

}
