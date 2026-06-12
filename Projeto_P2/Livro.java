public class Livro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anoPub;
    private boolean disponivel;

    public Livro(String isbn, String titulo, String autor, int anoPub) {
        this.isbn      = isbn;
        this.titulo    = titulo;
        this.autor     = autor;
        this.anoPub    = anoPub;
        this.disponivel = true;
    }

    // --- Getters ---
    public String getIsbn()      { return isbn; }
    public String getTitulo()    { return titulo; }
    public String getAutor()     { return autor; }
    public int    getAnoPub()    { return anoPub; }
    public boolean isDisponivel(){ return disponivel; }

    // --- Setters ---
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public void setTitulo(String titulo)          { this.titulo = titulo; }
    public void setAutor(String autor)            { this.autor = autor; }

    @Override
    public String toString() {
        String status = disponivel ? "DISPONIVEL" : "EMPRESTADO";
        return String.format("[%s] %s - %s (%d) [%s]", isbn, titulo, autor, anoPub, status);
    }

    // Dois livros são iguais se tiverem o mesmo ISBN
    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (outro == null || getClass() != outro.getClass()) return false;
        Livro outroLivro = (Livro) outro;
        return this.isbn.equals(outroLivro.isbn);
    }
}
