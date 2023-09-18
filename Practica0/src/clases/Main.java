package clases;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HistoriaGrandSlams hgs = new HistoriaGrandSlams("resultadosGrandSlams.csv");
//		Torneo t1 = new Torneo(1);
//		Torneo t2 = new Torneo(2);
//		
//		ArrayList<DatoTabular> torneos = new ArrayList<DatoTabular>();
//		
//		torneos.add(t1);
//		torneos.add(t2);
////		
//		Tenista tt1 = new Tenista("Carlos Alcaraz", "ESP", 0); 
//		Tenista tt2 = new Tenista("Rafa Nadal", "ESP", 2);
//		Tenista tt3 = new Tenista("Djokovic", "ESP", 1);
////		
//		ArrayList<DatoTabular> tenistas = new ArrayList<DatoTabular>();
//		
//		tenistas.add(tt1);
//		tenistas.add(tt2);
//		tenistas.add(tt3);
//		
//		Resultado r1 = new Resultado(1982, t1, tt1, 0, tt1, 0, "1-5");
//		Resultado r2 = new Resultado(1987, t1, tt1, 0, tt1, 0, "1-5");
//		Resultado r3 = new Resultado(1975, t1, tt1, 0, tt1, 0, "1-5");
//		Resultado r4 = new Resultado(1984, t1, tt1, 0, tt1, 0, "1-5");
//		
//		ArrayList<Resultado> resultados = new ArrayList<>();
////		
//		resultados.add(r1);
//		resultados.add(r2);
//		resultados.add(r3);
//		resultados.add(r4);

		VentanaGrandSlam ventana = new VentanaGrandSlam(hgs.getResultados(),hgs.getTenistas(),hgs.getTorneos());
//		
//		try {
//			Thread.sleep(2000);
////			System.out.println("Pasados 2s");
//			tt1.setNumVictoriasYordenar(99);
////			Thread.sleep(2000);
////			tt3.setNumVictorias(100);
//		}catch (Exception e) {
////			
//		}
////		
		
	}

}
