package crudpreentrega;

public abstract class Producto implements Vendible {

    private int id;
    private String nombre;
    private double precio;
    private static int contador = 1;

    public Producto(String nombre, double precio){
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getPrecio(){
        return this.precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }
}
