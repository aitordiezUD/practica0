package clases;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HistoriaGrandSlams hgs = new HistoriaGrandSlams("resultadosGrandSlams.csv");
		VentanaGrandSlam ventana = new VentanaGrandSlam(hgs.getResultados(),hgs.getTenistas(),hgs.getTorneos());
		
	}

}
