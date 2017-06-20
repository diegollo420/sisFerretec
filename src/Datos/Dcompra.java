
package Datos;

import java.sql.Date;

public class Dcompra {
    
    private int cod_compra;
    private Date fecha_compra;
    private long total_compra;
    private int cod_proveedorFK;
    private String tipo_comprobante;
    private int num_comprobante;
    public Dcompra() {
    }

    public Dcompra(int cod_compra, Date fecha_compra, long total_compra, int cod_proveedorFK,String tipo_comprobante,int num_comprobante) {
        this.cod_compra = cod_compra;
        this.fecha_compra = fecha_compra;
        this.total_compra = total_compra;
        this.cod_proveedorFK = cod_proveedorFK;
        this.tipo_comprobante =tipo_comprobante;
        this.num_comprobante=num_comprobante;
    }

    public int getCod_compra() {
        return cod_compra;
    }

    public void setCod_compra(int cod_compra) {
        this.cod_compra = cod_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public long getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(long total_compra) {
        this.total_compra = total_compra;
    }

    public int getCod_proveedorFK() {
        return cod_proveedorFK;
    }

    public void setCod_proveedorFK(int cod_proveedorFK) {
        this.cod_proveedorFK = cod_proveedorFK;
    }
         public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public int getNum_comprobante() {
        return num_comprobante;
    }

    public void setNum_comprobante(int num_comprobante) {
        this.num_comprobante = num_comprobante;
    }

}
