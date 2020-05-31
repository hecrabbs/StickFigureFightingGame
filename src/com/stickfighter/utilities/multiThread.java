package com.stickfighter.utilities;

import java.util.LinkedList;
import java.util.List;

public class multiThread extends ThreadGroup{
    // This is a group of threads
    private static IDAssigner multiID=new IDAssigner(1);
    private boolean isAlive;
    private List<Runnable> queue;
    private int id;

    public multiThread(int num){
        super("multiThread: "+multiID.next());
        setDaemon(true);//Daemon thread
        this.id=multiID.getBaseID();
        queue=new LinkedList<>();
        isAlive=true;
        for(int i=0;i<num;i++){
            new additionalThread(this).start();
        }
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        while(queue.size()==0){
            if(!isAlive){
                return null;
            }
            wait();
        }
        return queue.remove(0);
    }

    public void join(){
        synchronized (this){
            isAlive=false;
            notifyAll();
        }
        Thread[] threads=new Thread[activeCount()];
        int cnt=enumerate(threads);

        for(int i=0;i<cnt;i++){
            try{
                threads[i].join();
            } catch(InterruptedException e){

            }
        }
    }

    public synchronized void close(){
        if(!isAlive) return;
        isAlive=false;
        queue.clear();
        interrupt();
    }

    public synchronized void runTask(Runnable task){
        if(!isAlive){
            throw new IllegalStateException("multiThread: "+id+" is dead");
        }
        if(task!=null){
            queue.add(task);
            notify();
        }
    }
}
