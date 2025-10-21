package crudpreentrega;

public class Servicio extends Producto{
    private int duracion;

    public Servicio(String nombre, double precio, int duracion){
        super(nombre, precio);
        this.duracion = duracion;

        // Calculo el descuento del servicio al crear el objeto con el constructor
        double precioFinal = this.calcularPrecioFinal();
        this.setPrecio(precioFinal);
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

    @Override
    public double calcularPrecioFinal() {
        // Si la duracion del servicio es mayor a 2 horas (120 minutos) -> aplico un descuento final de 15%;
        if(this.duracion > 120){
            return this.getPrecio() * 0.85;
        }
        return this.getPrecio();
    }


}
