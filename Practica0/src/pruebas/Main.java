package pruebas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ordenar JTable por Edad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Edad");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton ordenarButton = new JButton("Ordenar por Edad");

        ordenarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarPorEdad(model);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(ordenarButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        // Agregar algunos datos de ejemplo a la tabla
        model.addRow(new Object[]{"Juan", 25});
        model.addRow(new Object[]{"María", 30});
        model.addRow(new Object[]{"Pedro", 20});
        model.addRow(new Object[]{"Ana", 35});
    }

    private static void ordenarPorEdad(DefaultTableModel model) {
        List<Object[]> rowDataList = new ArrayList<>();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = new Object[2];
            rowData[0] = model.getValueAt(i, 0);
            rowData[1] = model.getValueAt(i, 1);
            rowData[1] = model.getValueAt(i, 2);
            rowDataList.add(rowData);
        }

        rowDataList.sort(Comparator.comparing(o -> (Integer) o[2], Comparator.reverseOrder()));

        model.setRowCount(0);

        for (Object[] rowData : rowDataList) {
            model.addRow(rowData);
        }
    }
}
