package crudpreentrega;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        final ArrayList<Categoria> categorias = new ArrayList<>();
        final ArrayList<Producto> productos = new ArrayList<>();

        // Inyeccion de dependencias al crud de categorias y productos
        CrudConsola<Categoria> crudCategorias = new CrudCategorias(productos, categorias);
        CrudConsola<Producto> crudProductos = new CrudProductos(productos, categorias);


        int opcionMenuPrincipal;
        do {
            System.out.println("== Menu Principal ==");
            System.out.println("1) CRUD de Productos");
            System.out.println("2) CRUD de Categorias");
            System.out.println("3) Salir");
            System.out.print("Opcion: ");

            opcionMenuPrincipal = crudCategorias.scanner.nextInt();
            switch (opcionMenuPrincipal){
                case 1 -> {
                    int opcionCrudProductos;
                    do{
                        crudProductos.mostrarMenu();
                        opcionCrudProductos = crudProductos.scanner.nextInt();
                        crudProductos.scanner.nextLine();
                        switch (opcionCrudProductos){
                            case 1 -> crudProductos.crear();
                            case 2 -> crudProductos.listar();
                            case 3 -> crudProductos.actualizar();
                            case 4 -> crudProductos.eliminar();
                            case 5 -> System.out.println("Adios!");
                            default -> System.out.println("Opcion invalida");
                        }

                    } while(opcionCrudProductos != 5);
                }

                case 2 -> {
                    int opcionCrudCategorias;
                    do{
                        crudCategorias.mostrarMenu();
                        opcionCrudCategorias = crudCategorias.scanner.nextInt();
                        crudCategorias.scanner.nextLine();
                        switch (opcionCrudCategorias){
                            case 1 -> crudCategorias.crear();
                            case 2 -> crudCategorias.listar();
                            case 3 -> crudCategorias.actualizar();
                            case 4 -> crudCategorias.eliminar();
                            case 5 -> System.out.println("Adios!");
                            default -> System.out.println("Opcion invalida");
                        }
                    } while(opcionCrudCategorias != 5);
                }
                case 3 -> System.out.println("Adios!");
                default -> System.out.println("Opcion invalida");
            }
        } while(opcionMenuPrincipal != 3);
    }
}
