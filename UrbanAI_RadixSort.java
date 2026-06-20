import java.util.Arrays;

class UrbanAI_RadixSort {

    /**
     * Counting sort based on the digit represented by exp (1, 10, 100, ...).
     */
    static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // digits are 0-9

        // Count occurrences of each digit at the current exp position
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Convert count to cumulative positions (stable ordering)
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array (traverse from end to maintain stability)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy output back to original array
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    /**
     * Radix Sort driver – sorts the array by processing each digit.
     */
    static void radixSort(int[] arr) {
        // Find the maximum number to know the number of digits
        int max = Arrays.stream(arr).max().getAsInt();

        // Apply counting sort for each digit position
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    public static void main(String[] args) {
        // Sample data: emergency vehicle IDs / sensor codes in a smart city
        int[] vehicleIDs = { 1702, 945, 312, 1890, 542, 1101, 2300, 876, 65, 401 };

        System.out.println("=== UrbanAI - Smart City Data Sorting (Radix Sort) ===");
        System.out.println("Original Emergency Fleet Data: " + Arrays.toString(vehicleIDs));

        radixSort(vehicleIDs);

        System.out.println("Sorted Emergency Fleet Data (Ascending): " + Arrays.toString(vehicleIDs));
    }
}
