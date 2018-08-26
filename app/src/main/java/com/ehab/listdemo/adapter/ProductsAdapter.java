package com.ehab.listdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ehab.listdemo.R;
import com.ehab.listdemo.model.Category;
import com.ehab.listdemo.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Product> dataset;
    private Context context;

    public ProductsAdapter(Context context, ArrayList<Product> dataSet) {
        this.context = context;
        this.dataset = dataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ProductsAdapter.ViewHolder pavh = new ProductsAdapter.ViewHolder(itemLayoutView);
        return pavh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Product p = dataset.get(position);
        viewHolder.mainTextView.setText(p.getHeaderText());
        initChildLayoutManager(viewHolder.categoriesRecyclerview, p.getCategories());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setSelected(!p.isSelected());
                if (p.isSelected())
                    viewHolder.categoriesRecyclerview.setVisibility(View.VISIBLE);
                else
                    viewHolder.categoriesRecyclerview.setVisibility(View.GONE);
            }
        });
        if (p.isSelected())
            viewHolder.categoriesRecyclerview.setVisibility(View.VISIBLE);
        else
            viewHolder.categoriesRecyclerview.setVisibility(View.GONE);
    }

    private void initChildLayoutManager(RecyclerView rv_child, ArrayList<Category> categoryData) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_child.setLayoutManager(manager);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(context, categoryData);
        rv_child.setAdapter(categoriesAdapter);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.categories_recyclerview)
        RecyclerView categoriesRecyclerview;

        @BindView(R.id.text_main)
        TextView mainTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}