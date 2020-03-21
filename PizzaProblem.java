import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vijini
 */
 
public class PizzaProblem {
    
    public static void main(String[] args) {
        StudyGroup studyGroup = new StudyGroup();   //Create student group
        int students = 6;
        
        for (int i = 0; i < students; i++) {
            new Student(studyGroup).start();        //Create Student threads
        }
        
        new PizzaDelivery(studyGroup).start();      //Create Pizza Delivery thread
    }
}


//Study group class
class StudyGroup {

    int S = 5;
    int slices = 0;     //No. of pizza slices

    private final Lock mutex = new ReentrantLock();     //Lock to ensure one Student can take a slice at a time

    private final Condition orderPizza = mutex.newCondition();      //Condition to order pizza
    private final Condition deliverPizza = mutex.newCondition();    //Condition to deliver pizza

    private boolean firstToSee = true;                              //First student to see group is out of pizza

    void beginStudying() throws InterruptedException {
        mutex.lock();
        if (slices > 0) {
            --slices;                   //Decrease no. of slices. Student picks up a slice of pizza.
            //Student takes a slice and starts studying
            System.out.println("Student " + Thread.currentThread().getId() + " took a slice of pizza and is studying");
        } else {
            if (firstToSee) {
                //First student to see that the group is out of pizza
                System.out.println("Group out of pizza. Student " + Thread.currentThread().getId() + " calls Kamal's Pizza");
                orderPizza.signal();        //Call Kamal's Pizza and wake up delivery thread
                firstToSee = false;
            }
            System.out.println("Student " + Thread.currentThread().getId() + " sleeps");
            deliverPizza.await();           //Student sleeps till pizza is delivered
        }
        mutex.unlock();
        
    }

    void checkOrder() throws InterruptedException {
        mutex.lock();
        slices = S;                         //Fill plate with new pizza
        System.out.println("Pizza delivered");
        firstToSee = true;
        System.out.println("Wake up sleeping students\n");
        deliverPizza.signalAll();           //Wake up all sleeping Students
        orderPizza.await();                 //PizzaDelivery goes to sleep
        mutex.unlock();
        
    }
}


//Pizza Delivery class
class PizzaDelivery extends Thread{
    
    private StudyGroup studyGroup = new StudyGroup();

    public PizzaDelivery(StudyGroup sg) {
        this.studyGroup = sg;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                studyGroup.checkOrder();
                sleep(5000);                        //Wait till pizza is delivered
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}


//Student class
class Student extends Thread{

    StudyGroup studyGroup = new StudyGroup();
    private Random r = new Random(); 
    
    public Student(StudyGroup sg) {
        this.studyGroup = sg;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                studyGroup.beginStudying();
                sleep(r.nextInt(10000));            //Wait till pizza slice finishes
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
