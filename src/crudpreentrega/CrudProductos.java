package crudpreentrega;

import java.util.ArrayList;

public class CrudProductos extends CrudConsola<Producto> {
    private ArrayList<Producto> productos;
    private ArrayList<Categoria> categorias;

    public CrudProductos(ArrayList<Producto> productos, ArrayList<Categoria> categorias){
        this.productos = productos;
        this.categorias = categorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("== CRUD de Productos ==");
        System.out.println("1) Crear Producto");
        System.out.println("2) Listar Productos");
        System.out.println("3) Actualizar Producto");
        System.out.println("4) Eliminar Producto");
        System.out.println("5) Volver");
        System.out.print("Opcion: ");
    }

    @Override
    public void crear() {
        System.out.println("Que tipo de producto desea crear?");
        System.out.println("1) Articulo");
        System.out.println("2) Servicio");
        System.out.print("Opcion: ");
        int opcionProducto = scanner.nextInt();
        scanner.nextLine();

        if(opcionProducto == 1){
            System.out.print("Ingrese el nombre: ");
            String nombreArticulo = scanner.nextLine();
            System.out.print("Ingrese el precio: ");
            double precioArticulo = scanner.nextDouble();
            scanner.nextLine();

            if(categorias.isEmpty()){
                System.out.println("No hay categorias cargadas. Crea la primera para crear un articulo");
                return;
            }
            System.out.println("Categorias disponibles: ");
            for(Categoria categoria : this.categorias){
                System.out.println(categoria);
            }
            System.out.print("Ingrese el ID de la categoria para el nuevo articulo: ");
            int idCategoria = scanner.nextInt();
            scanner.nextLine();

            Categoria c = buscarCategoriaPorId(idCategoria, this.categorias);
            if(c == null){
                System.out.println("Categoria no encontrada");
                return;
            }
            this.productos.add(new Articulo(nombreArticulo, precioArticulo, c));
            System.out.println("Articulo '" + nombreArticulo + "' creado con exito");

        }

        else if(opcionProducto == 2){
            System.out.print("Ingrese el nombre: ");
            String nombreServicio = scanner.nextLine();
            System.out.print("Ingrese el precio: ");
            double precioServicio = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Ingrese la duracion (en minutos): ");
            int duracion = scanner.nextInt();
            while(duracion < 0){
                System.out.print("Error, la duracion no puede ser negativa. Ingrese una duracion valida: ");
                duracion = scanner.nextInt();
            }

            this.productos.add(new Servicio(nombreServicio, precioServicio, duracion));
            System.out.println("Servicio '" + nombreServicio + "' creado con exito");
        } else {
            System.out.println("Opcion invalida");
        }

    }

    @Override
    public void listar() {
        if(this.productos.isEmpty()){
            System.out.println("No hay productos. Crea uno");
            return;
        }
        System.out.println("Productos disponibles:");
        for(Producto p : this.productos){
            System.out.println(p);
        }
    }

    @Override
    public void actualizar() {
        this.listar();
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine();

        // Busco el producto que desea actualizar el usuario
        Producto p = buscarProductoPorId(idProducto, this.productos);
        if(p == null){
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("Que desea modificar del producto?");
        System.out.println("1) Nombre");
        System.out.println("2) Precio");
        if(p instanceof Articulo){
            System.out.println("3) Categoria");
        }
        if(p instanceof Servicio){
            System.out.println("3) Duracion");
        }

        System.out.print("Opcion: ");
        int opcionCambioProducto = scanner.nextInt();
        scanner.nextLine();
        switch (opcionCambioProducto){
            case 1 -> {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                p.setNombre(nuevoNombre);
            }
            case 2 -> {
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = scanner.nextDouble();
                scanner.nextLine();
                p.setPrecio(nuevoPrecio);
            }
            case 3 -> {
                if(p instanceof Articulo){
                    Articulo a = (Articulo) p;
                    System.out.print("Nueva categoria: ");
                    String nuevaCategoria = scanner.nextLine();
                    a.setCategoria(new Categoria(nuevaCategoria));
                } else if(p instanceof Servicio){
                    Servicio s = (Servicio) p;
                    System.out.print("Nueva duracion: ");
                    int nuevoPrecio = scanner.nextInt();
                    scanner.nextLine();
                    s.setDuracion(nuevoPrecio);
                }
            }
            default -> System.out.println("Opcion invalida");
        }

    }

    @Override
    public void eliminar() {
        System.out.println("Productos disponibles:");
        this.listar();
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int idProducto = scanner.nextInt();

        Producto p = buscarProductoPorId(idProducto, this.productos);
        if(p == null){
            System.out.println("Producto no encontrado");
            return;
        }

        this.productos.remove(p);
        System.out.println("Producto " + p.getNombre() + " con ID: " + p.getId() + " eliminado con exito");

    }
}
