public class Microprograma {

    private String endereco;
    private String conteudo;

    public Microprograma(String endereco, String conteudo){
        this.endereco = endereco;
        this.conteudo = conteudo;
    }

    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public String getConteudo(){
        return this.conteudo;
    }
}