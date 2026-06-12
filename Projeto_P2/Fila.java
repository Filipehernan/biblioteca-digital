// Fila genérica (FIFO) usando No<T> internamente
// O primeiro a entrar é o primeiro a sair — diferente da pilha (LIFO) do Projeto I.
public class Fila<T> {

    private No<T> primeiro; // frente da fila — próximo a sair
    private No<T> ultimo;   // fundo da fila — último a entrar
    private int   quantidade;

    public Fila() {
        this.primeiro   = null;
        this.ultimo     = null;
        this.quantidade = 0;
    }

    public boolean filaVazia() { return quantidade == 0; }
    public int     tamanho()   { return quantidade; }

    // Insere no final da fila
    public void enfileira(T info) {
        No<T> novoNo = new No<>(info);
        if (filaVazia()) {
            primeiro = novoNo;
            ultimo   = novoNo;
        } else {
            ultimo.setProximo(novoNo);
            ultimo = novoNo;
        }
        quantidade++;
    }

    // Remove e retorna o primeiro elemento da fila
    public T desenfileira() {
        if (filaVazia()) {
            throw new FilaVaziaException("Não é possível remover: a fila está vazia.");
        }

        T infoRemovida = primeiro.getInfo();
        primeiro = primeiro.getProximo();
        quantidade--;

        // Evita memory leak: se a fila ficou vazia, limpa o ponteiro ultimo também
        if (filaVazia()) {
            ultimo = null;
        }

        return infoRemovida;
    }

    // Consulta o primeiro elemento sem removê-lo
    public T primeiro() {
        if (filaVazia()) {
            throw new FilaVaziaException("A fila está vazia.");
        }
        return primeiro.getInfo();
    }

    // Exibe os elementos no formato [A]->[B]->[C]->\
    @Override
    public String toString() {
        if (filaVazia()) return "(fila vazia)";

        StringBuilder resultado = new StringBuilder();
        No<T> atual = primeiro;
        while (atual != null) {
            resultado.append("[").append(atual.getInfo()).append("]->");
            atual = atual.getProximo();
        }
        resultado.append("\\");
        return resultado.toString();
    }
}
