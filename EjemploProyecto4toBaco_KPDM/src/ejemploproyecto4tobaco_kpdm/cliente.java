
package ejemploproyecto4tobaco_kpdm;


public class cliente {
    String nit, nombre;
    int edad;
    char genero;

    public cliente(String nit, String nombre, int edad, char genero) {
        this.nit = nit;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public String getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public char getGenero() {
        return genero;
    }
    
    
}

