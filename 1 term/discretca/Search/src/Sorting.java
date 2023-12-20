import java.util.Arrays;

public class Sorting {
    private int counter = 0;
    public int getCounter() {
        return counter;
    }

    public int[] mergeSort(int[] array) {
        if(array.length < 2) {
            return array;
        }
        int n = array.length;
        int mid = n/2;
        int[] left = Arrays.copyOf(array, mid);
        int[] right = Arrays.copyOfRange(array, mid, n);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        while(i < left.length && j < right.length) {
            counter++;
            if (left[i] < right[j]) {
                result[i + j] = left[i];
                i++;
            } else {
                result[i + j] = right[j];
                j++;
            }
        }
        if(i == left.length) {
            while(j < right.length) {
                result[i + j] = right[j];
                j++;
            }
        } else {
            while(i < left.length) {
                result[i + j] = left[i];
                i++;
            }
        }
        return result;
    }
}
