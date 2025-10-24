package crudpreentrega;

public class Articulo extends Producto{
    private Categoria categoria;

    public Articulo(String nombre, double precio, Categoria categoria){
        super(nombre, precio);
        this.categoria = categoria;

        // Calculo el descuento del articulo al crear el objeto en el constructor
        double precioFinal = this.calcularPrecioFinal();
        this.setPrecio(precioFinal);
    }

    public Categoria getCategoria(){
        return this.categoria;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }


    @Override
    public String toString(){
        return "Articulo: " + this.getNombre() + " | ID: " + this.getId() + " | Precio: $" + this.getPrecio() + " | Categoria: " + this.categoria.getNombre() + " - ID: " + this.categoria.getId();
    }

    @Override
    public double calcularPrecioFinal() {
        // Si el precio del articulo es mayor a 5000, se le aplica un descuento del 10%
        if(this.getPrecio() > 5000){
            return this.getPrecio() * 0.90;
        }
        return this.getPrecio();
    }
}
