package zombicide_alejandro_dorado_casado;

public class Humanoide {
	protected String nombre;
	protected int salud;
	protected int salud_maxima;
	protected boolean vivo;

	public Humanoide(String nombre, int salud, int salud_maxima, boolean vivo) {
		this.nombre = nombre;
		this.salud = salud;
		this.salud_maxima = salud_maxima;
		this.vivo = vivo;
	}

	public Humanoide() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int dano) {
		this.salud -= dano;
	}
	
	public void setRestaurarSalud() {
		this.salud = salud_maxima;
	}

	public int getSalud_maxima() {
		return salud_maxima;
	}

	public void setSalud_maxima(int salud_maxima) {
		this.salud_maxima = salud_maxima;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo() {
		this.vivo = false;
	}

	public String toString() {
		return "nombre=" + nombre + ", salud=" + salud + ", salud_maxima=" + salud_maxima;
	}

}
