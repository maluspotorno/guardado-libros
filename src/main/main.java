package main;

import java.util.Scanner;
import main.services.AutorController;
import main.services.EditorialController;
import main.services.LibroController;



public class main {

    public static void main(String[] args) {
        int rta;
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        boolean continuar = true;
        AutorController autorC= new AutorController();
        EditorialController editorialC = new EditorialController();
        LibroController libroC = new LibroController();
        
        do {
            System.out.println("----- MENÚ -----");
            System.out.println("1- Búsqueda de un Autor por nombre.");
            System.out.println("2- Búsqueda de un libro por ISBN.");
            System.out.println("3- Búsqueda de un libro por Título.");
            System.out.println("4- Búsqueda de un libro/s por nombre de Autor.");
            System.out.println("5- Búsqueda de un libro/s por nombre de Editorial.");
            System.out.println("6- Salir");
            System.out.println("Ingrese una opción: ");
            rta = leer.nextInt();
            switch (rta) {
                case 1:
                    System.out.println("Ingrese el nombre del autor.");
                    String autorNombre = leer.next();
                    System.out.println(autorC.traerAutorNombre(autorNombre));
                    break;
                case 2:
                    System.out.println("Ingrese el ISBN del libro.");
                    long libroIsbn = leer.nextLong();
                    System.out.println(libroC.traerLibro(libroIsbn));
                    break;
                case 3:
                    System.out.println("Ingrese el título del libro.");
                    String libroTitulo = leer.next();
                    System.out.println(libroC.traerLibroNombre(libroTitulo));
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del autor.");
                    String libroAutor = leer.next();
                    System.out.println(libroC.traerLibroAutor(libroAutor));
                    break;
                case 5:
                    System.out.println("Ingrese el nombre de la editorial.");
                    String libroEditorial = leer.next();
                    System.out.println(libroC.traerLibroEditorial(libroEditorial));
                    break;
                case 6:
                    continuar = false;
                    break;    
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (continuar);
    
    }

}