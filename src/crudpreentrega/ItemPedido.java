package crudpreentrega;

public class ItemPedido {
    private static int contador = 1;
    
    private int id;
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public ItemPedido(Producto producto, int cantidad){
        this.id = contador++;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public int getId(){
        return this.id;
    }

    public Producto getProducto(){
        return this.producto;
    }

    public int getCantidad(){
        return this.cantidad;
    }

    public double getSubtotal(){
        return this.subtotal;
    }

    // Metodo que calcula el subtotal del item del pedido (por ejemplo 5 camisas a $3000 = $15000)
    public double calcularSubtotal(){
        return this.cantidad * this.producto.getPrecio();
    }

    @Override
    public String toString() {
        return  "-----------------------------\n" +
            "Item #" + id + "\n" +
            "Producto: " + producto.getNombre() + "\n" +
            "Cantidad: " + cantidad + "\n" +
            "Precio Unitario: $" + String.format("%.2f", producto.getPrecio()) + "\n" +
            "Subtotal: $" + String.format("%.2f", subtotal) + "\n" +
            "-----------------------------";
    }

}
