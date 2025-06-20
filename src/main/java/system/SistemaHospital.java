package system;

import java.util.HashMap;
import java.util.ArrayList;

public class SistemaHospital {
    private HashMap<String, Paciente> pacientes; // Diccionario: DNI -> Paciente
    private ArrayList<Medico> medicos; // Lista de médicos
    private FilaGuardia guardia; // Fila para emergencias y urgencias

    public SistemaHospital() {
        pacientes = new HashMap<>();
        medicos = new ArrayList<>();
        guardia = new FilaGuardia();
    }

    // Registrar paciente
    public void registrarPaciente(String dni, String nombre, String motivo, int nivelGravedad) {
        Paciente paciente = new Paciente(dni, nombre, motivo, nivelGravedad);
        pacientes.put(dni, paciente);
        if (nivelGravedad <= 2) { // Emergencia o Urgente -> Guardia
            guardia.encolar(paciente);
        }
    }

    // Registrar médico
    public void registrarMedico(String id, String nombre, String area) {
        medicos.add(new Medico(id, nombre, area));
    }

    // Asignar turno a médico
    public void asignarTurno(String dni) {
        Paciente paciente = pacientes.get(dni);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        if (paciente.getEstado().equals("asignado") || paciente.getEstado().equals("completado")) {
            throw new IllegalStateException("Paciente ya asignado o completado");
        }

        if (paciente.getNivelGravedad() <= 2) { // Guardia
            for (Medico medico : medicos) {
                if (medico.getArea().equals("guardia") && !guardia.isEmpty()) {
                    Paciente proximo = guardia.desencolar();
                    proximo.setEstado("asignado");
                    return; // Asignado al primer médico de guardia
                }
            }
            throw new IllegalStateException("No hay médicos de guardia disponibles");
        } else { // Consultorio (Rutina)
            for (Medico medico : medicos) {
                if (medico.getArea().equals("consultorio")) {
                    medico.getTurnos().agregarTurno(paciente);
                    paciente.setEstado("asignado");
                    return;
                }
            }
            throw new IllegalStateException("No hay médicos de consultorio disponibles");
        }
    }

    // Completar turno
    public void completarTurno(String dni) {
        Paciente paciente = pacientes.get(dni);
        if (paciente == null || !paciente.getEstado().equals("asignado")) {
            throw new IllegalStateException("Paciente no encontrado o no asignado");
        }
        paciente.setEstado("completado");
        for (Medico medico : medicos) {
            if (medico.getArea().equals("consultorio")) {
                medico.getTurnos().eliminarTurno(dni);
            }
        }
    }

    // Consultar próximo turno por médico
    public Paciente consultarProximoTurno(String idMedico) {
        for (Medico medico : medicos) {
            if (medico.getId().equals(idMedico)) {
                if (medico.getArea().equals("guardia")) {
                    return guardia.desencolar(); // Próximo de la guardia
                } else {
                    return medico.getTurnos().getProximoTurno(); // Próximo en consultorio
                }
            }
        }
        return null;
    }

    // Consultar estado de paciente
    public String consultarEstadoPaciente(String dni) {
        Paciente paciente = pacientes.get(dni);
        return paciente != null ? paciente.toString() : "Paciente no encontrado";
    }
}