package clases;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GraficoEvo extends JPanel implements ActionListener {
    private ArrayList<BarraTenista> barras;
    private Timer timer;
    private static int maxValue = 1;
    private HashMap<Tenista,BarraTenista> mapaTenistasBarras;
    private ArrayList<DatoTabular> resultados;
    private int anyoInicial;
    private int anyoFinal;
    int separation = 5;

    public GraficoEvo(ArrayList<DatoTabular> tenistas, ArrayList<DatoTabular> resultados, int anyoInicial, int anyoFinal) {
    	
        barras = new ArrayList<>();
        mapaTenistasBarras = new HashMap<Tenista,BarraTenista>();
        this.resultados = new ArrayList<DatoTabular>(resultados);
//        System.out.println(resultados);
        this.anyoInicial = anyoInicial;
        this.anyoFinal = anyoFinal;
        
        for (DatoTabular dato: tenistas) {
        	BarraTenista barraT = new BarraTenista((Tenista) dato);
        	barras.add(barraT);
        	mapaTenistasBarras.put((Tenista) dato, barraT);
        }
        int ydimension= 35*tenistas.size();
        setPreferredSize(new Dimension(400, ydimension));
        
        timer = new Timer(1000, this); // Temporizador de 2 segundos
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barHeight = 30;//getHeight() / barras.size();
        int maxWidth = getWidth() - 190; // Ancho máximo disponible
        int separation = 5; // Espacio entre las barras

        for (int i = 0; i < barras.size(); i++) {
            BarraTenista bar = barras.get(i);
            int barWidth = (int)(maxWidth*bar.getValue())/maxValue;//(int) ((maxWidth - (barras.size() - 1) * separation) * (bar.getValue() / (double) maxValue)); // Calcula la longitud relativa de la barra
            int y = i * (barHeight + separation); // Agrega el espacio de separación

            g.setColor(bar.getColor());
            g.fillRect(90, y, barWidth, barHeight);

            // Agrega etiquetas de texto junto a las barras
            int labelValue = bar.getValue();
            String labelValueStr = Integer.toString(labelValue);
            g.setColor(Color.BLACK); // Color del texto
            Font font = new Font("Arial", Font.PLAIN, 12); // Personaliza la fuente y el tamaño del texto
            g.setFont(font);
            int labelXValue = barWidth + 120; // Ajusta la posición horizontal del texto
            int labelYValue = y + barHeight / 2 + g.getFontMetrics().getHeight() / 2;
            g.drawString(labelValueStr, labelXValue, labelYValue);
            String labelName = bar.getName();
            int labelXName = 0; // Ajusta la posición horizontal del texto
            int labelYName = y + barHeight / 2 + g.getFontMetrics().getHeight() / 2;
            g.drawString(labelName, labelXName, labelYName);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    	if (anyoInicial<=anyoFinal) {
	        for (DatoTabular d : resultados) {
	        	Resultado r = (Resultado) d;
//	        	System.out.println(r);
	        	System.out.println("Comparando " + anyoInicial + " y " + r.getAnyo());
	        	if (r.getAnyo()==anyoInicial) {
	        		System.out.println("Dentro if, año: " + anyoInicial);
	        		
	        		BarraTenista b = mapaTenistasBarras.get(r.getGanador());
	        		b.incrementValue();
	        		if (b.getValue()>maxValue) {
	        			maxValue = b.getValue();
	        		}
	        	}
	        }

	        // Ordena las barras por valor
	        Collections.sort(barras);
	        anyoInicial++;
	        repaint(); // Vuelve a dibujar el componente
    	}
    }

 

    
}