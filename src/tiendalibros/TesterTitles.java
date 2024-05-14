package tiendalibros;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TesterTitles {

		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			int opcion, respuesta;;
			do {
	        System.out.println("Elige una opción:");
	        System.out.println("1. Ver tabla de titulos");
	        System.out.println("2. Insertar en la tabla de titulos");
	        System.out.println("3. Actualizar la tabla de titulos");
	        System.out.println("4. Borrar en la tabla de titulos");
	        opcion = scanner.nextInt();
	        scanner.nextLine(); // Limpiar el buffer del scanner
	        switch (opcion) {
	            case 1:
	                Get(scanner);
	                break;
	            case 2:
					Post(scanner);
	                break;
	            case 3:
					Put(scanner);
	            	break;
	            case 4:
	                Delete(scanner);
	            	break;
	        }
	        System.out.println("Desea continuar[0|1]");
	        respuesta = scanner.nextInt();
			} while (respuesta != 0);
	       //scanner.close();
		}
		private static void Get(Scanner pin) {
			TitleModel objtitle = new TitleModel();
			showDataSet(objtitle.Get());
			
		}
		private static void Post(Scanner pin) {
			try (Scanner scanner = new Scanner(System.in)) {
				// Solicitar al usuario que ingrese los datos del nuevo autor
				Titles newTitle = new Titles();
				System.out.println("Ingrese el ID del título:");
				newTitle.titleid = scanner.nextLine();
				System.out.println("Ingrese el nombre del título:");
				newTitle.title = scanner.nextLine();
				System.out.println("Ingrese el tipo de título:");
				newTitle.type = scanner.nextLine();
				System.out.println("Ingrese el ID del publicador:");
				newTitle.pubid = scanner.nextLine();
				System.out.println("Ingrese el precio del título:");
				newTitle.price = scanner.nextDouble();
				System.out.println("Ingrese el avance del título:");
				newTitle.advance = scanner.nextDouble();
				System.out.println("Ingrese las regalías del título:");
				newTitle.royalty = scanner.nextBigDecimal();
				System.out.println("Ingrese las ventas del título:");
				newTitle.ytdsales = scanner.nextBigDecimal();
				scanner.nextLine(); // Consumir la nueva línea
				System.out.println("Ingrese la nota/descripción del título:");
				newTitle.notes = scanner.nextLine();
				// Aquí debes manejar la entrada de la fecha de publicación utilizando SimpleDateFormat
				System.out.println("Ingrese la fecha de publicación del título (YYYY-MM-DD):");
				String pubDateInput = scanner.nextLine();
				try {
				    // Crear un objeto SimpleDateFormat para analizar la cadena de fecha
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    // Parsear la cadena de fecha en un objeto Date
				    Date parsedDate = dateFormat.parse(pubDateInput);
				    // Convertir el objeto Date a un Timestamp
				    newTitle.pubdate = new Timestamp(parsedDate.getTime());
				} catch (ParseException e) {
				    // Manejar la excepción si ocurre un error al analizar la fecha
				    System.out.println("Error al analizar la fecha. Asegúrese de ingresarla en el formato correcto (YYYY-MM-DD).");
				    e.printStackTrace(); // Opcional: Imprimir el seguimiento de la pila para ayudar en la depuración
				}
				// Crear una instancia de TitleModel
				TitleModel titleModel = new TitleModel();

				// Insertar el nuevo título
				boolean inserted = titleModel.Post(newTitle);
				if (inserted) {
				    System.out.println("Nuevo título insertado correctamente.");
				} else {
				    System.out.println("Error al insertar el nuevo título.");
				}
				//scanner.close();
			}
		}
		
		private static void Put(Scanner pin) {
			try (Scanner scanner = new Scanner(System.in)) {
				// Solicitar al usuario que ingrese el ID del autor a actualizar
				 System.out.println("Ingrese el ID del título que desea actualizar:");
				    String titleId = scanner.nextLine();
				    // Solicitar al usuario que ingrese los nuevos datos del título
				    Titles updatedTitle = new Titles();
				    updatedTitle.titleid = titleId; // ID del título a actualizar
				    System.out.println("Ingrese el nuevo nombre del título:");
				    updatedTitle.title = scanner.nextLine();
				    System.out.println("Ingrese el nuevo tipo de título:");
				    updatedTitle.type = scanner.nextLine();
				    System.out.println("Ingrese el ID del publicador:");
				    updatedTitle.pubid = scanner.nextLine();
				    System.out.println("Ingrese el nuevo precio del título:");
				    updatedTitle.price = scanner.nextDouble();
				    System.out.println("Ingrese el nuevo avance del título:");
				    updatedTitle.advance = scanner.nextDouble();
				    System.out.println("Ingrese las nuevas regalías del título:");
				    updatedTitle.royalty = scanner.nextBigDecimal();
				    System.out.println("Ingrese las nuevas ventas del título:");
				    updatedTitle.ytdsales = scanner.nextBigDecimal();
				    scanner.nextLine(); // Consumir la nueva línea
				    System.out.println("Ingrese la nueva nota/descripción del título:");
				    updatedTitle.notes = scanner.nextLine();
				    // Aquí debes manejar la entrada de la fecha de publicación al igual que en el método Post
				    System.out.println("Ingrese la nueva fecha de publicación del título (YYYY-MM-DD):");
				    String pubDateInput = scanner.nextLine();
				    try {
				        // Crear un objeto SimpleDateFormat para analizar la cadena de fecha
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        // Parsear la cadena de fecha en un objeto Date
				        Date parsedDate = dateFormat.parse(pubDateInput);
				        // Convertir el objeto Date a un Timestamp
				        updatedTitle.pubdate = new Timestamp(parsedDate.getTime());
				    } catch (ParseException e) {
				        // Manejar la excepción si ocurre un error al analizar la fecha
				        System.out.println("Error al analizar la fecha. Asegúrese de ingresarla en el formato correcto (YYYY-MM-DD).");
				        e.printStackTrace(); // Opcional: Imprimir el seguimiento de la pila para ayudar en la depuración
				    }
				    // Crear una instancia de TitleModel
				    TitleModel titleModel = new TitleModel();

				    // Actualizar el título existente
				    boolean updated = titleModel.Put(updatedTitle);
				    if (updated) {
				        System.out.println("Título actualizado correctamente.");
				    } else {
				        System.out.println("Error al actualizar el título.");
				    }
				    //scanner.close();
			}
		}
		private static void Delete(Scanner scanner) {
		    // Solicitar al usuario que ingrese el ID del autor que desea eliminar
		    System.out.println("Ingrese el ID del titulo que desea eliminar:");
		    String titleId = scanner.nextLine();

		    // Crear una instancia de AuthorModel
		    TitleModel titleModel = new TitleModel();

		    // Intentar eliminar el autor y mostrar el resultado
		    boolean deleted = titleModel.Delete(titleId);
		    if (deleted) {
		        System.out.println("Autor eliminado correctamente.");
		    } else {
		        System.out.println("Error al eliminar el autor.");
		    }
		}
		//Iterar los autors y muestra matriz en screen
		private static void showDataSet(ArrayList<Titles> odt) {
			System.out.println("Id\t|Titulo\t|Tipo\t|Id publicador\t|Precio\t|Avance\t|Regalia\t|Nota\t|Fecha de publicacion|");
			for (Titles ti : odt) {
				System.out.println(ti.titleid+"\t|"+ti.title+"\t|"+ti.type+"\t|"+ti.pubid+"\t|"+ti.price+"\t|"+ti.advance+"\t|"+ti.royalty+"\t|"+ti.ytdsales+"\t|"+ti.notes+"\t|"+ti.pubdate+"|");
			}
		}
}
