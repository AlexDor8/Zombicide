package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Espada extends Arma {
	public Espada(String nombre, int dano, int alcance, int acierto, int usos_habilidad_especial) {
		super(nombre, dano, alcance, acierto, usos_habilidad_especial);
	}

	// Generamos dos numeros aleatorios y eliminamos al zombie que ocupe esa posicion
	// en el Arraylist de zombies enemigos
	public ArrayList<Zombie> ataque_especial(ArrayList<Zombie> zombies) {
		int contador = 0;
		do {
			int zombie_aleatorio = (int) (Math.random() * zombies.size()) + 0;
			zombies.remove(zombie_aleatorio);
			contador++;
		} while ((!(contador < 2)));
		return zombies;
	}
}
