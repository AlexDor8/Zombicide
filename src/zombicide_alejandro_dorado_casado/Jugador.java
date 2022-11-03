package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Jugador extends Humanoide {
	protected Arma arma;
	protected boolean pasa_ronda;
	protected ArrayList<Arma> armas_disponibles;

	public Jugador(String nombre, int salud, int salud_maxima, boolean vivo, boolean pasa_ronda, Arma arma) {
		super(nombre, salud, salud_maxima, vivo);
		this.pasa_ronda = pasa_ronda;
		this.arma = arma;
		this.armas_disponibles = new ArrayList<Arma>();
		this.armas_disponibles.add(arma);
	}
	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public boolean isPasa_ronda() {
		return pasa_ronda;
	}

	public void setPasa_ronda(boolean pasa_ronda) {
		this.pasa_ronda = pasa_ronda;
	}
	
	public String toString() {
		return nombre + ", S=" + salud + ",Arma="+arma;
	}
	
	

	
}
