// Nó genérico — funciona para qualquer tipo T
// Diferente do NoDuplo (que só armazena Livro), este nó serve para qualquer dado.
// Essa é a vantagem dos tipos genéricos que a professora mencionou!
public class No<T> {

    private T     info;
    private No<T> proximo;

    public No(T info) {
        this.info    = info;
        this.proximo = null;
    }

    public T     getInfo()    { return info; }
    public No<T> getProximo() { return proximo; }

    public void setInfo(T info)          { this.info    = info; }
    public void setProximo(No<T> proximo){ this.proximo = proximo; }

    @Override
    public String toString() {
        return "[" + info + "]";
    }
}
