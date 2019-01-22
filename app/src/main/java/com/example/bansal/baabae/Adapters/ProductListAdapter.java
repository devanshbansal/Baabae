package com.example.bansal.baabae.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bansal.baabae.Models.OrderItem;
import com.example.bansal.baabae.Models.OrderSummary;
import com.example.bansal.baabae.Products.Cigarette;
import com.example.bansal.baabae.R;

import java.util.ArrayList;

public class ProductListAdapter extends ArrayAdapter<Cigarette> {
    Integer count;
    TextView brand,price;
    public OrderSummary summary;
    Cigarette[] obj;
    private final Context context;
    Spinner spinner;
    ArrayList<Spinner> spinnerArray;
    Button add;

    public ProductListAdapter(Context context, Cigarette[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.obj=objects;
        this.summary=new OrderSummary();
        this.spinnerArray= new ArrayList<Spinner>(obj.length);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cigarette_item, parent, false);
        }
        brand=convertView.findViewById(R.id.brand);
        add=convertView.findViewById(R.id.button14);
        add.setTag(position);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItem(v);
            }
        });
        brand.setText((CharSequence) obj[position].getName());
        price=convertView.findViewById(R.id.price);
        price.setText(obj[position].getPrice().toString());
        spinner=convertView.findViewById(R.id.planets_spinner);
        spinnerArray.add(spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(context,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        return convertView;
    }
    public void AddItem(View v){
        int t= (int) v.getTag();
        Spinner temp=spinnerArray.get((Integer)v.getTag());
        int count=  Integer.parseInt(temp.getSelectedItem().toString());
        if(count!=0)
        {
            summary.getItems().add(new OrderItem(obj[t].getName(),String.valueOf(obj[t].getPrice()),String.valueOf(count)));
            Toast.makeText(getContext(),"Item Added",Toast.LENGTH_SHORT).show(); }
    }
}
