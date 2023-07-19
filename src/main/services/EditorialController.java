package main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.entities.Editorial;
import main.persistence.EditorialJpaController;
import main.persistence.exceptions.NonexistentEntityException;

public class EditorialController {

    EditorialJpaController editorialJpa = new EditorialJpaController();

    public void crearEditorial(Editorial edi) {
        editorialJpa.create(edi);
    }

    public void eliminarEditorial(int id) {
        try {
            editorialJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEditorial(Editorial edi) {
        try {
            editorialJpa.edit(edi);
        } catch (Exception ex) {
            Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Editorial traerEditorial(int id) {
        return editorialJpa.findEditorial(id);
    }

    public ArrayList<Editorial> traerListaEditorial() {
        List<Editorial> listaAux = editorialJpa.findEditorialEntities();
        ArrayList<Editorial> listaEditorial = new ArrayList<> (listaAux);
        return listaEditorial;
    }
}
