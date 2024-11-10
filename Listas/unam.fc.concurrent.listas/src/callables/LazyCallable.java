package callables;


import java.util.concurrent.Callable;

import lists.LazyList;


public class LazyCallable implements Callable<Boolean>{
    private String item;
    private LazyList lazylist;//En este caso tiene como atributo a un tipo de lista CoarseGRained
    private int num;
    
    public LazyCallable(String item, LazyList lazyList, int num){
        this.item = item;
        this.lazylist = lazylist;
        this.num = num;
    }
    @Override
    public Boolean call(){
        try {
      //      System.out.println("Here___ call try");

            if (this.num > 25 && this.num<100) {//Probabilidad de ejecutar contains de 75%
                //System.out.println("Here___if contains");

                this.lazylist.contains(this.item);
                //System.out.println("Thead name: "+ Thread.currentThread().getName()+" contains: "+this.item); //Para ver como se ejecutan
    
            }
            if (this.num < 13) {//Probabilidad de ejecutar add
                //System.out.println("Thead name: "+ Thread.currentThread().getName()+" add: "+this.item); //Para ver como se ejecutan
                this.lazylist.add(this.item);
            }
            else{
            if (this.num < 25){//Probabilidad de ejecutar remove
                //System.out.println("Thead name: "+ Thread.currentThread().getName()+" remove: "+this.item); //Para ver como se ejecutan
                this.lazylist.remove(this.item);
            }}
            
            if (this.num == 110){//Para que un hilo imprima
                
                try {
                    Thread.sleep(10); // Sleeping for 100ms
                    }
                    catch (InterruptedException e) {
                    System.out.format("Interrupted Exception: " + e.getMessage());
                    e.printStackTrace();
                    }
                    System.out.println("Here before print");
                this.lazylist.printList();
            }
            return true;
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
    }
}
