package crudpreentrega;

import java.util.Scanner;

public abstract class CrudConsola<T> {
    // Un solo scanner para las subclases de esta clase padre
    protected final Scanner scanner = new Scanner(System.in);

    public abstract void mostrarMenu();
    public abstract void crear();
    public abstract void listar();
    public abstract void actualizar();
    public abstract void eliminar();

}