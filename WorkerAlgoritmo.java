public class WorkerAlgoritmo implements Runnable {
    public interface Algoritmo {
        void sortArray(int[] arr);
        void sortList(MiArrayList list);
    }

    private final String nombre;
    private final Algoritmo algoritmo;
    private final int[][] arrays;
    private final MiArrayList[] listas;
    private final long tiempoLimiteMs;

    private int coleccionesOrdenadas = 0;
    private long tiempoAcumuladoMs = 0;

    public WorkerAlgoritmo(String nombre, Algoritmo algoritmo,
                           int[][] arrays, MiArrayList[] listas,
                           long tiempoLimiteMs) {
        this.nombre = nombre;
        this.algoritmo = algoritmo;
        this.arrays = arrays;
        this.listas = listas;
        this.tiempoLimiteMs = tiempoLimiteMs;
    }

    @Override
    public void run() {
        long inicioGlobal = System.currentTimeMillis();
        int idxA = 0, idxL = 0;

        while (System.currentTimeMillis() - inicioGlobal < tiempoLimiteMs) {
            boolean usarArray = (idxA <= idxL);
            long t1 = System.currentTimeMillis();

            if (usarArray && arrays != null && arrays.length > 0) {
                int[] base = arrays[idxA % arrays.length];
                int[] work = java.util.Arrays.copyOf(base, base.length);
                algoritmo.sortArray(work);
                idxA++;
            } else if (listas != null && listas.length > 0) {
                MiArrayList base = listas[idxL % listas.length];
                MiArrayList work = base.clone();
                algoritmo.sortList(work);
                idxL++;
            } else {
                break;
            }

            long t2 = System.currentTimeMillis();
            tiempoAcumuladoMs += (t2 - t1);
            coleccionesOrdenadas++;
        }
    }

    public String getNombre() { return nombre; }
    public int getColeccionesOrdenadas() { return coleccionesOrdenadas; }
    public double getPromedioMsPorColeccion() {
        return (coleccionesOrdenadas == 0) ? 0.0 : (tiempoAcumuladoMs * 1.0 / coleccionesOrdenadas);
    }
}
