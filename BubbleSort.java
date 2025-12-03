public class BubbleSort {
    public static void sortArray(int[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    int t = arr[j]; arr[j] = arr[j+1]; arr[j+1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    public static void sortMiArrayList(MiArrayList list) {
        int[] arr = list.toArray();
        sortArray(arr);
        list.fromArray(arr);
    }
}
