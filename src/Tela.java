import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Tela extends JFrame implements Runnable {
    private JButton stopButton;
    private JButton startButton;
    private JPanel inputs;
    private JPanel Botoes;
    private JPanel tela;
    private JLabel textoProdutor3;
    private JLabel textoProdutor1;
    private JLabel textoConsumidor2;
    private JLabel textoConsumidor1;
    private JLabel bufferAtual;
    private JLabel textoProdutor2;
    public static volatile int[] buffer = {0, 0, 0, 0, 0};
    public Semaphore semaforo = new Semaphore(2);

    public Tela () {
        criarComponentes();

        Produtor produtor1 = new Produtor(1, semaforo, textoProdutor1, bufferAtual, true);
        new Thread(produtor1, "produtor1").start();

        Produtor produtor2 = new Produtor(2, semaforo, textoProdutor2, bufferAtual, true);
        new Thread(produtor2, "produtor2").start();

        Produtor produtor3 = new Produtor(3, semaforo, textoProdutor3, bufferAtual, true);
        new Thread(produtor3, "produtor3").start();

        Consumidor consumidor1 = new Consumidor(1, semaforo, textoConsumidor1,bufferAtual, true);
        new Thread(consumidor1, "Consumidor 1").start();

        Consumidor consumidor2 = new Consumidor(2, semaforo, textoConsumidor2,bufferAtual, true);
        new Thread(consumidor2, "Consumidor 2").start();

        startButton.addActionListener( e -> {
            produtor1.setExecutar(true);
            produtor1.run();

            produtor2.setExecutar(true);
            produtor2.run();

            produtor3.setExecutar(true);
            produtor3.run();

            consumidor1.setExecutar(true);
            consumidor1.run();

            consumidor2.setExecutar(true);
            consumidor2.run();
        });

        stopButton.addActionListener(e -> {
            produtor1.setExecutar(false);
            produtor1.run();

            produtor2.setExecutar(false);
            produtor2.run();

            produtor3.setExecutar(false);
            produtor3.run();

            consumidor1.setExecutar(false);
            consumidor1.run();

            consumidor2.setExecutar(false);
            consumidor2.run();
        });
    }

    private void criarComponentes(){
        setContentPane(tela);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        run();
        pack();
    }

    @Override
    public void run() {
        if(!isVisible()){
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela ja está aberta!");
        }
    }

    public static void main(String[] args) {
        Tela tela = new Tela();
    }
}
