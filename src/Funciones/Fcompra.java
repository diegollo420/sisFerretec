package Funciones;

import Datos.Dcompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fcompra {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    public Integer totalRegistros;

    public DefaultTableModel mostrar() {

        DefaultTableModel modelo;

        String[] titulos = {"Numero",
            "Fecha ", "Total","COD Prov", "Proveedor","Comprobante","Numero Descr"};

        String[] registros = new String[7];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select cod_compra , fecha_compra , Replace(Format(total_compra, 0), ',', '.') as total_compra , cod_proveedorFK,"
                + "(select nombre_persona from persona where cod_persona = cod_proveedorFK)"
                + "as ProveedorNom ,tipo_comprobante , num_comprobante from compra order by cod_compra DESC";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_compra");
                registros[1] = rs.getString("fecha_compra");
                registros[2] = rs.getString("total_compra");
                registros[3] = rs.getString("cod_proveedorFK");
                registros[4] = rs.getString("ProveedorNom");
                registros[5] = rs.getString("tipo_comprobante");
                registros[6] = rs.getString("num_comprobante");
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(Dcompra datos) {

        sSQL = "insert into compra "
                + "(fecha_compra,total_compra,cod_proveedorFK,tipo_comprobante,num_comprobante)"
                + "values(?,?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setDate(1, datos.getFecha_compra());
            pst.setLong(2, datos.getTotal_compra());
            pst.setInt(3, datos.getCod_proveedorFK());
            pst.setString(4, datos.getTipo_comprobante());
            pst.setInt(5, datos.getNum_comprobante());
          
            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    public boolean editar(Dcompra datos) {

        sSQL = "update compra set fecha_compra = ?, "
                + "total_compra = ? , cod_proveedorFK = ? ,tipo_comprobante = ? , num_comprobante =?  where cod_compra = ?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setDate(1, datos.getFecha_compra());
            pst.setLong(2, datos.getTotal_compra());
            pst.setInt(3, datos.getCod_proveedorFK());
            pst.setString(4, datos.getTipo_comprobante());
            pst.setInt(5, datos.getNum_comprobante());
            
            pst.setInt(6, datos.getCod_compra());
          

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    public boolean eliminar(Dcompra datos) {
        sSQL = "delete from compra where cod_compra = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, datos.getCod_compra());
            int N = pst.executeUpdate();

            if (N != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    /*++++++++++FUNCIONES ADICIONALES+++++++++++++++++*/
    public DefaultTableModel Buscar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos = {"Numero",
            "Fecha ", "Total","COD Prov", "Proveedor","Comprobante","Numero Descr"};

        String[] registros = new String[7];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select cod_compra , fecha_compra , Replace(Format(total_compra, 0), ',', '.') as total_compra  ,"
                + " cod_proveedorFK, (select nombre_persona from persona "
                + "where cod_persona = cod_proveedorFK) as proveedorNom , "+
                " tipo_comprobante , num_comprobante from compra where"
                + " (select nombre_persona from persona where cod_persona = cod_proveedorFK)"
                + " LIKE '%" + buscar + "%' and cod_proveedorFK = "
                + "(select cod_proveedor from proveedor WHERE cod_proveedor = cod_proveedorFK )"
                + "order by cod_compra DESC ";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_compra");
                registros[1] = rs.getString("fecha_compra");
                registros[2] = rs.getString("total_compra");
                registros[3] = rs.getString("cod_proveedorFK");
                registros[4] = rs.getString("NombreProv");
                registros[5] = rs.getString("tipo_comprobante");
                registros[6] = rs.getString("num_comprobante");
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public int BuscarCodigoCompra() {

        sSQL = "SELECT cod_compra from compra order by cod_compra DESC limit 1 ";

        try {
            int codigo_compra = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                codigo_compra = rs.getInt("cod_compra");
            }

            return codigo_compra;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }

    }

    public boolean Total(Dcompra datos) {

        sSQL = "update compra set total_compra = ? where cod_compra = ?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getTotal_compra());
            pst.setInt(2, datos.getCod_compra());

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }

    

}
