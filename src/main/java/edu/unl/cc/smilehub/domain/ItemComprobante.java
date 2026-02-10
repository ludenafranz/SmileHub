/*
@author Kleyner.ls
 */
package edu.unl.cc.smilehub.domain;

public class ItemComprobante {

    private double precioUnitario;
    private int cantidad;
    private double subtotalItem;

    public ItemComprobante(){
        super();
    }

    public ItemComprobante(double precioUnitario, int cantidad, double subtotal_Item){
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subtotalItem = subtotal_Item;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotalItem() {
        return precioUnitario*cantidad;
    }

    public void setSubtotalItem(double subtotalItem) {
        this.subtotalItem = subtotalItem;
    }

    @Override
    public String toString() {
        return "ItemComprobante{" +
                "precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                ", subtotal_Item=" + subtotalItem +
                '}';
    }
}
