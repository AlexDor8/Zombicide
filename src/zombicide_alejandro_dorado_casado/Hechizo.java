package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Hechizo extends Arma {
	public Hechizo(String nombre, int dano, int alcance, int acierto, int usos_habilidad_especial) {
		super(nombre, dano, alcance, acierto, usos_habilidad_especial);
	}

	// Al activarse elimina a dos zombies de tipo 1(Caminante)
	public ArrayList<Zombie> ataque_especial(ArrayList<Zombie> zombies) {
		int contador = 0;
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).getTipo() == 1) {
				zombies.remove(i);
				contador++;
			}

			if (contador == 1) {
				break;
			}
		}
		return zombies;
	}

}
