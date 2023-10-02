package ejemploproyecto4tobaco_kpdm;


public class usuario {
    String nombreUsuario, contraUsuario;
    
    public usuario(String nombreUsuario, String contraUsuario){
        this.nombreUsuario = nombreUsuario;
        this.contraUsuario = contraUsuario;
        
    }
    
    public String getNombre(){
        return this.nombreUsuario;
    }
    
    public String getcontra(){
        return this.contraUsuario;
    }
}
