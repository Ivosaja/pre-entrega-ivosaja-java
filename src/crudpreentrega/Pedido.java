package crudpreentrega;

import java.util.ArrayList;

public class Pedido {

    private static int contador = 1;
    private int id;
    private double total;
    private String estado;
    private ArrayList<ItemPedido> items;

    public Pedido(ArrayList<ItemPedido> items){
        this.id = contador++;
        this.items = items;
        this.total = calcularTotal();
        this.estado = "pendiente";
    }

    public int getId(){
        return this.id;
    }

    public double getTotal(){
        return this.total;
    }

    public ArrayList<ItemPedido> getItems(){
        return this.items;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public double calcularTotal(){
        double totalTemp = 0;
        for(ItemPedido i : this.items){
            totalTemp += i.getSubtotal();
        }
        this.total = totalTemp;
        return this.total;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        System.out.println("========= PEDIDO #" + this.id + " =========\n");
        for(ItemPedido i : this.items){
            sb.append(i.toString()).append("\n");
        }
        sb.append("Estado: ").append(this.estado).append("\n");
        sb.append("TOTAL DEL PEDIDO: ").append(this.total).append("\n");
        sb.append("===============================\n");
        return sb.toString();
    }

}
