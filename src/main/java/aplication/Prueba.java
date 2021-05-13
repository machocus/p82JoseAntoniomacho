package aplication;

import java.util.List;

import controllers.ControladorPuerto;
import entities.Barco;

public class Prueba {

	public static void main(String[] args) {
		ControladorPuerto cv = new ControladorPuerto();
		// Se obtienen todas las instancias
		List<Barco> listaBarcos = cv.findAll();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (Barco b : listaBarcos) {
			System.out.println(b);
		}

		// Se obtiene una entidad
		System.out.println("Buscar Barco de nombre kayac ------------ ");
		Barco aux = cv.findByNombre("kayac");
		System.out.println(aux);

		// creacion  de una entidad
		Barco v = new Barco();
		v.setCodbarcos(11);
		v.setNombre("BarcoP82");
		cv.createBarco(v); // Si est√° creada lanzar√° una excepci√≥n

		// Se obtienen todas las instancias
		listaBarcos = cv.findAll();
		System.out.println("Todas las entidades despues©s de crear una ------------ ");
//		listaVehiculos.forEach(System.out::println);
		for (Barco nombre : listaBarcos)
			System.out.println(nombre.toString());
		// Se modifica el barco
		Barco barcoModificar = cv.findByNombre("Titanic");
		if (barcoModificar != null) {
			barcoModificar.setCodbarcos(1111);
			cv.modifyBarco(barcoModificar);
		}

		// Se obtienen todas las instancias
		listaBarcos = cv.findAll();
		System.out.println("Todas las entidades despues de modificar una ------------ ");
//		listaVehiculos.forEach(System.out::println);
		for (Barco nombre : listaBarcos)
			System.out.println(nombre);
		// Borrado del barco kayac
		Barco barcoBorrar = cv.findByNombre("kayac");
		cv.borrarBarco(barcoBorrar);

		// Se obtienen todas las instancias
		listaBarcos = cv.findAll();
		System.out.println("Todas las entidades despues©s de borrar una ------------ ");
//		listaVehiculos.forEach(System.out::println);
		for (Barco nombre : listaBarcos)
			System.out.println(nombre);


	}

}
