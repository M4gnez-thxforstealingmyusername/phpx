package pl.phpx;

public class CompilationError extends RuntimeException {
    public CompilationError(String message) {
        super(message);
    }
}
