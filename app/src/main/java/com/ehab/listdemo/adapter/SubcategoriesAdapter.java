package com.ehab.listdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehab.listdemo.R;
import com.ehab.listdemo.model.Subcategory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubcategoriesAdapter extends RecyclerView.Adapter<SubcategoriesAdapter.ViewHolder> {

    private ArrayList<Subcategory> subCategoryData;

    public SubcategoriesAdapter(ArrayList<Subcategory> SubCategoryData) {
        this.subCategoryData = SubCategoryData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subcategory, parent, false);

        SubcategoriesAdapter.ViewHolder vh = new SubcategoriesAdapter.ViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        if (position == subCategoryData.size() - 1) {
            viewHolder.tvExpandCollapseToggle.setImageResource(R.drawable.ic_stat_name);
            viewHolder.tvExpandCollapseToggle.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvExpandCollapseToggle.setVisibility(View.GONE);
        }
        final Subcategory subcategory = subCategoryData.get(position);
        viewHolder.tvChild.setText(subcategory.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subcategory.setSelected(!subcategory.isSelected());
                if (subcategory.isSelected()) {
                    viewHolder.tvChild.setTextColor(Color.RED);
                }
                else{
                    viewHolder.tvChild.setTextColor(Color.WHITE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return subCategoryData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_main)
            TextView tvChild;

        @BindView(R.id.iv_expand_collapse_toggle)
        ImageView tvExpandCollapseToggle;

        public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
        }

    }
}
