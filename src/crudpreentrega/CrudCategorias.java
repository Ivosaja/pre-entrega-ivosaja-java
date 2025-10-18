package crudpreentrega;

import java.util.ArrayList;

public class CrudCategorias extends CrudConsola<Categoria> {

    private ArrayList<Categoria> categorias;

    public CrudCategorias(ArrayList<Categoria> categorias){
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
        for(Categoria c : this.categorias){
            System.out.println(c);
        }
    }

    @Override
    public void actualizar() {
        System.out.println("Categorias disponibles:");
        this.listar();
        System.out.print("Ingrese el ID de la categoria a actualizar: ");
        int idCategoria = scanner.nextInt();
        scanner.nextLine();

        // Busco la categoria que el usuario eligio por consola para actualizar
        Categoria categoriaActualizar = null;
        for(Categoria categoria : this.categorias){
            if(categoria.getId() == idCategoria){
                categoriaActualizar = categoria;
                break;
            }
        }

        if(categoriaActualizar == null){
            System.out.println("Categoria no encontrada");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        categoriaActualizar.setNombre(nuevoNombre);
        System.out.println("Categoria actualizada con exito");

    }

    @Override
    public void eliminar() {
        System.out.println("Categorias disponibles:");
        this.listar();
        System.out.print("Ingrese el ID de la categoria a eliminar: ");
        int idCategoria = scanner.nextInt();

        // Busco la categoria que el usuario eligio por consola para eliminar
        Categoria categoriaEliminar = null;
        for(Categoria categoria : this.categorias){
            if(categoria.getId() == idCategoria){
                categoriaEliminar = categoria;
                break;
            }
        }

        if(categoriaEliminar == null){
            System.out.println("Categoria no encontrada");
            return;
        }

        // Elimino la categoria
        this.categorias.remove(categoriaEliminar);
        System.out.println("Categoria eliminada con exito");
    }
}
