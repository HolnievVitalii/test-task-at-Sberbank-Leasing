import java.util.Random;

/**
 * @author Holniev Vitalii.
 * Array sorting program using bubble method
 */
public class BubbleSort {
    private static int[] arr = new int[20];

    public static void main(String[] args) {

        fillArray();//  filling the array with random elements
        printArray();
        System.out.print(" - non sorted array");
        System.out.println();
        sortBubble(arr);
        printArray();
        System.out.print(" - bubble sorted array");
    }

    /**
     * Sort bubble
     *
     * @param arr
     */
    static void sortBubble(int[] arr) {
        int in, out;
        int len = arr.length;
        for (out = len - 1; out >= 1; out--) {
            for (in = 0; in < out; in++) {
                if (arr[in] > arr[in + 1]) {
                    change(in, in + 1, arr);
                }
            }
        }
    }

    /**
     * a method that will be called from the sort Bubble method
     * and swap the elements of the array
     *
     * @param a
     * @param b
     * @param arr
     */
    private static void change(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * fill an array with random elements
     */
    private static void fillArray() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRndElement();
        }
    }

    /**
     * print an array
     */
    private static void printArray() {
        for (int n : arr) {
            System.out.print(n + ",");
        }
    }

    /**
     * generate a random element from 0 to 10000
     *
     * @return random element
     */
    private static int generateRndElement() {
        Random random = new Random();
        int rndElement = random.nextInt(100);
        return rndElement;
    }
}

