package main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.entities.Autor;
import main.persistence.AutorJpaController;
import main.persistence.exceptions.NonexistentEntityException;

public class AutorController {

    AutorJpaController autorJpa = new AutorJpaController();

    public void crearAutor(Autor lib) {
        autorJpa.create(lib);
    }

    public void eliminarAutor(int id) {
        try {
            autorJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAutor(Autor lib) {
        try {
            autorJpa.edit(lib);
        } catch (Exception ex) {
            Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Autor traerAutor(int id) {
        return autorJpa.findAutor(id);
    }
    
    public Autor traerAutorNombre(String nombre) {
        return autorJpa.findAutorByNombre(nombre);
    }

    public ArrayList<Autor> traerListaAutor() {
        List<Autor> listaAux = autorJpa.findAutorEntities();
        ArrayList<Autor> listaAutor = new ArrayList<> (listaAux);
        return listaAutor;
    }
}
