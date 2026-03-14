package Multithreading;

import java.util.concurrent.*;
import java.util.*;


class OrderService {
    public static void main(String[] args){
        System.out.println("Placing Order");
        sendSMS();
        System.out.println("task 1 done");
        sendEmail();
        System.out.println("task 2 done");
        String eta  = calculateETA();
        System.out.println("Order placed. ETA " + eta);
        System.out.println("task 3 complement");

        Thread thread = new Thread(() -> sendEmail());
        thread.start();
    }

    private static void sendSMS(){
        try {
            Thread.sleep(2000); //simulate delay
            System.out.println("SMS send");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private static void sendEmail() {
        try {
            Thread.sleep(3000); //simulate delay
            System.out.println("Email send");
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    private static String calculateETA(){
        try {
            Thread.sleep(3000); //simulate delay
            System.out.println("Email send");
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return "25 minutes";
    }
}
class SMSThread extends Thread {
    public void run(){
        try{
            Thread.sleep(2000);
            System.out.println("SMS sent using thread");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}


class EmailThread extends Thread {
    public void run(){
        try{
            Thread.sleep(4000);
            System.out.println("EMAIL sent using thread");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class SMSThreadRunnable implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(2000);
            System.out.println("SMS sent using thread");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class EmailThreadRunnable implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(3000);
            System.out.println("Email sent using thread");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}


class ETACalculator implements Callable<String> {
    private final String location;

    public ETACalculator(String location){
        this.location = location;
    }

    @Override
    public String call() throws Exception {
        System.out.println("[ " + Thread.currentThread().getName() +" ] Calculating ETA to: "+location);
        Thread.sleep(2000);
        return "ETA to " + location + ": 20 minutes";
    }
}




public class Main{
    public static void main(String[] args){


        FutureTask<String> etaThreadRunnable = new FutureTask<>(new ETACalculator("VA"));
        Thread etaThread = new Thread(etaThreadRunnable); // take runnable not callable 
        etaThread.start();

        try {
            String eta = (String)etaThreadRunnable.get();
            System.out.println("ETA is: "+ eta);
        }catch(Exception e){

        }


        // METHOD 2 - Direct instance of the thread
        // Thread smsThread = new Thread(new SMSThreadRunnable());
        // Thread emailThread = new Thread(new EmailThreadRunnable());


        // smsThread.run();
        // emailThread.run();






        // METHOD 1
        // SMSThread smsThread = new SMSThread();
        // EmailThread emailThread = new EmailThread();
        // System.out.println("task started");
        // smsThread.start();
        // System.out.println("Task 1 ongoing");
        // emailThread.start();
        // System.out.println("Task 2 ongoing");

        // try {
        //     smsThread.join();
        //     emailThread.join();
        //     System.out.println("task done");
        // } catch (Exception e) {
            
        // }
    }
}

