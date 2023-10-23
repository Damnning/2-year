import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {5, 16, 32, 9, 18, 78, 56, 42, 31}; // 5, 9, 16, 18, 31, 32, 42, 56, 78
        int[] result = Search.Linear(arr1, 10);
        System.out.println("Linear worst: " + result[0] + " " + result[1]);
        result = Search.Linear(arr1, 6);
        System.out.println("Linear middle: " + result[0] + " " + result[1]);
        result = Search.Linear(arr1, 1);
        System.out.println("Linear best: " + result[0] + " " + result[1]);
        System.out.println("-".repeat(10));
        result = Search.Binary(arr2, 18);
        System.out.println("Binary worst: " + result[0] + " " + result[1]);
        result = Search.Binary(arr2, 56);
        System.out.println("Binary middle: " + result[0] + " " + result[1]);
        result = Search.Binary(arr2, 31);
        System.out.println("Binary best: " + result[0] + " " + result[1]);

    }
}