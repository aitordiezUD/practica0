package clases;

import java.util.ArrayList;

public class Resultado extends DatoTabular implements Comparable<Resultado>{
	
	private int anyo;
	private Torneo torneo;
	private Tenista ganador;
	private int ranking_ganador;
	private Tenista subcampeon;
	private int ranking_subcampeon;
	private String resultado;
	
	
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
		setValor("Año",Integer.toString(anyo));
	}
	public Torneo getTorneo() {
		return torneo;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
		setValor("Torneo",torneo.toString());
	}
	public Tenista getGanador() {
		return ganador;
	}
	public void setGanador(Tenista ganador) {
		this.ganador = ganador;
		setValor("Ganador",ganador.toString());
	}
	public int getRanking_ganador() {
		return ranking_ganador;
	}
	public void setRanking_ganador(int ranking_ganador) {
		this.ranking_ganador = ranking_ganador;
		setValor("Ranking ganador",Integer.toString(ranking_ganador));
	}
	public Tenista getSubcampeon() {
		return subcampeon;
	}
	public void setSubcampeon(Tenista subcampeon) {
		this.subcampeon = subcampeon;
		setValor("Subcampeon",subcampeon.toString());
	}
	public int getRanking_subcampeon() {
		return ranking_subcampeon;
	}
	public void setRanking_subcampeon(int ranking_subcampeon) {
		this.ranking_subcampeon = ranking_subcampeon;
		setValor("Ranking subcampeon", Integer.toString(ranking_subcampeon));
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
		setValor("Resultado", resultado);
	}
//	@Override
	public String toString() {
		return "Resultado [anyo=" + anyo + ", torneo=" + torneo + ", ganador=" + ganador + ", ranking_ganador="
				+ ranking_ganador + ", subcampeon=" + subcampeon + ", ranking_subcampeon=" + ranking_subcampeon
				+ ", resultado=" + resultado + "]";
	}
	public Resultado(int anyo, Torneo torneo, Tenista ganador,
			int ranking_ganador, Tenista subcampeon, int ranking_subcampeon, String resultado) {
		super(crearCabeceras(), crearValores(anyo,torneo, ganador,ranking_ganador, subcampeon, ranking_subcampeon, resultado));
		this.anyo = anyo;
		this.torneo = torneo;
		this.ganador = ganador;
		this.ranking_ganador = ranking_ganador;
		this.subcampeon = subcampeon;
		this.ranking_subcampeon = ranking_subcampeon;
		this.resultado = resultado;
	}
	
	public Resultado(Resultado r) {
		super(crearCabeceras(), crearValores(r.anyo,r.torneo, r.ganador,r.ranking_ganador, r.subcampeon, r.ranking_subcampeon, r.resultado));
		this.anyo = r.anyo;
		this.torneo = r.torneo;
		this.ganador = r.ganador;
		this.ranking_ganador = r.ranking_ganador;
		this.subcampeon = r.subcampeon;
		this.ranking_subcampeon = r.ranking_subcampeon;
		this.resultado = r.resultado;
	}
	
	private static ArrayList<String> crearCabeceras(){
		ArrayList<String> a = new ArrayList<>();
		a.add("Año");
		a.add("Torneo");
		a.add("Ganador");
		a.add("Ranking ganador");
		a.add("Subcampeon");
		a.add("Ranking subcampeon");
		a.add("Resultado");
		return a;
	}
	
	private static ArrayList<String> crearValores(int anyo, Torneo torneo, Tenista ganador,
			int ranking_ganador, Tenista subcampeon, int ranking_subcampeon, String resultado){
		ArrayList<String> a = new ArrayList<>();
		a.add(Integer.toString(anyo));
		a.add(torneo.toString());
		a.add(ganador.toString());
		a.add(Integer.toString(ranking_ganador));
		a.add(subcampeon.toString());
		a.add(Integer.toString(ranking_subcampeon));
		a.add(resultado);
		return a;
	}
	
	@Override
    public int compareTo(Resultado otroResultado) {
        return Integer.compare(this.anyo, otroResultado.anyo);
    }
    
	
	
}
