package system;

public class AgendaMedico {
    private Turno cabeza;
    private Turno cola;

    public AgendaMedico() {
        cabeza = null;
        cola = null;
    }

    // Agregar turno al final (O(1))
    public void agregarTurno(Paciente paciente) {
        Turno nuevo = new Turno(paciente);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
    }

    // Eliminar turno por DNI (O(n))
    public boolean eliminarTurno(String dni) {
        Turno actual = cabeza;
        while (actual != null) {
            if (actual.getPaciente().getDni().equals(dni)) {
                if (actual == cabeza) {
                    cabeza = actual.getSiguiente();
                    if (cabeza != null) cabeza.setAnterior(null);
                    else cola = null;
                } else if (actual == cola) {
                    cola = actual.getAnterior();
                    cola.setSiguiente(null);
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    // Obtener próximo turno (O(1))
    public Paciente getProximoTurno() {
        if (cabeza == null) return null;
        return cabeza.getPaciente();
    }
}