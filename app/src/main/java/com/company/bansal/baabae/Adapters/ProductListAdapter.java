package com.company.bansal.baabae.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.bansal.baabae.Models.OrderSummary;
import com.company.bansal.baabae.R;

import java.util.Stack;

public class ProductListAdapter extends ArrayAdapter<String> {
    TextView brand;
    public OrderSummary summary;
    String[] obj;
    private final Context context;
    public static Stack<View> buttons;

    Button add;

    public ProductListAdapter(Context context, String[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.obj=objects;
        this.summary=new OrderSummary();
        buttons = new Stack<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cigarette_item, parent, false);
        }

        brand=convertView.findViewById(R.id.brand);
        add=convertView.findViewById(R.id.button14);
        add.setEnabled(false);
        add.setVisibility(View.INVISIBLE);
        add.setTag(position);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItem(v);
            }
        });
        brand.setText(obj[position]);

        return convertView;
    }
    public void AddItem(View v){
        buttons.add(v);
        v.setVisibility(View.INVISIBLE);
        int t= (int) v.getTag();

        Toast.makeText(getContext(),"Item Added",Toast.LENGTH_SHORT).show();

    }

    public static void setButtonsVisible(){
        while(buttons.isEmpty()!=true){
            View v = buttons.pop();
            v.setVisibility(View.VISIBLE);
        }
    }
}
