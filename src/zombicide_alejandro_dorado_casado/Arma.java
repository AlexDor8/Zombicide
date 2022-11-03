package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Arma {

	protected String nombre;
	protected int dano;
	protected int alcance;
	protected int acierto;
	protected int usos_habilidad_especial;

// Constructor vacio de Arma (arma inicial de todos los personajes excepto James)
	public Arma() {
		nombre = "Daga";
		dano = 1;
		alcance = 1;
		acierto = 4;
		usos_habilidad_especial = 1;
	}

	public Arma(String nombre, int dano, int alcance, int acierto, int usos_habilidad_especial) {
		this.nombre = nombre;
		this.dano = dano;
		this.alcance = alcance;
		this.acierto = acierto;
		this.usos_habilidad_especial = usos_habilidad_especial;
	}

	public int getUsos_habilidad_especial() {
		return usos_habilidad_especial;
	}

	public void setUsos_habilidad_especial() {
		this.usos_habilidad_especial = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getAlcance() {
		return alcance;
	}

	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}

	public int getAcierto() {
		return acierto;
	}

	public void setAcierto(int acierto) {
		this.acierto = acierto;
	}

	public String toString() {
		return "[" + nombre + ", dano=" + dano + ", alcance=" + alcance + ", acierto=" + acierto + "]";
	}

	public ArrayList<Zombie> ataque_especial(ArrayList<Zombie> zombis) {
		return null;
	}
}
