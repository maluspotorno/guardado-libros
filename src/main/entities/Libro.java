package main.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Libro implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long isbn;
    @Basic
    private String titulo;
    @Basic
    private int ejemplares;
    @Basic
    private int anio;
    @Basic
    private int ejemplaresPrestados;
    @Basic
    private int ejemplaresRestantes;
    @Basic
    private boolean alta;
    @OneToOne
    private Autor autor;
    @OneToOne
    private Editorial editorial;

    public Libro() {
    }
    
    

    public Libro(long isbn, String titulo, int ejemplares, int anio, int ejemplaresPrestados, int ejemplaresRestantes, boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ejemplares = ejemplares;
        this.anio = anio;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int year) {
        this.anio = year;
    }

    public int getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(int ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public int getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(int ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "LIBRO" + "\n" +
                "ISBN: " + isbn + "\n" +
                "Título: " + titulo + "\n" +
                "Ejemplares: " + ejemplares + "\n" + 
                "Año: " + anio + "\n" +
                "Ejemplares Prestados: " + ejemplaresPrestados + "\n" + 
                "Ejemplares Restantes: " + ejemplaresRestantes + "\n" +
                "Alta: " + alta + "\n" +
                "Autor: " + autor + "\n" +
                "Editorial: " + editorial;
    }
    
}
