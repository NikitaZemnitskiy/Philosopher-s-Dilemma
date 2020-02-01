public class Filosoph implements Runnable {
    int eatTimes = 0;
    Fork LeftHandFork;
    Fork RighrHandFork;
    String name;

    public Filosoph(Fork leftHandFork, Fork righrHandFork, String name) {
        LeftHandFork = leftHandFork;
        RighrHandFork = righrHandFork;
        this.name = name;
    }



    public void run() {
            while (true) {
                synchronized (LeftHandFork) {
                      synchronized (RighrHandFork){
                          if(!RighrHandFork.isOwned && !LeftHandFork.isOwned ){
                              RighrHandFork.takeFork();
                              LeftHandFork.takeFork();
                          }
                          else {
                              continue;
                          }
                      }
                }
                    try {
                        Thread.sleep(500);
                        eatTimes++;
                        System.out.println(name +" stoped dinner and he is thinking now");
                           LeftHandFork.letFork();
                           RighrHandFork.letFork();
                           Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

