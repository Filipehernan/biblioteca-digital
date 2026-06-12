// Coordena os empréstimos de livros e as filas de espera por ISBN
public class GestorEmprestimos {

    // Chave = ISBN do livro | Valor = fila de usuários esperando por ele
    private NossoHash<String, Fila<Usuario>> filasDeEspera;

    // Referência ao acervo para atualizar a disponibilidade dos livros
    private ListaDupla acervo;

    public GestorEmprestimos(ListaDupla acervo) {
        this.acervo        = acervo;
        this.filasDeEspera = new NossoHash<>();
    }

    // Empresta o livro se disponível; caso contrário, enfileira o usuário
    public void solicitarEmprestimo(String isbn, Usuario usuario) {
        Livro livro = acervo.buscarPorIsbn(isbn);

        if (livro == null) {
            System.out.println("Livro com ISBN " + isbn + " não encontrado no acervo.");
            return;
        }

        if (livro.isDisponivel()) {
            livro.setDisponivel(false);
            System.out.println("Empréstimo realizado para " + usuario.getNome()
                    + ": " + livro.getTitulo());
        } else {
            garantirFilaCriada(isbn);
            filasDeEspera.get(isbn).enfileira(usuario);
            System.out.println(usuario.getNome()
                    + " entrou na fila de espera para: " + livro.getTitulo());
        }
    }

    // Devolve o livro; se houver fila de espera, já empresta para o próximo
    public void devolverLivro(String isbn) {
        Livro livro = acervo.buscarPorIsbn(isbn);

        if (livro == null) {
            System.out.println("Livro com ISBN " + isbn + " não encontrado no acervo.");
            return;
        }

        Fila<Usuario> fila = filasDeEspera.get(isbn);

        if (fila != null && !fila.filaVazia()) {
            Usuario proximoUsuario = fila.desenfileira();
            // Livro permanece indisponível — já vai direto para o próximo da fila
            System.out.println("Livro devolvido: " + livro.getTitulo()
                    + " | Emprestado automaticamente para: " + proximoUsuario.getNome());
        } else {
            livro.setDisponivel(true);
            System.out.println("Livro devolvido e disponível: " + livro.getTitulo());
        }
    }

    // Exibe a fila de espera de um livro específico
    public void listarFilaDeEspera(String isbn) {
        Livro livro = acervo.buscarPorIsbn(isbn);
        String nomeLivro = (livro != null) ? livro.getTitulo() : isbn;

        System.out.println("Fila de espera para \"" + nomeLivro + "\":");
        Fila<Usuario> fila = filasDeEspera.get(isbn);
        if (fila == null || fila.filaVazia()) {
            System.out.println("  (nenhum usuário na fila)");
        } else {
            System.out.println("  " + fila);
        }
    }

    // Cria a fila de espera para o ISBN se ainda não existir
    private void garantirFilaCriada(String isbn) {
        if (!filasDeEspera.containsKey(isbn)) {
            filasDeEspera.put(isbn, new Fila<>());
        }
    }
}
