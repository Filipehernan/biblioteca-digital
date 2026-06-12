public class TesteListaDupla {

    public static void main(String[] args) {
        System.out.println("=== TESTE DA LISTA DUPLAMENTE ENCADEADA ===\n");

        ListaDupla acervo = new ListaDupla();

        // Inserindo livros no final
        acervo.insereFim(new Livro("978-1", "Estruturas de Dados", "Goodrich", 2013));
        acervo.insereFim(new Livro("978-2", "Algoritmos",          "Cormen",   2009));
        acervo.insereFim(new Livro("978-3", "Clean Code",          "Martin",   2008));

        // Inserindo livro no início
        acervo.insereInicio(new Livro("978-0", "Lógica de Programação", "Forbellone", 2022));

        System.out.println("--- Listando do início ao fim ---");
        acervo.listarDoInicio();

        System.out.println("\n--- Listando do fim ao início ---");
        acervo.listarDoFim();

        System.out.println("\n--- Buscando por ISBN '978-2' ---");
        Livro encontrado = acervo.buscarPorIsbn("978-2");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Livro não encontrado.");
        }

        System.out.println("\n--- Removendo o primeiro e o último ---");
        Livro primeiro = acervo.removePrimeiro();
        Livro ultimo   = acervo.removeUltimo();
        System.out.println("Removido do início: " + primeiro);
        System.out.println("Removido do fim:    " + ultimo);

        System.out.println("\n--- Acervo restante (tamanho: " + acervo.tamanho() + ") ---");
        acervo.listarDoInicio();
    }
}
