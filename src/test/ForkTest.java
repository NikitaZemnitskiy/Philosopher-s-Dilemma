import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class ForkTest {
    Fork fork0 = new Fork();
    Fork fork1 = new Fork();

    Philosopher goodTester = new Philosopher(fork0, fork1, "goodTester", 14, 26);
    Philosopher badTester = new Philosopher(fork1, fork0, "badTester", 15, 25);
    @org.junit.jupiter.api.Test
    void takeFork() throws InterruptedException {
        fork0.owner = badTester;
        fork1.owner = badTester;
        try {
            goodTester.eat();
            Assert.fail();
        }
        catch (IllegalStateException e){
            Assert.assertFalse(e.equals("goodTester tried to take a fork, but this fork already had an owner - badTester"));
        }

    }

    @org.junit.jupiter.api.Test
    void leftFork() {
    }
}
