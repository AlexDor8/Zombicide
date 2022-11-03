package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
	private ArrayList<Jugador> jugadores_seleccionados;
	private int nivel = Zombicide.jugadores_seleccionados.size();
	private ArrayList<Zombie> zombies_enemigos;
	private int ronda = 0;
	protected int jugador_actual;
	protected int zombie_actual;
	protected int movimiento;
	protected int alcance;

	public Partida(ArrayList<Jugador> jugadores_seleccionados) {
		this.zombies_enemigos = new ArrayList<Zombie>();
		this.jugadores_seleccionados = new ArrayList<Jugador>();
		this.jugadores_seleccionados.addAll(jugadores_seleccionados);
	}

	public void MenuPartida() {
		Scanner teclado = new Scanner(System.in);
		int opcion;
		mostrarZombies();
		do {
			System.out.println("");
			System.out.println("1- Atacar");
			System.out.println("2- Buscar");
			System.out.println("3- Cambiar arma");
			System.out.println("4- Usar habilidad especial");
			System.out.println("0- Pasar");
			opcion = teclado.nextInt();
		} while (!(opcion >= 0 && opcion <= 4));// En el caso de que no introduzca un numero entre 0 y 4, le volvemos a
		// mostrar el menu hasta que si lo haga
		OpcionMenuPartida(opcion);
	}

	// Segun la opcion que introduzca realizamos una accion o otra
	public void OpcionMenuPartida(int opcion) {
		switch (opcion) {
		case 1:
			num_veces_atacar();
			break;
		case 2:
			buscar();
			break;
		case 3:
			cambiarArma();
			break;
		case 4:
			habEspacialUsada();
			break;
		case 0:
			break;
		}
	}

	// Recorremos el Arraylist de jugadores y le cambiamos su salud a la que tenian
	// inicialmente
	public void resetearSalud() {
		for (int i = 0; i < Zombicide.jugadores.size(); i++) {
			Zombicide.jugadores.get(i).setRestaurarSalud();
		}
	}

	// Devolvemos el arma que se encuentre en la posicion 0 del Arraylist de armas
	// disponibles del jugador introducido por parametro, es decir, su arma incial
	public Arma armaInicial(int j) {
		return Zombicide.jugadores.get(j).armas_disponibles.get(0);
	}

	public void todasArmasMenosInicial(int j) {
		if (Zombicide.jugadores.get(j).armas_disponibles.size() > 1) {
			Arma arma_inicial = armaInicial(j);
			Zombicide.jugadores.get(j).setArma(arma_inicial);// Le equipamos la jugador su arma inicial
			// Eliminamos todas las armas excepto su arma inicial
			for (int i = 0; i < Zombicide.jugadores.get(j).armas_disponibles.size(); i++) {
				Zombicide.jugadores.get(j).armas_disponibles.remove(i + 1);
			}
		}
	}

	// Recorremos todos los jugadores para eliminar las armas añadidad en estos
	// durante la partida
	public void resetearArmas() {
		for (int j = 0; j < Zombicide.jugadores.size(); j++) {
			todasArmasMenosInicial(j);
		}
	}

	// Iniciamos la partida
	public void initPartida() {
		do {
			System.out.println(
					"Todos tus personajes han sido seleccionados \n |----- NIVEL: " + nivel + " - " + ronda + "-----|");
			initZombiesEnemigos();
			turnoJugador();
			ronda++;// Cada vez que ataquen todos los jugadores y zombies augmentamos en uno la
					// ronda
			if (zombies_enemigos.size() == 0) {// Si mueren todos los zombies enemigos, augmnetamos el nivel en uno y
												// generamos mas zombies enemigos
				nivel++;
				initZombiesEnemigos();
			}
		} while (jugadores_seleccionados.size() != 0);// En el caso de que mueran todos los jugadores se acaba la
														// partida
		resetearSalud();
		resetearArmas();
		System.out.println();
		System.err.println("Has perdido.");
		Zombicide.MenuZombicide();
	}

	// Generamos zombies aleatorios entre los tres distintos tipos hasta igualar en
	// cantidad el nivel
	public void initZombiesEnemigos() {
		for (int i = 0; i < nivel; i++) {
			int zombie_aleatorio = (int) (Math.random() * Zombicide.zombies.size()) + 0;
			// Dependiendo de que tipo de zombie sea el que hemos generado, lo añadimos a
			// nuestro Arraylist de zombies enemigos con su clase correspondiente
			if (Zombicide.zombies.get(zombie_aleatorio) instanceof Gordo) {
				zombies_enemigos.add(new Gordo(Zombicide.zombies.get(zombie_aleatorio).getNombre(),
						Zombicide.zombies.get(zombie_aleatorio).getSalud(),
						Zombicide.zombies.get(zombie_aleatorio).getSalud_maxima(),
						Zombicide.zombies.get(zombie_aleatorio).isVivo(),
						Zombicide.zombies.get(zombie_aleatorio).getMovimiento(),
						Zombicide.zombies.get(zombie_aleatorio).getDano(),
						Zombicide.zombies.get(zombie_aleatorio).getTipo()));
			} else if (Zombicide.zombies.get(zombie_aleatorio) instanceof Caminante) {
				zombies_enemigos.add(new Caminante(Zombicide.zombies.get(zombie_aleatorio).getNombre(),
						Zombicide.zombies.get(zombie_aleatorio).getSalud(),
						Zombicide.zombies.get(zombie_aleatorio).getSalud_maxima(),
						Zombicide.zombies.get(zombie_aleatorio).isVivo(),
						Zombicide.zombies.get(zombie_aleatorio).getMovimiento(),
						Zombicide.zombies.get(zombie_aleatorio).getDano(),
						Zombicide.zombies.get(zombie_aleatorio).getTipo()));
			} else if (Zombicide.zombies.get(zombie_aleatorio) instanceof Corredor) {
				zombies_enemigos.add(new Corredor(Zombicide.zombies.get(zombie_aleatorio).getNombre(),
						Zombicide.zombies.get(zombie_aleatorio).getSalud(),
						Zombicide.zombies.get(zombie_aleatorio).getSalud_maxima(),
						Zombicide.zombies.get(zombie_aleatorio).isVivo(),
						Zombicide.zombies.get(zombie_aleatorio).getMovimiento(),
						Zombicide.zombies.get(zombie_aleatorio).getDano(),
						Zombicide.zombies.get(zombie_aleatorio).getTipo()));
			}

		}
	}

	public void mostrarZombies() {
		System.out.print("==|");
		for (int i = 0; i < zombies_enemigos.size(); i++) {
			System.out.print(" " + zombies_enemigos.get(i).getNombre());
		}
		System.out.print("|==");
	}

	public void turnoJugador() {
		for (jugador_actual = 0; jugador_actual < jugadores_seleccionados.size(); jugador_actual++) {
			System.out.println("JUGADOR: " + jugadores_seleccionados.get(jugador_actual).toString());
			MenuPartida();
		}
		System.out.println("");
		turnoZombie();
	}

	public void turnoZombie() {
		for (zombie_actual = 0; zombie_actual < zombies_enemigos.size()
				&& jugadores_seleccionados.size() > 0; zombie_actual++) {
			System.out.println("ZOMBIE: " + zombies_enemigos.get(zombie_actual).toString());
			atacarZombie();
		}
	}

	public void buscar() {
		Arma arma_obtenida = Zombicide.getArma();
		if (arma_obtenida == null) {
			System.out.println("Mala suerte. No has encontrado una nueva arma");
		} else {
			System.out.println("Enhorabuena. Has encontrado una nueva arma! \nEs la siguiente: ");
			System.out.println(arma_obtenida.toString());
			if (arma_obtenida instanceof Hacha) {
				jugadores_seleccionados.get(jugador_actual).armas_disponibles
						.add(new Hacha(arma_obtenida.getNombre(), arma_obtenida.getDano(), arma_obtenida.getAlcance(),
								arma_obtenida.getAcierto(), arma_obtenida.getUsos_habilidad_especial()));
			}

			else if (arma_obtenida instanceof Arco) {
				jugadores_seleccionados.get(jugador_actual).armas_disponibles
						.add(new Arco(arma_obtenida.getNombre(), arma_obtenida.getDano(), arma_obtenida.getAlcance(),
								arma_obtenida.getAcierto(), arma_obtenida.getUsos_habilidad_especial()));
			} else if (arma_obtenida instanceof Hechizo) {
				jugadores_seleccionados.get(jugador_actual).armas_disponibles
						.add(new Hechizo(arma_obtenida.getNombre(), arma_obtenida.getDano(), arma_obtenida.getAlcance(),
								arma_obtenida.getAcierto(), arma_obtenida.getUsos_habilidad_especial()));
			} else if (arma_obtenida instanceof Espada) {
				jugadores_seleccionados.get(jugador_actual).armas_disponibles
						.add(new Espada(arma_obtenida.getNombre(), arma_obtenida.getDano(), arma_obtenida.getAlcance(),
								arma_obtenida.getAcierto(), arma_obtenida.getUsos_habilidad_especial()));
			}
		}
	}

	public void todasArmasJugador() {
		for (int i = 0; i < jugadores_seleccionados.get(jugador_actual).armas_disponibles.size(); i++) {
			System.out.println(
					i + "--> " + jugadores_seleccionados.get(jugador_actual).armas_disponibles.get(i).toString());
		}
	}

	public void cambiarArma() {
		Scanner teclado = new Scanner(System.in);
		if (jugadores_seleccionados.get(jugador_actual).armas_disponibles.size() == 1) {
			System.out.println("No puedes cambiar de arma. Solo tienes una arma.");
			MenuPartida();
		} else {
			int arma_seleccionada;
			do {
				System.out.println("Introduce el numero que corresponda a la arma que quieres:");
				todasArmasJugador();
				arma_seleccionada = teclado.nextInt();
			} while (!(arma_seleccionada >= 0
					&& arma_seleccionada < jugadores_seleccionados.get(jugador_actual).armas_disponibles.size()));
			Arma nueva_arma = jugadores_seleccionados.get(jugador_actual).armas_disponibles.get(arma_seleccionada);
			jugadores_seleccionados.get(jugador_actual).setArma(nueva_arma);
		}
	}

	public void habEspacialUsada() {
		if (jugadores_seleccionados.get(jugador_actual).getArma().getUsos_habilidad_especial() == 0) {
			System.out.println("Ya has usado la habilidad especial de este arma.");
			MenuPartida();
		} else {
			usarHabEspecial();
		}
	}

	public void usarHabEspecial() {
		if (jugadores_seleccionados.get(jugador_actual).getArma().ataque_especial(zombies_enemigos) == null) {
			System.out.println("Esta arma no tiene habilidad especial.");
		} else {
			this.zombies_enemigos = jugadores_seleccionados.get(jugador_actual).getArma()
					.ataque_especial(this.zombies_enemigos);
			jugadores_seleccionados.get(jugador_actual).getArma().setUsos_habilidad_especial();
			System.out.println("¡Has usado la habilidad especial de tu arma!");
		}
	}

	public Zombie atacarAzombieAleatorio() {
		int zombie_aleatorio = (int) (Math.random() * zombies_enemigos.size()) + 0;
		return zombies_enemigos.get(zombie_aleatorio);
	}

	public Jugador atacarJugadorAleatorio() {
		int jugador_aleatorio = (int) (Math.random() * jugadores_seleccionados.size()) + 0;
		return jugadores_seleccionados.get(jugador_aleatorio);
	}

	public int dadoAleatorio() {
		int tirada_dado = (int) (Math.random() * 6) + 1;
		return tirada_dado;
	}

	public void num_veces_atacar() {
		int contador = 0;
		do {
			atacar();
			contador++;
		} while (contador < jugadores_seleccionados.get(jugador_actual).getArma().getAlcance());
	}

	public void atacar() {
		Zombie zombie_enemigo = atacarAzombieAleatorio();
		int habilidad_muerte = (int) (Math.random() * 100) + 0;
		if (jugadores_seleccionados.get(jugador_actual).getArma().getDano() >= zombie_enemigo.getSalud()) {
			int numero_dado = dadoAleatorio();
			if (numero_dado >= jugadores_seleccionados.get(jugador_actual).getArma().getAcierto()) {
				System.out.println(zombie_enemigo.getNombre() + " ha muerto");
				if (habilidad_muerte >= 95) {
					System.out.println(zombie_enemigo.getNombre() + " ha activado su habilidad especial al morir!");
					zombie_enemigo.habilidad_especial(zombies_enemigos);
				}
				zombies_enemigos.remove(zombie_enemigo);
			} else {
				System.out.println("Ha fallado tu ataque con un " + numero_dado);
			}
		} else {
			System.out.println(zombie_enemigo.getNombre() + " ha evitado el ataque.");
		}
	}

	public void atacarZombie() {
		movimiento = zombies_enemigos.get(zombie_actual).getMovimiento();
		System.out.println("El zombie esta atacando...");
		do {
			Jugador jugador_recibeDano = atacarJugadorAleatorio();
			jugador_recibeDano.setSalud(zombies_enemigos.get(zombie_actual).getDano());
			System.out.println(jugador_recibeDano.getNombre() + " ha recibido "
					+ zombies_enemigos.get(zombie_actual).getDano() + " puntos de daño");
			if (jugador_recibeDano.getSalud() > 0) {
				System.out.println("Su vida actual es de " + jugador_recibeDano.getSalud() + " PS.");
			} else {
				System.out.println(jugador_recibeDano.getNombre() + " ha muerto");
				jugadores_seleccionados.remove(jugador_recibeDano);
			}
			movimiento--;
		} while (jugadores_seleccionados.size() != 0 && movimiento != 0);
	}
}