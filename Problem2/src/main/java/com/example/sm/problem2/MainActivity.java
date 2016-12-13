package com.example.sm.problem2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyBaseAdapter adapter;
    ListView listview;
    ArrayList<Employee> emp_list;

    String name, age, salary;
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //need something here
        emp_list = new ArrayList<Employee>();
        Employee tmp = new Employee("차경준", 22, 10000);

        adapter = new MyBaseAdapter(this, emp_list);
        listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener)adapter);
        //adapter.add(tmp);
       // emp_list.add(tmp);
    }
    @Override
    public void onClick(View v){
        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        EditText edit_salary = (EditText) findViewById(R.id.edit_salary);

        name = edit_name.getText().toString();
        age = edit_age.getText().toString();
        salary = edit_salary.getText().toString();
        employee= new Employee(name,Integer.parseInt(age), Integer.parseInt(salary));

        Log.d("dd", Integer.toString(adapter.getCount()));
        switch (v.getId()){
            case R.id.btn_inc:
                adapter.mData.get(adapter.selected_position).increase();
                break;
            case R.id.btn_dec:
                adapter.mData.get(adapter.selected_position).decrease();
                break;
            case R.id.btn_store:
                adapter.add(employee);
                break;
            case R.id.btn_modify:
                adapter.delete(adapter.selected_position);
                adapter.add(employee);
                break;
            case R.id.btn_delete:
                adapter.delete(adapter.selected_position);
                break;
        }
    }
}

interface Payment {
    void increase();
    void decrease();
}
