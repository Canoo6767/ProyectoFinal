public class AlgoritmosFactory {
    public static WorkerAlgoritmo.Algoritmo selection() {
        return new WorkerAlgoritmo.Algoritmo() {
            public void sortArray(int[] arr) { SelectionSort.sortArray(arr); }
            public void sortList(MiArrayList list) { SelectionSort.sortMiArrayList(list); }
        };
    }
    public static WorkerAlgoritmo.Algoritmo insertion() {
        return new WorkerAlgoritmo.Algoritmo() {
            public void sortArray(int[] arr) { InsertionSort.sortArray(arr); }
            public void sortList(MiArrayList list) { InsertionSort.sortMiArrayList(list); }
        };
    }
    public static WorkerAlgoritmo.Algoritmo shell() {
        return new WorkerAlgoritmo.Algoritmo() {
            public void sortArray(int[] arr) { ShellSort.sortArray(arr); }
            public void sortList(MiArrayList list) { ShellSort.sortMiArrayList(list); }
        };
    }
    public static WorkerAlgoritmo.Algoritmo merge() {
        return new WorkerAlgoritmo.Algoritmo() {
            public void sortArray(int[] arr) { MergeSort.sortArray(arr); }
            public void sortList(MiArrayList list) { MergeSort.sortMiArrayList(list); }
        };
    }
    public static WorkerAlgoritmo.Algoritmo quick() {
        return new WorkerAlgoritmo.Algoritmo() {
            public void sortArray(int[] arr) { QuickSort.sortArray(arr); }
            public void sortList(MiArrayList list) { QuickSort.sortMiArrayList(list); }
        };
    }
    public static WorkerAlgoritmo.Algoritmo bubble() {
        return new WorkerAlgoritmo.Algoritmo() {
            public void sortArray(int[] arr) { BubbleSort.sortArray(arr); }
            public void sortList(MiArrayList list) { BubbleSort.sortMiArrayList(list); }
        };
    }
}
