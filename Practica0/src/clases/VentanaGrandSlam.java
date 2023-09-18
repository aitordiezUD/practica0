package clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;


public class VentanaGrandSlam extends JFrame {
	
		TablaDatos tablaResultados;
		static TablaDatos tablaTenistas;
		TablaDatos tablaTorneos;

	public VentanaGrandSlam(ArrayList<DatoTabular> resultados, ArrayList<DatoTabular> tenistas,ArrayList<DatoTabular> torneos) {
		this.setSize(700,700);
		this.setTitle("Grand Slams");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTabbedPane paneles = new JTabbedPane();
		
		tablaResultados =  new TablaDatos(resultados);
		tablaTenistas =  new TablaDatos(tenistas);
//		System.out.println("TablaCreada");
//		tablaTenistas.agregarListener();
		tablaTorneos =  new TablaDatos(torneos);
		
		JScrollPane scrollPaneTablaResultados = new JScrollPane(tablaResultados);
		JScrollPane scrollPaneTablaTenistas = new JScrollPane(tablaTenistas);
		JScrollPane scrollPaneTablaTorneos = new JScrollPane(tablaTorneos);
		
		JPanel panelResultados = new JPanel();
		panelResultados.setLayout(new BorderLayout());

		// Panel que contiene la tabla
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(new BorderLayout()); // Usar BorderLayout para dividir el espacio
		panelTabla.add(scrollPaneTablaResultados, BorderLayout.CENTER);
		panelResultados.add(panelTabla, BorderLayout.CENTER);

		// Panel azul que ocupará el 20% del espacio
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.BLUE);
		panel2.setPreferredSize(new Dimension(panel2.getPreferredSize().width, (int) (this.getHeight() * 0.1)));
		panelResultados.add(panel2, BorderLayout.SOUTH);
		JButton botonEvolucion = new JButton("Evolución ranking");
		botonEvolucion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Resultado> listaAux = new ArrayList<Resultado>();
				for (DatoTabular dat: resultados) {
					listaAux.add((Resultado) dat);
				}
				Collections.sort(listaAux);
//				int anyoInicial = listaAux.get(0).getAnyo();
				int anyoInicial = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el año inicial de la evolución"));
//				int anyoFinal = listaAux.get(listaAux.size()-1).getAnyo();
				int anyoFinal = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el año final de la evolución"));
				
				GraficoEvo grafico = new GraficoEvo(tenistas, resultados, anyoInicial, anyoFinal);
				VentanaGraficoBarras vgb = new VentanaGraficoBarras(grafico);
				
			}
		});
		
		panel2.add(botonEvolucion);

		

		paneles.add("Resultados",panelResultados);
        paneles.add("Tenistas",scrollPaneTablaTenistas);
		paneles.add("Torneos",scrollPaneTablaTorneos);
		
		add(paneles);
		
		this.setVisible(true);
	}
}
