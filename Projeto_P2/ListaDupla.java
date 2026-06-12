// Lista duplamente encadeada de Livros (tipo concreto — sem genéricos, propositalmente)
// OBSERVAÇÃO: assim como VetorDinamico no Projeto I, esta lista só funciona com Livro.
// Para usar com outro tipo, seria necessário reescrever. Os genéricos (No<T>, Fila<T>)
// que implementamos nas próximas etapas resolvem exatamente esse problema.
public class ListaDupla {

    private NoDuplo cabeca; // primeiro nó
    private NoDuplo cauda;  // último nó
    private int     quantidade;

    public ListaDupla() {
        this.cabeca     = null;
        this.cauda      = null;
        this.quantidade = 0;
    }

    public int tamanho() { return quantidade; }

    public boolean estaVazia() { return quantidade == 0; }

    // Insere um livro no início da lista
    public void insereInicio(Livro livro) {
        NoDuplo novoNo = new NoDuplo(livro);
        if (estaVazia()) {
            cabeca = novoNo;
            cauda  = novoNo;
        } else {
            novoNo.setProximo(cabeca);
            cabeca.setAnterior(novoNo);
            cabeca = novoNo;
        }
        quantidade++;
    }

    // Insere um livro no final da lista
    public void insereFim(Livro livro) {
        NoDuplo novoNo = new NoDuplo(livro);
        if (estaVazia()) {
            cabeca = novoNo;
            cauda  = novoNo;
        } else {
            novoNo.setAnterior(cauda);
            cauda.setProximo(novoNo);
            cauda = novoNo;
        }
        quantidade++;
    }

    // Remove e retorna o primeiro livro; retorna null se a lista estiver vazia
    public Livro removePrimeiro() {
        if (estaVazia()) return null;

        Livro livroRemovido = cabeca.getConteudo();
        cabeca = cabeca.getProximo();

        if (cabeca != null) {
            cabeca.setAnterior(null);
        } else {
            cauda = null; // lista ficou vazia
        }
        quantidade--;
        return livroRemovido;
    }

    // Remove e retorna o último livro; retorna null se a lista estiver vazia
    public Livro removeUltimo() {
        if (estaVazia()) return null;

        Livro livroRemovido = cauda.getConteudo();
        cauda = cauda.getAnterior();

        if (cauda != null) {
            cauda.setProximo(null);
        } else {
            cabeca = null; // lista ficou vazia
        }
        quantidade--;
        return livroRemovido;
    }

    // Percorre a lista e retorna o livro com o ISBN informado, ou null se não encontrado
    public Livro buscarPorIsbn(String isbn) {
        NoDuplo atual = cabeca;
        while (atual != null) {
            if (atual.getConteudo().getIsbn().equals(isbn)) {
                return atual.getConteudo();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // Exibe os livros do primeiro ao último
    public void listarDoInicio() {
        if (estaVazia()) {
            System.out.println("  (acervo vazio)");
            return;
        }
        NoDuplo atual = cabeca;
        int posicao = 1;
        while (atual != null) {
            System.out.println("  " + posicao + ". " + atual.getConteudo());
            atual = atual.getProximo();
            posicao++;
        }
    }

    // Exibe os livros do último ao primeiro (navegando pelos ponteiros anterior)
    public void listarDoFim() {
        if (estaVazia()) {
            System.out.println("  (acervo vazio)");
            return;
        }
        NoDuplo atual = cauda;
        int posicao = quantidade;
        while (atual != null) {
            System.out.println("  " + posicao + ". " + atual.getConteudo());
            atual = atual.getAnterior();
            posicao--;
        }
    }
}
