/*
 * WONDEL ANTONIO ALONSO
 * 100604431
 */

package tiendalibros;

import java.util.Map;
import java.util.Scanner;

public class ModelEntity {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SaleModel sales = new SaleModel();
    	Map<String, Integer> salesByStore = sales.getTotalSalesByStore();
    	Map<String, Integer> salesByBook = sales.getTotalSalesByBook();
    	// Obtener y mostrar el total de ventas
        int totalSales = sales.getTotalSales();
        // Obtener y mostrar el promedio de ventas
        double averageSales = sales.getAverageSales();
        DBEntity objEntity = new DBEntity();
		int opcion, respuesta;			
		do {					
        System.out.println("Elige una opción:");
        System.out.println("1. Explorar todo el modelo del DB");
        System.out.println("2. Explorar la tabla de authors");
        System.out.println("3. Explorar la tabla de titles");
        System.out.println("4. Explorar la tabla de sales");
        System.out.println("5. Ver total de ventas por tienda");
        System.out.println("6. Ver total de ventas por libro");	 
        System.out.println("7. Ver total de todas las ventas");	        
        System.out.println("8. Ver promedio de todas las ventas");	        
        System.out.println("9. Cerrar DB");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
		DBEntity obj = new DBEntity();
        switch (opcion) {
            case 1:
            	objEntity.ShowDbObject(scanner);
                break;
            case 2:
            	TestAuthors.main(args);
                break;
            case 3:
            	TesterTitles.main(args);
            	break;
            case 4:
            	TesterSales.main(args);
            	break;
            case 5:
            	printSalesByStore(salesByStore);
                break;
            case 6:
                printSalesByBook(salesByBook);
                break;
            case 7:
            	System.out.println("Total de todas ventas: " + totalSales);
                break;
            case 8:
                System.out.println("Promedio de todas ventas: " + averageSales);
                break;
            case 9:
            	obj.CloseDB();
                break;                     
        }
        System.out.println("Desea continuar[0|1]");
        respuesta = scanner.nextInt();
		} while (respuesta != 0);
        scanner.close();
	}

	public static void printSalesByBook(Map<String, Integer> salesByBook) {
        System.out.println("Total de ventas por libro:");
        for (Map.Entry<String, Integer> entry : salesByBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
	public static void printSalesByStore(Map<String, Integer> salesByStore) {
        System.out.println("Total de ventas por tienda:");
        for (Map.Entry<String, Integer> entry : salesByStore.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
