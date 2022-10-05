import java.util.concurrent.Semaphore;

public class Main {

    static int[] buffer = new int[5];

    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(2);

        Produtor produtor1 = new Produtor(1, semaforo);
        new Thread(produtor1, "Produtor 1").start();

        Produtor produtor2 = new Produtor(2, semaforo);
        new Thread(produtor2, "Produtor 2").start();

        Produtor produtor3 = new Produtor(3, semaforo);
        new Thread(produtor3, "Produtor 3").start();

        Consumidor consumidor1 = new Consumidor(1, semaforo);
        new Thread(consumidor1, "Consumidor 1").start();

        Consumidor consumidor2 = new Consumidor(2, semaforo);
        new Thread(consumidor2, "Consumidor 2").start();

//        int numeroDePermissoes = 2;
//        int numeroDeProcessos = 6;
//
//        Semaphore semaphore = new Semaphore(numeroDePermissoes);
//        ProcessadorThread[] processos = new ProcessadorThread[numeroDeProcessos];
//
//        for (int i = 0; i < numeroDeProcessos; i++) {
//            processos[i] = new ProcessadorThread(i, semaphore);
//            processos[i].start();
//        }
    }

    public static void mostrarBuffer() {
        for (int i = 0; i < buffer.length; i++) {
            System.out.println("Posição " + i + ": " + buffer[i]);
        }
    }
}
