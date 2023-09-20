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
    private JLabel lblResultado;

    public GraficoEvo(ArrayList<DatoTabular> tenistas, ArrayList<DatoTabular> resultados, int anyoInicial, int anyoFinal) {
    	
        barras = new ArrayList<>();
        mapaTenistasBarras = new HashMap<Tenista,BarraTenista>();
        this.resultados = new ArrayList<DatoTabular>(resultados);
        this.anyoInicial = anyoInicial;
        this.anyoFinal = anyoFinal;
        
        lblResultado = new JLabel("Año " + Integer.toString(anyoInicial));
        add(lblResultado);

        System.out.println("hola");
        HashMap<Tenista,Integer> mapaVictorias = HistoriaGrandSlams.calculaClasificacion(HistoriaGrandSlams.anyoMin, anyoInicial-1);
        System.out.println("Adios");
        for (DatoTabular dato: tenistas) {
        	Tenista t = (Tenista) dato;
        	BarraTenista barraT = new BarraTenista(t);
        	if (mapaVictorias.containsKey(t)) {
        		barraT.setValue(mapaVictorias.get(t));
        	}
        	barras.add(barraT);
        	mapaTenistasBarras.put((Tenista) dato, barraT);
        }
        Collections.sort(barras);
        setPreferredSize(new Dimension(400, 375));
        
        timer = new Timer(2000, this); // Temporizador de 2 segundos
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barHeight = 30;
        int maxWidth = getWidth() - 190; // Ancho máximo disponible
        int separation = 5; // Espacio entre las barras

        for (int i = 0; i < 10;i++) {
            BarraTenista bar = barras.get(i);
            int barWidth = (int)(maxWidth*bar.getValue())/maxValue;
            int y = i * (barHeight + separation) + 25; // Agrega el espacio de separación

            g.setColor(bar.getColor());
            g.fillRect(100, y, barWidth, barHeight);

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
	        lblResultado.setText("Año " + Integer.toString(anyoInicial) );
	        repaint(); // Vuelve a dibujar el componente
    	}
    }

 

    
}