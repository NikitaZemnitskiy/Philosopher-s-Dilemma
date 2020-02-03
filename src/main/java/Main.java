
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Filosoph nikita = new Filosoph(fork1,fork2,"nikita",10,20);
        Filosoph aristitel = new Filosoph(fork2, fork3,"aristotel",15,25);
        Filosoph platon = new Filosoph(fork3,fork4,"platon", 20,27);
        Filosoph schopenhauer = new Filosoph(fork4,fork5,"shopenhauer",5,22);
        Filosoph nicsche = new Filosoph(fork5, fork1,"nitsche",10,20);


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
        Thread.sleep(12000);
        System.out.println("Platon ate " + platon.getEatTimes() + " times");
        System.out.println("Aristotel ate " + aristitel.getEatTimes() + " times");
        System.out.println("Nitsche ate " + nicsche.getEatTimes() + " times");
        System.out.println("Schopenhauer ate " + schopenhauer.getEatTimes() + " times");
        System.out.println("Nikita ate " + nikita.getEatTimes() + " times");
        System.out.println("--------------------------------");
        System.out.println("Platon ate " + platon.getEatTime() + " ms");
        System.out.println("Aristotel ate " + aristitel.getEatTime() + " ms");
        System.out.println("Nitsche ate " + nicsche.getEatTime() + " ms");
        System.out.println("Schopenhauer ate " + schopenhauer.getEatTime() + " ms");
        System.out.println("Nikita ate " + nikita.getEatTime() + " ms");
        long a = nikita.getEatTime()+platon.getEatTime()+nicsche.getEatTime()+schopenhauer.getEatTime()+aristitel.getEatTime();
        System.out.println("All philosophies spend "+a+" ms on the dinner today");
        System.exit(0);

    }
}
