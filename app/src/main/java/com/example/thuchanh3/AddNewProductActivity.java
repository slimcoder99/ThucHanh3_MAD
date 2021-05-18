package com.example.thuchanh3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thuchanh3.DatabaseProvider.SQLiteHelper;
import com.example.thuchanh3.Model.Item;
import com.example.thuchanh3.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class AddNewProductActivity extends AppCompatActivity {

    private EditText productNameEditText, productPricesEditText;
    private Spinner productImage, productCategory;
    private Button addBtn, restoreBtn;
    private String image, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        productNameEditText = (EditText) findViewById(R.id.edtAddName);
        productPricesEditText = (EditText) findViewById(R.id.edtAddPrices);
        productImage = (Spinner) findViewById(R.id.list_image);
        productCategory = (Spinner) findViewById(R.id.list_filter);
        addBtn = (Button) findViewById(R.id.btAdd);
        restoreBtn = (Button) findViewById(R.id.btRedo);

        addItemOnProductImage();
        addItemOnProductCategory();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProduct();
            }
        });

        restoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void addItemOnProductImage() {
        List<String> list = new ArrayList<>();
        list.add("iPhone11");
        list.add("iPhone12");
        list.add("macbook");
        list.add("typec");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        productImage.setAdapter(arrayAdapter);

        productImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        image = "iPhone11";
                    case 1:
                        image = "iPhone12";
                    case 2:
                        image = "macbook";
                    case 3:
                        image = "typec";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addItemOnProductCategory() {
        List<String> list = new ArrayList<>();
        list.add("Dien Thoai");
        list.add("May Tinh");
        list.add("Phu Kien");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        productCategory.setAdapter(arrayAdapter);

        productCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        category = "Dien Thoai";
                    case 1:
                        category = "May Tinh";
                    case 2:
                        category = "Phu Kien";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addNewProduct() {
        SQLiteHelper db1 = new SQLiteHelper(getBaseContext());
        String name = productPricesEditText.getText().toString();
        Float prices = Float.parseFloat(productPricesEditText.getText().toString());
        String image = this.image;
        String category = this.category;

        Item item = new Item(name, prices, image, category);

        db1.addItem(item);
        Toast.makeText(getBaseContext(),"Thêm dữ liệu thành công",Toast.LENGTH_SHORT).show();
        reset();
        setResult(RESULT_OK, null);
        finish();
    }

    private void reset() {
        productNameEditText.setText("");
        productPricesEditText.setText("");
    }

}