package clases;

import java.awt.Color;


class BarraTenista implements Comparable<BarraTenista> {
  private String name;
  private int value;
  private Color color;

  public BarraTenista(Tenista t) {
      this.name = t.getNombre();
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

  public void setValue(int value) {
		this.value = value;
	}

public Color getColor() {
      return color;
  }

  public void incrementValue() {
      value+= 1;
  }

  @Override
  public int compareTo(BarraTenista other) {
      // Compara las barras por valor en orden descendente
      return Integer.compare(other.value, this.value);
  }
}