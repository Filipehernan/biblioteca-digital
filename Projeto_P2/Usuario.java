public class Usuario {

    private int    matricula;
    private String nome;
    private String email;

    public Usuario(int matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome      = nome;
        this.email     = email;
    }

    // --- Getters ---
    public int    getMatricula() { return matricula; }
    public String getNome()      { return nome; }
    public String getEmail()     { return email; }

    // --- Setter ---
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("[%d] %s <%s>", matricula, nome, email);
    }

    // Dois usuários são iguais se tiverem a mesma matrícula
    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (outro == null || getClass() != outro.getClass()) return false;
        Usuario outroUsuario = (Usuario) outro;
        return this.matricula == outroUsuario.matricula;
    }
}
