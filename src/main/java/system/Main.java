package system;

public class Main {
    public static void main(String[] args) {
        SistemaHospital hospital = new SistemaHospital();

        // Caso de prueba 1: Registrar pacientes
        try {
            hospital.registrarPaciente("12345678", "Juan Pérez", "Infarto", 1); // Emergencia
            hospital.registrarPaciente("87654321", "Ana López", "Fiebre alta", 2); // Urgente
            hospital.registrarPaciente("11223344", "Carlos Ruiz", "Chequeo", 3); // Rutina
            System.out.println("Pacientes registrados correctamente");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Caso de prueba 2: Registrar médicos
        hospital.registrarMedico("M1", "Dr. Gómez", "guardia");
        hospital.registrarMedico("M2", "Dr. Silva", "consultorio");
        System.out.println("Médicos registrados");

        // Caso de prueba 3: Asignar turnos
        try {
            hospital.asignarTurno("12345678"); // Emergencia a guardia
            hospital.asignarTurno("87654321"); // Urgente a guardia
            hospital.asignarTurno("11223344"); // Rutina a consultorio
            System.out.println("Turnos asignados");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Caso de prueba 4: Consultar estados
        System.out.println(hospital.consultarEstadoPaciente("12345678"));
        System.out.println(hospital.consultarEstadoPaciente("11223344"));

        // Caso de prueba 5: Completar turno
        try {
            hospital.completarTurno("11223344");
            System.out.println(hospital.consultarEstadoPaciente("11223344"));
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Caso de prueba 6: Consultar próximo turno
        Paciente proximo = hospital.consultarProximoTurno("M2");
        System.out.println("Próximo turno de Dr. Silva: " + (proximo != null ? proximo : "Ninguno"));
    }
}