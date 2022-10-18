import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {
    private Integer idConsumidor;
    private Semaphore semaforo;
    private JLabel textoConsumidor, bufferAtual;
    private Boolean executar;

    public Consumidor(Integer idConsumidor, Semaphore semaforo, JLabel textoProdutor, JLabel buffer, Boolean executar) {
        this.idConsumidor = idConsumidor;
        this.semaforo = semaforo;
        this.textoConsumidor = textoProdutor;
        this.bufferAtual = buffer;
        this.executar = executar;
    }

    @Override
    public synchronized void run() {
        Random random = new Random();

        while (this.getExecutar()) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
                semaforo.acquire();
                int posicao = random.nextInt(5);

                if (Tela.buffer[posicao] != 0) {
                    System.out.println("ID consumidor: " + idConsumidor + "; " +
                            "Número: " + Tela.buffer[posicao] + "; Posição: " + posicao);
                    setTexto("Adicionou " + 0 + " na posição " + (posicao + 1));
                    Tela.buffer[posicao] = 0;

                } else {
                    System.out.println("Posição " + posicao + " igual a 0. " +
                            "ID consumidor: " + idConsumidor);
                    setTexto("Posição " + (posicao + 1) + " já preenchida");
                }
                Thread.sleep((long) (Math.random() * 1000));
                mostrarBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }

    public void setTexto(String texto) {
        this.textoConsumidor.setText(texto);
    }

    public void mostrarBuffer() {
        int[] buffer = Tela.buffer;
        bufferAtual.setText("{ " + buffer[0] + ", " + buffer[1] + ", " + buffer[2] + ", " + buffer[3] + ", " + buffer[4] + "}");
    }

    public Boolean getExecutar() {
        return executar;
    }

    public void setExecutar(Boolean executar) {
        this.executar = executar;
    }
}
