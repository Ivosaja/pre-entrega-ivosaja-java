package crudpreentrega;

import java.util.ArrayList;

public class CrudPedidos extends CrudConsola<Pedido> {

    private ArrayList<Producto> productos;
    private ArrayList<Pedido> pedidos;

    public CrudPedidos(ArrayList<Producto> productos, ArrayList<Pedido> pedidos){
        this.productos = productos;
        this.pedidos = pedidos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("== CRUD de Pedidos ==");
        System.out.println("1) Crear Pedido");
        System.out.println("2) Listar Pedidos");
        System.out.println("3) Actualizar Pedido");
        System.out.println("4) Eliminar Pedido");
        System.out.println("5) Volver");
    }

    @Override
    public void crear() {
        if(this.productos.isEmpty()){
            System.out.println("No hay productos disponibles para crear un pedido. Crea un producto antes");
            return;
        }
        System.out.println("Productos disponibles:");
        for(Producto p : this.productos){
            System.out.println(p);
        }

        ArrayList<ItemPedido> itemsPedido = new ArrayList<>();
        boolean seguirAgregando = true;

        // Encierro en bucle por si quiere agregar muchos productos
        while(seguirAgregando){
            int idProducto = super.leerEntero("Ingrese el ID del producto que quieres agregar al pedido: ");
            Producto p = super.buscarProductoPorId(idProducto, this.productos);
            if(p == null){
                System.out.println("Producto no encontrado");
                continue;
            }


            boolean yaExiste = false;
            for(ItemPedido i : itemsPedido){
                if(i.getProducto().getId() == p.getId()){
                    System.out.println("Ese item ya se encuentra en el pedido. Agrega otro");
                    yaExiste = true;
                    break;
                }
            }

            if(yaExiste){
                continue;
            }
    
            int cantidadProducto = super.leerEntero("Ingrese la cantidad que quiere de ese producto: ");
            cantidadProducto = this.validarCantidadProducto(cantidadProducto);
            ItemPedido i = new ItemPedido(p, cantidadProducto);
            itemsPedido.add(i);

            String seguir = leerString("Quieres agregar otro producto? (s/n): ");
            seguirAgregando = seguir.equalsIgnoreCase("s");
        }

        // Creo el pedido
        Pedido pedidoCreado = new Pedido(itemsPedido);
        System.out.println("Pedido con ID: " + pedidoCreado.getId() + " creado con exito");
        this.pedidos.add(pedidoCreado);


    }

    @Override
    public boolean listar() {
        if(this.pedidos.isEmpty()){
            System.out.println("No hay pedidos. Crea el primero");
            return false;
        }
        System.out.println("Pedidos hechos:");
        for(Pedido p : this.pedidos){
            System.out.println(p);
        }
        return true;
    }

    @Override
    public void actualizar() {
        if(!this.listar()) return;
        
        int idPedido = super.leerEntero("Ingrese el ID del pedido que desea actualizar: ");
        Pedido p = super.buscarPedidoPorId(idPedido, this.pedidos);
        if(p == null){
            System.out.println("Pedido no encontrado");
            return;
        }

        String nuevoEstadoPedido = super.leerString("Ingrese el nuevo estado del pedido (aceptado/cancelado): ");
        nuevoEstadoPedido = this.validarEstado(nuevoEstadoPedido);

        p.setEstado(nuevoEstadoPedido);
        System.out.println("Estado de Pedido con ID: " + p.getId() + " actualizado con exito");
        
    }

    @Override
    public void eliminar() {
        if(!this.listar()) return;
        
        int idPedido = super.leerEntero("Ingrese el ID del pedido a eliminar: ");
        Pedido p = super.buscarPedidoPorId(idPedido, this.pedidos);
        if(p == null){
            System.out.println("Pedido no encontrado");
            return;
        }

        this.pedidos.remove(p);
        System.out.println("Pedido eliminado con exito");
    }

    // Metodo encargado de validar el nuevo estado de un pedido
    private String validarEstado(String estado){
        estado = estado.trim().toLowerCase();
        while (!estado.equals("aceptado") && !estado.equals("cancelado")) {
            System.out.println("Error, el estado puede ser 'aceptado' o 'cancelado'");
            estado = super.leerString("Ingrese el nuevo estado del pedido (aceptado/cancelado): ");
        }
        return estado;
    }

    private int validarCantidadProducto(int cantidad){
        while(cantidad < 0){
            System.out.println("Error, la cantidad no puede ser negativa");
            cantidad = super.leerEntero("Ingrese la cantidad que quiere de ese producto: ");
        }
        return cantidad;
    }


}
