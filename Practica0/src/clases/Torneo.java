package clases;

import java.util.ArrayList;

public class Torneo extends DatoTabular{
	private int codigo;
	private String nombre;
	private String ciudad;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
		setValor("Codigo",Integer.toString(codigo));
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		setValor("Nombre",nombre);
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
		setValor("Ciudad",ciudad);
	}
	
	public Torneo(int codigo) {
		super(crearCabeceras(), crearValores(codigo));
		this.codigo = codigo;
		this.nombre = getNombreFromCodigo(codigo);
		this.ciudad = getCiudadFromCodigo(codigo);
	}
	
	public Torneo(Torneo t) {
		super(crearCabeceras(), crearValores(t.codigo));
		this.codigo = t.codigo;
		this.nombre = getNombreFromCodigo(t.codigo);
		this.ciudad = getCiudadFromCodigo(t.codigo);
	}
	
//	@Override
	public String toString() {
		return nombre;
	}
	
	private static ArrayList<String> crearCabeceras(){
		ArrayList<String> a = new ArrayList<>();
		a.add("Codigo");
		a.add("Nombre");
		a.add("Ciudad");
		return a;
	}
	
	private static ArrayList<String> crearValores(int c){
		ArrayList<String> a = new ArrayList<>();
		a.add(Integer.toString(c));
		a.add(getNombreFromCodigo(c));
		a.add(getCiudadFromCodigo(c));
		return a;
	}
	
	private static String getCiudadFromCodigo(int codigo) {
		if (codigo==1) {
			return "Melbourne";
		}else if (codigo==2) {
			return "Paris";
		}else if (codigo==3) {
			return "Londres";
		}else {
			return "Nueva York";
		}
	}
	
	private static String getNombreFromCodigo(int codigo) {
		if (codigo==1) {
			return "Australian Open";
		}else if (codigo==2) {
			return "Roland Garros";
		}else if (codigo==3) {
			return "Wimbledon";
		}else {
			return "US Open";
		}
	}
	
}
