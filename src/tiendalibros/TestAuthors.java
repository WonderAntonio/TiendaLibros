package tiendalibros;

import java.util.ArrayList;
import java.util.Scanner;

public class TestAuthors {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion, respuesta;
		do {
        System.out.println("Elige una opción:");
        System.out.println("1. Ver tabla de autores");
        System.out.println("2. Insertar en la tabla de autores");
        System.out.println("3. Actualizar la tabla de autores");
        System.out.println("4. Borrar en la tabla de autores");
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
		AuthorModel objautor = new AuthorModel();
		showDataSet(objautor.Get());
		
	}
	private static void Post(Scanner pin) {
		Scanner scanner = new Scanner(System.in);
		// Solicitar al usuario que ingrese los datos del nuevo autor
	    Author newAuthor = new Author();
	    System.out.println("Ingrese el ID del nuevo autor:");
	    newAuthor.auid = scanner.nextLine();
	    System.out.println("Ingrese el nombre del nuevo autor:");
	    newAuthor.aufname = scanner.nextLine();
	    System.out.println("Ingrese el apellido del nuevo autor:");
	    newAuthor.aulname = scanner.nextLine();
	    System.out.println("Ingrese la dirección del nuevo autor:");
	    newAuthor.auaddress = scanner.nextLine();
	    System.out.println("Ingrese el teléfono del nuevo autor:");
	    newAuthor.auphone = scanner.nextLine();
	    System.out.println("Ingrese la ciudad donde vive el nuevo autor:");
	    newAuthor.aucity = scanner.nextLine();
	    System.out.println("Ingrese el estado donde vive el nuevo autor:");
	    newAuthor.austate = scanner.nextLine();
	    System.out.println("Ingrese el zip del nuevo autor:");
	    newAuthor.auzip = scanner.nextLine();
	    System.out.println("¿El autor tiene contrato? (Sí/No):");
	    String contractInput = scanner.nextLine().toLowerCase(); // Convertir a minúsculas para evitar problemas de mayúsculas/minúsculas
	    // Convertir la entrada en un valor booleano
	    boolean hasContract = contractInput.equals("sí") || contractInput.equals("si") || contractInput.equals("s") || contractInput.equals("yes");
	    newAuthor.aucontract = hasContract;
	    // Crear una instancia de AuthorModel
	    AuthorModel authorModel = new AuthorModel();

	    // Insertar el nuevo autor
	    boolean inserted = authorModel.Post(newAuthor);
	    if (inserted) {
	        System.out.println("Nuevo autor insertado correctamente.");
	    } else {
	        System.out.println("Error al insertar el nuevo autor.");
	    }
	    scanner.close();
	}
	
	private static void Put(Scanner pin) {
		Scanner scanner = new Scanner(System.in);
		// Solicitar al usuario que ingrese el ID del autor a actualizar
	    System.out.println("Ingrese el ID del autor que desea actualizar:");
	    String authorId = scanner.nextLine();
	    // Solicitar al usuario que ingrese los nuevos datos del autor
	    Author updatedAuthor = new Author();
	    updatedAuthor.auid = authorId; // ID del autor a actualizar
	    System.out.println("Ingrese el nuevo nombre del autor:");
	    updatedAuthor.aufname = scanner.nextLine();
	    System.out.println("Ingrese el nuevo apellido del autor:");
	    updatedAuthor.aulname = scanner.nextLine();
	    System.out.println("Ingrese la nueva dirección del autor:");
	    updatedAuthor.auaddress = scanner.nextLine();
	    System.out.println("Ingrese el nuevo teléfono del nuevo autor:");
	    updatedAuthor.auphone = scanner.nextLine();
	    System.out.println("Ingrese la nueva ciudad donde vive autor:");
	    updatedAuthor.aucity = scanner.nextLine();
	    System.out.println("Ingrese el nuevo estado donde vive el autor:");
	    updatedAuthor.austate = scanner.nextLine();
	    System.out.println("Ingrese el nuevo zip del autor:");
	    updatedAuthor.auzip = scanner.nextLine();
	    System.out.println("¿El autor aun tiene contrato? (Sí/No):");
	    String contractInput = scanner.nextLine().toLowerCase(); // Convertir a minúsculas para evitar problemas de mayúsculas/minúsculas
	    // Convertir la entrada en un valor booleano
	    boolean hasContract = contractInput.equals("sí") || contractInput.equals("si") || contractInput.equals("s") || contractInput.equals("yes");
	    updatedAuthor.aucontract = hasContract;
	    // Crear una instancia de AuthorModel
	    AuthorModel authorModel = new AuthorModel();

	    // Actualizar el autor existente
	    boolean updated = authorModel.Put(updatedAuthor);
	    if (updated) {
	        System.out.println("Autor actualizado correctamente.");
	    } else {
	        System.out.println("Error al actualizar el autor.");
	    }
	    scanner.close();
	}
	private static void Delete(Scanner scanner) {
	    // Solicitar al usuario que ingrese el ID del autor que desea eliminar
	    System.out.println("Ingrese el ID del autor que desea eliminar:");
	    String authorId = scanner.nextLine();

	    // Crear una instancia de AuthorModel
	    AuthorModel authorModel = new AuthorModel();

	    // Intentar eliminar el autor y mostrar el resultado
	    boolean deleted = authorModel.Delete(authorId);
	    if (deleted) {
	        System.out.println("Autor eliminado correctamente.");
	    } else {
	        System.out.println("Error al eliminar el autor.");
	    }
	}
	//Iterar los autors y muestra matriz en screen
	private static void showDataSet(ArrayList<Author> odt) {
		System.out.println("Id\t|Nombre\t|Apellidos|Dir\t|Tel\t|Ciudad\t|Estado\t|Zip\t|Contrato\t|");
		for (Author au : odt) {
			System.out.println(au.auid+"\t|"+au.aufname+"\t|"+au.aulname+"\t|"+au.auaddress+"\t|"+au.auphone+"\t|"+au.aucity+"\t|"+au.austate+"\t|"+au.auzip+"\t|"+au.aucontract+"|");
		}
	}
}
