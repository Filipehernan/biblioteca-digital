import java.util.Scanner;

// Classe principal — integra ListaDupla, Catalogo e GestorEmprestimos
public class BibliotecaDigital {

    private ListaDupla       acervo;
    private Catalogo         catalogo;
    private GestorEmprestimos gestorEmprestimos;

    public BibliotecaDigital() {
        this.acervo            = new ListaDupla();
        this.catalogo          = new Catalogo();
        this.gestorEmprestimos = new GestorEmprestimos(acervo);
    }

    // Cadastra o livro tanto no acervo navegável quanto no catálogo indexado
    public void cadastrarLivro(Livro livro) {
        acervo.insereFim(livro);
        catalogo.cadastrar(livro);
        System.out.println("Livro cadastrado: " + livro);
    }

    // =========================================================
    // Menu interativo de console
    // =========================================================
    public static void main(String[] args) {
        BibliotecaDigital biblioteca = new BibliotecaDigital();
        Scanner leitor = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        BIBLIOTECA DIGITAL            ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean executando = true;

        while (executando) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Buscar livro por ISBN");
            System.out.println("3. Listar acervo do início ao fim");
            System.out.println("4. Listar acervo do fim ao início");
            System.out.println("5. Solicitar empréstimo");
            System.out.println("6. Devolver livro");
            System.out.println("7. Ver fila de espera de um livro");
            System.out.println("8. Executar demonstração automática");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = leitor.nextLine().trim();

            switch (opcao) {
                case "1":
                    cadastrarLivroViaMenu(biblioteca, leitor);
                    break;

                case "2":
                    System.out.print("ISBN do livro: ");
                    String isbnBusca = leitor.nextLine().trim();
                    Livro encontrado = biblioteca.catalogo.buscar(isbnBusca);
                    if (encontrado != null) {
                        System.out.println("Livro encontrado: " + encontrado);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case "3":
                    System.out.println("--- Acervo (início ao fim) ---");
                    biblioteca.acervo.listarDoInicio();
                    break;

                case "4":
                    System.out.println("--- Acervo (fim ao início) ---");
                    biblioteca.acervo.listarDoFim();
                    break;

                case "5":
                    solicitarEmprestimoViaMenu(biblioteca, leitor);
                    break;

                case "6":
                    System.out.print("ISBN do livro a devolver: ");
                    String isbnDevolucao = leitor.nextLine().trim();
                    biblioteca.gestorEmprestimos.devolverLivro(isbnDevolucao);
                    break;

                case "7":
                    System.out.print("ISBN do livro: ");
                    String isbnFila = leitor.nextLine().trim();
                    biblioteca.gestorEmprestimos.listarFilaDeEspera(isbnFila);
                    break;

                case "8":
                    executarDemonstracao(biblioteca);
                    break;

                case "0":
                    System.out.println("Encerrando. Até logo!");
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        leitor.close();
    }

    private static void cadastrarLivroViaMenu(BibliotecaDigital biblioteca, Scanner leitor) {
        System.out.print("ISBN: ");
        String isbn = leitor.nextLine().trim();

        if (biblioteca.catalogo.existe(isbn)) {
            System.out.println("Já existe um livro com este ISBN.");
            return;
        }

        System.out.print("Título: ");
        String titulo = leitor.nextLine().trim();

        System.out.print("Autor: ");
        String autor = leitor.nextLine().trim();

        int anoPub = 0;
        while (anoPub <= 0) {
            System.out.print("Ano de publicação: ");
            try {
                anoPub = Integer.parseInt(leitor.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um ano válido.");
            }
        }

        biblioteca.cadastrarLivro(new Livro(isbn, titulo, autor, anoPub));
    }

    private static void solicitarEmprestimoViaMenu(BibliotecaDigital biblioteca, Scanner leitor) {
        System.out.print("ISBN do livro: ");
        String isbn = leitor.nextLine().trim();

        int matricula = 0;
        while (matricula <= 0) {
            System.out.print("Matrícula do usuário: ");
            try {
                matricula = Integer.parseInt(leitor.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite uma matrícula válida.");
            }
        }

        System.out.print("Nome do usuário: ");
        String nome = leitor.nextLine().trim();

        System.out.print("Email do usuário: ");
        String email = leitor.nextLine().trim();

        Usuario usuario = new Usuario(matricula, nome, email);
        biblioteca.gestorEmprestimos.solicitarEmprestimo(isbn, usuario);
    }

    // Demonstração automática cobrindo todos os cenários pedidos no projeto
    private static void executarDemonstracao(BibliotecaDigital biblioteca) {
        System.out.println("\n=== DEMONSTRAÇÃO AUTOMÁTICA ===\n");

        // Cadastrando livros
        Livro livro1 = new Livro("978-85-1", "Estruturas de Dados", "Goodrich", 2013);
        Livro livro2 = new Livro("978-85-2", "Clean Code",          "Martin",   2008);
        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);

        System.out.println("\n--- Acervo do início ao fim ---");
        biblioteca.acervo.listarDoInicio();

        System.out.println("\n--- Acervo do fim ao início ---");
        biblioteca.acervo.listarDoFim();

        System.out.println("\n--- Buscando '978-85-2' pelo catálogo ---");
        Livro buscado = biblioteca.catalogo.buscar("978-85-2");
        System.out.println("Encontrado: " + buscado);

        // Usuários
        Usuario ana    = new Usuario(1001, "Ana Lima",    "ana@email.com");
        Usuario bruno  = new Usuario(1002, "Bruno Costa", "bruno@email.com");
        Usuario carla  = new Usuario(1003, "Carla Souza", "carla@email.com");

        System.out.println("\n--- Empréstimo disponível ---");
        biblioteca.gestorEmprestimos.solicitarEmprestimo("978-85-1", ana);

        System.out.println("\n--- Tentativas com livro indisponível (geram fila) ---");
        biblioteca.gestorEmprestimos.solicitarEmprestimo("978-85-1", bruno);
        biblioteca.gestorEmprestimos.solicitarEmprestimo("978-85-1", carla);

        System.out.println("\n--- Fila de espera ---");
        biblioteca.gestorEmprestimos.listarFilaDeEspera("978-85-1");

        System.out.println("\n--- 1ª devolução (próximo da fila atendido automaticamente) ---");
        biblioteca.gestorEmprestimos.devolverLivro("978-85-1");

        System.out.println("\n--- Fila após 1ª devolução ---");
        biblioteca.gestorEmprestimos.listarFilaDeEspera("978-85-1");

        System.out.println("\n--- 2ª devolução (próximo da fila) ---");
        biblioteca.gestorEmprestimos.devolverLivro("978-85-1");

        System.out.println("\n--- 3ª devolução (fila vazia — livro fica disponível) ---");
        biblioteca.gestorEmprestimos.devolverLivro("978-85-1");

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }
}
