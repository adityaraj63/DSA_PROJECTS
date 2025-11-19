
import java.util.*;

public class SortingVisualizer {

    // Utility function to print array
    static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // -------------------- BUBBLE SORT --------------------
    static void bubbleSort(int[] arr) {
        System.out.println("\n--- Bubble Sort Steps ---");

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            System.out.println("Pass " + (i + 1) + ":");
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                printArray(arr);
            }
            System.out.println();
        }
    }

    // -------------------- SELECTION SORT --------------------
    static void selectionSort(int[] arr) {
        System.out.println("\n--- Selection Sort Steps ---");

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            System.out.println("Selecting minimum for position " + i);

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            printArray(arr);
        }
    }

    // -------------------- INSERTION SORT --------------------
    static void insertionSort(int[] arr) {
        System.out.println("\n--- Insertion Sort Steps ---");

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            System.out.println("Inserting " + key);

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                printArray(arr);
            }
            arr[j + 1] = key;
            printArray(arr);
        }
    }

    // -------------------- MERGE SORT --------------------
    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
            printArray(arr);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int i = 0; i < n2; i++) R[i] = arr[mid + 1 + i];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // -------------------- QUICK SORT --------------------
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            printArray(arr);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        System.out.println("Pivot: " + pivot);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                printArray(arr);
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // -------------------- HEAP SORT --------------------
    static void heapSort(int[] arr) {
        System.out.println("\n--- Heap Sort Steps ---");

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
            printArray(arr);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            System.out.println("After swapping root with index " + i);
            printArray(arr);

            heapify(arr, i, 0);
            printArray(arr);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    // -------------------- MAIN PROGRAM --------------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        while (true) {
            System.out.println("\nChoose Sorting Algorithm:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Heap Sort");
            System.out.println("7. Exit");

            int choice = sc.nextInt();
            int[] copy = Arrays.copyOf(arr, arr.length);

            switch (choice) {
                case 1: bubbleSort(copy); break;
                case 2: selectionSort(copy); break;
                case 3: insertionSort(copy); break;
                case 4: mergeSort(copy, 0, copy.length - 1); break;
                case 5: quickSort(copy, 0, copy.length - 1); break;
                case 6: heapSort(copy); break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

