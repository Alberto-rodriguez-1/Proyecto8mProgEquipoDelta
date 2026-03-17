public class Solucion{
    private Integer id;
    private String meme;
    private String realidad;
    public Solucion(Integer id,String meme,String realidad){
        setId(id);
        setMeme(meme);
        setRealidad(realidad);
    }
    public void setId(Integer id){
        this.id=id;
    }
    public void setMeme(String meme){
        this.meme=meme;
    }
    public void setRealidad(String realidad){
        this.realidad=realidad;
    }
    public Integer getId(){
        return this.id;
    }
    public String getMeme(){
        return this.meme;
    }
    public String getRealidad(){
        return this.realidad;
    }
}