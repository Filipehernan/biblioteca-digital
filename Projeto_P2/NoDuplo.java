// Nó com ponteiros para o próximo e para o anterior (lista duplamente encadeada)
public class NoDuplo {

    private Livro  conteudo;
    private NoDuplo proximo;
    private NoDuplo anterior;

    public NoDuplo(Livro conteudo) {
        this.conteudo  = conteudo;
        this.proximo   = null;
        this.anterior  = null;
    }

    public Livro   getConteudo()  { return conteudo; }
    public NoDuplo getProximo()   { return proximo; }
    public NoDuplo getAnterior()  { return anterior; }

    public void setConteudo(Livro conteudo)   { this.conteudo  = conteudo; }
    public void setProximo(NoDuplo proximo)   { this.proximo   = proximo; }
    public void setAnterior(NoDuplo anterior) { this.anterior  = anterior; }
}
