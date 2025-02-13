# Jantar dos filósofos

Simulação em Java do problema clássico de concorrência. Cinco threads alternam entre pensar e comer, compartilhando cinco garfos. Cada filósofo precisa obter dois garfos adjacentes e sempre adquire primeiro o de menor índice. Essa ordem impede a espera circular pelos garfos.

## Estrutura

- [DiningPhilosophers.java](src/DiningPhilosophers.java) cria a mesa e inicia as cinco threads.
- [Table.java](src/Table.java) representa os garfos com `ReentrantLock`.
- [Philosopher.java](src/Philosopher.java) simula o pensamento, a aquisição dos garfos, a refeição e a liberação dos locks.

## Como executar

Com um JDK instalado, compile e execute na raiz:

```bash
javac -encoding UTF-8 -d build src/DiningPhilosophers.java src/Philosopher.java src/Table.java
java -cp build DiningPhilosophers
```

O terminal registra a atividade das threads em uma ordem que varia a cada execução. A simulação roda continuamente. Pressione Ctrl+C para encerrá-la no terminal.
