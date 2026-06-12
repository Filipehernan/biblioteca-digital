// Catálogo indexado por ISBN usando tabela hash — busca instantânea
public class Catalogo {

    private NossoHash<String, Livro> indice;

    public Catalogo() {
        this.indice = new NossoHash<>();
    }

    // Insere o livro no catálogo
    public void cadastrar(Livro livro) {
        indice.put(livro.getIsbn(), livro);
    }

    // Localiza e retorna o livro pelo ISBN
    public Livro buscar(String isbn) {
        return indice.get(isbn);
    }

    // Verifica se o ISBN está cadastrado
    public boolean existe(String isbn) {
        return indice.containsKey(isbn);
    }

    // Exibe todos os livros cadastrados (percorre toda a tabela hash)
    public void exibirCatalogo() {
        System.out.println("--- Catálogo Indexado ---");
        indice.exibeMap();
    }
}
