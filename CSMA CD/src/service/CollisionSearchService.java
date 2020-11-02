package service;

public class CollisionSearchService {

    final public int PROBABILITY=30;
    final public int delay=500;

    public void checkFreeChannel() {
       while (true) {
           if (actionHasHappened()) {
               try {
                   Thread.sleep(delay);
               } catch (InterruptedException e) {
                   System.out.println("Thread was interrupted");
               }
           }
           else {
               break;
           }
       }
    }

    public int fixCollision() {
        final int minTimeInterval=50;
        final int maxPower=10;
        int attempt;
        for(attempt=0; attempt<16; ++attempt) {
            int power=attempt;
            if (attempt>maxPower) {
                power=maxPower;
            }

            double randTime=getRand(0, (int) Math.pow(2,power));
            try {
                Thread.sleep((long) (randTime*minTimeInterval));
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
            if (!collisionHappened()) {
                break;
            }
        }

        return attempt+1;
    }

    public boolean collisionHappened() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
        return actionHasHappened();
    }

    private boolean actionHasHappened() {
        double rand=getRand(1,100);
        return rand<PROBABILITY;
    }

    private double getRand(int left, int right) {
        return Math.random()*right+left;
    }
}
