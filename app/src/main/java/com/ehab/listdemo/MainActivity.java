package com.ehab.listdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ehab.listdemo.adapter.ProductsAdapter;
import com.ehab.listdemo.model.Category;
import com.ehab.listdemo.model.Product;
import com.ehab.listdemo.model.Subcategory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewParent;

    ArrayList<Product> parentChildObj;
    ArrayList<Product> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewParent = findViewById(R.id.rv_parent);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewParent.setLayoutManager(manager);
        recyclerViewParent.setHasFixedSize(true);

        dataset = generateDummyData();
        ProductsAdapter productsAdapter = new ProductsAdapter(this, dataset);
        recyclerViewParent.setAdapter(productsAdapter);

    }

    public void doneClicked(View view) {
        String selectedCategories = "";
        String selectedSubCategories = "";

        for (int i = 0; i < dataset.size(); i++) {
            ArrayList<Category> cats = dataset.get(i).getCategories();
            for (int j = 0; j < cats.size(); j++) {
                if (cats.get(j).isSelected()) {
                    selectedCategories += cats.get(j).getName();
                    selectedCategories += "\n";
                }
                ArrayList<Subcategory> subs = cats.get(j).getSubcategories();
                for (int k = 0; k < subs.size(); k++) {
                    if(subs.get(k).isSelected()){
                        selectedSubCategories += subs.get(k).getName();
                        selectedSubCategories += "\n";
                    }
                }
            }
        }

        Toast.makeText(this, selectedCategories + "Subs::\n" +selectedSubCategories, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Product> generateDummyData() {
        parentChildObj = new ArrayList<>();


        ArrayList<Subcategory> sublist1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Subcategory sc1 = new Subcategory();
            sc1.setName("SubCategory 1." + (i + 1));
            sublist1.add(sc1);
        }

        ArrayList<Category> list1 = new ArrayList<>();
        ArrayList<Category> list2 = new ArrayList<>();
        ArrayList<Category> list3 = new ArrayList<>();
        ArrayList<Category> list4 = new ArrayList<>();
        ArrayList<Category> list5 = new ArrayList<>();


        for (int i = 0; i < 3; i++) {
            Category c1 = new Category();
            c1.setSubcategories(sublist1);
            c1.setName("Category 1." + (i + 1));
            list1.add(c1);
        }

        for (int i = 0; i < 5; i++) {
            Category c2 = new Category();
            c2.setSubcategories(sublist1);
            c2.setName("Category 2." + (i + 1));
            list2.add(c2);
        }


        for (int i = 0; i < 2; i++) {
            Category c3 = new Category();
            c3.setSubcategories(sublist1);
            c3.setName("Category 3." + (i + 1));
            list3.add(c3);
        }


        for (int i = 0; i < 4; i++) {
            Category c4 = new Category();
            c4.setSubcategories(sublist1);
            c4.setName("Category 4." + (i + 1));
            list4.add(c4);
        }

        for (int i = 0; i < 2; i++) {
            Category c5 = new Category();
            c5.setSubcategories(sublist1);
            c5.setName("Category 5." + (i + 1));
            list5.add(c5);
        }

        Product pc1 = new Product();
        pc1.setCategories(list1);
        pc1.setHeaderText("Product1");
        parentChildObj.add(pc1);

        Product pc2 = new Product();
        pc2.setCategories(list2);
        pc2.setHeaderText("Product2");
        parentChildObj.add(pc2);


        Product pc3 = new Product();
        pc3.setCategories(list3);
        pc3.setHeaderText("Product3");
        parentChildObj.add(pc3);

        Product pc4 = new Product();
        pc4.setCategories(list4);
        pc4.setHeaderText("Product4");
        parentChildObj.add(pc4);

        Product pc5 = new Product();
        pc5.setCategories(list5);
        pc5.setHeaderText("Product5");
        parentChildObj.add(pc5);


        return parentChildObj;
    }

}
