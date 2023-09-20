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
			

			if (primerDato instanceof Resultado) {

				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
				setRowSorter(sorter);

	            int columnaAnyo = modelo.findColumn("Año");
	            Comparator<Object> comparadorAnyo = (obj1Str, obj2Str) -> {
	           	try {
	           		int obj1 = Integer.parseInt((String) obj1Str);
		          	int obj2 = Integer.parseInt((String) obj2Str);
		           	return Integer.compare((int) obj1, (int) obj2);
	           	}catch (Exception e){
	           		return 0; }
	            };
	            sorter.setComparator(columnaAnyo, comparadorAnyo);

	            sorter.toggleSortOrder(columnaAnyo);
			}else if (primerDato instanceof Torneo) {
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
				setRowSorter(sorter);

	            int columnaCodigo = 0;
	            Comparator<Object> comparadorCodigo = (obj1Str, obj2Str) -> {
	           	try {
	           		int obj1 = Integer.parseInt((String) obj1Str);
		          	int obj2 = Integer.parseInt((String) obj2Str);
		           	return Integer.compare((int) obj1, (int) obj2);
	           	}catch (Exception e){
	           		return 0; }
	            };
	            sorter.setComparator(columnaCodigo, comparadorCodigo);

	            sorter.toggleSortOrder(columnaCodigo);
			}
			
			
			
		}
		for (DatoTabular dato : datos) {
			modelo.addRow(dato.getValores().toArray());
		}
		DatoTabular primerDato = datos.get(0);
		if (primerDato instanceof Tenista) {
			this.ordenarPorVictorias();
		}
		
		
	}
	

	
	public void actualizarTablaTenistas(Tenista t){
		DefaultTableModel modelo = (DefaultTableModel) this.getModel();
		for (int i = 0; i<this.getRowCount(); i++) {
			String nombreTenista = (String) this.getValueAt(i, 0);
			if (t.getNombre().equals(nombreTenista)) {
				modelo.setValueAt(Integer.toString(t.getNumVictorias()), i, 2);
				break;
			}
		}
	}
	
	public void ordenarPorVictorias() {
		DefaultTableModel model = (DefaultTableModel) this.getModel();
        List<Object[]> rowDataList = new ArrayList<>();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = new Object[3];
            rowData[0] = model.getValueAt(i, 0);
            rowData[1] = model.getValueAt(i, 1);
            try {
            	rowData[2] = Integer.parseInt((String) model.getValueAt(i, 2));
            }catch (Exception e) {
            	rowData[2] = model.getValueAt(i, 2);
            }
            rowDataList.add(rowData);
        }
        rowDataList.sort(Comparator.comparing(o -> (Integer) o[2], Comparator.reverseOrder()));
        model.setRowCount(0);
        
        for (Object[] rowData : rowDataList) {
            model.addRow(rowData);
        }
        
    }
	

}
