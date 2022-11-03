package zombicide_alejandro_dorado_casado;

import java.util.ArrayList;
import java.util.Scanner;

public class Zombicide {
	protected static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	protected static ArrayList<Jugador> jugadores_seleccionados = new ArrayList<Jugador>();
	protected static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	private static ArrayList<Arma> armas = new ArrayList<Arma>();

	public static void main(String[] args) {
		init_zombies();
		init_personajes();
		init_objetos();
		MenuZombicide();
	}

	public static void MenuZombicide() {
		Scanner teclado = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("MENU ZOMBICIDE");
			System.out.println("1- Nueva partida");
			System.out.println("2- Nuevo personaje");
			System.out.println("0- Salir");
			opcion = teclado.nextInt();
		} while (!(opcion >= 0 && opcion <= 2));// En el caso de que no introduzca un numero entre 0 y 2, le volvemos a
												// mostrar el menu hasta que si lo haga
		OpcionMenuPrincipal(opcion);
	}

	// Segun la opcion que introduzca realizamos una accion o otra
	public static void OpcionMenuPrincipal(int opcion) {
		switch (opcion) {
		case 1:
			nueva_partida();
			break;
		case 2:
			nuevo_personaje();
			MenuZombicide();
			break;
		case 0:
			break;
		}
	}

	// Iniciamos la partida con los jugadores que hemos seleccionado previamente
	public static void nueva_partida() {
		seleccionarJugadores();
		Partida partida = new Partida(jugadores_seleccionados);
		partida.initPartida();

	}

	public static void nuevo_personaje() {
		Scanner teclado = new Scanner(System.in);
		if (jugadores.size() < 10) {// En el caso de que hayan menos de 10 jugadores
			System.out.println("Introduce el nombre del nuevo personaje:");
			String nombre = teclado.nextLine();
			boolean personajeExiste = personajeYaExiste(nombre);// Comprobamos que no exista ya un jugador con ese mismo
																// nombre
			if (personajeExiste == true) {
				System.out.println("Este jugador ya existe.");
				nuevo_personaje();
			} else {// En el caso de que no, lo anadimos en nuestro arraylist de jugadores
				jugadores.add(new Jugador(nombre, 5, 5, true, true, new Arma()));
				System.out.println("El jugador ha sido añadido a tu lista de jugadores!");
			}
		} else {
			System.out.println("¡El maximo de jugadores es 10. No puedes añadir mas personajes.");
		}
	}

	// Devolvemos un boolean con valor false en el caso de que no haya ningun
	// jugador con el nombre introducido por el ususario, en caso contrario,
	// devolvemos true indicando que si que lo hay
	public static boolean personajeYaExiste(String nombre) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {
				return true;
			}
		}
		return false;
	}

	public static void init_personajes() {
		jugadores.add(new Jugador("James", 7, 7, true, true, new Arma("Mandoble", 2, 1, 4, 1)));
		jugadores.add(new Jugador("Marie", 5, 5, true, true, new Arma()));
		jugadores.add(new Jugador("Jaci", 5, 5, true, true, new Arma()));
	}

	public static void init_zombies() {
		zombies.add(new Caminante(true));
		zombies.add(new Gordo(true));
		zombies.add(new Corredor(true));
	}

	public static void init_objetos() {
		armas.add(new Arco("Arco largo", 1, 2, 3, 1));
		armas.add(new Hacha("Hacha doble", 2, 1, 3, 1));
		armas.add(new Hechizo("Bola de fuego", 1, 3, 4, 1));
		armas.add(new Espada("Espada corta", 1, 1, 4, 1));
	}

	// Recorremos el Arraylist de jugadores y mostramos los datos de cada uno
	public static void todosJugadores() {
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println(i + "-->" + jugadores.get(i).toString());
		}
		System.out.println("");
	}

	public static ArrayList<Jugador> seleccionarJugadores() {
		Scanner teclado = new Scanner(System.in);
		if (jugadores.size() == 3) {// En el caso de que unicamente hayan 3 jugadores creados, devolvemos estos que
									// seran nuestros jugadores seleccionados
			jugadores_seleccionados = jugadores;
			return jugadores_seleccionados;
		} else {
			int num_jugadores;
			int num_seleccionados = 1;
			int jugador_seleccionado;
			do {
				System.out
						.println("Con cuantos jugadores quieres jugar? (El minimo de jugadores es 3, el maximo es 6)");
				num_jugadores = teclado.nextInt();
			} while (!(num_jugadores >= 3 && num_jugadores <= jugadores.size() && num_jugadores <= 6));
			// En el caso de que no introduzca un numero entre 3 y 6, volvemos a pedirselo
			do {
				do {
					System.out.println("Selecciona el jugador numero " + num_seleccionados + ":");
					System.out.println("Introduce el numero que corresponda al jugador que quieras seleccionar:");
					todosJugadores();
					jugador_seleccionado = teclado.nextInt();// Mostramos todos los jugadores
				} while (!(jugador_seleccionado < jugadores.size() && jugador_seleccionado >= 0));
				// En el caso de que introduzca un numero que no corresponda a ninguna posicion
				// del Arraylist de jugadores, volvemos a pedirselo
				boolean jugador_ya_seleccionado = jugadorYaSeleccionado(jugador_seleccionado);
				if (jugador_ya_seleccionado == false) {// Comprobamos que no seleccine dos veces a un mismo jugador
					jugadores_seleccionados.add(new Jugador(jugadores.get(jugador_seleccionado).getNombre(),
							jugadores.get(jugador_seleccionado).getSalud(),
							jugadores.get(jugador_seleccionado).getSalud_maxima(),
							jugadores.get(jugador_seleccionado).isVivo(),
							jugadores.get(jugador_seleccionado).isPasa_ronda(),
							jugadores.get(jugador_seleccionado).getArma()));
					num_seleccionados++;
				} else {
					System.out.println("Este jugador ya ha sido seleccionado. Selecciona otro jugador.");
				}
			} while (!(num_seleccionados > num_jugadores));
			return jugadores_seleccionados;
		}
	}

	// Recorremos el Arraylist de jugadores seleccionados, en caso de que el usuario
	// introduza un numero que corresponda a la posicion de un jugador seleccionada
	// previamente devolvemos un true. En caso contrario, devolvemos un false
	public static boolean jugadorYaSeleccionado(int jugador_seleccionado) {
		for (int i = 0; i < jugadores_seleccionados.size(); i++) {
			if (jugadores_seleccionados.get(i).getNombre()
					.equalsIgnoreCase(jugadores.get(jugador_seleccionado).getNombre())) {
				return true;
			}
		}
		return false;
	}

	// Generamos un numero aleatorio entre 0 y 100. En el caso de que este sea mayor
	// que 40, devolvemos un arma aleatoria de nuestro Arraylist de armas
	public static Arma getArma() {
		int porcentage = (int) (Math.random() * 100) + 1;
		if (porcentage > 40) {
			int arma_aleatoria = (int) (Math.random() * armas.size()) + 0;
			return armas.get(arma_aleatoria);
		} else {
			return null;
		}
	}
}