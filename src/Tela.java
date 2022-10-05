import javax.swing.*;

public class Tela extends JFrame implements Runnable {
    private JButton stopButton;
    private JButton startButton;
    private JPanel Botoes;
    private JPanel Tela;

    public Tela () {
        criarComponentes();
    }

    private void criarComponentes(){
        setContentPane(Tela);
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
}
