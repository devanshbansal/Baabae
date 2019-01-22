package com.example.bansal.baabae.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.bansal.baabae.Models.OrderItem;
import com.example.bansal.baabae.Models.OrderSummary;
import com.example.bansal.baabae.R;

import java.util.ArrayList;

public class RecentOrderAdapter extends ArrayAdapter<OrderSummary> {
    Context context;
    TextView recent;
    Button recent_button;
    ArrayList<OrderSummary> summ = new ArrayList<OrderSummary>();
    public RecentOrderAdapter(Context context, ArrayList<OrderSummary> objects) {
        super(context, 0, (objects));
        this.context = context;
        this.summ.addAll(objects);
    }
    public void refreshEvents(ArrayList<OrderSummary> events) {

      ArrayList<OrderSummary>  temp = new ArrayList<OrderSummary>();
      temp.addAll(events);

        summ.clear();

        summ.addAll(temp);
        notifyDataSetChanged();
    }
    public void clearAdapter()
    {
        summ.clear();

        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recent_order_item, parent, false);
        }
       if(summ.size()>0){
        ArrayList<OrderItem> items=summ.get(position).getItems();
        String summary="";
        for(int i=0;i<items.size();i++)
        {
            summary=summary+items.get(i).getName()+": "+items.get(i).getQuantity()+"\n";
        }
        recent=convertView.findViewById(R.id.recent);
        recent_button=convertView.findViewById(R.id.recent_button);
        recent.setText(summary); }
        return convertView;
    }

}
