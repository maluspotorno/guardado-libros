package main.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class PersistenceManager {

    private static final String PERSISTENCE_UNIT_NAME = "Guia16_JPA_Ej_1PU";
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }
}
