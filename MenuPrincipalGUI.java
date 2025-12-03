import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class MenuPrincipalGUI extends JFrame {
    private JTextArea output;

    public MenuPrincipalGUI() {
        setTitle("Proyecto Final - Algoritmos de Ordenamiento");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Área de salida
        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Panel de botones
        JPanel panel = new JPanel(new GridLayout(2, 4, 8, 8));
        JButton btnSelection   = new JButton("SelectionSort");
        JButton btnInsertion   = new JButton("InsertionSort");
        JButton btnShell       = new JButton("ShellSort");
        JButton btnMerge       = new JButton("MergeSort");
        JButton btnQuick       = new JButton("QuickSort");
        JButton btnBubble      = new JButton("BubbleSort");
        JButton btnBenchmark   = new JButton("Benchmark rápido");
        JButton btnConcurrente = new JButton("Ejecución concurrente");

        panel.add(btnSelection);
        panel.add(btnInsertion);
        panel.add(btnShell);
        panel.add(btnMerge);
        panel.add(btnQuick);
        panel.add(btnBubble);
        panel.add(btnBenchmark);
        panel.add(btnConcurrente);

        add(panel, BorderLayout.SOUTH);

        // Acciones
        btnSelection.addActionListener(e -> ejecutarUnaVez("SelectionSort"));
        btnInsertion.addActionListener(e -> ejecutarUnaVez("InsertionSort"));
        btnShell.addActionListener(e -> ejecutarUnaVez("ShellSort"));
        btnMerge.addActionListener(e -> ejecutarUnaVez("MergeSort"));
        btnQuick.addActionListener(e -> ejecutarUnaVez("QuickSort"));
        btnBubble.addActionListener(e -> ejecutarUnaVez("BubbleSort"));
        btnBenchmark.addActionListener(e -> benchmarkPequeno());
        btnConcurrente.addActionListener(e -> ejecutarConcurrente());
    }

    // Ejecuta un algoritmo sobre un array y un MiArrayList pequeños
    private void ejecutarUnaVez(String algoritmo) {
        int[] arr = generarArrayAleatorio(20, 1, 100);
        MiArrayList list = arrayToMiArrayList(arr);

        int[] arrBefore = Arrays.copyOf(arr, arr.length);
        String header = "== " + algoritmo + " ==\n";
        String before = "Array antes:     " + Arrays.toString(arrBefore) + "\n";

        long t1 = System.currentTimeMillis();
        switch (algoritmo) {
            case "SelectionSort": SelectionSort.sortArray(arr); SelectionSort.sortMiArrayList(list); break;
            case "InsertionSort": InsertionSort.sortArray(arr); InsertionSort.sortMiArrayList(list); break;
            case "ShellSort":     ShellSort.sortArray(arr);     ShellSort.sortMiArrayList(list);     break;
            case "MergeSort":     MergeSort.sortArray(arr);     MergeSort.sortMiArrayList(list);     break;
            case "QuickSort":     QuickSort.sortArray(arr);     QuickSort.sortMiArrayList(list);     break;
            case "BubbleSort":    BubbleSort.sortArray(arr);    BubbleSort.sortMiArrayList(list);    break;
        }
        long t2 = System.currentTimeMillis();

        String afterA = "Array después:   " + Arrays.toString(arr) + "\n";
        String afterL = "MiArrayList desp:" + list.toString() + "\n";
        String time   = "Tiempo total: " + (t2 - t1) + " ms\n\n";
        append(header + before + afterA + afterL + time);
    }

    // Benchmark rápido con tamaños pequeños
    private void benchmarkPequeno() {
        int[][] tamaños = {
            {100, 1, 1000},
            {2000, 1, 10000},
            {10000, 1, 100000}
        };
        StringBuilder sb = new StringBuilder();
        sb.append("== Benchmark rápido (arrays y MiArrayList) ==\n");

        for (int[] cfg : tamaños) {
            int n = cfg[0], min = cfg[1], max = cfg[2];
            sb.append("Tamaño: ").append(n).append("\n");

            int[] base = generarArrayAleatorio(n, min, max);
            sb.append(medir("SelectionSort", base));
            sb.append(medir("InsertionSort", base));
            sb.append(medir("ShellSort",     base));
            sb.append(medir("MergeSort",     base));
            sb.append(medir("QuickSort",     base));
            sb.append(medir("BubbleSort",    base));
            sb.append("\n");
        }
        append(sb.toString());
    }

    private String medir(String algoritmo, int[] base) {
        int[] arr = Arrays.copyOf(base, base.length);
        MiArrayList list = arrayToMiArrayList(base);

        long tA1 = System.currentTimeMillis();
        switch (algoritmo) {
            case "SelectionSort": SelectionSort.sortArray(arr); break;
            case "InsertionSort": InsertionSort.sortArray(arr); break;
            case "ShellSort":     ShellSort.sortArray(arr);     break;
            case "MergeSort":     MergeSort.sortArray(arr);     break;
            case "QuickSort":     QuickSort.sortArray(arr);     break;
            case "BubbleSort":    BubbleSort.sortArray(arr);    break;
        }
        long tA2 = System.currentTimeMillis();

        long tL1 = System.currentTimeMillis();
        switch (algoritmo) {
            case "SelectionSort": SelectionSort.sortMiArrayList(list); break;
            case "InsertionSort": InsertionSort.sortMiArrayList(list); break;
            case "ShellSort":     ShellSort.sortMiArrayList(list);     break;
            case "MergeSort":     MergeSort.sortMiArrayList(list);     break;
            case "QuickSort":     QuickSort.sortMiArrayList(list);     break;
            case "BubbleSort":    BubbleSort.sortMiArrayList(list);    break;
        }
        long tL2 = System.currentTimeMillis();

        return String.format("  %-12s  array: %6d ms | list: %6d ms\n",
                algoritmo, (tA2 - tA1), (tL2 - tL1));
    }

    // Ejecución concurrente con tiempo límite
    private void ejecutarConcurrente() {
        String s = JOptionPane.showInputDialog(this, "Tiempo total (ms):", "5000");
        if (s == null) return;
        long tiempo;
        try { tiempo = Long.parseLong(s.trim()); } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Tiempo inválido.");
            return;
        }
        List<ControladorConcurrente.Resultado> res = ControladorConcurrente.ejecutar(tiempo);

        StringBuilder sb = new StringBuilder();
        sb.append("== Resultados concurrentes (").append(tiempo).append(" ms) ==\n");
        for (ControladorConcurrente.Resultado r : res) {
            sb.append(String.format("  %-10s  colecciones: %4d  promedio: %8.2f ms  completoTodas: %s\n",
                    r.nombre, r.colecciones, r.promedioMs, r.completoTodas ? "sí" : "no"));
        }
        sb.append("\n-- Ranking (mejor promedio primero) --\n");
        int rank = 1;
        for (ControladorConcurrente.Resultado r : res) {
            sb.append(String.format("  %d) %-10s  promedio: %8.2f ms\n", rank++, r.nombre, r.promedioMs));
        }
        append(sb.toString());
    }

    private int[] generarArrayAleatorio(int n, int min, int max) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(max - min + 1) + min;
        return arr;
    }

    private MiArrayList arrayToMiArrayList(int[] arr) {
        MiArrayList list = new MiArrayList(arr.length);
        for (int v : arr) list.add(v);
        return list;
    }

    private void append(String text) {
        output.append(text);
        output.setCaretPosition(output.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipalGUI().setVisible(true));
    }
}
