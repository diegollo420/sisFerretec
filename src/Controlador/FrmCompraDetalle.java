package Controlador;

import static Controlador.FrmVistaProveedor.Comprueba;
import static Controlador.FRMPRINCIPAL.deskPricipal;
import static Controlador.FrmVistaProducto.comprobarProducto;
import Datos.Dcompra;
import Datos.Ddetalle_compra;
import Datos.Dproducto;
import Funciones.Fcompra;
import Funciones.Fdetalle_compra;
import Funciones.Fproducto;

import Funciones.conexion;
import java.awt.Component;


import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class FrmCompraDetalle extends javax.swing.JInternalFrame {

    int foco;
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    public FrmCompraDetalle() {
        initComponents();
  
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);  
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        DetallesFormVenta();
        OcultaBotones();
        Calendar c2 = new GregorianCalendar();
        dcFecha_compra.setCalendar(c2);
        txtStockDetalle.setVisible(false);
        txtCantidadProducto.setEditable(false);
        btnClick.setVisible(false);
        // txtTotal_venta.setEditable(false);
        txtNumComprobante.setEditable(true);
        txtNumComprobante.setText("0");
        btnBuscarPro.requestFocus();
        //  txtSubTotal.setEditable(false);
        // txtIva.setEditable(false);
     
        //txtCambio.setEditable(false);
        mostrar("0");

        jTabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //l.setBorder(new LineBorder(Color.black, 1));
                l.setBackground(new java.awt.Color(36, 33, 33));
                l.setForeground(new java.awt.Color(25, 118, 210));
                l.setFont(new java.awt.Font("Arial", 1, 12));
                return l;
            }
        });
    }

    public void ocultar_columnas() {

        jTabla.getColumnModel().getColumn(0).setMaxWidth(0);
        jTabla.getColumnModel().getColumn(0).setMinWidth(0);
        jTabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTabla.getColumnModel().getColumn(5).setMaxWidth(0);
        jTabla.getColumnModel().getColumn(5).setMinWidth(0);
        jTabla.getColumnModel().getColumn(5).setPreferredWidth(0);
        jTabla.getColumnModel().getColumn(6).setMaxWidth(0);
        jTabla.getColumnModel().getColumn(6).setMinWidth(0);
        jTabla.getColumnModel().getColumn(6).setPreferredWidth(0);
    }

    public void limpiarProductosDetalle() {
        txtCod_producto.setText("");
        txtNombre_producto.setText("");
        txtCantidadProducto.setText("");
        txtPrecio_producto.setText("");
    }

    public void BuscarCodigoVenta() {

        Fcompra funcion = new Fcompra();
        int codigo = funcion.BuscarCodigoCompra();
        //ACA PODRIA PONER PARA EN UN LBL MOSTRAR EL NUMERO DE VENTA
        txtCod_compra.setText(String.valueOf(codigo));
        txtCod_compraFK.setText(String.valueOf(codigo));
    }

    public void seleccionProd() {
        Fdetalle_compra funcion = new Fdetalle_compra();
        long cod_producto = funcion.selecProd();

        if (cod_producto > 0) {
            txtCod_producto.setText(String.valueOf(cod_producto));

            String nombre_producto = funcion.SelectNombre();
            txtNombre_producto.setText(String.valueOf(nombre_producto));

            int stock_producto = funcion.selecStock();
            txtStockDetalle.setText(String.valueOf(stock_producto));

            long precio_productoS = funcion.selectPrecio();

            String precio_producto1 = String.valueOf(precio_productoS);
            String precio_producto2 = precio_producto1.replaceAll("\\.", "");

            txtPrecio_producto.setText(String.valueOf(precio_producto2));

            if (cboModoIngreso.getSelectedItem() == "x Mayor") {

                txtCantidadProducto.setEditable(true);
                txtCantidadProducto.setText("");
                foco = 1;
            } else if (cboModoIngreso.getSelectedItem() == "x Unidad") {

                foco = 0;
                insertarDetalle();
            }

        } else {
            JOptionPane.showMessageDialog(null, "No existe el codigo en el sistema");
            txtCod_producto.requestFocus();
            txtCod_producto.setText("");

        }
    }

    public void insertarDetalle() {
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        int cantidad = 1;
        long Stock = Long.valueOf(txtStockDetalle.getText());
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad");
            return;
        }

        String prec = txtPrecio_producto.getText();
        String prec2 = prec.replaceAll("\\.", "");

        long var1 = Long.parseLong(prec2);
        long var2 = Long.parseLong(txtCantidadProducto.getText());
        long resultadoDetalle = var1 * var2;

        Ddetalle_compra datos = new Ddetalle_compra();
        Fdetalle_compra funcion = new Fdetalle_compra();

        datos.setCantidad_detalle_compra(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        datos.setCod_compraFK(Integer.parseInt(txtCod_compraFK.getText()));

        String precioProducto = txtPrecio_producto.getText();
        String precioProducto2 = precioProducto.replaceAll("\\.", "");
        datos.setPrecio_producto(Long.valueOf(precioProducto2));

        String subTotal = String.valueOf(resultadoDetalle);
        String subTotal2 = subTotal.replaceAll("\\.", "");
        datos.setSubTotal(Long.valueOf(subTotal2));

        if (funcion.insertarDetalle(datos)) {

            String valorProd1 = txtPrecio_producto.getText();
            String valorProd2 = valorProd1.replaceAll("\\.", "");
            long valorProd = Long.parseLong(valorProd2);

            String subTotalS1 = txtSubTotal.getText();
            String subTotalS2 = subTotalS1.replaceAll("\\.", "");
            long valor2 = Long.parseLong(subTotalS2);

            long total = valorProd * cantidad;
            long resultado = total + valor2;

            String mostrar0 = formatea.format(resultado);
            txtSubTotal.setText(String.valueOf(mostrar0));

            String mostrar3 = formatea.format(resultado);
            txtTotal_venta.setText(String.valueOf(mostrar3));

            Dcompra datos1 = new Dcompra();
            Fcompra funcion1 = new Fcompra();
            datos1.setCod_compra(Integer.parseInt(txtCod_compraFK.getText()));

            String totalCompra = String.valueOf(txtTotal_venta.getText());
            String totalCompra2 = totalCompra.replaceAll("\\.", "");
            datos1.setTotal_compra(Long.valueOf(totalCompra2));

            funcion1.Total(datos1);

            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            stock = stock + cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);

        } else {
            JOptionPane.showMessageDialog(null, "El codigo ingresado no esta en el sistema");
        }
        txtCod_producto.setText("");
        txtPrecio_producto.setText("");
        txtCantidadProducto.setText("1");
        txtNombre_producto.setText("");
        txtCantidadProducto.setEditable(false);
    }

    public void mostrar(String cod_venta) {
        try {
            DefaultTableModel modelo;
            Fdetalle_compra func = new Fdetalle_compra();
            modelo = func.mostrar(cod_venta);
            jTabla.setModel(modelo);
            ocultar_columnas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void DetallesFormVenta() {

        txtCod_proveedor.setVisible(false);
        txtCod_compra.setVisible(false);
        txtNombreProveedor.setEditable(false);
        txtCod_detalle.setVisible(false);
        txtCod_compraFK.setVisible(false);
        txtNombre_producto.setEditable(false);
        txtCantidadProducto.setEditable(false);
        txtPrecio_producto.setEditable(false);
        txtCod_producto.setEditable(false);
        txtTotal_venta.setEditable(false);
    }

    public void DetallesFormVentaProd() {
        txtCantidadProducto.setEditable(true);
        txtPrecio_producto.setEditable(false);
    }

    public void OcultaBotones() {
        btnbuscarProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnQuitarProducto.setEnabled(false);
    }

    public void activaBotones() {
        btnbuscarProducto.setEnabled(true);
        btnAgregarProducto.setEnabled(true);
        btnQuitarProducto.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        dcFecha_compra = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cboComprobante = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        btnBuscarPro = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNumComprobante = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabla = jTabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtTotal_venta = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtCod_producto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPrecio_producto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNombre_producto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        btnQuitarProducto = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        btnbuscarProducto = new javax.swing.JButton();
        cboModoIngreso = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtCod_proveedor = new javax.swing.JTextField();
        txtCod_compraFK = new javax.swing.JTextField();
        txtCod_detalle = new javax.swing.JTextField();
        txtCod_compra = new javax.swing.JTextField();
        txtStockDetalle = new javax.swing.JTextField();
        btnClick = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(828, 547));

        jPanel2.setBackground(new java.awt.Color(36, 33, 33));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(822, 521));

        jPanel1.setBackground(new java.awt.Color(36, 33, 33));

        jPanel3.setBackground(new java.awt.Color(36, 33, 33));

        dcFecha_compra.setBackground(new java.awt.Color(36, 33, 33));
        dcFecha_compra.setForeground(new java.awt.Color(36, 33, 33));
        dcFecha_compra.setToolTipText("");
        dcFecha_compra.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(207, 207, 207));
        jLabel2.setText("  Fecha :");

        cboComprobante.setBackground(new java.awt.Color(36, 33, 33));
        cboComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboComprobante.setForeground(new java.awt.Color(207, 207, 207));
        cboComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleta", "Factura" }));
        cboComprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboComprobanteItemStateChanged(evt);
            }
        });
        cboComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboComprobanteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(207, 207, 207));
        jLabel4.setText("Numero :");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(207, 207, 207));
        jLabel6.setText("Proveedor:");

        txtNombreProveedor.setBackground(new java.awt.Color(36, 33, 33));
        txtNombreProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombreProveedor.setForeground(new java.awt.Color(207, 207, 207));
        txtNombreProveedor.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtNombreProveedor.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombreProveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnBuscarPro.setBackground(new java.awt.Color(36, 33, 33));
        btnBuscarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/buscar.png"))); // NOI18N
        btnBuscarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(36, 33, 33));
        btnGuardar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(207, 207, 207));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/guardar.png"))); // NOI18N
        btnGuardar.setText("Iniciar ");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(207, 207, 207));
        jLabel1.setText("    Tipo :");

        txtNumComprobante.setBackground(new java.awt.Color(36, 33, 33));
        txtNumComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNumComprobante.setForeground(new java.awt.Color(207, 207, 207));
        txtNumComprobante.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtNumComprobante.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNumComprobante.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNumComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumComprobanteActionPerformed(evt);
            }
        });
        txtNumComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumComprobanteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboComprobante, 0, 178, Short.MAX_VALUE)
                    .addComponent(dcFecha_compra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNombreProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNumComprobante))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dcFecha_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnBuscarPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(txtNumComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jScrollPane3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTabla.setBackground(new java.awt.Color(36, 33, 33));
        jTabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabla.setForeground(new java.awt.Color(207, 207, 207));
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cod producto", "Nombre", "Precio", "Cantidad"
            }
        ));
        jTabla.setRowHeight(20);
        jTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTabla);

        jPanel7.setBackground(new java.awt.Color(238, 238, 238));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("SUB TOTAL");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("TOTAL");

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSubTotal.setText("0");
        txtSubTotal.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSubTotal.setSelectedTextColor(new java.awt.Color(0, 0, 0));

        txtTotal_venta.setEditable(false);
        txtTotal_venta.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal_venta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal_venta.setText("0");
        txtTotal_venta.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTotal_venta.setSelectedTextColor(new java.awt.Color(0, 0, 0));

        btnNuevo.setBackground(new java.awt.Color(36, 33, 33));
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(207, 207, 207));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/nuevo.png"))); // NOI18N
        btnNuevo.setText("Terminar Compra");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu/coins17.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal_venta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSubTotal)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotal_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(36, 33, 33));

        txtCod_producto.setBackground(new java.awt.Color(36, 33, 33));
        txtCod_producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCod_producto.setForeground(new java.awt.Color(207, 207, 207));
        txtCod_producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtCod_producto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCod_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCod_producto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCod_productoCaretUpdate(evt);
            }
        });
        txtCod_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod_productoActionPerformed(evt);
            }
        });
        txtCod_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCod_productoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(207, 207, 207));
        jLabel10.setText("Codigo :");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(207, 207, 207));
        jLabel12.setText("Precio :");

        txtPrecio_producto.setBackground(new java.awt.Color(36, 33, 33));
        txtPrecio_producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtPrecio_producto.setForeground(new java.awt.Color(207, 207, 207));
        txtPrecio_producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtPrecio_producto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecio_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPrecio_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecio_productoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(207, 207, 207));
        jLabel7.setText("Producto :");

        txtNombre_producto.setBackground(new java.awt.Color(36, 33, 33));
        txtNombre_producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombre_producto.setForeground(new java.awt.Color(207, 207, 207));
        txtNombre_producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtNombre_producto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombre_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre_producto.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                txtNombre_productoMouseWheelMoved(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(207, 207, 207));
        jLabel11.setText("Cantidad :");

        txtCantidadProducto.setBackground(new java.awt.Color(36, 33, 33));
        txtCantidadProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCantidadProducto.setForeground(new java.awt.Color(207, 207, 207));
        txtCantidadProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtCantidadProducto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCantidadProducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCantidadProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadProductoActionPerformed(evt);
            }
        });
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });

        btnQuitarProducto.setBackground(new java.awt.Color(36, 33, 33));
        btnQuitarProducto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnQuitarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/Quitar.png"))); // NOI18N
        btnQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoActionPerformed(evt);
            }
        });

        btnAgregarProducto.setBackground(new java.awt.Color(36, 33, 33));
        btnAgregarProducto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/Agregarr.png"))); // NOI18N
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnbuscarProducto.setBackground(new java.awt.Color(36, 33, 33));
        btnbuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/buscar.png"))); // NOI18N
        btnbuscarProducto.setText(" ");
        btnbuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarProductoActionPerformed(evt);
            }
        });

        cboModoIngreso.setBackground(new java.awt.Color(36, 33, 33));
        cboModoIngreso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboModoIngreso.setForeground(new java.awt.Color(207, 207, 207));
        cboModoIngreso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "x Mayor", "x Unidad", " " }));
        cboModoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboModoIngresoActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(207, 207, 207));
        jLabel17.setText("Modo :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCod_producto)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cboModoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 85, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio_producto)))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre_producto, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(txtCantidadProducto))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(btnQuitarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscarProducto)
                    .addComponent(cboModoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCod_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtNombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(btnAgregarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrecio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11))
                    .addComponent(btnQuitarProducto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        txtCod_compraFK.setText(" ");

        txtCod_detalle.setText(" ");

        txtCod_compra.setText(" ");

        btnClick.setText("prod");
        btnClick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClickActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(207, 207, 207));
        jLabel5.setText("FORMULARIO DE COMPRAS");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/CErrar2.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCod_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCod_compraFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCod_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStockDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCod_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(19, 19, 19))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCod_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod_compraFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClick))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClickActionPerformed

        seleccionProd();
        if (foco == 1) {
            txtCantidadProducto.requestFocus();
        } else if (foco == 0) {
            txtCod_producto.requestFocus();
            mostrar(txtCod_compraFK.getText());
        }


    }//GEN-LAST:event_btnClickActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtCod_proveedor.setText("");
        txtNombreProveedor.setText("");
        txtNumComprobante.setText("");
        txtStockDetalle.setText("");
        txtCod_compra.setText("");
        txtCod_compraFK.setText("");
        txtCod_detalle.setText("");

        txtTotal_venta.setText("0");
        txtSubTotal.setText("0");

        txtCod_producto.setText("");
        txtNombre_producto.setText("");
        txtPrecio_producto.setText("");
        txtCantidadProducto.setText("");
        btnGuardar.setEnabled(true);
        txtNumComprobante.setText("");
        btnNuevo.setEnabled(false);
        cboComprobante.setSelectedIndex(0);
        mostrar("0");

        btnbuscarProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnQuitarProducto.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnbuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarProductoActionPerformed
        comprobarProducto = 1;
        FrmVistaProducto form = new FrmVistaProducto();
        deskPricipal.add(form);
        // form.setIconifiable(true);
        // form.setMaximizable(false);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbuscarProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        if (txtCod_producto.getText().length() == 0 || txtNombre_producto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Producto.");
            btnbuscarProducto.requestFocus();
            return;
        }
        if (txtCantidadProducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una Cantidad.");
            txtCantidadProducto.requestFocus();
            return;
        }
        if (txtPrecio_producto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un precio.");
            txtPrecio_producto.requestFocus();
            return;
        }
        int Cantidad = Integer.parseInt(txtCantidadProducto.getText());
        long Stock = Long.valueOf(txtStockDetalle.getText());

        //long var1 = Long.parseLong(txtPrecio_producto.getText());
        String prec = txtPrecio_producto.getText();
        String prec2 = prec.replaceAll("\\.", "");

        long var1 = Long.parseLong(prec2);
        long var2 = Long.parseLong(txtCantidadProducto.getText());
        long resultadoDetalle = var1 * var2;

        Ddetalle_compra datos = new Ddetalle_compra();
        Fdetalle_compra funcion = new Fdetalle_compra();

        datos.setCantidad_detalle_compra(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        datos.setCod_compraFK(Integer.parseInt(txtCod_compraFK.getText()));

        String precioProducto = txtPrecio_producto.getText();
        String precioProducto2 = precioProducto.replaceAll("\\.", "");
        datos.setPrecio_producto(Long.valueOf(precioProducto2));

        //  datos.setPrecio_producto(Long.valueOf(txtPrecio_producto.getText()));
        String subTotal = String.valueOf(resultadoDetalle);
        String subTotal2 = subTotal.replaceAll("\\.", "");
        datos.setSubTotal(Long.valueOf(subTotal2));

        if (funcion.insertar(datos)) {

            long valorProd = Long.parseLong(precioProducto2);

            String subTotal3 = txtSubTotal.getText();
            String subTotal4 = subTotal3.replaceAll("\\.", "");
            //long valor2 = Long.parseLong(txtSubTotal.getText());
            long valor2 = Long.parseLong(subTotal4);
            long total = valorProd * Cantidad;
            long resultado = total + valor2;

            String mostrar0 = formatea.format(resultado);
            txtSubTotal.setText(String.valueOf(mostrar0));

            String mostrar3 = formatea.format(resultado);
            txtTotal_venta.setText(String.valueOf(mostrar3));

            Dcompra datos1 = new Dcompra();
            Fcompra funcion1 = new Fcompra();
            datos1.setCod_compra(Integer.parseInt(txtCod_compraFK.getText()));

            String totalCompra = String.valueOf(txtTotal_venta.getText());
            String totalCompra2 = totalCompra.replaceAll("\\.", "");
            datos1.setTotal_compra(Long.valueOf(totalCompra2));

            funcion1.Total(datos1);
            /**
             * ****Aumentar Stock*+++++++++
             */
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            int cantidad = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            cantidad = Integer.parseInt(txtCantidadProducto.getText());
            stock = stock + cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "Error Ingreso");
        }
        mostrar(txtCod_compraFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(false);
        txtCantidadProducto.setText("1");

        cboModoIngreso.setSelectedIndex(0);
        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoActionPerformed
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        if (!txtCod_detalle.getText().equals("")) {

            int cantidad = Integer.parseInt(txtCantidadProducto.getText());
            long Stock = Long.valueOf(txtStockDetalle.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad");
                return;
            }
            if (cantidad > Stock) {
                JOptionPane.showMessageDialog(null, "La cantidad a quitar supera el Stock del producto.");
                txtCod_producto.setText("");
                txtNombre_producto.setText("");
                txtPrecio_producto.setText("");
                txtStockDetalle.setText("");
                txtCod_producto.requestFocus();

                return;
            }

            Ddetalle_compra datos = new Ddetalle_compra();
            Fdetalle_compra funcion = new Fdetalle_compra();
            datos.setCod_detalle_compra(Integer.parseInt(txtCod_detalle.getText()));
            funcion.eliminar(datos);
            mostrar(txtCod_compraFK.getText());

            String precioProducto = txtPrecio_producto.getText();
            String precioProducto2 = precioProducto.replaceAll("\\.", "");

            // long valorProd = Long.valueOf(txtPrecio_producto.getText());
            long valorProd = Long.valueOf(precioProducto2);

            int cantidadProd = Integer.parseInt(txtCantidadProducto.getText());

            String subTotal1 = txtSubTotal.getText();
            String subTotal2 = subTotal1.replaceAll("\\.", "");
            //long valor2 = Long.valueOf(txtSubTotal.getText());
            long valor2 = Long.valueOf(subTotal2);

            long total = valorProd * cantidadProd;
            long resultado = valor2 - total;

            String mostrar0 = formatea.format(resultado);
            txtSubTotal.setText(String.valueOf(mostrar0));

            String mostrar3 = formatea.format(resultado);
            txtTotal_venta.setText(String.valueOf(mostrar3));

            Dcompra datos1 = new Dcompra();
            Fcompra funcion1 = new Fcompra();
            datos1.setCod_compra(Integer.parseInt(txtCod_compraFK.getText()));

            String totalCompra = String.valueOf(txtTotal_venta.getText());
            String totalCompra2 = totalCompra.replaceAll("\\.", "");
            datos1.setTotal_compra(Long.valueOf(totalCompra2));
            funcion1.Total(datos1);
            /**
             * ****Quitar Stock*+++++++++
             */
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock2 = 0;
            int cantidad2 = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock2 = Integer.parseInt(txtStockDetalle.getText());
            cantidad2 = Integer.parseInt(txtCantidadProducto.getText());
            int totalRestar = stock2 - cantidad2;
            datos2.setStock_producto(totalRestar);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        }
        mostrar(txtCod_compraFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(true);
        txtCantidadProducto.setEditable(true);
        txtCod_producto.setEditable(true);
    }//GEN-LAST:event_btnQuitarProductoActionPerformed

    private void txtCantidadProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadProductoActionPerformed

    }//GEN-LAST:event_txtCantidadProductoActionPerformed

    private void txtPrecio_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecio_productoActionPerformed

    }//GEN-LAST:event_txtPrecio_productoActionPerformed

    private void txtNombre_productoMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_txtNombre_productoMouseWheelMoved

    }//GEN-LAST:event_txtNombre_productoMouseWheelMoved

    private void txtCod_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCod_productoKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCod_productoKeyTyped

    private void txtCod_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod_productoActionPerformed

        btnClickActionPerformed(evt);
    }//GEN-LAST:event_txtCod_productoActionPerformed

    private void txtCod_productoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCod_productoCaretUpdate

    }//GEN-LAST:event_txtCod_productoCaretUpdate

    private void jTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaMouseClicked

        txtCod_producto.setEditable(false);
        txtNombre_producto.setEditable(false);
        txtCantidadProducto.setEditable(false);
        txtPrecio_producto.setEditable(false);
        btnQuitarProducto.setEnabled(true);

        int fila = jTabla.rowAtPoint(evt.getPoint());
        txtCod_detalle.setText(jTabla.getValueAt(fila, 0).toString());
        txtCod_producto.setText(jTabla.getValueAt(fila, 1).toString());
        txtNombre_producto.setText(jTabla.getValueAt(fila, 2).toString());
        txtPrecio_producto.setText(jTabla.getValueAt(fila, 3).toString());
        txtCantidadProducto.setText(jTabla.getValueAt(fila, 4).toString());
        txtCod_compraFK.setText(jTabla.getValueAt(fila, 5).toString());
        txtStockDetalle.setText(jTabla.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_jTablaMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        /*PARTE VALIDACION DE CAMPOS*/
        if (txtCod_proveedor.getText().length() == 0 || txtNombreProveedor.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Proveedor");
            btnBuscarPro.requestFocus();
            return;
        }
        if (txtNumComprobante.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un Numero para el comprobante");
            txtNumComprobante.requestFocus();
            return;
        }

        Dcompra datos = new Dcompra();
        Fcompra funcion = new Fcompra();
        Calendar cal;
        int d, m, a;
        cal = dcFecha_compra.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        datos.setCod_proveedorFK(Integer.parseInt(txtCod_proveedor.getText()));
        datos.setFecha_compra(new Date(a, m, d));

        String totalCompra = String.valueOf(txtTotal_venta.getText());
        String totalCompra2 = totalCompra.replaceAll("\\.", "");
        datos.setTotal_compra(Long.valueOf(totalCompra2));

        //datos.setTotal_compra(Long.valueOf(txtTotal_venta.getText()));
        int comprobante = cboComprobante.getSelectedIndex();
        datos.setTipo_comprobante((String) cboComprobante.getItemAt(comprobante));
        datos.setNum_comprobante(Integer.parseInt(txtNumComprobante.getText()));

        if (funcion.insertar(datos)) {
            DetallesFormVentaProd();
            BuscarCodigoVenta();
            activaBotones();
            btnGuardar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se Ingresaron Datos");
        }
        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
        txtCantidadProducto.setEditable(false);
        txtCantidadProducto.setText("1");

        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProActionPerformed
        FrmVistaProveedor form = new FrmVistaProveedor();
        Comprueba = 2;
        deskPricipal.add(form);
        //   form.setClosable(true);

        form.setMaximizable(false);
        form.toFront();
        form.setVisible(true);
        // this.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnBuscarProActionPerformed

    private void cboComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboComprobanteActionPerformed
        if (cboComprobante.getSelectedItem() == "Factura") {
            txtNumComprobante.setText("0");
            txtNumComprobante.setEditable(true);

        } else {

            txtNumComprobante.setEditable(false);
            txtNumComprobante.setText("0");
        }
    }//GEN-LAST:event_cboComprobanteActionPerformed

    private void cboComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboComprobanteItemStateChanged

    }//GEN-LAST:event_cboComprobanteItemStateChanged

    private void cboModoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboModoIngresoActionPerformed
        if (cboModoIngreso.getSelectedItem() == "x Mayor") {
            txtCantidadProducto.setText("0");
            txtCantidadProducto.setEditable(false);

            txtNombre_producto.setEditable(false);
            txtStockDetalle.setEditable(false);
            txtPrecio_producto.setEditable(false);
            txtCod_producto.setEditable(true);
            txtCod_producto.requestFocus();

        } else if (cboModoIngreso.getSelectedItem() == "x Unidad") {

            txtCod_producto.setEditable(true);
            txtCantidadProducto.setEditable(false);
            txtNombre_producto.setEditable(false);
            txtStockDetalle.setEditable(false);
            txtPrecio_producto.setEditable(false);
        }
    }//GEN-LAST:event_cboModoIngresoActionPerformed

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void txtNumComprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumComprobanteKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumComprobanteKeyTyped

    private void txtNumComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumComprobanteActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCompraDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCompraDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCompraDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCompraDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCompraDetalle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarPro;
    private javax.swing.JButton btnClick;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitarProducto;
    private javax.swing.JButton btnbuscarProducto;
    private javax.swing.JComboBox<String> cboComprobante;
    private javax.swing.JComboBox<String> cboModoIngreso;
    private com.toedter.calendar.JDateChooser dcFecha_compra;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTabla;
    public static javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtCod_compra;
    private javax.swing.JTextField txtCod_compraFK;
    private javax.swing.JTextField txtCod_detalle;
    public static javax.swing.JTextField txtCod_producto;
    public static javax.swing.JTextField txtCod_proveedor;
    public static javax.swing.JTextField txtNombreProveedor;
    public static javax.swing.JTextField txtNombre_producto;
    private javax.swing.JTextField txtNumComprobante;
    public static javax.swing.JTextField txtPrecio_producto;
    public static javax.swing.JTextField txtStockDetalle;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal_venta;
    // End of variables declaration//GEN-END:variables
}
