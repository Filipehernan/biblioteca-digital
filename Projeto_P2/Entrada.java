// Entrada da tabela hash com dois tipos genéricos: chave (K) e valor (V)
// O atributo proximo permite encadear entradas na mesma posição (colisão)
public class Entrada<K, V> {

    // Atributos de pacote (sem modificador de acesso) — acessíveis pelo NossoHash
    K             key;
    V             value;
    Entrada<K, V> proximo;

    public Entrada(K key, V value) {
        this.key     = key;
        this.value   = value;
        this.proximo = null;
    }
}
