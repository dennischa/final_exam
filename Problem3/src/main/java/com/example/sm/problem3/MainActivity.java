package com.example.sm.problem3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    static boolean key = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<CustomerThread> list = new ArrayList<CustomerThread>();
        Manager manager = new Manager();

        for(int i = 0 ; i < 10 ; i++){
            Customer customer = new Customer("Customer" + i);
            CustomerThread ct = new CustomerThread(customer);
            list.add(ct);
            manager.add_customer(customer);
            ct.start();
        }


        for(CustomerThread ct : list){
            try {
                if(ct.chance != 0)
                    throw new InterruptedException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        manager.sort();

        MyBaseAdapter adapter = new MyBaseAdapter(this, manager.list);
        ListView listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);


    }
}

class CustomerThread extends Thread{

    Customer customer;
    int chance = 10;

    CustomerThread(Customer customer){
        this.customer = customer;
    }

    public void start(){
        if( MainActivity.key && chance > 0){
            MainActivity.key =false;
            customer.work();
            chance--;
            MainActivity.key =true;
        }
    }
    // need something here
}

abstract class Person{

    static int money = 100000;
    int spent_money = 0;
    abstract void work();

}


class Customer extends Person{

    String name;
    Customer(String name){
        this.name = name;
    }

    public void work(){
        Random a = new Random();
        int tmp =  a.nextInt(1000);
        money -= tmp;
        spent_money += tmp;
    }

    // need something here
}


class Manager extends Person{
    ArrayList <Customer> list = new ArrayList<Customer>();

    void add_customer(Customer customer) {
        list.add(customer);
    }

    void sort(){
        for(int i = 0 ; i < list.size() - 1; i++)
        {
            for(int j = i + 1 ; j < list.size() ; j++){
                if(list.get(i).spent_money > list.get(j).spent_money)
                {
                    int tmp = list.get(i).spent_money;
                    list.get(i).spent_money = list.get(j).spent_money;
                    list.get(j).spent_money = tmp;
                }
            }
        }
    }

    @Override
    void work() {
        sort();
    }
}

// need something here

