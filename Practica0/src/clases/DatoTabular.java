package clases;

import java.io.*;
import java.util.ArrayList;

public abstract class DatoTabular {
	private ArrayList<String> cabeceras;
	private ArrayList<String> valores;
	public ArrayList<String> getCabeceras() {
		return cabeceras;
	}
	public void setCabeceras(ArrayList<String> cabeceras) {
		this.cabeceras = cabeceras;
	}
	public ArrayList<String> getValores() {
		return valores;
	}
	public void setValores(ArrayList<String> valores) {
		this.valores = valores;
	}
//	@Override
	public String toString() {
		return "DatoTabular [cabeceras=" + cabeceras + ", valores=" + valores + "]";
	}
	public DatoTabular(ArrayList<String> cabeceras, ArrayList<String> valores) {
		super();
		this.cabeceras = cabeceras;
		this.valores = valores;
	}
	
	public void setValor(String cabecera, String valor) {
		int indexCabecera = cabeceras.indexOf(cabecera);
		valores.set(indexCabecera, valor);
		
	};
	public String getValor(String cabecera) {
		int indexCabecera = cabeceras.indexOf(cabecera);
		return valores.get(indexCabecera);
	};
	
	public static ArrayList<DatoTabular> cargaCsv(String nombreFichero){
		File f = new File(nombreFichero);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<DatoTabular> datos = new ArrayList<DatoTabular>();
		
		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
			while(o != null) {
				if (o instanceof Tenista){
					Tenista t  = new Tenista((Tenista) o);
					datos.add(t);
					o = ois.readObject();
				}else if (o instanceof Torneo){
					Torneo t  = new Torneo((Torneo) o);
					datos.add(t);
					o = ois.readObject();
				}else {
					Resultado r = new Resultado((Resultado) o);
					datos.add(r);
					o = ois.readObject();
				}
			}
		}catch(Exception exc) {
			exc.getMessage();
		}
		return datos;
	};
	
	public static void guardaCsv(String nombreFichero, ArrayList<DatoTabular> datos){
		File f = new File(nombreFichero);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			if (datos.size() >0) {
				for (int i = 0; i<datos.size();i++) {
					if (datos.get(i)!= null) {
						oos.writeObject(datos.get(i));
					}	
				}
			}
		}catch (Exception exc){
			exc.getMessage();
		}
		try {
			oos.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}  
		
	};
	
}
