package com.saswat.retrofitapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.CategoryHolder> {

    Context context;
    String[] categories;
    NewsCategoryOnClickListener listener;
    private int SelectedPostion = -1;


    public CategoryAdaptor(Context context) {
        this.context = context;
        this.categories = context.getResources().getStringArray(R.array.news_categories);
    }

    public void setListener(NewsCategoryOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_cateogry, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        String newsCategory = categories[position];
        holder.mTextViewCategory.setText(newsCategory);
        holder.mConstraintLayout.setOnClickListener(view -> {
            if (listener != null) {
                listener.onNewsCategoryClick(newsCategory);
                SelectedPostion = position;
                notifyDataSetChanged();
            }
        });

        if (position == SelectedPostion) {
            holder.mConstraintLayout.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.category_selected, null));
            holder.mTextViewCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorBlack, null));
        } else {
            holder.mConstraintLayout.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.category_unselected, null));
            holder.mTextViewCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorWhite, null));
        }
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.length : 0;
    }

    public interface NewsCategoryOnClickListener {
        void onNewsCategoryClick(String category);
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mConstraintLayout;
        TextView mTextViewCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            mConstraintLayout = itemView.findViewById(R.id.inner_card_view);
            mTextViewCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}
