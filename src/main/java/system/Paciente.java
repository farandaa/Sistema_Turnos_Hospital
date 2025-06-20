package system;

public class Paciente {
    private String dni;
    private String nombre;
    private String motivoConsulta;
    private int nivelGravedad; // 1 = Emergencia, 2 = Urgente, 3 = Rutina
    private String estado; // "en espera", "asignado", "completado"

    public Paciente(String dni, String nombre, String motivoConsulta, int nivelGravedad) {
        if (!esDniValido(dni) || nivelGravedad < 1 || nivelGravedad > 3) {
            throw new IllegalArgumentException("DNI inválido o nivel de gravedad fuera de rango (1-3)");
        }
        this.dni = dni;
        this.nombre = nombre;
        this.motivoConsulta = motivoConsulta;
        this.nivelGravedad = nivelGravedad;
        this.estado = "en espera";
    }

    // Validación simple de DNI (8 dígitos numéricos)
    private boolean esDniValido(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    // Getters y setters
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public int getNivelGravedad() { return nivelGravedad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Paciente{DNI=" + dni + ", nombre=" + nombre + ", motivo=" + motivoConsulta +
                ", gravedad=" + nivelGravedad + ", estado=" + estado + "}";
    }
}