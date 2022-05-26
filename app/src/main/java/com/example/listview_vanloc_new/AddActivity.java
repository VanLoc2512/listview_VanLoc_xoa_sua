package com.example.listview_vanloc_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class AddActivity extends AppCompatActivity {
    EditText addProductName,addUnit,addPrice;
    Product products;
    int position;
    public final static String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        addProductName = (EditText)findViewById(R.id.addProductName);
        addUnit = (EditText)findViewById(R.id.addUnit);
        addPrice = (EditText)findViewById(R.id.addPrice);
        Intent it = getIntent();
        position = it.getExtras().getInt(EXTRA_POSITION);
        products = (Product) SaleManager.get().getProducts().get(position);
        addProductName.setText(products.getProductName());
        addUnit.setText(products.getUnit());
        String s = (new DecimalFormat("#,###.##")).format(products.getPrice());
        addPrice.setText(s);
        Button addOK = (Button)findViewById(R.id.btnOK);
        Button addCancel = (Button)findViewById(R.id.btnCancel);
        addOK.setOnClickListener(new AddActivity().OKClickListener());
       addCancel.setOnClickListener(new AddActivity().CancelClickListener());
    }

    private View.OnClickListener CancelClickListener() {
        Intent returnIntent = new Intent(this,AddActivity.class);
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
        return null;
    }

    private View.OnClickListener OKClickListener() {
        products.setProductName(addProductName.getText().toString());
        products.setUnit(addUnit.getText().toString());
        String s = addPrice.getText().toString();
        s = s.replace(",", "");
        double price = Double.parseDouble(s);
        products.setPrice(price);
        Intent returnIntent = new Intent(this,AddActivity.class);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        return null;
    }


}
