import java.util.*;

public class ControladorConcurrente {

    public static class Resultado {
        String nombre;
        int colecciones;
        double promedioMs;
        boolean completoTodas;
    }

    public static List<Resultado> ejecutar(long tiempoLimiteMs) {
        int[][] arrays = {
            GeneradorDatos.C100(),
            GeneradorDatos.C50000(),
            GeneradorDatos.C100000(),
            GeneradorDatos.C100000_1a5()
        };
        MiArrayList[] listas = {
            GeneradorDatos.L100(),
            GeneradorDatos.L50000(),
            GeneradorDatos.L100000(),
            GeneradorDatos.L100000_1a5()
        };

        WorkerAlgoritmo wSelection = new WorkerAlgoritmo("Selection", AlgoritmosFactory.selection(), arrays, listas, tiempoLimiteMs);
        WorkerAlgoritmo wInsertion = new WorkerAlgoritmo("Insertion", AlgoritmosFactory.insertion(), arrays, listas, tiempoLimiteMs);
        WorkerAlgoritmo wShell     = new WorkerAlgoritmo("Shell",     AlgoritmosFactory.shell(),     arrays, listas, tiempoLimiteMs);
        WorkerAlgoritmo wMerge     = new WorkerAlgoritmo("Merge",     AlgoritmosFactory.merge(),     arrays, listas, tiempoLimiteMs);
        WorkerAlgoritmo wQuick     = new WorkerAlgoritmo("Quick",     AlgoritmosFactory.quick(),     arrays, listas, tiempoLimiteMs);
        WorkerAlgoritmo wBubble    = new WorkerAlgoritmo("Bubble",    AlgoritmosFactory.bubble(),    arrays, listas, tiempoLimiteMs);

        Thread t1 = new Thread(wSelection);
        Thread t2 = new Thread(wInsertion);
        Thread t3 = new Thread(wShell);
        Thread t4 = new Thread(wMerge);
        Thread t5 = new Thread(wQuick);
        Thread t6 = new Thread(wBubble);

        long inicio = System.currentTimeMillis();
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start();

        try {
            long restante;
            while ((restante = tiempoLimiteMs - (System.currentTimeMillis() - inicio)) > 0) {
                Thread.sleep(Math.min(100, restante));
            }
            t1.join(50); t2.join(50); t3.join(50); t4.join(50); t5.join(50); t6.join(50);
        } catch (InterruptedException ignored) {}

        List<Resultado> resultados = new ArrayList<>();
        resultados.add(build(wSelection));
        resultados.add(build(wInsertion));
        resultados.add(build(wShell));
        resultados.add(build(wMerge));
        resultados.add(build(wQuick));
        resultados.add(build(wBubble));

        resultados.sort(Comparator.comparingDouble(r -> r.promedioMs));
        return resultados;
    }

    private static Resultado build(WorkerAlgoritmo w) {
        Resultado r = new Resultado();
        r.nombre = w.getNombre();
        r.colecciones = w.getColeccionesOrdenadas();
        r.promedioMs = w.getPromedioMsPorColeccion();
        r.completoTodas = r.colecciones >= 8; // heurística: 4 arrays + 4 listas
        return r;
    }
}
