package clases;


import java.util.ArrayList;

public class Tenista extends DatoTabular implements Comparable<Tenista>{
	private String nombre;
	private String nacionalidad;
	private int numVictorias;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		setValor("Nombre",nombre);
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
		setValor("Nacionalidad",nacionalidad);
	}
	public int getNumVictorias() {
		return numVictorias;
	}
	
	public void setNumVictorias(int numVictorias) {
		this.numVictorias = numVictorias;
		setValor("VictoriasGrandSlam",Integer.toString(numVictorias));
	}
	
	public void setNumVictoriasYordenar(int numVictorias) {
		this.numVictorias = numVictorias;
		VentanaGrandSlam.tablaTenistas.actualizarTablaTenistas(this);
		setValor("VictoriasGrandSlam",Integer.toString(numVictorias));
		VentanaGrandSlam.tablaTenistas.ordenarPorVictorias();
	}
	public Tenista(String nombre, String nacionalidad, int numVictorias) {
		super(crearCabeceras(), crearValores(nombre,nacionalidad,numVictorias));
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.numVictorias = numVictorias;
	}
	
	public Tenista(Tenista t) {
		super(crearCabeceras(), crearValores(t.nombre,t.nacionalidad,t.numVictorias));
		this.nombre = t.nombre;
		this.nacionalidad = t.nacionalidad;
		this.numVictorias = t.numVictorias;
	}
	
//	@Override
	public String toString() {
		return nombre;
	}
	
	private static ArrayList<String> crearCabeceras(){
		ArrayList<String> a = new ArrayList<>();
		a.add("Nombre");
		a.add("Nacionalidad");
		a.add("VictoriasGrandSlam");
		return a;
	}
	
	private static ArrayList<String> crearValores(String nombre, String nacionalidad, int numVictorias){
		ArrayList<String> a = new ArrayList<>();
		a.add(nombre);
		a.add(nacionalidad);
		a.add(Integer.toString(numVictorias));
		return a;
	}
//	@Override
	public int compareTo(Tenista o) {
		// TODO Auto-generated method stub
		  return Integer.compare(o.numVictorias,this.numVictorias);
	}
	
	
	
}
