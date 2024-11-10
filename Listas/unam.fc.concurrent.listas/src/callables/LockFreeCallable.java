package callables;

import java.util.concurrent.Callable;

import lists.NonBlocking;

public class LockFreeCallable implements Callable<Boolean>{
    private String item;
    private NonBlocking locklist;//En este caso tiene como atributo a un tipo de lista LockFree
    private int num;

    public LockFreeCallable(String item, NonBlocking locklist, int num){
        this.item = item;
        this.locklist= locklist;
        this.num = num;
    }

    @Override
    public Boolean call(){
        try {
            if (this.num > 25 && this.num<100) {//Probabilidad de ejecutar contains de 75%
            this.locklist.contains(this.item);
            //System.out.println("Thead name: "+ Thread.currentThread().getName()+" contains: "+this.item);//Para ver como se ejecutan

            }
            if (this.num < 13) {//Probabilidad de ejecutar add
                //System.out.println("Thead name: "+ Thread.currentThread().getName()+" add: "+this.item);//Para ver como se ejecutan
                this.locklist.add(this.item);
            }
            else{
            if (this.num < 25){//Probabilidad de ejecutar remove
                //System.out.println("Thead name: "+ Thread.currentThread().getName()+" remove: "+this.item);//Para ver como se ejecutan
                this.locklist.remove(this.item);
            }}
            
            if (this.num == 110){//Para que un hilo imprima
                
                try {
                    Thread.sleep(1000); // Sleeping for 100ms
                    }
                    catch (InterruptedException e) {
                    System.out.format("Interrupted Exception: " + e.getMessage());
                    e.printStackTrace();
                    }
                this.locklist.printlist();
            }


            return true;
            
        } catch (Exception e) {
            return false;
            
        }
        
    }
}
