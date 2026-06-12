// Tabela hash genérica com encadeamento externo para tratar colisões
public class NossoHash<K, V> {

    private static final int CAPACIDADE_INICIAL = 16;

    private Entrada<K, V>[] tabela;

    @SuppressWarnings("unchecked")
    public NossoHash() {
        tabela = new Entrada[CAPACIDADE_INICIAL];
    }

    // Função hash vista em aula: usa o hashCode() da chave e limita ao tamanho da tabela
    private int calcularPosicao(K key) {
        int hashCode = key.hashCode();
        if (hashCode < 0) hashCode = -hashCode; // garante posição positiva
        return hashCode % tabela.length;
    }

    // Insere ou atualiza a chave na tabela (novo valor vai para a frente da cadeia)
    public void put(K key, V value) {
        int posicao = calcularPosicao(key);
        Entrada<K, V> novaEntrada = new Entrada<>(key, value);
        novaEntrada.proximo = tabela[posicao];
        tabela[posicao]     = novaEntrada;
    }

    // Localiza e retorna o valor pela chave; retorna null se não encontrado
    public V get(K key) {
        int posicao = calcularPosicao(key);
        Entrada<K, V> atual = tabela[posicao];
        while (atual != null) {
            if (atual.key.equals(key)) {
                return atual.value;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // Verifica se a chave existe na tabela
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    // Verifica se o valor existe em qualquer posição (requer varredura completa)
    // Por que varredura completa? Porque na tabela hash a busca rápida é por CHAVE.
    // Para buscar por valor, não há atalho — é preciso verificar todas as posições.
    public boolean containsValue(V value) {
        for (int i = 0; i < tabela.length; i++) {
            Entrada<K, V> atual = tabela[i];
            while (atual != null) {
                if (atual.value != null && atual.value.equals(value)) {
                    return true;
                }
                atual = atual.proximo;
            }
        }
        return false;
    }

    // Exibe todas as posições da tabela, inclusive as vazias
    public void exibeMap() {
        for (int i = 0; i < tabela.length; i++) {
            System.out.print("  [" + i + "] ");
            if (tabela[i] == null) {
                System.out.println("(vazio)");
            } else {
                Entrada<K, V> atual = tabela[i];
                while (atual != null) {
                    System.out.print("(" + atual.key + " -> " + atual.value + ") ");
                    atual = atual.proximo;
                }
                System.out.println();
            }
        }
    }
}
