import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Produtor implements Runnable {
    private Integer idProdutor;
    private Semaphore semaforo;
    private JLabel textoProdutor, bufferAtual;
    private Boolean executar;

    public Produtor(Integer idProdutor, Semaphore semaforo, JLabel textoProdutor, JLabel buffer, Boolean executar) {
        this.idProdutor = idProdutor;
        this.semaforo = semaforo;
        this.textoProdutor = textoProdutor;
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

                if (Tela.buffer[posicao] == 0) {
                    int num = random.nextInt(10);
                    Tela.buffer[posicao] = num;
                    System.out.println("ID produtor: " + idProdutor + "; " +
                            "Número: " + num + "; Posição: " + posicao);
                    setTexto("Adicionou " + num + " na posição " + (posicao + 1));

                } else {
                    System.out.println("Posição " + posicao + " diferente a 0. " +
                            "ID produtor: " + idProdutor);
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
        this.textoProdutor.setText(texto);
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
