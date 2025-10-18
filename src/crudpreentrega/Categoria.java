package crudpreentrega;

public class Categoria {
    private int id;
    private String nombre;
    private static int contador = 1;

    public Categoria(String nombre){
        this.id = contador++;
        this.nombre = nombre;
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

    @Override
    public String toString(){
        return "Categoria: " + this.nombre + " | ID: " + this.id;
    }
}
