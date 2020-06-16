public class Memoria {
    
    private String endereco;
    private String conteudo;

    public Memoria(String endereco, String conteudo){
        this.endereco = endereco;
        this.conteudo = conteudo;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public String getConteudo(){
        return this.conteudo;
    }

}