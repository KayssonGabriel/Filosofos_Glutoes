import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe que implementa a mesa do jantar dos filósofos contendo os garfos.
 * Cada garfo é representado por um objeto Lock (semáforo binário).
 */
public class Table {
    /**
     * Vetor de garfos compartilhados entre os filósofos
     */
    public final Lock[] forks;

    /**
     * Construtor que inicializa a mesa com 5 garfos.
     */
    public Table() {
        forks = new ReentrantLock[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }
    }
}
