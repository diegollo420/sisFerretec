package Datos;

public class Ddetalle_compra {

    private int cod_detalle_compra;
    private int cantidad_detalle_compra;
    private long cod_productoFK;
    private int cod_compraFK;
    private long subTotal;
    private long precio_producto;

    public Ddetalle_compra() {
    }

    public Ddetalle_compra(int cod_detalle_compra, int cantidad_detalle_compra, long cod_productoFK, int cod_compraFK, long subTotal,long precio_producto) {
        this.cod_detalle_compra = cod_detalle_compra;
        this.cantidad_detalle_compra = cantidad_detalle_compra;
        this.cod_productoFK = cod_productoFK;
        this.cod_compraFK = cod_compraFK;
        this.subTotal = subTotal;
        this.precio_producto = precio_producto;
    }

    public int getCod_detalle_compra() {
        return cod_detalle_compra;
    }

    public void setCod_detalle_compra(int cod_detalle_compra) {
        this.cod_detalle_compra = cod_detalle_compra;
    }

    public int getCantidad_detalle_compra() {
        return cantidad_detalle_compra;
    }

    public void setCantidad_detalle_compra(int cantidad_detalle_compra) {
        this.cantidad_detalle_compra = cantidad_detalle_compra;
    }

    public long getCod_productoFK() {
        return cod_productoFK;
    }

    public void setCod_productoFK(long cod_productoFK) {
        this.cod_productoFK = cod_productoFK;
    }

    public int getCod_compraFK() {
        return cod_compraFK;
    }

    public void setCod_compraFK(int cod_compraFK) {
        this.cod_compraFK = cod_compraFK;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }
    public long getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(long precio_producto) {
        this.precio_producto = precio_producto;
    } 
    
    
    
}
