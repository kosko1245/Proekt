package bg.tu_varna.sit.b4.f22621694.Commands;
/**
 * Интерфейс за командите в приложението.
 */
public interface Command {
    void execute(String[] args);
    String getUsage();
}
