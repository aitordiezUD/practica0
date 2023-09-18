package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InteractiveBarsAnimation extends JPanel implements ActionListener {
    private List<Bar> bars;
    private Timer timer;
    private int totalWidth;

    public InteractiveBarsAnimation() {
        bars = new ArrayList<>();
        bars.add(new Bar("barra1"));
        bars.add(new Bar("barra2"));
        bars.add(new Bar("barra3"));
        bars.add(new Bar("barra4"));

        timer = new Timer(2000, this); // Temporizador de 2 segundos
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int maxHeight = getHeight();
        int x = 0;

        for (Bar bar : bars) {
            int barWidth = (int) (totalWidth * (bar.getValue() / (double) totalWidth)); // Calcula la longitud relativa de la barra
            int y = x;

            g.setColor(bar.getColor());
            g.fillRect(0, y, barWidth, maxHeight / bars.size());

            // Agrega etiquetas de texto junto a las barras
            String label = bar.getName();
            g.setColor(Color.BLACK); // Color del texto
            Font font = new Font("Arial", Font.PLAIN, 12); // Personaliza la fuente y el tamaño del texto
            g.setFont(font);
            int labelX = barWidth + 5; // Ajusta la posición horizontal del texto
            int labelY = y + (maxHeight / bars.size()) / 2 + g.getFontMetrics().getHeight() / 2;
            g.drawString(label, labelX, labelY);

            x += maxHeight / bars.size();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        int index = random.nextInt(bars.size());
        bars.get(index).incrementValue();

        totalWidth++; // Aumenta el ancho total disponible

        // Ordena las barras por valor
        Collections.sort(bars);

        repaint(); // Vuelve a dibujar el componente
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interactive Bars Animation");
        InteractiveBarsAnimation animation = new InteractiveBarsAnimation();
        frame.add(animation);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class Bar implements Comparable<Bar> {
        private String name;
        private int value;
        private Color color;

        public Bar(String name) {
            this.name = name;
            this.value = 0;
            this.color = new Color((int) (Math.random() * 0x1000000)); // Color aleatorio
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }

        public void incrementValue() {
            value++;
        }

        @Override
        public int compareTo(Bar other) {
            // Compara las barras por valor en orden descendente
            return Integer.compare(other.value, this.value);
        }

        public String getName() {
            return name;
        }
    }
}
