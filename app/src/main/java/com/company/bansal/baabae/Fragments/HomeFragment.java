package com.company.bansal.baabae.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.bansal.baabae.Adapters.HomeListAdapter;
import com.company.bansal.baabae.Adapters.ProductListAdapter;
import com.company.bansal.baabae.HomeActivity;
import com.company.bansal.baabae.Models.OrderItem;
import com.company.bansal.baabae.Models.OrderSummary;
import com.company.bansal.baabae.Models.SignUpForm;
import com.company.bansal.baabae.Models.Transaction;
import com.company.bansal.baabae.Products.Benson;
import com.company.bansal.baabae.Products.Cigarette;
import com.company.bansal.baabae.Products.Classic;
import com.company.bansal.baabae.Products.Davidoff;
import com.company.bansal.baabae.Products.Dunhill;
import com.company.bansal.baabae.Products.Goldflake;
import com.company.bansal.baabae.Products.Marlboro;
import com.company.bansal.baabae.R;
import com.company.bansal.baabae.Utility.UtilityFunctions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    TextView text;
    OrderSummary summary;
    EditText address;
    SignUpForm userForm;
   public static Button cart, emptyCart;
    FirebaseDatabase database;
    public static ArrayList<String> selectedBrands = new ArrayList<>();
    HomeListAdapter adapter;
    ArrayList<String[]> branditems = new ArrayList<>();
    Button confirm;
    String orderDetails;
    Button add;
    Cigarette obj[];
    String userAddress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         database = FirebaseDatabase.getInstance();     // Getting database reference
        final DatabaseReference myRef = database.getReference( );
        userForm=HomeActivity.userForm;
        cart=view.findViewById(R.id.cart);
        cart.setEnabled(false);
        cart.setVisibility(View.INVISIBLE);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConfirmDialog();
            }
        });



        branditems.add(Marlboro.items);
        branditems.add(Benson.items);
        branditems.add(Classic.items);
        branditems.add(Davidoff.items);
        branditems.add(Dunhill.items);
        branditems.add(Goldflake.items);

        obj=new Cigarette[5];
        obj[0] = new Cigarette("Marlboro Advance",15);
        obj[1]= new Cigarette("Marlboro Light",15);
        obj[2]=new Cigarette("Marlboro Red",15);                  // Adding 5 Cigarettes brands
        obj[3]=new Cigarette("Marlboro Methanol",15);
        obj[4]=new Cigarette("Kings Light",15);

        ListView listView = (ListView) view.findViewById(R.id.lv);
        //   address = view.findViewById(R.id.address);
        final String[] brandlist = new String[6];
        brandlist[0]="Marlboro";
        brandlist[1]="Benson";
        brandlist[2]="Classic";
        brandlist[3]="Davidoff";
        brandlist[4]="Dunhill";
        brandlist[5]="Goldflake";


        adapter = new HomeListAdapter(getContext(),brandlist);               // Finding views
        listView.setAdapter(adapter);
        // confirm=view.findViewById(R.id.place);

//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case DialogInterface.BUTTON_POSITIVE:    //Action on the order confirmation
//                                String id = UtilityFunctions.generateUniqueID();
//                                Transaction trans = new Transaction(id,UtilityFunctions.generateTimeStamp(),address.getText().toString(),summary);
//                                myRef.child("transactions").child("pending").child(id).setValue(trans);
//                                myRef.child("orders").child("users").child(userForm.getNumber()).child(id).setValue(trans);
//                                adapter.summary = new OrderSummary();
//                                adapter.setButtonsVisible();
//                                Intent i = new Intent(getActivity(),Checkout.class);
//                                startActivity(i);
//                                break;
//
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                break;
//                        }
//                    }
//                };
//                summary = adapter.summary;
//                orderDetails="";
//                ArrayList<OrderItem> items = summary.getItems();
//                for(int i=0;i<summary.getItems().size();i++){
//                    orderDetails=orderDetails+items.get(i).getName()+"\n";
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Your order details are:\n"+orderDetails+"\n").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
//            }
//        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //   Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.product_list_dialog_adapter);
                final String brandName = brandlist[position];
                final Integer brandPosition = position;

                dialog.setTitle(brandName);
                ListView listView = (ListView) dialog.findViewById(R.id.lv_dialog);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog.dismiss();
                        selectedBrands.add(brandName+" "+branditems.get(brandPosition)[position]);
                        cart.setVisibility(View.VISIBLE);
                        cart.setEnabled(true);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        dialog.dismiss();
                                   OpenConfirmDialog();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("We've picked the boxes, reaching you ASAP. Continue?").setPositiveButton("PLACE ORDER", dialogClickListener)
                                .setNegativeButton("ADD MORE", dialogClickListener).show();



                    }
                });
                ProductListAdapter productListAdapter = new ProductListAdapter(getContext(),branditems.get(position));// Finding views
                listView.setAdapter(productListAdapter);
                dialog.show();
            }
        });


    }

    public void AddItem(View v){
        ViewParent parent=v.getParent();
    }

    public void OpenConfirmDialog(){
        final Dialog d = new Dialog(getContext());
        d.setContentView(R.layout.confirm_order_dialog);
        TextView detailView = d.findViewById(R.id.textView9);
        Button confirm = d.findViewById(R.id.button10);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog finalDialog = new Dialog(getContext());
                finalDialog.setContentView(R.layout.address_dialog);
                finalDialog.setTitle("Where at?");
                Button done= finalDialog.findViewById(R.id.done);
                final EditText a1 = finalDialog.findViewById(R.id.a1);
                final EditText a2 = finalDialog.findViewById(R.id.a2);
                final EditText a3 = finalDialog.findViewById(R.id.a3);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isNetworkAvailable())
                        {
                            Toast.makeText(getContext(),"Please check your Internet Connection",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(a1.getText().toString().isEmpty()||a2.getText().toString().isEmpty()||a3.getText().toString().isEmpty())
                        {
                            Toast.makeText(getContext(),"Please enter the address details correctly",Toast.LENGTH_SHORT).show();
                            return;
                        }


                        userAddress = a1.getText().toString()+" "+a2.getText().toString()+" "+a3.getText().toString();
                        ArrayList<OrderItem> orderItems=new ArrayList<>();
                        for(int i=0;i<selectedBrands.size();i++)
                            orderItems.add(new OrderItem(selectedBrands.get(i)));
                        OrderSummary su = new OrderSummary(orderItems);
                        String id=UtilityFunctions.generateUniqueID();
                        Transaction trans = new Transaction(id,UtilityFunctions.generateTimeStamp(),userAddress,HomeActivity.userForm.getNumber(),su);
                        final DatabaseReference myRef = database.getReference();
                        myRef.child("transactions").child("pending").child(id).setValue(trans);
                        myRef.child("upcoming_orders").child(userForm.getNumber()).child(id).setValue(trans);
                        myRef.child("orders").child("users").child(userForm.getNumber()).child(id).setValue(trans, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                  Toast.makeText(getContext(),"Sorry, your order couldn't be placed. Please check your Internet Connection and try again",Toast.LENGTH_SHORT).show();
                                } else {
                                    finalDialog.dismiss();
                                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    selectedBrands.clear();
                                                    cart.setVisibility(View.INVISIBLE);
                                                    dialog.dismiss();
                                                    break;

                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    selectedBrands.clear();
                                                    cart.setVisibility(View.INVISIBLE);
                                                    dialog.dismiss();
                                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                                    intent.setData(Uri.parse("tel:9877952375"));
                                                    startActivity(intent);
                                                    break;
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setMessage("Boom! Baabae on the way carrying your preferred brand. Pay on the spot with Cash / Paytm / GooglePay").setPositiveButton("NEW ORDER", dialogClickListener)
                                            .setNegativeButton("HELP", dialogClickListener).show();
                                }
                            }
                        });


                        }


                    });

                finalDialog.show();
                d.dismiss();

            }
        });

        Button addMore= d.findViewById(R.id.button12);

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        Button emptyCart = d.findViewById(R.id.button11);
        emptyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.setEnabled(false);
                cart.setVisibility(View.INVISIBLE);
                selectedBrands.clear();
                d.dismiss();
            }
        });
        String details="<b>Shopping Cart Details:</b>"+"\n";
        for(int i=0;i<selectedBrands.size();i++){
            details=details+""+selectedBrands.get(i)+" ";

        }
        detailView.setText(Html.fromHtml(details));
        d.show();
    }

    public int saveOrderTransaction(){
        final int[] result = {-1};
        ArrayList<OrderItem> orderItems=new ArrayList<>();
        for(int i=0;i<selectedBrands.size();i++)
            orderItems.add(new OrderItem(selectedBrands.get(i)));
        OrderSummary su = new OrderSummary(orderItems);
        String id=UtilityFunctions.generateUniqueID();
        Transaction trans = new Transaction(id,UtilityFunctions.generateTimeStamp(),userAddress,HomeActivity.userForm.getNumber(),su);
        final DatabaseReference myRef = database.getReference();
        myRef.child("transactions").child("pending").child(id).setValue(trans);
        myRef.child("orders").child("users").child(userForm.getNumber()).child(id).setValue(trans, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                   result[0] =0;
                } else {
                 result[0] =1;
                }
            }
        });
        return result[0];
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
