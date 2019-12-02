package com.example.currencychange.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencychange.R;
import com.example.currencychange.models.Rates;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
  // String currencyName[];
  // List<Rates> RatesName ;
   List<String> ratesName;

    public CurrencyAdapter(List<String> ratesName) {
        this.ratesName = ratesName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     View view = LayoutInflater.from(parent.getContext())
             .inflate(R.layout.currency_item,parent,false);

        return new  CurrencyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
           final String rates = ratesName.get(position);
          holder.curr.setText(rates);
          if(onClickListener !=null){
              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      onClickListener.onItemClick(position,rates);
                  }
              });
          }

    }

    @Override
    public int getItemCount() {
       if(ratesName == null) return 0;
       return ratesName.size();
    }


          OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
           void onItemClick(int pos,String name);

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView curr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          //  this.curr = itemText;
            curr= itemView.findViewById(R.id.item_view);
        }
    }



}
