import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {
    private Integer idConsumidor;
    private Semaphore semaforo;

    public Consumidor(Integer idConsumidor, Semaphore semaforo) {
        this.idConsumidor = idConsumidor;
        this.semaforo = semaforo;
    }

    @Override
    public synchronized void run() {
        Random random = new Random();

        while (true) {
            try {
                semaforo.acquire();
                int posicao = random.nextInt(5);
                if (Main.buffer[posicao] != 0) {
                    System.out.println("ID consumidor: " + idConsumidor + "; " +
                            "Número: " + Main.buffer[posicao] + "; Posição: " + posicao);
                    Main.buffer[posicao] = 0;
                } else {
                    System.out.println("Posição " + posicao + " igual a 0. " +
                            "ID consumidor: " + idConsumidor);
                }
                Thread.sleep((long) (Math.random() * 1000));
                Main.mostrarBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }
}
