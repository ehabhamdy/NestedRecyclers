package com.ehab.listdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehab.listdemo.model.Subcategory;

import com.ehab.listdemo.R;
import com.ehab.listdemo.model.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Category> categoryData;
    private Context context;

    public CategoriesAdapter(Context context, ArrayList<Category> categoryData) {
        this.categoryData = categoryData;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);


        CategoriesAdapter.ViewHolder vh = new CategoriesAdapter.ViewHolder(itemLayoutView);
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;


        /*if (position == categoryData.size() - 1) {
            viewHolder.tvExpandCollapseToggle.setImageResource(R.drawable.ic_stat_name);
            viewHolder.tvExpandCollapseToggle.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvExpandCollapseToggle.setVisibility(View.GONE);
        }*/

        final Category category = categoryData.get(position);
        viewHolder.tvChild.setText(category.getName());
        initChildLayoutManager(viewHolder.subCategoriesRecyclerview, category.getSubcategories());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category.setSelected(!category.isSelected());
                if (category.isSelected()) {
                    viewHolder.tvChild.setTextColor(Color.RED);
                    viewHolder.subCategoriesRecyclerview.setVisibility(View.VISIBLE);
                }
                else{
                    viewHolder.tvChild.setTextColor(Color.WHITE);
                    viewHolder.subCategoriesRecyclerview.setVisibility(View.GONE);
                }
            }
        });


        if (category.isSelected())
            viewHolder.subCategoriesRecyclerview.setVisibility(View.VISIBLE);
        else
            viewHolder.subCategoriesRecyclerview.setVisibility(View.GONE);

    }

    private void initChildLayoutManager(RecyclerView rv_child, ArrayList<Subcategory> subCategoryData) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_child.setLayoutManager(manager);
        SubcategoriesAdapter subcategoriesAdapter = new SubcategoriesAdapter(subCategoryData);
        rv_child.setAdapter(subcategoriesAdapter);
    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_main)
        TextView tvChild;

        @BindView(R.id.subcategories_recyclerview)
        RecyclerView subCategoriesRecyclerview;

       /* @BindView(R.id.iv_expand_collapse_toggle)
        ImageView tvExpandCollapseToggle;*/

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
