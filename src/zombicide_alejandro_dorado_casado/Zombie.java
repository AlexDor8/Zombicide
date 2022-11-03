package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Zombie extends Humanoide {
	protected int movimiento;
	protected int dano;
	protected int tipo;

	public Zombie(String nombre, int salud, int salud_maxima, boolean vivo, int movimiento, int dano, int tipo) {
		super(nombre, salud, salud_maxima, vivo);
		this.movimiento = movimiento;
		this.dano = dano;
		this.tipo = tipo;
	}
	public Zombie() {
		
	}
	
	public int getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String toString() {
		return nombre + " movimiento = " + movimiento +", dano = " +dano+ 
				 " salud= " + salud;
	}
	
	public ArrayList<Zombie> habilidad_especial(ArrayList<Zombie> zombies) {
		return zombies;
	}
	
}
