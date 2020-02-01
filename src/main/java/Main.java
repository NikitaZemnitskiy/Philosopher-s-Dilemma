
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Filosoph nikita = new Filosoph(fork1,fork2,"nikita");
        Filosoph aristitel = new Filosoph(fork2, fork3,"aristotel");
        Filosoph platon = new Filosoph(fork3,fork4,"platon");
        Filosoph schopenhauer = new Filosoph(fork4,fork5,"shopenhauer");
        Filosoph nicsche = new Filosoph(fork5, fork1,"nitsche");


        Thread thread1 = new Thread(aristitel);
        Thread thread2 = new Thread(platon);
        Thread thread3 = new Thread(nicsche);
        Thread thread4 = new Thread(schopenhauer);
        Thread thread5 = new Thread(nikita);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        Thread.sleep(15000);
        System.out.println("Platon ate " + platon.eatTimes + " times");
        System.out.println("Aristotel ate " + aristitel.eatTimes + " times");
        System.out.println("Nitsche ate " + nicsche.eatTimes + " times");
        System.out.println("Schopenhauer ate " + schopenhauer.eatTimes + " times");
        System.out.println("Nikita ate " + nikita.eatTimes + " times");
        System.exit(0);

    }
}
