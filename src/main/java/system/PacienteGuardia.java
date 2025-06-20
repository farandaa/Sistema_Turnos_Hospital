package system;

public class PacienteGuardia {
    private Paciente paciente;
    private int prioridad; // 1 = Emergencia, 2 = Urgente

    public PacienteGuardia(Paciente paciente) {
        this.paciente = paciente;
        this.prioridad = paciente.getNivelGravedad();
    }

    public Paciente getPaciente() { return paciente; }
    public int getPrioridad() { return prioridad; }
}