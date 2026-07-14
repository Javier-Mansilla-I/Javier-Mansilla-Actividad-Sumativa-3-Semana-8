package ui;

import data.GestorEntidades;
import model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interfaz gráfica principal de LlanquihueTourApp.
 *
 * Jerarquía de registro:
 *   Persona  ──(herencia)──▶  Empleado  ──(herencia)──▶  GuiaTuristico
 *   Persona  implements Registrable (contrato común)
 *
 * La GUI parte SIEMPRE desde una instancia Persona y amplía los campos
 * según el tipo concreto que se desea registrar.
 */
public class AppGUI extends JFrame {

    // ── Paleta de colores ────────────────────────────────────────────────
    private static final Color COLOR_FONDO      = new Color(10, 24, 42);
    private static final Color COLOR_PANEL      = new Color(18, 40, 66);
    private static final Color COLOR_CARD       = new Color(24, 52, 84);
    private static final Color COLOR_ACENTO     = new Color(0, 168, 204);   // Turquesa — Persona / base
    private static final Color COLOR_ACENTO2    = new Color(0, 210, 160);   // Verde agua — Vehículo
    private static final Color COLOR_GUIA       = new Color(255, 185, 60);  // Ámbar — GuiaTuristico
    private static final Color COLOR_TEXTO      = new Color(220, 235, 248);
    private static final Color COLOR_TEXTO_GRIS = new Color(140, 170, 200);

    private static final Font FONT_TITULO  = new Font("Segoe UI", Font.BOLD, 26);
    private static final Font FONT_SUBTIT  = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font FONT_NORMAL  = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_LABEL   = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_MONO    = new Font("Consolas", Font.PLAIN, 12);

    // ── Estado ───────────────────────────────────────────────────────────
    private final GestorEntidades gestor;
    private final CardLayout cardLayout;
    private final JPanel panelPrincipal;

    // ── Campos formulario Persona (base común) ───────────────────────────
    private JTextField txtPerNombre, txtPerApellido, txtPerRut;
    private JTextField txtPerCorreo, txtPerTelefono;
    private JTextField txtPerCalle, txtPerNumero, txtPerCiudad, txtPerComuna;

    // ── Campos formulario GuiaTuristico (extiende Persona → Empleado) ────
    private JTextField txtGuiaNombre, txtGuiaApellido, txtGuiaRut;
    private JTextField txtGuiaCorreo, txtGuiaTelefono;
    private JTextField txtGuiaPuesto, txtGuiaSueldo;
    private JTextField txtGuiaIdiomas, txtGuiaEspecialidad;

    // ── Campos formulario Vehiculo ───────────────────────────────────────
    private JTextField txtVehPatente, txtVehMarca, txtVehModelo, txtVehCapacidad;

    // ── Campos formulario ServicioTuristico ──────────────────────────────
    private JTextField txtSerNombre, txtSerDuracion;
    private JComboBox<String> cbSerTipo;
    private JLabel lblSerExtra;
    private JTextField txtSerExtra;

    // ── Panel de listado ─────────────────────────────────────────────────
    private JTextArea areaListado;

    // ─────────────────────────────────────────────────────────────────────
    //  Constructor
    // ─────────────────────────────────────────────────────────────────────
    public AppGUI(GestorEntidades gestor) {
        this.gestor = gestor;

        setTitle("LlanquihueTour — Sistema de Gestión v2.0");
        setSize(960, 820);
        setMinimumSize(new Dimension(820, 680));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(COLOR_FONDO);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        cardLayout    = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBackground(COLOR_FONDO);

        panelPrincipal.add(crearPanelMenu(),     "MENU");
        panelPrincipal.add(crearPanelPersona(),  "PERSONA");
        panelPrincipal.add(crearPanelGuia(),     "GUIA");
        panelPrincipal.add(crearPanelVehiculo(), "VEHICULO");
        panelPrincipal.add(crearPanelServicio(), "SERVICIO");
        panelPrincipal.add(crearPanelListado(),  "LISTADO");

        add(panelPrincipal);
        setVisible(true);
        cardLayout.show(panelPrincipal, "MENU");
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Panel Menú Principal
    // ─────────────────────────────────────────────────────────────────────
    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(COLOR_FONDO);

        // ── Header ──
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PANEL);
        header.setBorder(new EmptyBorder(26, 40, 26, 40));

        JLabel lblTitulo = new JLabel("🏔  LlanquihueTour", SwingConstants.CENTER);
        lblTitulo.setFont(FONT_TITULO);
        lblTitulo.setForeground(COLOR_ACENTO);

        JLabel lblSubtitulo = new JLabel(
                "Herencia · Polimorfismo · Interfaz Registrable · instanceof", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Consolas", Font.ITALIC, 13));
        lblSubtitulo.setForeground(COLOR_TEXTO_GRIS);

        JPanel headerTexts = new JPanel(new GridLayout(2, 1, 0, 6));
        headerTexts.setBackground(COLOR_PANEL);
        headerTexts.add(lblTitulo);
        headerTexts.add(lblSubtitulo);
        header.add(headerTexts, BorderLayout.CENTER);

        JPanel lineaAcento = new JPanel();
        lineaAcento.setBackground(COLOR_ACENTO);
        lineaAcento.setPreferredSize(new Dimension(0, 3));
        header.add(lineaAcento, BorderLayout.SOUTH);
        panel.add(header, BorderLayout.NORTH);

        // ── Centro: tarjetas ──
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(COLOR_FONDO);
        centro.setBorder(new EmptyBorder(20, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(10, 10, 10, 10);
        gbc.fill    = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Tarjeta 1 — Registrar Persona
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        centro.add(crearTarjetaMenu(
                "🧑", "Registrar Persona",
                "Instancia BASE de la jerarquía.\n" +
                "Registra datos personales: RUT, nombre,\n" +
                "correo, teléfono y dirección.",
                "➤ new Persona(...)",
                new String[]{"setRut()", "setNombre()", "setCorreo()", "mostrarResumen()"},
                COLOR_ACENTO,
                e -> irA("PERSONA")), gbc);

        // Tarjeta 2 — Registrar GuiaTuristico
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1;
        centro.add(crearTarjetaMenu(
                "👤", "Registrar Guía Turístico",
                "Extiende Persona → Empleado.\n" +
                "Añade puesto, sueldo, idiomas\n" +
                "y área de especialidad.",
                "➤ new GuiaTuristico(...)",
                new String[]{"getIdiomas()", "getAreaEspecialidad()", "getPuesto()", "mostrarResumen()"},
                COLOR_GUIA,
                e -> irA("GUIA")), gbc);

        // Tarjeta 3 — Registrar Servicio Turístico
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        centro.add(crearTarjetaMenu(
                "🏔", "Registrar Servicio",
                "Crea servicios turísticos (Excursión,\n" +
                "Paseo Lacustre o Ruta Gastronómica)\n" +
                "seleccionando el tipo dinámicamente.",
                "➤ new ServicioTuristico(...)",
                new String[]{"getNombre()", "getDuracionHoras()", "mostrarResumen()"},
                new Color(0, 180, 216),
                e -> irA("SERVICIO")), gbc);

        // Tarjeta 4 — Registrar Vehículo
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        centro.add(crearTarjetaMenu(
                "🚌", "Registrar Vehículo",
                "Implementa Registrable de forma\n" +
                "independiente (no hereda de Persona).\n" +
                "Gestión de flota de transporte.",
                "➤ new Vehiculo(...)",
                new String[]{"getPatente()", "getCapacidad()", "getMarca()", "mostrarResumen()"},
                COLOR_ACENTO2,
                e -> irA("VEHICULO")), gbc);

        // Tarjeta 5 — Ver Registros
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        centro.add(crearTarjetaMenu(
                "📋", "Ver Todos los Registros",
                "Recorre ArrayList<Registrable> con\n" +
                "for-each + instanceof para diferenciar\n" +
                "y mostrar cada tipo de entidad.",
                "➤ gestor.obtenerResumenes()",
                new String[]{"for-each loop", "instanceof check", "mostrarResumen()", "downcasting"},
                new Color(140, 100, 240),
                e -> { actualizarListado(); irA("LISTADO"); }), gbc);

        panel.add(centro, BorderLayout.CENTER);
        return panel;
    }


    /** Tarjeta del menú con badge de funciones */
    private JPanel crearTarjetaMenu(String icono, String titulo, String descripcion,
                                    String constructorTag, String[] funciones,
                                    Color colorAcento, ActionListener accion) {
        JPanel tarjeta = new JPanel(new BorderLayout(0, 10));
        tarjeta.setBackground(COLOR_CARD);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(colorAcento, 1, true),
                new EmptyBorder(14, 16, 14, 16)));
        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Icono + título
        JLabel lblIcono = new JLabel(icono + "  " + titulo, SwingConstants.LEFT);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        lblIcono.setForeground(colorAcento);

        // Constructor tag
        JLabel lblTag = new JLabel(constructorTag);
        lblTag.setFont(new Font("Consolas", Font.BOLD, 12));
        lblTag.setForeground(new Color(colorAcento.getRed(), colorAcento.getGreen(), colorAcento.getBlue(), 200));

        // Descripción
        JTextArea lblDesc = new JTextArea(descripcion);
        lblDesc.setEditable(false);
        lblDesc.setOpaque(false);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(COLOR_TEXTO_GRIS);
        lblDesc.setLineWrap(true);
        lblDesc.setWrapStyleWord(true);

        // Contenedor superior para el header, tag y descripción
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setOpaque(false);
        panelSuperior.add(lblIcono);
        panelSuperior.add(Box.createVerticalStrut(4));
        panelSuperior.add(lblTag);
        panelSuperior.add(Box.createVerticalStrut(8));
        panelSuperior.add(lblDesc);

        // Badges de funciones (centro)
        JPanel badgesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
        badgesPanel.setOpaque(false);
        for (String fn : funciones) {
            JLabel badge = new JLabel(fn);
            badge.setFont(new Font("Consolas", Font.PLAIN, 11));
            badge.setForeground(colorAcento);
            badge.setBackground(new Color(colorAcento.getRed(), colorAcento.getGreen(), colorAcento.getBlue(), 28));
            badge.setOpaque(true);
            badge.setBorder(new EmptyBorder(3, 7, 3, 7));
            badgesPanel.add(badge);
        }

        // Botón (abajo a la derecha)
        JButton btn = crearBoton("Abrir →", colorAcento);
        btn.addActionListener(accion);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelInferior.setOpaque(false);
        panelInferior.add(btn);

        // Agregar los componentes a la tarjeta con BorderLayout
        tarjeta.add(panelSuperior, BorderLayout.NORTH);
        tarjeta.add(badgesPanel, BorderLayout.CENTER);
        tarjeta.add(panelInferior, BorderLayout.SOUTH);

        // Hover
        tarjeta.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                tarjeta.setBackground(new Color(
                        colorAcento.getRed(), colorAcento.getGreen(), colorAcento.getBlue(), 25));
                repaint();
            }
            @Override public void mouseExited(MouseEvent e) {
                tarjeta.setBackground(COLOR_CARD);
                repaint();
            }
            @Override public void mouseClicked(MouseEvent e) { accion.actionPerformed(null); }
        });

        return tarjeta;
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Panel Formulario PERSONA (instancia base)
    // ─────────────────────────────────────────────────────────────────────
    private JPanel crearPanelPersona() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtPerRut      = new JTextField(20);
        txtPerNombre   = new JTextField(20);
        txtPerApellido = new JTextField(20);
        txtPerCorreo   = new JTextField(20);
        txtPerTelefono = new JTextField(20);
        txtPerCalle    = new JTextField(20);
        txtPerNumero   = new JTextField(20);
        txtPerCiudad   = new JTextField(20);
        txtPerComuna   = new JTextField(20);

        JTextField[] todos = {txtPerRut, txtPerNombre, txtPerApellido, txtPerCorreo,
                              txtPerTelefono, txtPerCalle, txtPerNumero, txtPerCiudad, txtPerComuna};
        for (JTextField c : todos) estilizarCampo(c);

        agregarCamposPersonales(form, gbc, 0, COLOR_ACENTO, txtPerRut, txtPerNombre, txtPerApellido, txtPerCorreo, txtPerTelefono);

        agregarSeccion(form, gbc, "── Dirección  (composición) ────────────────", 6, COLOR_TEXTO_GRIS);
        agregarFila(form, gbc, "Calle:",    txtPerCalle,   0, 7);
        agregarFila(form, gbc, "Número:",   txtPerNumero,  0, 8);
        agregarFila(form, gbc, "Ciudad:",   txtPerCiudad,  0, 9);
        agregarFila(form, gbc, "Comuna:",   txtPerComuna,  0, 10);

        return crearFormularioGenerico(
                "🧑  Registrar Persona",
                "new Persona(rut, nombre, apellido, correo, telefono, direccion)  ·  implements Registrable",
                COLOR_ACENTO,
                form,
                "✔ Registrar Persona",
                e -> registrarPersona()
        );
    }

    private JPanel crearPanelGuia() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtGuiaNombre      = new JTextField(20);
        txtGuiaApellido    = new JTextField(20);
        txtGuiaRut         = new JTextField(20);
        txtGuiaCorreo      = new JTextField(20);
        txtGuiaTelefono    = new JTextField(20);
        txtGuiaPuesto      = new JTextField(20);
        txtGuiaSueldo      = new JTextField(20);
        txtGuiaIdiomas     = new JTextField(20);
        txtGuiaEspecialidad = new JTextField(20);

        JTextField[] todos = {txtGuiaNombre, txtGuiaApellido, txtGuiaRut, txtGuiaCorreo,
                              txtGuiaTelefono, txtGuiaPuesto, txtGuiaSueldo,
                              txtGuiaIdiomas, txtGuiaEspecialidad};
        for (JTextField c : todos) estilizarCampo(c);

        agregarCamposPersonales(form, gbc, 0, COLOR_ACENTO, txtGuiaRut, txtGuiaNombre, txtGuiaApellido, txtGuiaCorreo, txtGuiaTelefono);

        agregarSeccion(form, gbc, "── Empleado  (extends Persona) ───────────────", 6, new Color(100, 200, 255));
        agregarFila(form, gbc, "Puesto:",           txtGuiaPuesto, 0, 7);
        agregarFila(form, gbc, "Sueldo Base ($):",  txtGuiaSueldo, 0, 8);

        agregarSeccion(form, gbc, "── GuiaTuristico  (extends Empleado) ─────────", 9, COLOR_GUIA);
        agregarFila(form, gbc, "Idiomas  (ej: Español, Inglés):", txtGuiaIdiomas,      0, 10);
        agregarFila(form, gbc, "Área de Especialidad:",            txtGuiaEspecialidad, 0, 11);

        return crearFormularioGenerico(
                "👤  Registrar Guía Turístico",
                "GuiaTuristico  extends  Empleado  extends  Persona  implements  Registrable",
                COLOR_GUIA,
                form,
                "✔ Registrar Guía",
                e -> registrarGuia()
        );
    }

    private JPanel crearPanelVehiculo() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtVehPatente   = new JTextField(24);
        txtVehMarca     = new JTextField(24);
        txtVehModelo    = new JTextField(24);
        txtVehCapacidad = new JTextField(24);

        JTextField[] campos = {txtVehPatente, txtVehMarca, txtVehModelo, txtVehCapacidad};
        for (JTextField c : campos) estilizarCampo(c);

        agregarSeccion(form, gbc, "── Vehiculo  implements Registrable ──────────", 0, COLOR_ACENTO2);
        agregarFila(form, gbc, "Patente  (ej: BBLK-45):",   txtVehPatente,   0, 1);
        agregarFila(form, gbc, "Marca:",                     txtVehMarca,     0, 2);
        agregarFila(form, gbc, "Modelo:",                    txtVehModelo,    0, 3);
        agregarFila(form, gbc, "Capacidad (pasajeros):",     txtVehCapacidad, 0, 4);

        return crearFormularioGenerico(
                "🚌  Registrar Vehículo",
                "Vehiculo  implements  Registrable  (independiente de la jerarquía Persona)",
                COLOR_ACENTO2,
                form,
                "✔ Registrar Vehículo",
                e -> registrarVehiculo()
        );
    }

    private JPanel crearPanelServicio() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtSerNombre   = new JTextField(22);
        txtSerDuracion = new JTextField(22);

        String[] tipos = { "Servicio Base", "Excursión Cultural", "Paseo Lacustre", "Ruta Gastronómica" };
        cbSerTipo = new JComboBox<>(tipos);
        cbSerTipo.setFont(FONT_NORMAL);
        cbSerTipo.setBackground(Color.WHITE);
        cbSerTipo.setForeground(new Color(10, 24, 42));
        cbSerTipo.setBorder(BorderFactory.createLineBorder(new Color(36, 76, 118), 1));
        cbSerTipo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == -1) {
                    setForeground(new Color(10, 24, 42));
                } else if (isSelected) {
                    setBackground(new Color(0, 168, 204));
                    setForeground(Color.WHITE);
                } else {
                    setBackground(new Color(18, 40, 66));
                    setForeground(new Color(220, 235, 248));
                }
                setFont(FONT_NORMAL);
                setBorder(new EmptyBorder(4, 6, 4, 6));
                return this;
            }
        });

        lblSerExtra = crearLabel("Dato adicional:");
        txtSerExtra = new JTextField(22);

        estilizarCampo(txtSerNombre);
        estilizarCampo(txtSerDuracion);
        estilizarCampo(txtSerExtra);

        lblSerExtra.setVisible(false);
        txtSerExtra.setVisible(false);

        cbSerTipo.addActionListener(e -> {
            String seleccionado = (String) cbSerTipo.getSelectedItem();
            if ("Servicio Base".equals(seleccionado)) {
                lblSerExtra.setVisible(false);
                txtSerExtra.setVisible(false);
            } else {
                lblSerExtra.setVisible(true);
                txtSerExtra.setVisible(true);
                if ("Excursión Cultural".equals(seleccionado)) {
                    lblSerExtra.setText("Lugar Histórico:");
                } else if ("Paseo Lacustre".equals(seleccionado)) {
                    lblSerExtra.setText("Tipo de Embarcación:");
                } else if ("Ruta Gastronómica".equals(seleccionado)) {
                    lblSerExtra.setText("Número de Paradas:");
                }
            }
            form.revalidate();
            form.repaint();
        });

        Color colorServicio = new Color(0, 180, 216);
        agregarSeccion(form, gbc, "── Información del Servicio Turístico ────────", 0, colorServicio);
        agregarFila(form, gbc, "Nombre del Servicio:", txtSerNombre, 0, 1);
        agregarFila(form, gbc, "Duración (horas):",    txtSerDuracion, 0, 2);
        agregarFila(form, gbc, "Tipo de Servicio:",     cbSerTipo, 0, 3);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        form.add(lblSerExtra, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        form.add(txtSerExtra, gbc);

        return crearFormularioGenerico(
                "🏔  Registrar Servicio Turístico",
                "ServicioTuristico (Base)  o  subclases específicas (ExcursionCultural, PaseoLacustre, RutaGastronomica)",
                colorServicio,
                form,
                "✔ Registrar Servicio",
                e -> registrarServicio()
        );
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Panel Listado
    // ─────────────────────────────────────────────────────────────────────
    private JPanel crearPanelListado() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(COLOR_FONDO);
        panel.add(crearHeader(
                "📋  Registro de Entidades",
                "ArrayList<Registrable>  ·  for-each  ·  instanceof  ·  polimorfismo",
                new Color(140, 100, 240)), BorderLayout.NORTH);

        areaListado = new JTextArea();
        areaListado.setEditable(false);
        areaListado.setFont(FONT_MONO);
        areaListado.setBackground(new Color(8, 18, 34));
        areaListado.setForeground(COLOR_TEXTO);
        areaListado.setBorder(new EmptyBorder(16, 20, 16, 20));
        areaListado.setLineWrap(true);
        areaListado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaListado);
        scroll.setBorder(null);
        scroll.setBackground(new Color(8, 18, 34));
        panel.add(scroll, BorderLayout.CENTER);

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        pie.setBackground(COLOR_PANEL);
        JButton btnVolver     = crearBotonSecundario("← Volver al Menú");
        JButton btnActualizar = crearBoton("↺ Actualizar", new Color(140, 100, 240));
        btnVolver.addActionListener(e -> irA("MENU"));
        btnActualizar.addActionListener(e -> actualizarListado());
        pie.add(btnVolver);
        pie.add(btnActualizar);
        panel.add(pie, BorderLayout.SOUTH);

        return panel;
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Lógica de registro
    // ─────────────────────────────────────────────────────────────────────

    /** Registra una instancia Persona base */
    private void registrarPersona() {
        try {
            String rut      = validarNoVacio(txtPerRut,      "RUT");
            String nombre   = validarNoVacio(txtPerNombre,   "Nombre");
            String apellido = validarNoVacio(txtPerApellido, "Apellido");
            String correo   = validarNoVacio(txtPerCorreo,   "Correo");
            String telefono = validarNoVacio(txtPerTelefono, "Teléfono");
            String calle    = validarNoVacio(txtPerCalle,    "Calle");
            String numero   = validarNoVacio(txtPerNumero,   "Número");
            String ciudad   = validarNoVacio(txtPerCiudad,   "Ciudad");
            String comuna   = validarNoVacio(txtPerComuna,   "Comuna");

            Direccion dir  = new Direccion(calle, numero, ciudad, comuna);
            Persona persona = new Persona(rut, nombre, apellido, correo, telefono, dir);

            gestor.agregarEntidad(persona);
            mostrarExito("¡Persona registrada!\n\n" + persona.mostrarResumen());
            limpiarCampos(txtPerRut, txtPerNombre, txtPerApellido, txtPerCorreo,
                          txtPerTelefono, txtPerCalle, txtPerNumero, txtPerCiudad, txtPerComuna);
            irA("MENU");

        } catch (IllegalArgumentException ex) {
            mostrarError("Error de validación:\n" + ex.getMessage());
        }
    }

    /** Registra un GuiaTuristico (instanciado desde la jerarquía Persona → Empleado → GuiaTuristico) */
    private void registrarGuia() {
        try {
            String nombre      = validarNoVacio(txtGuiaNombre,       "Nombre");
            String apellido    = validarNoVacio(txtGuiaApellido,      "Apellido");
            String rut         = validarNoVacio(txtGuiaRut,           "RUT");
            String correo      = validarNoVacio(txtGuiaCorreo,        "Correo");
            String telefono    = validarNoVacio(txtGuiaTelefono,      "Teléfono");
            String puesto      = validarNoVacio(txtGuiaPuesto,        "Puesto");
            String sueldoStr   = validarNoVacio(txtGuiaSueldo,        "Sueldo Base");
            String idiomas     = validarNoVacio(txtGuiaIdiomas,       "Idiomas");
            String especialidad = validarNoVacio(txtGuiaEspecialidad, "Área de Especialidad");

            double sueldo;
            try {
                sueldo = Double.parseDouble(sueldoStr.replace(",", ".").replace("$", "").trim());
            } catch (NumberFormatException ex) {
                mostrarError("El sueldo base debe ser un número válido (ej: 850000).");
                return;
            }

            Direccion dir = new Direccion("Sin especificar", "0", "Puerto Varas", "Puerto Varas");
            GuiaTuristico guia = new GuiaTuristico(
                    rut, nombre, apellido, correo, telefono, dir,
                    puesto, sueldo, idiomas, especialidad);

            gestor.agregarEntidad(guia);
            mostrarExito("¡Guía Turístico registrado!\n\n" + guia.mostrarResumen());
            limpiarCampos(txtGuiaNombre, txtGuiaApellido, txtGuiaRut, txtGuiaCorreo,
                          txtGuiaTelefono, txtGuiaPuesto, txtGuiaSueldo,
                          txtGuiaIdiomas, txtGuiaEspecialidad);
            irA("MENU");

        } catch (IllegalArgumentException ex) {
            mostrarError("Error de validación:\n" + ex.getMessage());
        }
    }

    private void registrarVehiculo() {
        try {
            String patente = validarNoVacio(txtVehPatente,   "Patente");
            String marca   = validarNoVacio(txtVehMarca,     "Marca");
            String modelo  = validarNoVacio(txtVehModelo,    "Modelo");
            String capStr  = validarNoVacio(txtVehCapacidad, "Capacidad");

            int capacidad;
            try {
                capacidad = Integer.parseInt(capStr.trim());
            } catch (NumberFormatException ex) {
                mostrarError("La capacidad debe ser un número entero (ej: 12).");
                return;
            }

            Vehiculo vehiculo = new Vehiculo(patente, marca, modelo, capacidad);
            gestor.agregarEntidad(vehiculo);
            mostrarExito("¡Vehículo registrado!\n\n" + vehiculo.mostrarResumen());
            limpiarCampos(txtVehPatente, txtVehMarca, txtVehModelo, txtVehCapacidad);
            irA("MENU");

        } catch (IllegalArgumentException ex) {
            mostrarError("Error de validación:\n" + ex.getMessage());
        }
    }

    private void registrarServicio() {
        try {
            String nombre = validarNoVacio(txtSerNombre, "Nombre del Servicio");
            String durStr = validarNoVacio(txtSerDuracion, "Duración (horas)");

            int duracion;
            try {
                duracion = Integer.parseInt(durStr.trim());
            } catch (NumberFormatException ex) {
                mostrarError("La duración debe ser un número entero (ej: 4).");
                return;
            }

            String tipo = (String) cbSerTipo.getSelectedItem();
            Registrable servicio;

            if ("Servicio Base".equals(tipo)) {
                servicio = new ServicioTuristico(nombre, duracion);
            } else {
                String extra = validarNoVacio(txtSerExtra, lblSerExtra.getText().replace(":", ""));
                if ("Excursión Cultural".equals(tipo)) {
                    servicio = new ExcursionCultural(nombre, duracion, extra);
                } else if ("Paseo Lacustre".equals(tipo)) {
                    servicio = new PaseoLacustre(nombre, duracion, extra);
                } else { // Ruta Gastronómica
                    int paradas;
                    try {
                        paradas = Integer.parseInt(extra.trim());
                    } catch (NumberFormatException ex) {
                        mostrarError("El número de paradas debe ser un número entero.");
                        return;
                    }
                    servicio = new RutaGastronomica(nombre, duracion, paradas);
                }
            }

            gestor.agregarEntidad(servicio);
            mostrarExito("¡Servicio registrado!\n\n" + servicio.mostrarResumen());
            limpiarCampos(txtSerNombre, txtSerDuracion, txtSerExtra);
            cbSerTipo.setSelectedIndex(0);
            irA("MENU");

        } catch (IllegalArgumentException ex) {
            mostrarError("Error de validación:\n" + ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Helpers de formulario
    // ─────────────────────────────────────────────────────────────────────

    /** Agrega un separador de sección con texto en color */
    private void agregarSeccion(JPanel form, GridBagConstraints gbc,
                                 String texto, int row, Color color) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Consolas", Font.BOLD, 11));
        lbl.setForeground(color);
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 4; gbc.weightx = 1.0;
        form.add(lbl, gbc);
        gbc.gridwidth = 1;
    }

    /** Agrega un par label + campo en la fila indicada */
    private void agregarFila(JPanel form, GridBagConstraints gbc,
                              String etiqueta, JComponent campo,
                              int col, int row) {
        gbc.gridx = col;     gbc.gridy = row; gbc.weightx = 0;
        form.add(crearLabel(etiqueta), gbc);
        gbc.gridx = col + 1; gbc.weightx = 1.0;
        form.add(campo, gbc);
    }

    /** Helper para unificar la estructura de todos los formularios */
    private JPanel crearFormularioGenerico(String titulo, String subtitulo, Color colorAcento,
                                            JPanel form, String btnRegistrarTexto, ActionListener accionRegistrar) {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(COLOR_FONDO);
        panel.add(crearHeader(titulo, subtitulo, colorAcento), BorderLayout.NORTH);

        form.setBackground(COLOR_PANEL);
        form.setBorder(new EmptyBorder(25, 48, 25, 48));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 20; // Fila lejana para asegurar que vaya al final
        gbc.gridwidth = 4;

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotones.setBackground(COLOR_PANEL);
        JButton btnVolver = crearBotonSecundario("← Volver");
        JButton btnRegistrar = crearBoton(btnRegistrarTexto, colorAcento);
        btnVolver.addActionListener(e -> irA("MENU"));
        btnRegistrar.addActionListener(accionRegistrar);
        panelBotones.add(btnVolver);
        panelBotones.add(btnRegistrar);
        form.add(panelBotones, gbc);

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBackground(COLOR_PANEL);
        scroll.getViewport().setBackground(COLOR_PANEL);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /** Agrega los campos personales comunes a un formulario */
    private void agregarCamposPersonales(JPanel form, GridBagConstraints gbc, int startRow, Color color,
                                         JTextField rut, JTextField nombre, JTextField apellido,
                                         JTextField correo, JTextField telefono) {
        agregarSeccion(form, gbc, "── Datos personales ──────────────────────", startRow, color);
        agregarFila(form, gbc, "RUT  (ej: 12345678-9):", rut,       0, startRow + 1);
        agregarFila(form, gbc, "Nombre:",                nombre,    0, startRow + 2);
        agregarFila(form, gbc, "Apellido:",              apellido,  0, startRow + 3);
        agregarFila(form, gbc, "Correo electrónico:",    correo,    0, startRow + 4);
        agregarFila(form, gbc, "Teléfono:",              telefono,  0, startRow + 5);
    }


    // ─────────────────────────────────────────────────────────────────────
    //  Helpers de UI genéricos
    // ─────────────────────────────────────────────────────────────────────
    private void actualizarListado() {
        areaListado.setText(gestor.obtenerResumenes());
        areaListado.setCaretPosition(0);
    }

    private void irA(String panel) {
        cardLayout.show(panelPrincipal, panel);
    }

    private String validarNoVacio(JTextField campo, String nombre) {
        String val = campo.getText().trim();
        if (val.isEmpty())
            throw new IllegalArgumentException("El campo \"" + nombre + "\" no puede estar vacío.");
        return val;
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarExito(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos(JTextField... campos) {
        for (JTextField c : campos) c.setText("");
    }

    private JPanel crearHeader(String titulo, String subtitulo, Color colorAcento) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PANEL);
        header.setBorder(new EmptyBorder(18, 36, 18, 36));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(FONT_TITULO);
        lblTitulo.setForeground(colorAcento);

        JLabel lblSub = new JLabel(subtitulo);
        lblSub.setFont(new Font("Consolas", Font.ITALIC, 11));
        lblSub.setForeground(COLOR_TEXTO_GRIS);

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 4));
        textos.setBackground(COLOR_PANEL);
        textos.add(lblTitulo);
        textos.add(lblSub);
        header.add(textos, BorderLayout.CENTER);

        JPanel linea = new JPanel();
        linea.setBackground(colorAcento);
        linea.setPreferredSize(new Dimension(0, 2));
        header.add(linea, BorderLayout.SOUTH);

        return header;
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_TEXTO_GRIS);
        return lbl;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(FONT_NORMAL);
        campo.setBackground(new Color(8, 22, 42));
        campo.setForeground(COLOR_TEXTO);
        campo.setCaretColor(COLOR_ACENTO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(36, 76, 118), 1, true),
                new EmptyBorder(6, 10, 6, 10)));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(FONT_SUBTIT);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorder(new EmptyBorder(10, 22, 10, 22));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(color.darker()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(color); }
        });
        return btn;
    }

    private JButton crearBotonSecundario(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(FONT_NORMAL);
        btn.setForeground(COLOR_TEXTO_GRIS);
        btn.setBackground(new Color(28, 52, 82));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorder(new EmptyBorder(10, 18, 10, 18));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(48, 78, 116)); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(new Color(28, 52, 82)); }
        });
        return btn;
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Lanzamiento
    // ─────────────────────────────────────────────────────────────────────
    public static void lanzar(GestorEntidades gestor) {
        SwingUtilities.invokeLater(() -> new AppGUI(gestor));
    }
}
