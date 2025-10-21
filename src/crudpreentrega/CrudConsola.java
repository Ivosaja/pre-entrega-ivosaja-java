package crudpreentrega;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class CrudConsola<T> {
    // Un solo scanner para las subclases de esta clase padre
    protected final Scanner scanner = new Scanner(System.in);

    public abstract void mostrarMenu();
    public abstract void crear();
    public abstract void listar();
    public abstract void actualizar();
    public abstract void eliminar();

    // Metodos helpers para separar responsabilidad
    protected Categoria buscarCategoriaPorId(int idCategoria, ArrayList<Categoria> categorias){
        for(Categoria categoria : categorias){
            if(categoria.getId() == idCategoria){
                return categoria;
            }
        }
        return null;
    }

    protected Producto buscarProductoPorId(int idProducto, ArrayList<Producto> productos){
        for(Producto p : productos){
            if(p.getId() == idProducto){
                return p;
            }
        }
        return null;
    }

}