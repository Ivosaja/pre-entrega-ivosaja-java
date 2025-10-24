package crudpreentrega;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class CrudConsola<T> {
    // Un solo scanner para las subclases de esta clase padre
    protected final Scanner scanner = new Scanner(System.in);

    public abstract void mostrarMenu();
    public abstract void crear();
    public abstract boolean listar();
    public abstract void actualizar();
    public abstract void eliminar();

    // Metodos helpers para separar responsabilidad
    protected int leerEntero(String mensaje){
        while(true){
            try{
                System.out.print(mensaje);
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());

            } catch(NumberFormatException e){
                System.out.println("Error, debe ser un numero entero");
            }
        }
    }

    protected double leerDouble(String mensaje){
        while(true){
            try{
                System.out.print(mensaje);
                String input = scanner.nextLine();
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e){
                System.out.println("Error, debe ingresar un numero con punto");
            }
        }
    }

    protected String leerString(String mensaje){
        System.out.print(mensaje);
        return scanner.nextLine();

    }

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

    protected Pedido buscarPedidoPorId(int idPedido, ArrayList<Pedido> pedidos){
        for(Pedido p: pedidos){
            if(p.getId() == idPedido){
                return p;
            }
        }
        return null;
    }

}