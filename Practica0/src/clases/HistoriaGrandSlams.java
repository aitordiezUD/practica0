package clases;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class HistoriaGrandSlams {
	static ArrayList<DatoTabular> resultados;
	static ArrayList<DatoTabular> tenistas;
	static ArrayList<DatoTabular> torneos;
	static HashMap<String,Torneo> torneosMapaNombre;
	static HashMap<Integer,Torneo> torneosMapaCodigo;
	static HashMap<String,Tenista> tenistasMapaNombre;
	static TreeMap<Tenista,Integer> mapaVictoriasTenistas;
	
	static String ficheroCSV = null;
	
	static int anyoMin = 10000000;


	
	public ArrayList<DatoTabular> getResultados() {
		return resultados;
	}


	public void setResultados(ArrayList<DatoTabular> resultados) {
		this.resultados = resultados;
	}


	public ArrayList<DatoTabular> getTenistas() {
		return tenistas;
	}


	public void setTenistas(ArrayList<DatoTabular> tenistas) {
		this.tenistas = tenistas;
	}


	public ArrayList<DatoTabular> getTorneos() {
		return torneos;
	}


	public void setTorneos(ArrayList<DatoTabular> torneos) {
		this.torneos = torneos;
	}


	public HashMap<String, Torneo> getTorneosMapaNombre() {
		return torneosMapaNombre;
	}


	public void setTorneosMapaNombre(HashMap<String, Torneo> torneosMapaNombre) {
		this.torneosMapaNombre = torneosMapaNombre;
	}


	public HashMap<Integer, Torneo> getTorneosMapaCodigo() {
		return torneosMapaCodigo;
	}


	public void setTorneosMapaCodigo(HashMap<Integer, Torneo> torneosMapaCodigo) {
		this.torneosMapaCodigo = torneosMapaCodigo;
	}


	public HashMap<String, Tenista> getTenistasMapaNombre() {
		return tenistasMapaNombre;
	}


	public void setTenistasMapaNombre(HashMap<String, Tenista> tenistasMapaNombre) {
		this.tenistasMapaNombre = tenistasMapaNombre;
	}


	public TreeMap<Tenista, Integer> getMapaVictoriasTenistas() {
		return mapaVictoriasTenistas;
	}


	public void setMapaVictoriasTenistas(TreeMap<Tenista, Integer> mapaVictoriasTenistas) {
		this.mapaVictoriasTenistas = mapaVictoriasTenistas;
	}


	public HistoriaGrandSlams(String ficheroCsv) {
		
		this.resultados = new ArrayList<DatoTabular>();
		this.tenistas = new ArrayList<DatoTabular>();
		this.torneos = new ArrayList<DatoTabular>();
		this.torneosMapaNombre = new HashMap<String,Torneo>();
		this.torneosMapaCodigo = new HashMap<Integer,Torneo>();
		this.tenistasMapaNombre = new HashMap<String,Tenista>();
		this.mapaVictoriasTenistas = new TreeMap<Tenista,Integer>();
		ficheroCSV = ficheroCsv;
		File f  = new File(ficheroCsv);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr= new FileReader(f);
            br = new BufferedReader(fr);
            String linea = br.readLine();
            linea = br.readLine(); //Asi nos saltamos la fila de las cabeceras
            while (linea != null) {
            	String[] campos = linea.split(";");
            	//Mirar si el ganador esta en la lista; si esta sumarle una victoria y si no esta añadirle. Lo mismo con el subcampeon
            	if (tenistas.isEmpty()) {
            		Tenista c = new Tenista(campos[2], campos[4], 1);
            		tenistas.add(c);
            		tenistasMapaNombre.put(c.getNombre(), c);
            		mapaVictoriasTenistas.put(c, 1);
//            		Ahora con el subcampeon:
            		Tenista s = new Tenista(campos[5], campos[7], 1);
            		tenistas.add(s);
            		tenistasMapaNombre.put(s.getNombre(), s);
            		mapaVictoriasTenistas.put(s, 1);
//            		Ahora con el subcampeon:
            	}else {
            		if(tenistasMapaNombre.containsKey(campos[2])) { //Es decir el ganador ya esta en la lista
            			Tenista c = tenistasMapaNombre.get(campos[2]);
            			c.setNumVictorias(c.getNumVictorias() + 1);
            			mapaVictoriasTenistas.replace(c, c.getNumVictorias());
            		}else { //Ahora si el campeon no esta en la lista
            			Tenista c = new Tenista(campos[2], campos[4], 1);
                		tenistas.add(c);
                		tenistasMapaNombre.put(c.getNombre(), c);
                		mapaVictoriasTenistas.put(c, 1);
            		} //Ahora si el subcampeon no esta en la lista le añadimos, si no esta no cambiamos nada porque no hay nada que actualuizar
            		if(!tenistasMapaNombre.containsKey(campos[5])) {
            			Tenista c = new Tenista(campos[5], campos[7], 0);
                		tenistas.add(c);
                		tenistasMapaNombre.put(c.getNombre(), c);
                		mapaVictoriasTenistas.put(c, 0);
            		}
            	}
            	
            	//Ahora hacemos el mismo proceso con los torneos
            	if (torneos.isEmpty()) {
            		Torneo t = new Torneo(Integer.parseInt(campos[1]));
            		torneos.add(t);
            		torneosMapaNombre.put(t.getNombre(), t);
            		torneosMapaCodigo.put(t.getCodigo(), t);
            	}else {
            		if(!torneosMapaCodigo.containsKey(Integer.parseInt(campos[1]))) {
            			Torneo t = new Torneo(Integer.parseInt(campos[1]));
            			torneos.add(t);
            			torneosMapaNombre.put(t.getNombre(), t);
                		torneosMapaCodigo.put(t.getCodigo(), t);
            		}
            	}
            	//Añadir finalmente todos los resultados a la lista
            	
            	
            	Resultado r = new Resultado(Integer.parseInt(campos[0]), torneosMapaCodigo.get(Integer.parseInt(campos[1])),
            			tenistasMapaNombre.get(campos[2]), Integer.parseInt(campos[3]), tenistasMapaNombre.get(campos[5]),  Integer.parseInt(campos[6]), campos[8]);
            	resultados.add(r);
            	if (r.getAnyo()<anyoMin) {
            		anyoMin = r.getAnyo();
            	}
            	linea = br.readLine();
            }
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}


	//	#Actualizacion del treemap de victorias
	public void actualizarVictorias(Tenista ganador) {
		for (Tenista t: mapaVictoriasTenistas.keySet()) {
			if (ganador == t) {
				mapaVictoriasTenistas.replace(t, mapaVictoriasTenistas.get(t)+1);
			}
		}
	};
	
	public static HashMap<Tenista,Integer> calculaClasificacion(int anyoInicial,int anyoFinal) {
		HashMap<Tenista,Integer> mapa = new HashMap<Tenista, Integer>();
		while (anyoInicial<=anyoFinal) {
			for (DatoTabular d: resultados) {
				Resultado r = (Resultado) d;
				if (r.getAnyo()==anyoInicial) {
					Tenista ganador = r.getGanador();
					if (mapa.get(ganador) == null) {
						mapa.put(ganador, 1);
					}else {
						mapa.replace(ganador, mapa.get(ganador)+1);
					}
				}
			}
			anyoInicial++;
		}
		
		return mapa;
		
	};
	
	public static void guardarFichero () {
		File f = new File(ficheroCSV);
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
			
			pw.println("Anyo;Major;Champion;Seed_Champion;Ctry_Champion;Runner-up;Seed_Runner-up;Ctry-Runner;Score in the final");
			DefaultTableModel modelo = (DefaultTableModel) VentanaGrandSlam.tablaResultados.getModel();
			for (int i=0;i<modelo.getRowCount();i++) {
				String paisGanador = tenistasMapaNombre.get(modelo.getValueAt(i, 2)).getNacionalidad();
				String paisFinalista = tenistasMapaNombre.get(modelo.getValueAt(i, 4)).getNacionalidad();
				String codigoTorneo = Integer.toString(torneosMapaNombre.get(modelo.getValueAt(i, 1)).getCodigo());
				
				pw.println(modelo.getValueAt(i, 0)+";"+codigoTorneo+ ";"+modelo.getValueAt(i, 2)+";"+modelo.getValueAt(i, 3)+";" 
						+ paisGanador + ";"+ modelo.getValueAt(i, 4)+ ";" + modelo.getValueAt(i, 5)+";"+ paisFinalista +";"+modelo.getValueAt(i, 6));
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
