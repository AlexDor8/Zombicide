package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Corredor extends Zombie {
	public Corredor(String nombre, int salud, int salud_maxima, boolean vivo, int movimiento, int dano, int tipo) {
		super(nombre, salud, salud_maxima, vivo, movimiento, dano, tipo);
	}

	public Corredor(boolean vivo) {
		nombre = "Corredor";
		salud = 1;
		salud_maxima = 1;
		this.vivo = vivo;
		movimiento = 2;
		dano = 1;
		tipo = 3;
	}

	// Eliminamos a todos los zombies de tipo 3 (Corredor) de nuestro Arraylist de zombies enemigos
	public ArrayList<Zombie> habilidad_especial(ArrayList<Zombie> zombies) {
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).getTipo() == 3) {
				zombies.remove(i);
			}
		}
		return zombies;
	}
}
