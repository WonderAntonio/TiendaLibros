package tiendalibros;

import java.util.ArrayList;
import java.util.Scanner;

public class UnitTesterUX {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.println("Elige una opción:");
        System.out.println("1. Explorar todo el modelo del DB");
        System.out.println("2. Probar el buscador de autores");
        System.out.println("3. Cerrar BD");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
		DBEntity obj = new DBEntity();
        switch (opcion) {
            case 1:
                Lesson5(scanner);
                break;
            case 2:
                UnitTesterAutor(scanner);
                break;
            case 3:
            	obj.CloseDB();
            	break;         	
            default:
                System.out.println("Opción no válida");
        }

        scanner.close();
	}
	//Explora todo el modelo del DB
	private static void Lesson5(Scanner pin) {
		DBEntity objEntity = new DBEntity();
		objEntity.ShowDbObject(pin);
		objEntity.CloseDB();
	}
	private static void UnitTesterAutor(Scanner pin) {
		AuthorModel objautor = new AuthorModel();
		String criterio = "";
		short opt = 0;
		System.out.println("buscador de autores.....");
		do {
			System.out.print("\nentre el criterio");
			criterio = pin.nextLine();
			showDataSet(objautor.Get(criterio));
			//System.out.print(objautor.getActionMessage());
			System.out.print("\nDesea continuar[0]|[1]");
			opt = pin.nextShort();
		}while(opt == 1);
		//objautor.CloseDB();
	}//Fin unitTesterAutor get
	
	//Iterar los autors y muestra matriz en screen
	private static void showDataSet(ArrayList<Author> odt) {
		System.out.println("Id\t|Nombre\t|Apellidos|Dir\t|Tel");
		for (Author au : odt) {
			System.out.println(au.auid+"Id\t|"+au.aufname+"Id\t|"+au.aulname+"Id\t|"+au.auaddress+"Id\t|"+au.auphone);
		}
	}
}
