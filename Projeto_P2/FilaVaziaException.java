// Exceção lançada quando se tenta operar em uma fila sem elementos
public class FilaVaziaException extends RuntimeException {

    public FilaVaziaException(String mensagem) {
        super(mensagem);
    }
}
