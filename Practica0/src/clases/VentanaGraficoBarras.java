package clases;

import javax.swing.*;

public class VentanaGraficoBarras extends JFrame {
	public VentanaGraficoBarras(GraficoEvo grafico) {
		this.setTitle("Evolucion");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		JScrollPane panel = new JScrollPane(grafico);
		add(panel);
		
		this.setVisible(true);
		
		
		
		
	}
}
