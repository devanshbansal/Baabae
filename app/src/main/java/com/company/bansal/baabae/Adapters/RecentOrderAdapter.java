package com.company.bansal.baabae.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.company.bansal.baabae.Models.OrderItem;
import com.company.bansal.baabae.Models.Transaction;
import com.company.bansal.baabae.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecentOrderAdapter extends ArrayAdapter<Transaction> {
    Context context;
    TextView recent;
    Button recent_button;
    ArrayList<Transaction> summ = new ArrayList<Transaction>();
    public RecentOrderAdapter(Context context, ArrayList<Transaction> objects) {
        super(context, 0, (objects));
        this.context = context;
        this.summ.addAll(objects);
    }
    public void refreshEvents(ArrayList<Transaction> events) {

      ArrayList<Transaction>  temp = new ArrayList<Transaction>();
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
        ArrayList<OrderItem> items=summ.get(position).getSummary().getItems();
        String summary="";
        for(int i=0;i<items.size();i++)
        {
            summary=summary+"<b>"+items.get(i).getName()+"</b>"+"<br>";
        }
         summary=summary+"<b>Ordered at: </b>";
           String time = "";
           String dat="";
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").parse(summ.get(position).getTransactionTimeStamp());
            dat=new SimpleDateFormat("dd-MM-yyyy").format(date);
             time = new SimpleDateFormat("H:mm").format(date);
        }catch (Exception e){}
        //summary=summary+summ.get(position).getTransactionID()+"\n";
           summary=summary+"<b>Time</b>: "+time+"\n";
           summary=summary+"<b>Date</b>: "+dat+"\n";
           recent=convertView.findViewById(R.id.recent);
           // recent_button=convertView.findViewById(R.id.recent_button);
           recent.setText(Html.fromHtml(summary)); }
        return convertView;
    }

}
