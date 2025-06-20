package system;

public class Turno {
    private Paciente paciente;
    private Turno siguiente;
    private Turno anterior;

    public Turno(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
        this.anterior = null;
    }

    public Paciente getPaciente() { return paciente; }
    public Turno getSiguiente() { return siguiente; }
    public Turno getAnterior() { return anterior; }
    public void setSiguiente(Turno siguiente) { this.siguiente = siguiente; }
    public void setAnterior(Turno anterior) { this.anterior = anterior; }
}