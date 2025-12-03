import java.util.Random;

public class GeneradorDatos {
    private static final Random R = new Random();

    public static int[] aleatorio(int n, int min, int max) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = R.nextInt(max - min + 1) + min;
        return a;
    }

    public static MiArrayList aleatorioLista(int n, int min, int max) {
        MiArrayList list = new MiArrayList(n);
        for (int i = 0; i < n; i++) list.add(R.nextInt(max - min + 1) + min);
        return list;
    }

    // Requeridos por la evidencia
    public static int[] C100()               { return aleatorio(100, 1, 1000000); }
    public static int[] C50000()             { return aleatorio(50000, 1, 1000000); }
    public static int[] C100000()            { return aleatorio(100000, 1, 1000000); }
    public static int[] C100000_1a5()        { return aleatorio(100000, 1, 5); }

    public static MiArrayList L100()         { return aleatorioLista(100, 1, 1000000); }
    public static MiArrayList L50000()       { return aleatorioLista(50000, 1, 1000000); }
    public static MiArrayList L100000()      { return aleatorioLista(100000, 1, 1000000); }
    public static MiArrayList L100000_1a5()  { return aleatorioLista(100000, 1, 5); }
}
