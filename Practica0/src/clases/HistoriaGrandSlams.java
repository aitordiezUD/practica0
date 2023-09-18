package clases;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HistoriaGrandSlams {
	private ArrayList<DatoTabular> resultados;
	private ArrayList<DatoTabular> tenistas;
	private ArrayList<DatoTabular> torneos;
	private HashMap<String,Torneo> torneosMapaNombre;
	private HashMap<Integer,Torneo> torneosMapaCodigo;
	private HashMap<String,Tenista> tenistasMapaNombre;
	private TreeMap<Tenista,Integer> mapaVictoriasTenistas;
	
	


//	public HistoriaGrandSlams() {
//			this.resultados = new ArrayList<Resultado>();
//			this.tenistas = new ArrayList<Tenista>();
//			this.torneos = new ArrayList<Torneo>();
//			this.torneosMapaNombre = new HashMap<String,Torneo>();
//			this.torneosMapaCodigo = new HashMap<Integer,Torneo>();
//			this.tenistasMapaNombre = new HashMap<String,Tenista>();
//			this.mapaVictoriasTenistas = new TreeMap<Tenista,Integer>();
//		}
	
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
	

}
