
public class Main {
    public static void main(String[] args) {
        // Crear instancias de Estudiante y Curso
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez", "2000-01-01", "matriculado");
        Curso curso1 = new Curso(101, "Matemáticas", "Curso de matemáticas básicas", 5, "v1.0");

        // Instanciar GestorAcademico
        GestorAcademico gestor = new GestorAcademico();

        // Agregar estudiante y curso
        gestor.matricularEstudiante(estudiante1);
        gestor.agregarCurso(curso1);

        try {
            // Inscribir estudiante en curso
            gestor.inscribirEstudianteCurso(estudiante1, 101);
            
            // Desinscribir estudiante del curso
            gestor.desinscribirEstudianteCurso(1, 101);
            
            // Verificar que la inscripción y desinscripción funcionen correctamente
        } catch (EstudianteYaInscritoException | EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }
}
