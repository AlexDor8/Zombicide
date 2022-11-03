package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Gordo extends Zombie {
	public Gordo(String nombre, int salud, int salud_maxima, boolean vivo, int movimiento, int dano, int tipo) {
		super(nombre, salud, salud_maxima, vivo, movimiento, dano, tipo);
	}

	public Gordo(boolean vivo) {
		nombre = "Gordo";
		salud = 2;
		salud_maxima = 2;
		this.vivo = vivo;
		movimiento = 1;
		dano = 1;
		tipo = 2;
	}
	
	// Eliminamos un zombie de tipo 2 (Gordo) de nuestro Arraylist de zombies enemigos
	public ArrayList<Zombie> habilidad_especial(ArrayList<Zombie> zombies) {
		int contador = 0;
			for (int i = 0;i <zombies.size() && contador < 1;i++) {
				if (zombies.get(i).getTipo() == 2) {
						zombies.remove(i);	
						contador++;
				}
			}
		return zombies;
	}
}
