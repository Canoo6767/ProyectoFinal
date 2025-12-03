public class SelectionSort {
    public static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void sortMiArrayList(MiArrayList list) {
        int[] arr = list.toArray();
        sortArray(arr);
        list.fromArray(arr);
    }
}
