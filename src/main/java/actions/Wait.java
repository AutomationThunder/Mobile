package actions;

import org.apache.log4j.Logger;

public class Wait {

    private static Logger log=Logger.getLogger(Wait.class);

    public void forSeconds(int seconds) {
        try{
            Thread.sleep(seconds*1000);
        }catch (InterruptedException e){
            log.warn("Failed to execute thread.sleep()");
        }
    }
}
