package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Caminante extends Zombie {

	public Caminante(String nombre, int salud, int salud_maxima, boolean vivo, int movimiento, int dano, int tipo) {
		super(nombre, salud, salud_maxima, vivo, movimiento, dano, tipo);
	}

	public Caminante(boolean vivo) {
		nombre = "Caminante";
		salud = 1;
		salud_maxima = 1;
		this.vivo = vivo;
		movimiento = 1;
		dano = 1;
		tipo = 1;
	}

	// Devuelve la cantidad de caminantes enemigos
	public int numero_caminates(ArrayList<Zombie> zombies) {
		int contador = 0;
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).getTipo() == 1) {
				contador++;
			}
		}
		return contador;
	}

	// Añadimos en el Arraylist de zombies enemigos tantos caminantes como haya, es
	// decir, los duplicamos
	public ArrayList<Zombie> habilidad_especial(ArrayList<Zombie> zombies) {
		int cantidad_nuevos_caminantes = numero_caminates(zombies);// Cantidad de caminantes enemigos
		for (int i = 0; i < cantidad_nuevos_caminantes; i++) {
			zombies.add(new Caminante(true));
		}
		return zombies;
	}

}
