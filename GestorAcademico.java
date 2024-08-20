// src/GestorAcademico.java
import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Curso> cursos = new ArrayList<>();
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones = new HashMap<>();

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        if (inscripciones.containsKey(idCurso)) {
            if (inscripciones.get(idCurso).contains(estudiante)) {
                throw new EstudianteYaInscritoException("El estudiante ya está inscrito en este curso.");
            } else {
                inscripciones.get(idCurso).add(estudiante);
            }
        } else {
            ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
            listaEstudiantes.add(estudiante);
            inscripciones.put(idCurso, listaEstudiantes);
        }
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        ArrayList<Estudiante> listaEstudiantes = inscripciones.get(idCurso);
        if (listaEstudiantes != null) {
            Estudiante estudiante = listaEstudiantes.stream()
                    .filter(e -> e.getId() == idEstudiante)
                    .findFirst()
                    .orElseThrow(() -> new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en este curso."));
            listaEstudiantes.remove(estudiante);
            if (listaEstudiantes.isEmpty()) {
                inscripciones.remove(idCurso);
            }
        } else {
            throw new EstudianteNoInscritoEnCursoException("El ID del curso no es válido.");
        }
    }
}
