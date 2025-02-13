import java.util.Random;

/**
 * Classe que representa um Filósofo que alterna entre pensar e comer.
 * Implementa Runnable para que cada filósofo seja executado em uma thread separada.
 */
public class Philosopher implements Runnable {
    /**
     * Identificador do filósofo
     */
    private final int philosopherId;
    /**
     * Referência para a mesa compartilhada contendo os garfos
     */
    private final Table table;
    /**
     * Gerador de números aleatórios para simular os tempos de pensar e comer
     */
    private final Random random;

    /**
     * Construtor do Filósofo.
     *
     * @param id    Identificador do filósofo.
     * @param table Referência para a mesa compartilhada.
     */
    public Philosopher(int id, Table table) {
        this.philosopherId = id;
        this.table = table;
        this.random = new Random();
    }

    /**
     * Método executado pela thread do filósofo.
     * Ele alterna entre pensar e comer indefinidamente.
     */
    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }

    /**
     * Simula o tempo que o filósofo passa pensando.
     * A thread dorme por um período aleatório entre 0 e 5000 milissegundos.
     */
    private void think() {
        System.out.println("Philosopher " + philosopherId + " thinking...");
        try {
            Thread.sleep(random.nextInt(5000)); // Tempo aleatório para pensar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Simula o processo de comer do filósofo.
     * O filósofo precisa adquirir os dois garfos necessários para comer.
     * Para evitar deadlock, ele adquire os garfos na ordem: primeiro o de menor índice e depois o de maior índice.
     * Ao final, os garfos são liberados.
     */
    private void eat() {
        // Índice do garfo à esquerda e à direita
        int left = philosopherId;
        int right = (philosopherId + 1) % table.forks.length;

        System.out.println("Philosopher " + philosopherId + " trying to take forks...");

        // Determina a ordem de aquisição dos garfos para evitar deadlock:
        // sempre adquire primeiro o garfo com menor índice.
        int firstFork = Math.min(left, right);
        int secondFork = Math.max(left, right);

        // Adquire os dois garfos (locks)
        table.forks[firstFork].lock();
        table.forks[secondFork].lock();

        try {
            System.out.println("Philosopher " + philosopherId + " eating...");
            Thread.sleep(random.nextInt(10000)); // Tempo aleatório para comer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Libera os garfos ao sair do bloco (similar ao lock_guard em C++)
            table.forks[secondFork].unlock();
            table.forks[firstFork].unlock();
        }

        System.out.println("Philosopher " + philosopherId + " released forks.");
    }
}
