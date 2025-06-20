package system;

public class Medico {
    private String id;
    private String nombre;
    private String area; // "guardia" o "consultorio"
    private AgendaMedico turnos; // Para turnos programados en consultorios

    public Medico(String id, String nombre, String area) {
        if (!area.equals("guardia") && !area.equals("consultorio")) {
            throw new IllegalArgumentException("Área debe ser 'guardia' o 'consultorio'");
        }
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.turnos = new AgendaMedico();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getArea() { return area; }
    public AgendaMedico getTurnos() { return turnos; }

    @Override
    public String toString() {
        return "Medico{ID=" + id + ", nombre=" + nombre + ", area=" + area + "}";
    }
}