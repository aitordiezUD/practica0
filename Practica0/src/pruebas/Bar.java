package pruebas;

import java.awt.Color;

//import pruebas.BarChart.Bar;

class Bar implements Comparable<Bar> {
    private String name;
    private int value;
    private Color color;

    public Bar(String name) {
        this.name = name;
        this.value = 0;
        this.color = new Color((int) (Math.random() * 0x1000000)); // Color aleatorio
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public void incrementValue() {
        value+= 100;
    }

    @Override
    public int compareTo(Bar other) {
        // Compara las barras por valor en orden descendente
        return Integer.compare(other.value, this.value);
    }
}