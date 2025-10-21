package crudpreentrega;

import java.util.ArrayList;

public class CrudCategorias extends CrudConsola<Categoria> {

    private ArrayList<Categoria> categorias;
    private ArrayList<Producto> productos;

    public CrudCategorias(ArrayList<Producto> productos, ArrayList<Categoria> categorias){
        this.productos = productos;
        this.categorias = categorias;
    }

    @Override
    public void mostrarMenu(){
        System.out.println("== CRUD de Categorias ==");
        System.out.println("1) Crear Categoria");
        System.out.println("2) Listar Categorias");
        System.out.println("3) Actualizar Categoria");
        System.out.println("4) Eliminar Categoria");
        System.out.println("5) Volver");
        System.out.print("Opcion: ");
    }

    @Override
    public void crear() {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        Categoria nuevaCategoria = new Categoria(nombre);
        this.categorias.add(nuevaCategoria);
        System.out.println("Categoria " + nuevaCategoria.getNombre() + " creada con exito");

    }

    @Override
    public void listar() {
        if(this.categorias.isEmpty()){
            System.out.println("No hay categorias cargadas. Crea la primera");
            return;
        }
        System.out.println("Categorias disponibles:");
        for(Categoria c : this.categorias){
            System.out.println(c);
        }
    }

    @Override
    public void actualizar() {
        this.listar();
        System.out.print("Ingrese el ID de la categoria a actualizar: ");
        int idCategoria = scanner.nextInt();
        scanner.nextLine();

        Categoria c = buscarCategoriaPorId(idCategoria, this.categorias);
        if(c == null){
            System.out.println("Categoria no encontrada");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        c.setNombre(nuevoNombre);
        System.out.println("Categoria actualizada con exito");

    }

    @Override
    public void eliminar() {
        System.out.println("Categorias disponibles:");
        this.listar();
        System.out.print("Ingrese el ID de la categoria a eliminar: ");
        int idCategoria = scanner.nextInt();

        Categoria c = buscarCategoriaPorId(idCategoria, this.categorias);
        if(c == null){
            System.out.println("Categoria no encontrada");
            return;
        }

        // Verifico si hay algun producto que tenga la categoria que el usuario quiere eliminar
        // En caso que haya, debe borrar primero los productos, luego puede borrar las categorias
        boolean categoriaUtilizadaEnArticulo = false;
        for(Producto p : this.productos){
            if(p instanceof Articulo){
                if(((Articulo) p).getCategoria() == c){
                    categoriaUtilizadaEnArticulo = true;
                    break;
                }
            }
        }

        if(categoriaUtilizadaEnArticulo){
            System.out.println("No se puede eliminar. Hay articulos que usan esta categoria");
            System.out.println("Primero debe eliminar los articulos con esta categoria");
            return;
        }

        // Elimino la categoria
        this.categorias.remove(c);
        System.out.println("Categoria eliminada con exito");
    }
}
