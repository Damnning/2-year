import java.util.Arrays;

public class Sorting {
    private int counter = 0;
    public int getCounter() {
        return counter;
    }
    public static final String SWAP_COLOR = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void printArray(int[] arr) {
        printArray(arr, -1, -1);
    }

    private void printArray(int[] arr, int num1, int num2) {
        System.out.print("[");
        String color;
        for (int i = 0; i < arr.length; i++) {
            if (i == num1 || i == num2)
                color = SWAP_COLOR;
            else
                color = ANSI_RESET;
            System.out.print(color + arr[i] + " " + ANSI_RESET);
        }
        System.out.println("]");

    }
    public int insertionSort(int[] array) {
        int comparisons = 0;
        for (int left = 0; left < array.length; left++) {
            // Вытаскиваем значение элемента
            int value = array[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                comparisons++;
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            array[i + 1] = value;
        }
        return comparisons;
    }

    public int shakerSort(int[] arr) {
        int comparisons = 0;
        int left = 0;
        int right = arr.length - 1;
        int temp;
        printArray(arr);
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    printArray(arr, i, i + 1);
                }
            }
            comparisons += right - left;
            right--;
            for (int i = right; i > left; i--) {
                if (arr[i - 1] > arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    printArray(arr, i - 1, i);
                }
            }
            comparisons += right - left;
            left++;
        }
        printArray(arr);
        return comparisons;
    }
    public int quickSort(int[] array) {
         return quickSort(array, 0, array.length - 1,0);

    }
    private int quickSort(int[] array, int low, int high, int comparisons) {
        if (array.length == 0)
            return comparisons;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return comparisons;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                comparisons++;
                i++;
            }

            while (array[j] > opora) {
                comparisons++;
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            comparisons += quickSort(array, low, j,comparisons);

        if (high > i)
            comparisons += quickSort(array, i, high,comparisons);
        return comparisons;
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
