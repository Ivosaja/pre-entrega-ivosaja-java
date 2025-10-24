package crudpreentrega;

import java.util.ArrayList;

public class CrudProductos extends CrudConsola<Producto> {
    private ArrayList<Producto> productos;
    private ArrayList<Categoria> categorias;
    private ArrayList<Pedido> pedidos;

    public CrudProductos(ArrayList<Producto> productos, ArrayList<Categoria> categorias, ArrayList<Pedido> pedidos){
        this.productos = productos;
        this.categorias = categorias;
        this.pedidos = pedidos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("== CRUD de Productos ==");
        System.out.println("1) Crear Producto");
        System.out.println("2) Listar Productos");
        System.out.println("3) Actualizar Producto");
        System.out.println("4) Eliminar Producto");
        System.out.println("5) Volver");
    }

    @Override
    public void crear() {
        System.out.println("1) Articulo");
        System.out.println("2) Servicio");
        int opcionProducto = super.leerEntero("Que tipo de producto desea crear? (1/2): ");

        if(opcionProducto == 1){
            // Primero valido si no tiene categorias para que no cargue datos si despues no le va a dejar crear un articulo ya que
            // no hay categorias
            if(categorias.isEmpty()){
                System.out.println("No hay categorias cargadas. Crea la primera para crear un articulo");
                return;
            }

            String nombreArticulo = super.leerString("Ingrese el nombre: ");
            double precioArticulo = super.leerDouble("Ingrese el precio: ");
            precioArticulo = this.validarPrecio(precioArticulo); // Valido el precio del articulo

            System.out.println("Categorias disponibles: ");
            for(Categoria categoria : this.categorias){
                System.out.println(categoria);
            }
            int idCategoria = super.leerEntero("Ingrese el ID de la categoria para el nuevo articulo: ");

            Categoria c = super.buscarCategoriaPorId(idCategoria, this.categorias);
            if(c == null){
                System.out.println("Categoria no encontrada");
                return;
            }
            this.productos.add(new Articulo(nombreArticulo, precioArticulo, c));
            System.out.println("Articulo '" + nombreArticulo + "' creado con exito");

        } else if(opcionProducto == 2){
            String nombreServicio = super.leerString("Ingrese el nombre: ");
            double precioServicio = super.leerDouble("Ingrese el precio: ");
            precioServicio = this.validarPrecio(precioServicio); // Valido el precio del servicio

            int duracion = super.leerEntero("Ingrese la duracion del servicio (en minutos): ");
            duracion = this.validarDuracion(duracion); // Valido la duracion en minutos del servicio

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
        int idProducto = super.leerEntero("Ingrese el ID del producto a actualizar: ");

        // Busco el producto que desea actualizar el usuario
        Producto p = super.buscarProductoPorId(idProducto, this.productos);
        if(p == null){
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("1) Nombre");
        System.out.println("2) Precio");
        if(p instanceof Articulo){
            System.out.println("3) Categoria");
        }
        if(p instanceof Servicio){
            System.out.println("3) Duracion");
        }

        int opcionCambioProducto = super.leerEntero("Que desea modificar del producto? (1/2/3): ");
        switch (opcionCambioProducto){
            case 1 -> {
                String nuevoNombre = super.leerString("Nuevo nombre: ");
                p.setNombre(nuevoNombre);
                System.out.println("Nombre de producto modificado con exito");
            }
            case 2 -> {
                double nuevoPrecio = super.leerDouble("Nuevo precio: ");
                nuevoPrecio = this.validarPrecio(nuevoPrecio); // Valido el nuevo precio del producto
                p.setPrecio(nuevoPrecio);
                System.out.println("Precio de producto modificado con exito");
            }
            case 3 -> {
                if(p instanceof Articulo){
                    Articulo a = (Articulo) p;
                    String nuevaCategoria = super.leerString("Nueva categoria: ");
                    a.setCategoria(new Categoria(nuevaCategoria));
                    System.out.println("Categoria de articulo modificada con exito");

                } else if(p instanceof Servicio){
                    Servicio s = (Servicio) p;
                    int nuevaDuracion = super.leerEntero("Nueva duracion: ");
                    nuevaDuracion = this.validarDuracion(nuevaDuracion); // Valido la nueva duracion del servicio al actualizar
                    s.setDuracion(nuevaDuracion);
                    System.out.println("Duracion de servicio modificada con exito");
                }
            }
            default -> System.out.println("Opcion invalida");
        }

    }

    @Override
    public void eliminar() {
        this.listar();
        int idProducto = super.leerEntero("Ingrese el ID del producto a eliminar: ");

        Producto p = super.buscarProductoPorId(idProducto, this.productos);
        if(p == null){
            System.out.println("Producto no encontrado");
            return;
        }

        boolean yaExisteEnPedido = false;
        for(Pedido pedido : this.pedidos){
            for(ItemPedido item : pedido.getItems()){
                if(item.getProducto().getId() == p.getId()){
                    yaExisteEnPedido = true;
                    break;
                }
            }
            if(yaExisteEnPedido){
                break;
            }
        }

        if(yaExisteEnPedido){
            System.out.println("No puedes eliminar este producto porque ya se encuentra en un pedido");
            System.out.println("Primero debe eliminar los pedidos que lo contienen");
            return;
        }

        this.productos.remove(p);
        System.out.println("Producto " + p.getNombre() + " con ID: " + p.getId() + " eliminado con exito");

    }

    // Metodos helpers del crud de productos
    private double validarPrecio(double precio){
        while (precio < 0) {
            System.out.println("Error, el precio del producto no puede ser negativo");
            precio = super.leerDouble("Ingrese el precio del producto: ");
        }
        return precio;
    }

    private int validarDuracion(int duracion){
        while(duracion < 0){
            System.out.println("Error, la duracion del servicio no puede ser negativa");
            duracion = super.leerEntero("Ingrese la duracion del servicio (en minutos): ");
        }
        return duracion;
    }
}
