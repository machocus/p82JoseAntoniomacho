package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the barcos database table.
 * 
 */
@Entity
@Table(name="barcos")
@NamedQueries({
	@NamedQuery(name="Barco.findAll", query="SELECT b FROM Barco b"),
	@NamedQuery(name = "Barco.findNombre", query = "SELECT v FROM Barco v WHERE v.nombre = :nombre")
})

public class Barco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codbarcos;

	private String nombre;

	public Barco() {
	}

	public int getCodbarcos() {
		return this.codbarcos;
	}

	public void setCodbarcos(int codbarcos) {
		this.codbarcos = codbarcos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Barco [codbarcos=" + codbarcos + ", nombre=" + nombre + "]";
	}
	

}