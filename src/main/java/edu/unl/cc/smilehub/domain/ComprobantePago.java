/*
@author Kleyner.ls
 */
package edu.unl.cc.smilehub.domain;

import java.util.ArrayList;
import java.util.List;

public class ComprobantePago {

    private List<ItemComprobante> items;
    private double subtotal;
    private double total;

    public ComprobantePago() {
        this.items = new ArrayList<>();
        this.subtotal = 0;
        this.total = 0;
    }

    public ComprobantePago(List<ItemComprobante> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La lista de items no puede ser nula o vacía");
        }
        this.items = items;
        calcularValores();
    }

    public void generarComprobantePago() {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("No se puede generar el comprobante sin items");
        }
        calcularValores();
    }

    public void agregarItem(ItemComprobante item) {
        if (item == null) {
            throw new IllegalArgumentException("El item no puede ser nulo");
        }
        this.items.add(item);
        calcularValores();
    }

    private void calcularValores() {
        if (items == null || items.isEmpty()) {
            this.subtotal = 0;
            this.total = 0;
            return;
        }

        this.subtotal = items.stream()
                .mapToDouble(ItemComprobante::getSubtotalItem)
                .sum();

        this.total = this.subtotal;
    }


    public void setItems(List<ItemComprobante> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La lista de items no puede ser nula o vacía");
        }
        this.items = items;
        calcularValores();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "ComprobantePago{" +
                "items=" + items +
                ", subtotal=" + subtotal +
                ", total=" + total +
                '}';
    }
}
