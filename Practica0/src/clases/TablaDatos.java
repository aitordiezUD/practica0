package clases;

import javax.swing.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TablaDatos extends JTable{
	
	public TablaDatos(ArrayList<DatoTabular> datos) {

		
		
		DefaultTableModel modelo = new DefaultTableModel();
		setModel(modelo);
		

		if (!datos.isEmpty()) {
			DatoTabular primerDato = datos.get(0);
			for (String cabecera : primerDato.getCabeceras()) {
				modelo.addColumn(cabecera);
				
			}
			
			// Ordenar automaticamente tabla de Resultados
			if (primerDato instanceof Resultado) {
				//Hacer que se ordene automaticamente la lista al introducir un nuevo resultado dependiendo del del año del partido, ordenando de menor a mayor
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
				setRowSorter(sorter);

	                // Establecer un comparador personalizado para la columna de año
	            int columnaAnyo = modelo.findColumn("Año"); // Asegúrate de que la columna de año se llame "Anio" o cámbialo según corresponda
	            Comparator<Object> comparadorAnyo = (obj1Str, obj2Str) -> {
	           	try {
	           		int obj1 = Integer.parseInt((String) obj1Str);
		          	int obj2 = Integer.parseInt((String) obj2Str);
		           	return Integer.compare((int) obj1, (int) obj2);
	           	}catch (Exception e){
	           		return 0; }
	            };
	            sorter.setComparator(columnaAnyo, comparadorAnyo);

	            // Ordenar automáticamente por la columna de año de menor a mayor
	            sorter.toggleSortOrder(columnaAnyo);
			}
			
			
			
		}
		for (DatoTabular dato : datos) {
			modelo.addRow(dato.getValores().toArray());
		}
		DatoTabular primerDato = datos.get(0);
		if (primerDato instanceof Tenista) {
//			System.out.println("Tenista!!!");
			this.ordenarPorVictorias();
		}
		
		
	}
	

	
	public void actualizarTablaTenistas(Tenista t){
		DefaultTableModel modelo = (DefaultTableModel) this.getModel();
		for (int i = 0; i<this.getRowCount(); i++) {
			String nombreTenista = (String) this.getValueAt(i, 0);
//			System.out.println("Comparar " + t +" con " + nombreTenista);
			if (t.getNombre().equals(nombreTenista)) {
//				System.out.println("Dentro if");
//				System.out.println("Row: " + i);
				modelo.setValueAt(Integer.toString(t.getNumVictorias()), i, 2);
//				System.out.println("Valor cambiado a: "+ modelo.getValueAt(i, 2));
//				modelo.fireTableDataChanged();
//				this.repaint();
				break;
			}
		}
	}
	
	public void ordenarPorVictorias() {
		DefaultTableModel model = (DefaultTableModel) this.getModel();
        List<Object[]> rowDataList = new ArrayList<>();
        int rowCount = model.getRowCount();
//        System.out.println("Row Count: " + rowCount);
        for (int i = 0; i < rowCount; i++) {
//        	System.out.println("Dentro for");
            Object[] rowData = new Object[3];
            rowData[0] = model.getValueAt(i, 0);
//            System.out.println("Paso 1");
            rowData[1] = model.getValueAt(i, 1);
//            System.out.println("Paso 2");
//            System.out.println(model.getValueAt(i, 2));
           
            try {
            	rowData[2] = Integer.parseInt((String) model.getValueAt(i, 2));
            }catch (Exception e) {
            	rowData[2] = model.getValueAt(i, 2);
            }
            
//            System.out.println(rowData[2].getClass().getName());
//            System.out.println("Paso 3");
            rowDataList.add(rowData);
        }
//        System.out.println(rowDataList);
        rowDataList.sort(Comparator.comparing(o -> (Integer) o[2], Comparator.reverseOrder()));
        model.setRowCount(0);
        
        for (Object[] rowData : rowDataList) {
            model.addRow(rowData);
        }
        
    }

}
