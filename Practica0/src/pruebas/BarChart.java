package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BarChart extends JPanel implements ActionListener {
    private List<Bar> bars;
    private Timer timer;
    private static int maxValue = 1;
    int separation = 5;

    public BarChart() {
        bars = new ArrayList<>();
        bars.add(new Bar("barra1"));
        bars.add(new Bar("barra2"));
        bars.add(new Bar("barra3"));
        bars.add(new Bar("barra4"));
        
        setPreferredSize(new Dimension(400, 1000));
        
        timer = new Timer(1000, this); // Temporizador de 2 segundos
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
    	 super.paintComponent(g);

//    	    int barHeight = getHeight() / bars.size();
    	    int barHeight = 250;
    	    int maxWidth = getWidth()-120;

    	    for (int i = 0; i < bars.size(); i++) {
    	        Bar bar = bars.get(i);
//    	        int barWidth = bar.getValue();
    	        int barWidth = (int)(maxWidth*bar.getValue())/maxValue;
    	        int y = i * barHeight;
    	        
    	        g.setColor(bar.getColor());
    	        g.fillRect(60, y, barWidth, barHeight);
    	        
    	        // Agrega etiquetas de texto junto a las barras
    	        int labelValue = bar.getValue();
    	        String labelValueStr = Integer.toString(labelValue);
    	        g.setColor(Color.BLACK); // Color del texto
    	        Font font = new Font("Arial", Font.PLAIN, 12); // Personaliza la fuente y el tamaño del texto
    	        g.setFont(font);
    	        int labelXValue = barWidth + 60; // Ajusta la posición horizontal del texto
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
        Random random = new Random();
        int index = random.nextInt(bars.size());
        bars.get(index).incrementValue();
        if (bars.get(index).getValue()> maxValue) {
        	maxValue = bars.get(index).getValue();
        }
        
        
        // Ordena las barras por valor
        Collections.sort(bars);

        repaint(); // Vuelve a dibujar el componente
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorted Bars Animation");
        BarChart animation = new BarChart();
        
        JScrollPane scrollPane = new JScrollPane(animation);
        frame.add(scrollPane);
        
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
}

