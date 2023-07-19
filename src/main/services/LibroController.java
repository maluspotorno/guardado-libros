package main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.entities.Libro;
import main.persistence.LibroJpaController;
import main.persistence.exceptions.NonexistentEntityException;

public class LibroController {

    LibroJpaController libroJpa = new LibroJpaController();

    public void crearLibro(Libro lib) {
        libroJpa.create(lib);
    }

    public void eliminarLibro(int id) {
        try {
            libroJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarLibro(Libro lib) {
        try {
            libroJpa.edit(lib);
        } catch (Exception ex) {
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Libro traerLibro(long id) {
        return libroJpa.findLibro(id);
    }
    
    public Libro traerLibroNombre(String nombre) {
        return libroJpa.findLibroByNombre(nombre);
    }

    public ArrayList<Libro> traerListaLibro() {
        List<Libro> listaAux = libroJpa.findLibroEntities();
        ArrayList<Libro> listaLibro = new ArrayList<> (listaAux);
        return listaLibro;
    }
    
    public ArrayList<Libro> traerLibroAutor(String nombre) {
        List<Libro> listaAux = libroJpa.findLibroByAutor(nombre);
        ArrayList<Libro> listaLibro = new ArrayList<> (listaAux);
        return listaLibro;
    }
    
    public ArrayList<Libro> traerLibroEditorial(String nombre) {
        List<Libro> listaAux = libroJpa.findLibroByEditorial(nombre);
        ArrayList<Libro> listaLibro = new ArrayList<> (listaAux);
        return listaLibro;
    }
}
