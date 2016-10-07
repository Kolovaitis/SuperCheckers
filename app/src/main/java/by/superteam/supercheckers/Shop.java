package by.superteam.supercheckers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Shop extends Activity {

    private LinearLayout productsItems;
    private ArrayList<LinearLayout> productsLayouts;
    private ArrayList<Product> products;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        productsItems = (LinearLayout) findViewById(R.id.products);
        inflater = getLayoutInflater();

        productsLayouts = new ArrayList<LinearLayout>();
        products = new ArrayList<Product>();

        getProductsFromServer();
    }

    private void getProductsFromServer(){

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query("Products", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //boolean doNext = true;
            while (true) {
                Log.d("Debug", "while is called");
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                Boolean isAcquired = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("isAcquired")));
                Product product = new Product(id, name, description, imageId, isAcquired);
                products.add(product);
                if (cursor.isLast()) {
                    break;
                }
                cursor.moveToNext();
            }


            final View.OnClickListener forAcquiredProducts = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((Switch) v).isChecked()) {
                        //
                    } else {

                        //products.get(productsItems.indexOfChild((LinearLayout)v.getParent().getParent())).apply();
                    }
                }
            };

            View.OnClickListener forNotAcquiredProducts = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Debug", "onClick for button is called.");
                    LinearLayout parent = (LinearLayout) v.getParent().getParent();
                    products.get(productsItems.indexOfChild(parent)).acquireProduct();
                    parent.removeView(v);

                    inflater.inflate(R.layout.switch_for_product, parent, true).setOnClickListener(forAcquiredProducts);
                }
            };

            for (int i = 0; i < products.size(); i++) {
                LinearLayout item = (LinearLayout) inflater.inflate(R.layout.shop_element, productsItems, false);

                Log.d("Debug", Integer.toString(products.get(i).getImageResource()));
                ((ImageView) item.findViewById(R.id.propductImage)).setImageResource(products.get(i).getImageResource());
                ((TextView) item.findViewById(R.id.productName)).setText(products.get(i).getName());
                ((TextView) item.findViewById(R.id.productDescription)).setText(products.get(i).getDescription());

                if (products.get(i).isAcquired()) {
                    Switch switchView = (Switch) inflater.inflate(R.layout.switch_for_product, (LinearLayout)item.findViewById(R.id.linearForView));
                    switchView.setOnClickListener(forAcquiredProducts);
                    switchView.setChecked(false);
                    //

                    item.addView(switchView);
                } else {
                    Button b = (Button)(inflater.inflate(R.layout.button_in_product, (LinearLayout)item.findViewById(R.id.linearForView), false)).findViewById(R.id.buyButton);
                    b.setOnClickListener(forNotAcquiredProducts);
                    ((LinearLayout) item.findViewById(R.id.linearForView)).addView(b);
                }

                productsItems.addView(item);
                productsLayouts.add(item);
            }
        }else {
            LinearLayout item = (LinearLayout) inflater.inflate(R.layout.shop_element, productsItems);
            ((TextView)item.findViewById(R.id.productName)).setText("There is no products.");
            ((TextView)item.findViewById(R.id.productDescription)).setText("Touch to add products");
            item.findViewById(R.id.productName).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addProductsToShop();
                    ((LinearLayout)v.getParent().getParent()).removeView(v);
                }
            });
        }
        cursor.close();
    }

    private void addProductsToShop(){
        String[] names = {"SpartPack", "StarPack"};
        String[] descriptions = {"Skin", "Skin"};
        String[] imageResources = {Integer.toString(R.drawable.red), Integer.toString(R.drawable.purple)};
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        for (int i = 0; i < names.length; i++){
            ContentValues values = new ContentValues();
            values.put("_id", Integer.toString(i));
            values.put("name", names[i]);
            values.put("description", descriptions[i]);
            values.put("imageId", imageResources[i]);
            Log.d("Debug", imageResources[i]);
            values.put("isAcquired", "false");

            db.insert("Products", null, values);
        }
        getProductsFromServer();
    }
}

class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table Products (" +
                "_id," +
                "name," +
                "description," +
                "imageId," + "isAcquired" +
                ");");


        //db.execSQL("create table Table2 (" + "requestType" + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

class Product{
    private int id;
    private String name;
    private String description;
    private int imageResource;
    private boolean isAcquired;
    private boolean isApplied;

    public Product(int id, String name, String description, int imageResource, boolean isAcquired){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.isAcquired = isAcquired;
    }

    public boolean isAcquired(){
        return isAcquired;
    }

    public void acquireProduct(){
        this.isAcquired = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}
