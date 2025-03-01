import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SistemaAcademico {
    // Lista global para almacenar estudiantes
    static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    static ArrayList<Nota> notas = new ArrayList<>();

    public static void main(String[] args) {
        // Uso de las librerías de interfaz gráfica: awt, swing
        JFrame frame = new JFrame("Ventana Principal");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Sistema Académico", SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnEstudiantes = new JButton("Estudiantes");
        JButton btnNotas = new JButton("Notas");
        JButton btnConsulta = new JButton("Consulta");

        btnEstudiantes.addActionListener(e -> new VentanaEstudiantes());
        btnNotas.addActionListener(e -> new VentanaNotas());
        btnConsulta.addActionListener(e -> new VentanaConsulta());

        panel.add(btnEstudiantes);
        panel.add(btnNotas);
        panel.add(btnConsulta);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

class Estudiante {
    String matricula;
    String nombres;
    String apellidos;
    String fechaNacimiento;
    String modalidad;
    String carrera;

    public Estudiante(String matricula, String nombres, String apellidos, String fechaNacimiento, String modalidad,
            String carrera) {
        this.matricula = matricula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.modalidad = modalidad;
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Nombres: " + nombres + " " + apellidos + ", Carrera: " + carrera;
    }
}

class Nota {
    String matricula;
    String materia;
    double calificacion;

    public Nota(String matricula, String materia, double calificacion) {
        this.matricula = matricula;
        this.materia = materia;
        this.calificacion = calificacion;
    }
}

class VentanaEstudiantes extends JFrame {
    private JTextField txtMatricula, txtNombres, txtApellidos, txtFechaNacimiento;
    private JComboBox<String> cmbModalidad, cmbCarrera;

    public VentanaEstudiantes() {
        setTitle("Registro de Estudiantes");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(7, 2));

        txtMatricula = new JTextField(10);
        txtNombres = new JTextField(10);
        txtApellidos = new JTextField(10);
        txtFechaNacimiento = new JTextField(10);
        cmbModalidad = new JComboBox<>(new String[] { "Online", "Presencial", "Distancia" });
        cmbCarrera = new JComboBox<>(new String[] { "Ingeniería de Software", "Ingeniería en Sistemas de Información",
                "Ingeniería en Telecomunicaciones", "Ingeniería Industrial", "Otra" });

        panel.add(new JLabel("Matrícula:"));
        panel.add(txtMatricula);
        panel.add(new JLabel("Nombres:"));
        panel.add(txtNombres);
        panel.add(new JLabel("Apellidos:"));
        panel.add(txtApellidos);
        panel.add(new JLabel("Fecha Nacimiento:"));
        panel.add(txtFechaNacimiento);
        panel.add(new JLabel("Modalidad:"));
        panel.add(cmbModalidad);
        panel.add(new JLabel("Carrera:"));
        panel.add(cmbCarrera);

        JButton btnGrabar = new JButton("Grabar");
        btnGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String nombres = txtNombres.getText();
                String apellidos = txtApellidos.getText();
                String fechaNacimiento = txtFechaNacimiento.getText();
                String modalidad = (String) cmbModalidad.getSelectedItem();
                String carrera = (String) cmbCarrera.getSelectedItem();

                Estudiante estudiante = new Estudiante(matricula, nombres, apellidos, fechaNacimiento, modalidad,
                        carrera);
                SistemaAcademico.estudiantes.add(estudiante); // Almacena el estudiante en el arreglo
                JOptionPane.showMessageDialog(null, "Estudiante grabado exitosamente.");
                clearFields(); // Limpiar campos
            }
        });

        panel.add(btnGrabar);
        add(panel);
        setVisible(true);
    }

    private void clearFields() {
        txtMatricula.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtFechaNacimiento.setText("");
    }
}

class VentanaNotas extends JFrame {
    private JTextField txtMatricula, txtMateria, txtCalificacion;

    public VentanaNotas() {
        setTitle("Registro de Notas");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(4, 2));

        txtMatricula = new JTextField(10);
        txtMateria = new JTextField(10);
        txtCalificacion = new JTextField(10);

        panel.add(new JLabel("Matrícula:"));
        panel.add(txtMatricula);
        panel.add(new JLabel("Materia:"));
        panel.add(txtMateria);
        panel.add(new JLabel("Calificación:"));
        panel.add(txtCalificacion);

        JButton btnGrabar = new JButton("Grabar");
        btnGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String materia = txtMateria.getText();
                double calificacion = Double.parseDouble(txtCalificacion.getText());

                Nota nota = new Nota(matricula, materia, calificacion);
                SistemaAcademico.notas.add(nota); // Almacena la nota en el arreglo
                JOptionPane.showMessageDialog(null, "Nota grabada exitosamente.");
                clearFields(); // Limpiar campos
            }
        });

        panel.add(btnGrabar);
        add(panel);
        setVisible(true);
    }

    private void clearFields() {
        txtMatricula.setText("");
        txtMateria.setText("");
        txtCalificacion.setText("");
    }
}

class VentanaConsulta extends JFrame {
    public VentanaConsulta() {
        setTitle("Consulta de Estudiantes y Notas");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField(8);
        panel.add(txtMatricula);
        panel.add(new JLabel("Datos del estudiante:"));
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                Estudiante estudianteEncontrado = null;

                // Buscar estudiante en la lista
                for (Estudiante est : SistemaAcademico.estudiantes) {
                    if (est.matricula.equals(matricula)) {
                        estudianteEncontrado = est;
                        break;
                    }
                }

                if (estudianteEncontrado != null) {
                    // Mostrar los datos del estudiante
                    StringBuilder infoEstudiante = new StringBuilder(estudianteEncontrado.toString());
                    infoEstudiante.append("\n\nNotas:\n");

                    // Buscar las notas asociadas al estudiante
                    boolean tieneNotas = false;
                    for (Nota nota : SistemaAcademico.notas) {
                        if (nota.matricula.equals(matricula)) {
                            tieneNotas = true;
                            infoEstudiante.append("Materia: ").append(nota.materia).append(", Calificación: ")
                                    .append(nota.calificacion).append("\n");
                        }
                    }

                    if (!tieneNotas) {
                        infoEstudiante.append("No tiene notas registradas.");
                    }

                    textArea.setText(infoEstudiante.toString());
                } else {
                    textArea.setText("Estudiante no encontrado.");
                }
            }
        });

        panel.add(btnConsultar);
        add(panel);
        setVisible(true);
    }
}