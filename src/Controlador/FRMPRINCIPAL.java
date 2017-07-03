package Controlador;

import static Controlador.rptVentasFechas.comprobar;
import static Controlador.rptComprasFechas.comprobarCompras;
import Funciones.conexion;
import Reportes.frmStockCritico;
import java.awt.Graphics;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicDesktopPaneUI;
import javax.swing.plaf.basic.BasicMenuBarUI;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FRMPRINCIPAL extends javax.swing.JFrame {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    public FRMPRINCIPAL() {

        initComponents();

        this.setLocationRelativeTo(null);
        lblCod_persona.setVisible(false);
        this.setExtendedState(MAXIMIZED_BOTH);

        menuBar.setOpaque(true);

        menuBar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new java.awt.Color(36, 33, 33));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        deskPricipal.setUI(new BasicDesktopPaneUI() {

            public void paintComponent(Graphics g) {
                g.setColor(new java.awt.Color(36, 33, 33));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        });
    }

    public void abrirVenta() {

        FrmVentaDetalle form = new FrmVentaDetalle();
        deskPricipal.add(form);

        form.setClosable(true);
        form.setIconifiable(true);
        FrmVentaDetalle.txtCod_usuario.setText(lblCod_persona.getText());
        FrmVentaDetalle.txtNombre_usuario.setText(lblNombre_persona.getText());
        try {
            form.setMaximum(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deskPricipal = new javax.swing.JDesktopPane();
        lblCod_persona = new javax.swing.JLabel();
        lblNombre_persona = new javax.swing.JLabel();
        lblAcceso = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jmIVenta = new javax.swing.JMenu();
        jmIVentas = new javax.swing.JMenuItem();
        MenuCompras = new javax.swing.JMenu();
        JMICompra = new javax.swing.JMenuItem();
        MenuPersonal = new javax.swing.JMenu();
        JmIClientes = new javax.swing.JMenuItem();
        JmIUsuarios = new javax.swing.JMenuItem();
        JmIProveedores = new javax.swing.JMenuItem();
        MenuProductos = new javax.swing.JMenu();
        JmIProductos = new javax.swing.JMenuItem();
        JmIStock = new javax.swing.JMenuItem();
        MenuInformes = new javax.swing.JMenu();
        JmIStockCritico = new javax.swing.JMenuItem();
        JmIVentasPorFecha = new javax.swing.JMenuItem();
        JmIProdVendFEc = new javax.swing.JMenuItem();
        JmIProdCompraFEc = new javax.swing.JMenuItem();
        JmITotalRecaudacion = new javax.swing.JMenuItem();
        JmiHerramientas = new javax.swing.JMenu();
        JmIHerramientaVenta = new javax.swing.JMenuItem();
        JmIHerramientaRespaldo = new javax.swing.JMenuItem();
        jMIAyuda = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(36, 33, 33));

        deskPricipal.setBackground(new java.awt.Color(36, 33, 33));
        deskPricipal.setToolTipText("");
        deskPricipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deskPricipal.setOpaque(true
        );

        lblCod_persona.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCod_persona.setForeground(new java.awt.Color(207, 207, 207));
        lblCod_persona.setText("CODIGO");
        deskPricipal.add(lblCod_persona);
        lblCod_persona.setBounds(30, 20, 170, 15);

        lblNombre_persona.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNombre_persona.setForeground(new java.awt.Color(207, 207, 207));
        lblNombre_persona.setText("NOMBRE");
        deskPricipal.add(lblNombre_persona);
        lblNombre_persona.setBounds(30, 50, 170, 15);

        lblAcceso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAcceso.setForeground(new java.awt.Color(207, 207, 207));
        lblAcceso.setText("ACCESO");
        deskPricipal.add(lblAcceso);
        lblAcceso.setBounds(30, 80, 170, 10);

        menuBar.setBackground(new java.awt.Color(36, 33, 33));
        menuBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuBar.setToolTipText("");
        menuBar.setAlignmentX(0.2F);
        menuBar.setAlignmentY(0.2F);
        menuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuBar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        menuBar.setOpaque(true);

        jmIVenta.setBackground(new java.awt.Color(36, 33, 33));
        jmIVenta.setForeground(new java.awt.Color(245, 245, 245));
        jmIVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/ventaMenu.png"))); // NOI18N
        jmIVenta.setText("Venta");
        jmIVenta.setToolTipText("");
        jmIVenta.setAlignmentX(0.0F);
        jmIVenta.setAlignmentY(0.0F);
        jmIVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jmIVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmIVenta.setOpaque(true
        );

        jmIVentas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jmIVentas.setBackground(new java.awt.Color(36, 33, 33));
        jmIVentas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmIVentas.setForeground(new java.awt.Color(255, 255, 255));
        jmIVentas.setText("Venta");
        jmIVentas.setOpaque(true);
        jmIVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmIVentasActionPerformed(evt);
            }
        });
        jmIVenta.add(jmIVentas);

        menuBar.add(jmIVenta);

        MenuCompras.setBackground(new java.awt.Color(36, 33, 33));
        MenuCompras.setForeground(new java.awt.Color(245, 245, 245));
        MenuCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/compraMenu.png"))); // NOI18N
        MenuCompras.setText("Compra");
        MenuCompras.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MenuCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuCompras.setOpaque(true);

        JMICompra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        JMICompra.setBackground(new java.awt.Color(36, 33, 33));
        JMICompra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JMICompra.setForeground(new java.awt.Color(255, 255, 255));
        JMICompra.setText("Compra");
        JMICompra.setOpaque(true);
        JMICompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMICompraActionPerformed(evt);
            }
        });
        MenuCompras.add(JMICompra);

        menuBar.add(MenuCompras);

        MenuPersonal.setBackground(new java.awt.Color(36, 33, 33));
        MenuPersonal.setForeground(new java.awt.Color(245, 245, 245));
        MenuPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/usuarioMenu.png"))); // NOI18N
        MenuPersonal.setText("Personal");
        MenuPersonal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MenuPersonal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuPersonal.setOpaque(true);

        JmIClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        JmIClientes.setBackground(new java.awt.Color(36, 33, 33));
        JmIClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIClientes.setForeground(new java.awt.Color(255, 255, 255));
        JmIClientes.setText("Clientes");
        JmIClientes.setOpaque(true);
        JmIClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIClientesActionPerformed(evt);
            }
        });
        MenuPersonal.add(JmIClientes);

        JmIUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        JmIUsuarios.setBackground(new java.awt.Color(36, 33, 33));
        JmIUsuarios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        JmIUsuarios.setText("Usuarios");
        JmIUsuarios.setOpaque(true);
        JmIUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIUsuariosActionPerformed(evt);
            }
        });
        MenuPersonal.add(JmIUsuarios);

        JmIProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        JmIProveedores.setBackground(new java.awt.Color(36, 33, 33));
        JmIProveedores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIProveedores.setForeground(new java.awt.Color(255, 255, 255));
        JmIProveedores.setText("Proveedores");
        JmIProveedores.setOpaque(true);
        JmIProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIProveedoresActionPerformed(evt);
            }
        });
        MenuPersonal.add(JmIProveedores);

        menuBar.add(MenuPersonal);

        MenuProductos.setBackground(new java.awt.Color(36, 33, 33));
        MenuProductos.setForeground(new java.awt.Color(245, 245, 245));
        MenuProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/codigoMenu.png"))); // NOI18N
        MenuProductos.setText("Productos");
        MenuProductos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MenuProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuProductos.setOpaque(true);
        MenuProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuProductosMouseClicked(evt);
            }
        });
        MenuProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProductosActionPerformed(evt);
            }
        });

        JmIProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        JmIProductos.setBackground(new java.awt.Color(36, 33, 33));
        JmIProductos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIProductos.setForeground(new java.awt.Color(255, 255, 255));
        JmIProductos.setText("Productos");
        JmIProductos.setOpaque(true);
        JmIProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmIProductosMouseClicked(evt);
            }
        });
        JmIProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIProductosActionPerformed(evt);
            }
        });
        MenuProductos.add(JmIProductos);

        JmIStock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        JmIStock.setBackground(new java.awt.Color(36, 33, 33));
        JmIStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIStock.setForeground(new java.awt.Color(255, 255, 255));
        JmIStock.setText("Modificar Stock");
        JmIStock.setOpaque(true);
        JmIStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmIStockMouseClicked(evt);
            }
        });
        JmIStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIStockActionPerformed(evt);
            }
        });
        MenuProductos.add(JmIStock);

        menuBar.add(MenuProductos);

        MenuInformes.setBackground(new java.awt.Color(36, 33, 33));
        MenuInformes.setForeground(new java.awt.Color(245, 245, 245));
        MenuInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/reporteMenu.png"))); // NOI18N
        MenuInformes.setText("Informes");
        MenuInformes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MenuInformes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuInformes.setOpaque(true);

        JmIStockCritico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_MASK));
        JmIStockCritico.setBackground(new java.awt.Color(36, 33, 33));
        JmIStockCritico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIStockCritico.setForeground(new java.awt.Color(255, 255, 255));
        JmIStockCritico.setText("Stock de productos");
        JmIStockCritico.setOpaque(true);
        JmIStockCritico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIStockCriticoActionPerformed(evt);
            }
        });
        MenuInformes.add(JmIStockCritico);

        JmIVentasPorFecha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.SHIFT_MASK));
        JmIVentasPorFecha.setBackground(new java.awt.Color(36, 33, 33));
        JmIVentasPorFecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIVentasPorFecha.setForeground(new java.awt.Color(255, 255, 255));
        JmIVentasPorFecha.setText("Ventas por Fecha");
        JmIVentasPorFecha.setOpaque(true);
        JmIVentasPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIVentasPorFechaActionPerformed(evt);
            }
        });
        MenuInformes.add(JmIVentasPorFecha);

        JmIProdVendFEc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.SHIFT_MASK));
        JmIProdVendFEc.setBackground(new java.awt.Color(36, 33, 33));
        JmIProdVendFEc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIProdVendFEc.setForeground(new java.awt.Color(255, 255, 255));
        JmIProdVendFEc.setText("Ventas de productos por Fecha");
        JmIProdVendFEc.setOpaque(true);
        JmIProdVendFEc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIProdVendFEcActionPerformed(evt);
            }
        });
        MenuInformes.add(JmIProdVendFEc);

        JmIProdCompraFEc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.SHIFT_MASK));
        JmIProdCompraFEc.setBackground(new java.awt.Color(36, 33, 33));
        JmIProdCompraFEc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIProdCompraFEc.setForeground(new java.awt.Color(255, 255, 255));
        JmIProdCompraFEc.setText("Compras de productos por Fecha");
        JmIProdCompraFEc.setOpaque(true);
        JmIProdCompraFEc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIProdCompraFEcActionPerformed(evt);
            }
        });
        MenuInformes.add(JmIProdCompraFEc);

        JmITotalRecaudacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.SHIFT_MASK));
        JmITotalRecaudacion.setBackground(new java.awt.Color(36, 33, 33));
        JmITotalRecaudacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmITotalRecaudacion.setForeground(new java.awt.Color(255, 255, 255));
        JmITotalRecaudacion.setText("Total Recaudacion ");
        JmITotalRecaudacion.setOpaque(true);
        JmITotalRecaudacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmITotalRecaudacionActionPerformed(evt);
            }
        });
        MenuInformes.add(JmITotalRecaudacion);

        menuBar.add(MenuInformes);

        JmiHerramientas.setBackground(new java.awt.Color(36, 33, 33));
        JmiHerramientas.setForeground(new java.awt.Color(245, 245, 245));
        JmiHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/toolsMenu.png"))); // NOI18N
        JmiHerramientas.setText("Herramientas");
        JmiHerramientas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        JmiHerramientas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmiHerramientas.setOpaque(true);

        JmIHerramientaVenta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        JmIHerramientaVenta.setBackground(new java.awt.Color(36, 33, 33));
        JmIHerramientaVenta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JmIHerramientaVenta.setForeground(new java.awt.Color(255, 255, 255));
        JmIHerramientaVenta.setText("Herramientas de Ventas");
        JmIHerramientaVenta.setOpaque(true);
        JmIHerramientaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIHerramientaVentaActionPerformed(evt);
            }
        });
        JmiHerramientas.add(JmIHerramientaVenta);

        JmIHerramientaRespaldo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        JmIHerramientaRespaldo.setText("Herramienta de Respaldo");
        JmIHerramientaRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmIHerramientaRespaldoActionPerformed(evt);
            }
        });
        JmiHerramientas.add(JmIHerramientaRespaldo);

        menuBar.add(JmiHerramientas);

        jMIAyuda.setBackground(new java.awt.Color(36, 33, 33));
        jMIAyuda.setForeground(new java.awt.Color(245, 245, 245));
        jMIAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/ayuda.png"))); // NOI18N
        jMIAyuda.setText("Ayuda");
        jMIAyuda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMIAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMIAyudaMouseClicked(evt);
            }
        });
        menuBar.add(jMIAyuda);

        jMenu5.setBackground(new java.awt.Color(36, 33, 33));
        jMenu5.setForeground(new java.awt.Color(245, 245, 245));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImanegesMenuBar/exitMenu.png"))); // NOI18N
        jMenu5.setText("Salir");
        jMenu5.setAutoscrolls(true);
        jMenu5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu5.setOpaque(true);
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        menuBar.add(jMenu5);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, deskPricipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, deskPricipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProductosActionPerformed

    }//GEN-LAST:event_MenuProductosActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed

    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        this.dispose();
    }//GEN-LAST:event_jMenu5MouseClicked

    private void MenuProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuProductosMouseClicked

    }//GEN-LAST:event_MenuProductosMouseClicked

    private void JmIStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIStockActionPerformed
        FrmStock form = new FrmStock();
        deskPricipal.add(form);

        form.setClosable(true);
        form.setIconifiable(true);
        try {
            form.setMaximum(true);
        } catch (Exception e) {
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_JmIStockActionPerformed

    private void JmIProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmIProductosMouseClicked

    }//GEN-LAST:event_JmIProductosMouseClicked

    private void JmIStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmIStockMouseClicked

    }//GEN-LAST:event_JmIStockMouseClicked

    private void JmIProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIProductosActionPerformed
        FrmProducto form = new FrmProducto();
        deskPricipal.add(form);

        form.setClosable(true);
        form.setIconifiable(true);
        try {
            form.setMaximum(true);
        } catch (Exception e) {
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_JmIProductosActionPerformed

    private void JmIClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIClientesActionPerformed
        FrmCliente form = new FrmCliente();
        deskPricipal.add(form);

        form.setClosable(true);
        form.setIconifiable(true);
        try {
            form.setMaximum(true);
        } catch (Exception e) {
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_JmIClientesActionPerformed

    private void JmIUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIUsuariosActionPerformed
        FrmUsuario form = new FrmUsuario();
        deskPricipal.add(form);

        form.setClosable(true);
        form.setIconifiable(true);
        try {
            form.setMaximum(true);
        } catch (Exception e) {
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_JmIUsuariosActionPerformed

    private void JmIHerramientaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIHerramientaVentaActionPerformed

        FrmMostrarVentas form = new FrmMostrarVentas();
        deskPricipal.add(form);
        try {
            form.setMaximum(true);
            form.setClosable(true);
            form.setIconifiable(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);

    }//GEN-LAST:event_JmIHerramientaVentaActionPerformed

    private void JmIStockCriticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIStockCriticoActionPerformed
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Stock minimo de los productos"));
        if (cantidad < 0) {

            JOptionPane.showMessageDialog(null, "Debe ingresar un valor mayor");
            return;
        }
        try {

            JasperReport jr = (JasperReport) JRLoader.loadObject(frmStockCritico.class.getResource("/Reportes/StockCritico.jasper"));
            Map parametro = new HashMap<String, Integer>();
            parametro.put("stock_producto", cantidad);

            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.show();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "error" + e);
        }


    }//GEN-LAST:event_JmIStockCriticoActionPerformed

    private void JmIVentasPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIVentasPorFechaActionPerformed
        rptVentasFechas form = new rptVentasFechas();
        deskPricipal.add(form);

        try {
            form.setMaximum(false);
            form.setClosable(true);
            form.setIconifiable(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }
        this.setLocationRelativeTo(null);
        form.toFront();
        form.setVisible(true);

        comprobar = 2;
    }//GEN-LAST:event_JmIVentasPorFechaActionPerformed

    private void JmIProdVendFEcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIProdVendFEcActionPerformed

        rptVentasFechas form = new rptVentasFechas();
        deskPricipal.add(form);

        try {
            form.setMaximum(false);
            form.setClosable(true);
            form.setIconifiable(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }
        this.setLocationRelativeTo(null);
        form.toFront();
        form.setVisible(true);

        comprobar = 1;


    }//GEN-LAST:event_JmIProdVendFEcActionPerformed

    private void JmIProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIProveedoresActionPerformed
        FrmProveedor form = new FrmProveedor();
        deskPricipal.add(form);

        form.setClosable(true);
        form.setIconifiable(true);
        try {
            form.setMaximum(true);
        } catch (Exception e) {
        }

        form.toFront();
        form.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_JmIProveedoresActionPerformed

    private void JmIProdCompraFEcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIProdCompraFEcActionPerformed
        rptComprasFechas form = new rptComprasFechas();
        deskPricipal.add(form);

        try {
            form.setMaximum(false);
            form.setClosable(true);
            form.setIconifiable(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }
        this.setLocationRelativeTo(null);
        form.toFront();
        form.setVisible(true);

        comprobarCompras = 1;


    }//GEN-LAST:event_JmIProdCompraFEcActionPerformed

    private void JmITotalRecaudacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmITotalRecaudacionActionPerformed
        rptTotalRecaudacion form = new rptTotalRecaudacion();
        deskPricipal.add(form);

        try {
            form.setMaximum(false);
            form.setClosable(true);
            form.setIconifiable(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }
        this.setLocationRelativeTo(null);
        form.toFront();
        form.setVisible(true);


    }//GEN-LAST:event_JmITotalRecaudacionActionPerformed

    private void jmIVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmIVentasActionPerformed
        abrirVenta();
    }//GEN-LAST:event_jmIVentasActionPerformed

    private void JMICompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMICompraActionPerformed
        FrmCompraDetalle form = new FrmCompraDetalle();
        deskPricipal.add(form);

        try {
            form.setMaximum(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
        }
        this.setLocationRelativeTo(null);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_JMICompraActionPerformed

    private void jMIAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMIAyudaMouseClicked
        JOptionPane.showMessageDialog(rootPane, "ATAJOS DE TECLADO\n"
                + "MENU\n"
                + "F1 = abrir formulario Venta\n"
                + "F2 = Compra\n"
                + "F3 = clientes\n"
                + "F4 =  usuarios\n"
                + "F5 = proveedores\n"
                + "F6 = producto\n"
                + "F7 = stock producto\n"
                + "F8 = Herramientas de venta\n"
                + "F9 = Herramientas de compra\n"
                + "\n"
                + "INFORMES\n"
                + "Shift + 1 = Stock producto\n"
                + "Shift + 2 = Ventas por fecha\n"
                + "Shift + 3 = venta de productos por fecha\n"
                + "Shift + 4 = compra de productos por fecha\n"
                + "Shift + 5 = total recaudacion\n"
                + "\n"
                + "ATAJOS PARA FORMULARIO VENTA.\n"
                + "ALT + x = ejecuta boton iniciar\n"
                + "ALT + n = ejecuta boton Nuevo\n"
                + "ALT + c = ejecuta boton Calcular\n"
                + "ALT + A = ejecuta boton Agregar\n"
                + "\n"
                + "FOCO EN CAJAS DE TEXTO FORMULARIO VENTA.\n"
                + "ALT + u = foco en descuento\n"
                + "ALT + i = foco en Importe\n"
                + "ALT + o = foco en Cantidad Producto\n"
                + "ALT + p = foco en Codigo Producto\"\n"
                + "ALT + y = foco en Modo producto (x unidad , x mayor)"
        );
    }//GEN-LAST:event_jMIAyudaMouseClicked

    private void JmIHerramientaRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmIHerramientaRespaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmIHerramientaRespaldoActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    //  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FRMPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRMPRINCIPAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JMenuItem JMICompra;
    public static javax.swing.JMenuItem JmIClientes;
    javax.swing.JMenuItem JmIHerramientaRespaldo;
    public static javax.swing.JMenuItem JmIHerramientaVenta;
    javax.swing.JMenuItem JmIProdCompraFEc;
    javax.swing.JMenuItem JmIProdVendFEc;
    javax.swing.JMenuItem JmIProductos;
    public static javax.swing.JMenuItem JmIProveedores;
    javax.swing.JMenuItem JmIStock;
    javax.swing.JMenuItem JmIStockCritico;
    javax.swing.JMenuItem JmITotalRecaudacion;
    public static javax.swing.JMenuItem JmIUsuarios;
    javax.swing.JMenuItem JmIVentasPorFecha;
    public static javax.swing.JMenu JmiHerramientas;
    public static javax.swing.JMenu MenuCompras;
    public static javax.swing.JMenu MenuInformes;
    public static javax.swing.JMenu MenuPersonal;
    public static javax.swing.JMenu MenuProductos;
    public static javax.swing.JDesktopPane deskPricipal;
    javax.swing.JMenu jMIAyuda;
    public static javax.swing.JMenu jMenu5;
    public static javax.swing.JMenu jmIVenta;
    javax.swing.JMenuItem jmIVentas;
    public static javax.swing.JLabel lblAcceso;
    public static javax.swing.JLabel lblCod_persona;
    public static javax.swing.JLabel lblNombre_persona;
    public static javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
