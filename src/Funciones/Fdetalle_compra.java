package Funciones;


import Controlador.FrmCompraDetalle;
import Datos.Ddetalle_compra;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fdetalle_compra {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    
    public Integer totalRegistros;

    public DefaultTableModel mostrar(String cod_compra) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo detalle", "Cod Producto", "Nombre ",
                    "Precio", "Cantidad",
                    "Cod Compra", "Stock","Sub Total"};

        String[] registros = new String[8];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "SELECT cod_detalle_compra ,cod_productoFK,"
                + "(SELECT nombre_producto FROM producto WHERE cod_productoFK = cod_producto)AS productoNom, "
                + " Replace(Format(precio_producto, 0), ',', '.') as precio_producto  ,cantidad_detalle_compra, cod_compraFK ,"
                + "(SELECT stock_producto FROM producto WHERE cod_productoFK=cod_producto)As "
                + "stock , Replace(Format(subTotal, 0), ',', '.') as subTotal  FROM detalle_compra WHERE cod_compraFK = '" + cod_compra + "' ORDER BY cod_detalle_compra ASC ";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_detalle_compra");
                registros[1] = rs.getString("cod_productoFK");
                registros[2] = rs.getString("productoNom");
                registros[3] = rs.getString("precio_producto");
                registros[4] = rs.getString("cantidad_detalle_compra");
                registros[5] = rs.getString("cod_compraFK");
                registros[6] = rs.getString("stock");
                registros[7] = rs.getString("subtotal");
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(Ddetalle_compra datos) {

        sSQL = "insert into detalle_compra (cantidad_detalle_compra , cod_productoFK,precio_producto"
                + ", cod_compraFK ,subtotal) values (?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, datos.getCantidad_detalle_compra());
            pst.setLong(2, datos.getCod_productoFK());
            pst.setLong(3, datos.getPrecio_producto());
            pst.setInt(4, datos.getCod_compraFK());
            pst.setLong(5, datos.getSubTotal());
            
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
    public boolean eliminar(Ddetalle_compra datos) {
        sSQL = "delete from detalle_compra where cod_detalle_compra = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, datos.getCod_detalle_compra());
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
     

    public long selecProd() {
        long codigo = Long.parseLong(FrmCompraDetalle.txtCod_producto.getText());
        sSQL= "SELECT cod_producto FROM producto WHERE cod_producto = " + codigo;
        try {
             long cod = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                cod = rs.getLong("cod_producto");
            }
            return cod;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }//cierre funcion */
    
    
    
    
    
         public String SelectNombre() {
        long codigo = Long.parseLong(FrmCompraDetalle.txtCod_producto.getText());
        sSQL = "SELECT nombre_producto FROM producto WHERE cod_producto = " + codigo;
        try {
             String nombre ="";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                nombre = rs.getString("nombre_producto");
            }
            return nombre;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return "";
        }
    }//cierre funcion */

         
         
         
         
   public int selecStock() {
        long codigo = Long.parseLong(FrmCompraDetalle.txtCod_producto.getText());
        sSQL = "SELECT stock_producto FROM producto WHERE cod_producto = " + codigo;
        try {
            int stock = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                stock = rs.getInt("stock_producto");
            }
            return stock;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }//cierre funcion */
 
   
    public long selectPrecio() {
        long codigo = Long.parseLong(FrmCompraDetalle.txtCod_producto.getText());
        sSQL= "SELECT precio_compra as precio_compra  FROM producto WHERE cod_producto = " + codigo;
        try {
            long precio_producto = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                precio_producto = rs.getInt("precio_compra");
            }
            return precio_producto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }//cierre funcion
    
    
  
    
    
    public boolean insertarDetalle(Ddetalle_compra datos) {

        sSQL= "INSERT INTO detalle_compra (cantidad_detalle_compra , cod_productoFK,precio_producto,"
                + "cod_compraFk ,subtotal) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, datos.getCantidad_detalle_compra());
            pst.setLong(2, datos.getCod_productoFK());
            pst.setLong(3, datos.getPrecio_producto());
            pst.setInt(4, datos.getCod_compraFK());
            pst.setLong(5, datos.getSubTotal());
            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
        JOptionPane.showMessageDialog(null, "El codigo ingresado no esta en el sistema");
 return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }//cierre funcion
    
    

    
    
    
}
