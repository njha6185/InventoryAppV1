package com.example.nitishkumar.inventoryappv1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitishkumar.inventoryappv1.data.InventoryDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nitishkumar.inventoryappv1.data.InventoryContract.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.insertButtonView) Button insertButton;
    @BindView(R.id.readButtonView) Button readButton;
    @BindView(R.id.readDataTextView) TextView readDataText;

    private InventoryDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        insertButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        readDataText.setText("Hello World\n");

        dbHelper = new InventoryDbHelper(this);
    }
/**************************************** insertData Method Inserts the data to the SQLite Database *********************/
    private void insertData()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BooksEntry.COLUMN_PRODUCT_NAME, "Arihant Maths");
        values.put(BooksEntry.COLUMN_PRICE, 250);
        values.put(BooksEntry.COLUMN_QUANTITY, 5);
        values.put(BooksEntry.COLUMN_SUPPLIER_NAME, "Gupta Books Depo");
        values.put(BooksEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "1234567890");
        long newRowId = database.insert(BooksEntry.TABLE_NAME, null, values);
        if (newRowId == -1)
        {
            Toast.makeText(this, "Some error Occured, Data Not Inserted!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Data Successfully Inserted with ID : " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
    /*************** queryData Method Reads and Display the data from the SQLite Database to textview *********************/
    private void queryData()
    {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String [] projection = {BooksEntry._ID,
        BooksEntry.COLUMN_PRODUCT_NAME,
        BooksEntry.COLUMN_PRICE,
        BooksEntry.COLUMN_QUANTITY,
        BooksEntry.COLUMN_SUPPLIER_NAME,
        BooksEntry.COLUMN_SUPPLIER_PHONE_NUMBER};

        Cursor cursor = database.query(BooksEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        try{

            readDataText.append("<---------------------->\nBooks Table Contain " + cursor.getCount() + " Books. \n");

            int idColumnIndex = cursor.getColumnIndex(BooksEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext())
            {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);

                readDataText.append("\n--> "+ currentID + " - " + currentProductName + " - " + currentPrice + " - " +
                        currentQuantity + " - " + currentSupplierName + " - " + currentSupplierPhoneNumber + "\n");
            }

        }finally {
            cursor.close();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case  R.id.insertButtonView :
                insertData();
                break;

            case R.id.readButtonView :
                Toast.makeText(this, "Read Data!!!", Toast.LENGTH_SHORT).show();
                queryData();
                break;
        }
    }
}