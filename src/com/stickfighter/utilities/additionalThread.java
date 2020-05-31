package com.stickfighter.utilities;

public class additionalThread extends Thread{
    private static IDAssigner threadID=new IDAssigner(1);
    private multiThread mult;

    public additionalThread(multiThread mult){
        super(mult, "additionalThread: "+threadID.next());
        this.mult=mult;
    }

    //@Override
    public void run(){
        while(!isInterrupted()){
            Runnable task=null;
            try {
                task=mult.getTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(task==null){ return; }
            try{
                task.run();
            } catch(Throwable t){
                mult.uncaughtException(this,t);
            }
        }

    }


}
