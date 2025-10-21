package crudpreentrega;

public class Servicio extends Producto{
    private int duracion;

    public Servicio(String nombre, double precio, int duracion){
        super(nombre, precio);
        this.duracion = duracion;
    }

    public int getDuracion(){
        return this.duracion;
    }

    public void setDuracion(int duracion){
        this.duracion = duracion;
    }

    @Override
    public String toString(){
        return "Servicio: " + this.getNombre() + " | ID: " + this.getId() + " | Precio: $" + this.getPrecio() + " | Duracion (minutos): " + this.duracion;
    }


}
