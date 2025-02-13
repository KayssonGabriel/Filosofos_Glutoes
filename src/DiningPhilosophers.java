/**
 * Classe principal que inicia a execução do jantar dos filósofos.
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        System.out.println("THE PHILOSOPHERS DINNER\n");

        // Cria a mesa com os garfos compartilhados
        Table table = new Table();
        Thread[] threads = new Thread[5];

        // Cria e inicia as threads para os 5 filósofos
        for (int i = 0; i < 5; i++) {
            Philosopher philosopher = new Philosopher(i, table);
            threads[i] = new Thread(philosopher, "Philosopher-" + i);
            threads[i].start();
        }

        // Aguarda todas as threads terminarem (neste exemplo, elas executam indefinidamente)
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
            Thread.currentThread().interrupt();
        }
    }
}
