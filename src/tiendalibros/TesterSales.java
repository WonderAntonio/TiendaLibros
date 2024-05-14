package tiendalibros;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TesterSales {
			public static void main(String[] args) {
				Scanner scanner = new Scanner(System.in);
				int opcion, respuesta;			
				do {					
		        System.out.println("Elige una opción:");
		        System.out.println("1. Ver tabla de ventas");
		        System.out.println("2. Insertar en la tabla de ventas");
		        System.out.println("3. Actualizar la tabla de ventas");
		        System.out.println("4. Borrar en la tabla de ventas");	        
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
				SaleModel objsale = new SaleModel();
				showDataSet(objsale.Get());
				//System.out.print(objautor.getActionMessage());
				//objautor.CloseDB();
			}//Fin unitTesterAutor get
			private static void Post(Scanner pin) {
				try (Scanner scanner = new Scanner(System.in)) {
					// Solicita al usuario que ingrese los datos del nuevo autor
					Sales newSale = new Sales();
					System.out.println("Ingrese el ID de la tienda:");
					newSale.storid = scanner.nextLine();
					System.out.println("Ingrese el numero de orden de la orden:");
					newSale.ordnum = scanner.nextLine();
					System.out.println("Ingrese la fecha de la orden (YYYY-MM-DD):");
					String pubDateInput = scanner.nextLine();
					try {
					    // Crea un objeto SimpleDateFormat para analizar la cadena de fecha
					    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    // Parsear la cadena de fecha en un objeto Date
					    Date parsedDate = dateFormat.parse(pubDateInput);
					    // Convertir el objeto Date a un Timestamp
					    newSale.orddate = new Timestamp(parsedDate.getTime());
					} catch (ParseException e) {
					    // Manejar la excepción si ocurre un error al analizar la fecha
					    System.out.println("Error al analizar la fecha. Asegúrese de ingresarla en el formato correcto (YYYY-MM-DD).");
					    e.printStackTrace(); // Opcional: Imprimir el seguimiento de la pila para ayudar en la depuración
					}
					System.out.println("Ingrese el Qty:");
					newSale.qty = Integer.parseInt(scanner.nextLine());
					System.out.println("Ingrese el payterm:");
					newSale.payterms = scanner.nextLine();
					System.out.println("Ingrese el ID del titulo:");
					newSale.titleid = scanner.nextLine();
					// Crea una instancia de TitleModel
					SaleModel saleModel = new SaleModel();
					// Insertar el nuevo título
					boolean inserted = saleModel.Post(newSale);
					if (inserted) {
					    System.out.println("Nueva orden insertada correctamente.");
					} else {
					    System.out.println("Error al insertar la nueva orden.");
					}
					//scanner.close();
				}
			}
			
			private static void Put(Scanner pin) {
				try (Scanner scanner = new Scanner(System.in)) {
					// Solicitar al usuario que ingrese los datos del nuevo autor
					Sales updateSale = new Sales();
					System.out.println("Ingrese el ID de la tienda:");
					updateSale.storid = scanner.nextLine();
					System.out.println("Ingrese el nuevo numero de la orden:");
					updateSale.ordnum = scanner.nextLine();
					System.out.println("Ingrese la nueva fecha de la orden (YYYY-MM-DD):");
					String pubDateInput = scanner.nextLine();
					try {
					    // Crea un objeto SimpleDateFormat para analizar la cadena de fecha
					    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    // Parsear la cadena de fecha en un objeto Date
					    Date parsedDate = dateFormat.parse(pubDateInput);
					    // Convertir el objeto Date a un Timestamp
					    updateSale.orddate = new Timestamp(parsedDate.getTime());
					} catch (ParseException e) {
					    // Manejar la excepción si ocurre un error al analizar la fecha
					    System.out.println("Error al analizar la fecha. Asegúrese de ingresarla en el formato correcto (YYYY-MM-DD).");
					    e.printStackTrace(); 
					}
					System.out.println("Ingrese el nuevo Qty:");
					updateSale.qty = Integer.parseInt(scanner.nextLine());
					System.out.println("Ingrese el payterm:");
					updateSale.payterms = scanner.nextLine();
					System.out.println("Ingrese el ID del titulo:");
					updateSale.titleid = scanner.nextLine();
					// Crear una instancia de TitleModel
					SaleModel saleModel = new SaleModel();

					// Insertar el nuevo título
					boolean inserted = saleModel.Put(updateSale);
					if (inserted) {
					    System.out.println("Nueva orden insertado correctamente.");
					} else {
					    System.out.println("Error al insertar la nueva orden.");
					}
					//scanner.close();
				}
			}
			private static void Delete(Scanner scanner) {
			    // Solicitar al usuario que ingrese el ID del autor que desea eliminar
			    System.out.println("Ingrese el numero de la orden que desea eliminar:");
			    String orNum = scanner.nextLine();
			    // Crear una instancia de AuthorModel
			    SaleModel saleModel = new SaleModel();

			    // Intentar eliminar el autor y mostrar el resultado
			    boolean deleted = saleModel.Delete(orNum);
			    if (deleted) {
			        System.out.println("Orden eliminada correctamente.");
			    } else {
			        System.out.println("Error al eliminar la orden.");
			    }
			}
			//Iterar los autors y muestra matriz en screen
			private static void showDataSet(ArrayList<Sales> odt) {
				System.out.println("TiendaId\t|NumOrden\t|FechaOrden\t|Qty\t|Payterms\t|TituloId\t|");
				for (Sales sa : odt) {
					System.out.println(sa.storid+"\t|"+sa.ordnum+"\t|"+sa.orddate+"\t|"+sa.qty+"\t|"+sa.payterms+"\t|"+sa.titleid+"|");
				}
			}
	}

