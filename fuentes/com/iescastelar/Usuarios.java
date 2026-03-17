public class Usuarios{
    private String nombre;
    private Integer puntuacion;
    public Usuarios(String nombre, Integer puntuacion){
        this.nombre=nombre;
        this.puntuacion=puntuacion;
    }
    public String getNombre(){
        return this.nombre;
    }
    public Integer getPuntuacion(){
        return this.puntuacion;
    }
    @Override
    public String toString(){
        return this.nombre+";"+this.puntuacion;
    }
}