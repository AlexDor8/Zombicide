package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;

public class Arco extends Arma {
	public Arco(String nombre, int dano, int alcance, int acierto, int usos_habilidad_especial) {
		super(nombre, dano, alcance, acierto, usos_habilidad_especial);
	}

    // Al activarse elimina a un zombie de tipo 3(Corredor)
	public ArrayList<Zombie> ataque_especial(ArrayList<Zombie> zombies) {
		int contador = 0;
		for (int i = 0; i < zombies.size() && contador < 1; i++) {
			if (zombies.get(i).getTipo() == 3) {
				zombies.remove(i);
				contador++;
			}
		}
		return zombies;
	}

}
