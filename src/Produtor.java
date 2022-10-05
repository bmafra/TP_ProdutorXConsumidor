import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Produtor implements Runnable {
    private Integer idProdutor;
    private Semaphore semaforo;

    public Produtor(Integer idProdutor, Semaphore semaforo) {
        this.idProdutor = idProdutor;
        this.semaforo = semaforo;
    }

    @Override
    public synchronized void run() {
        Random random = new Random();

        while (true) {
            try {
                semaforo.acquire();
                int posicao = random.nextInt(5);
                if (Main.buffer[posicao] == 0) {
                    int num = random.nextInt(10);
                    Main.buffer[posicao] = num;
                    System.out.println("ID produtor: " + idProdutor + "; " +
                            "Número: " + num + "; Posição: " + posicao);
                } else {
                    System.out.println("Posição " + posicao + " diferente a 0. " +
                            "ID produtor: " + idProdutor);
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
