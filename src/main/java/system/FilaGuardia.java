package system;

import java.util.ArrayList;

public class FilaGuardia {
    private ArrayList<PacienteGuardia> heap;

    public FilaGuardia() {
        heap = new ArrayList<>();
    }

    // Encolar paciente según prioridad (O(log n))
    public void encolar(Paciente paciente) {
        if (paciente.getNivelGravedad() > 2) {
            throw new IllegalArgumentException("Solo niveles 1 (emergencia) o 2 (urgente) en guardia");
        }
        PacienteGuardia nodo = new PacienteGuardia(paciente);
        heap.add(nodo);
        subir(heap.size() - 1);
    }

    // Desencolar paciente con mayor prioridad (menor valor) (O(log n))
    public Paciente desencolar() {
        if (heap.isEmpty()) return null;
        PacienteGuardia nodo = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) bajar(0);
        return nodo.getPaciente();
    }

    private void subir(int indice) {
        int padre = (indice - 1) / 2;
        while (indice > 0 && heap.get(padre).getPrioridad() > heap.get(indice).getPrioridad()) {
            swap(indice, padre);
            indice = padre;
            padre = (indice - 1) / 2;
        }
    }

    private void bajar(int indice) {
        int menor = indice;
        int izquierda = 2 * indice + 1;
        int derecha = 2 * indice + 2;

        if (izquierda < heap.size() && heap.get(izquierda).getPrioridad() < heap.get(menor).getPrioridad()) {
            menor = izquierda;
        }
        if (derecha < heap.size() && heap.get(derecha).getPrioridad() < heap.get(menor).getPrioridad()) {
            menor = derecha;
        }
        if (menor != indice) {
            swap(indice, menor);
            bajar(menor);
        }
    }

    private void swap(int i, int j) {
        PacienteGuardia temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() { return heap.isEmpty(); }
}