public class Meme {
    private Integer id;
    private String texto;

    public Meme(String texto, Integer id) {
        this.setTexto(texto);
        this.setId(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }

    public Integer getId() {
        return this.id;
    }
}