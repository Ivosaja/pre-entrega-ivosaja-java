package crudpreentrega;

public class Articulo extends Producto{
    private Categoria categoria;

    public Articulo(String nombre, double precio, Categoria categoria){
        super(nombre, precio);
        this.categoria = categoria;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }


    @Override
    public String toString(){
        return "Articulo: " + this.getNombre() + " | ID: " + this.getId() + " | Precio: $" + this.getPrecio() + " | Categoria: " + this.categoria;
    }
}
