package com.example.sqlitepractise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlitepractise.model.Product;

public class MainActivity extends AppCompatActivity {
EditText name,price,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editTextName);
        price = (EditText) findViewById(R.id.editTextPhone);
        id = (EditText) findViewById(R.id.editTextStreet);

    }
public void run(View v){
        DBhelper dBhelper=new DBhelper(getApplicationContext());
    Product product=new Product(name.getText().toString(),price.getText().toString());
        dBhelper.addProduct(product);
}
    public void data(View v){
        DBhelper dBhelper=new DBhelper(getApplicationContext());
        Product p=dBhelper.getproduct(Integer.parseInt(name.getText().toString()));
        name.setText(p.getProduct_name());
        price.setText(p.getProduct_price());
    }
}
