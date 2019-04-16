package limited.pvt.global.stachi.glisteruser.app;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.InputFilterMinMax;
import limited.pvt.global.stachi.glisteruser.app.Products.ProductDatabase1;

public class Product1 extends AppCompatActivity
{
    TextView productName , mRP , salesPrice , quantity , pay ;
    TextView p1_mrp , p2_mrp , p3_mrp , p4_mrp , p5_mrp , p6_mrp , p7_mrp , p8_mrp , p9_mrp , p10_mrp ,
            p11_mrp , p12_mrp , p13_mrp , p14_mrp ;
    TextView p1_pay , p2_pay , p3_pay , p4_pay , p5_pay , p6_pay , p7_pay , p8_pay , p9_pay , p10_pay ,
            p11_pay , p12_pay , p13_pay , p14_pay ;
    CheckBox p1_name , p2_name , p3_name , p4_name , p5_name , p6_name , p7_name , p8_name , p9_name , p10_name ,
            p11_name , p12_name , p13_name , p14_name ;
    EditText p1_salesPrice , p2_salesPrice , p3_salesPrice , p4_salesPrice , p5_salesPrice , p6_salesPrice , p7_salesPrice , p8_salesPrice ,
            p9_salesPrice , p10_salesPrice , p11_salesPrice , p12_salesPrice , p13_salesPrice , p14_salesPrice ;
    EditText p1_quantity , p2_quantity , p3_quantity , p4_quantity , p5_quantity , p6_quantity , p7_quantity , p8_quantity , p9_quantity ,
            p10_quantity , p11_quantity , p12_quantity , p13_quantity , p14_quantity ;
    TextView p1_finalResult , p2_finalResult , p3_finalResult , p4_finalResult , p5_finalResult , p6_finalResult , p7_finalResult ,
            p8_finalResult , p9_finalResult , p10_finalResult , p11_finalResult , p12_finalResult, p13_finalResult , p14_finalResult ;
    TextView total , paymentReceived , outstanding , totalAmount  , outstandingAmount ;
    EditText paymentReceivedAmount ;
    Button submitButton ;
    Button submitDatabase ;

    private DatabaseReference mDatabaseRef ;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private TextView textView ;
    private DatabaseReference getUserDataReference ;
    String lat  ;
    String lng ;
    Double latitude , longitude ;
    private TextView mTextAddress;
    String address , city , state , country , postalCode , knownName ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product1);

        productName = (TextView) findViewById(R.id.productName);
        mRP = (TextView) findViewById(R.id.mRP);
        salesPrice  = (TextView) findViewById(R.id.salesPrice);
        quantity = (TextView) findViewById(R.id.quantity);
        pay = (TextView) findViewById(R.id.pay);

        p1_mrp  = (TextView) findViewById(R.id.p1_mrp);
        p2_mrp = (TextView) findViewById(R.id.p2_mrp);
        p3_mrp  = (TextView) findViewById(R.id.p3_mrp);
        p4_mrp  = (TextView) findViewById(R.id.p4_mrp);
        p5_mrp = (TextView) findViewById(R.id.p5_mrp);
        p6_mrp  = (TextView) findViewById(R.id.p6_mrp);
        p7_mrp = (TextView) findViewById(R.id.p7_mrp);
        p8_mrp  = (TextView) findViewById(R.id.p8_mrp);
        p9_mrp  = (TextView) findViewById(R.id.p9_mrp);
        p10_mrp = (TextView) findViewById(R.id.p10_mrp);
        p11_mrp  = (TextView) findViewById(R.id.p11_mrp);
        p12_mrp = (TextView) findViewById(R.id.p12_mrp);
        p13_mrp  = (TextView) findViewById(R.id.p13_mrp);
        p14_mrp  = (TextView) findViewById(R.id.p14_mrp);

        p1_pay  = (TextView) findViewById(R.id.p1_pay);
        p2_pay = (TextView) findViewById(R.id.p2_pay);
        p3_pay  = (TextView) findViewById(R.id.p3_pay);
        p4_pay  = (TextView) findViewById(R.id.p4_pay);
        p5_pay = (TextView) findViewById(R.id.p5_pay);
        p6_pay  = (TextView) findViewById(R.id.p6_pay);
        p7_pay = (TextView) findViewById(R.id.p7_pay);
        p8_pay  = (TextView) findViewById(R.id.p8_pay);
        p9_pay  = (TextView) findViewById(R.id.p9_pay);
        p10_pay = (TextView) findViewById(R.id.p10_pay);
        p11_pay  = (TextView) findViewById(R.id.p11_pay);
        p12_pay = (TextView) findViewById(R.id.p12_pay);
        p13_pay  = (TextView) findViewById(R.id.p13_pay);
        p14_pay  = (TextView) findViewById(R.id.p14_pay);

        p1_finalResult = (TextView) findViewById(R.id.p1_finalResult);
        p2_finalResult = (TextView) findViewById(R.id.p2_finalResult);
        p3_finalResult = (TextView) findViewById(R.id.p3_finalResult);
        p4_finalResult = (TextView) findViewById(R.id.p4_finalResult);
        p5_finalResult = (TextView) findViewById(R.id.p5_finalResult);
        p6_finalResult = (TextView) findViewById(R.id.p6_finalResult);
        p7_finalResult = (TextView) findViewById(R.id.p7_finalResult);
        p8_finalResult = (TextView) findViewById(R.id.p8_finalResult);
        p9_finalResult = (TextView) findViewById(R.id.p9_finalResult);
        p10_finalResult = (TextView) findViewById(R.id.p10_finalResult);
        p11_finalResult = (TextView) findViewById(R.id.p11_finalResult);
        p12_finalResult = (TextView) findViewById(R.id.p12_finalResult);
        p13_finalResult = (TextView) findViewById(R.id.p13_finalResult);
        p14_finalResult = (TextView) findViewById(R.id.p14_finalResult);

        p1_quantity = (EditText) findViewById(R.id.p1_quantity);
        p2_quantity = (EditText) findViewById(R.id.p2_quantity);
        p3_quantity = (EditText) findViewById(R.id.p3_quantity);
        p4_quantity = (EditText) findViewById(R.id.p4_quantity);
        p5_quantity = (EditText) findViewById(R.id.p5_quantity);
        p6_quantity = (EditText) findViewById(R.id.p6_quantity);
        p7_quantity = (EditText) findViewById(R.id.p7_quantity);
        p8_quantity = (EditText) findViewById(R.id.p8_quantity);
        p9_quantity = (EditText) findViewById(R.id.p9_quantity);
        p10_quantity = (EditText) findViewById(R.id.p10_quantity);
        p11_quantity = (EditText) findViewById(R.id.p11_quantity);
        p12_quantity = (EditText) findViewById(R.id.p12_quantity);
        p13_quantity = (EditText) findViewById(R.id.p13_quantity);
        p14_quantity = (EditText) findViewById(R.id.p14_quantity);

        p1_salesPrice  = (EditText) findViewById(R.id.p1_salesPrice);
        p2_salesPrice = (EditText) findViewById(R.id.p2_salesPrice);
        p3_salesPrice  = (EditText) findViewById(R.id.p3_salesPrice);
        p4_salesPrice  = (EditText) findViewById(R.id.p4_salesPrice);
        p5_salesPrice = (EditText) findViewById(R.id.p5_salesPrice);
        p6_salesPrice  = (EditText) findViewById(R.id.p6_salesPrice);
        p7_salesPrice = (EditText) findViewById(R.id.p7_salesPrice);
        p8_salesPrice  = (EditText) findViewById(R.id.p8_salesPrice);
        p9_salesPrice  = (EditText) findViewById(R.id.p9_salesPrice);
        p10_salesPrice = (EditText) findViewById(R.id.p10_salesPrice);
        p11_salesPrice  = (EditText) findViewById(R.id.p11_salesPrice);
        p12_salesPrice = (EditText) findViewById(R.id.p12_salesPrice);
        p13_salesPrice  = (EditText) findViewById(R.id.p13_salesPrice);
        p14_salesPrice  = (EditText) findViewById(R.id.p14_salesPrice);

        totalAmount = (TextView) findViewById(R.id.totalPriceAmount) ;
        paymentReceivedAmount = (EditText) findViewById(R.id.paymentReceivedAmount) ;
        outstandingAmount = (TextView) findViewById(R.id.outStandingAmount) ;

        submitDatabase = (Button) findViewById(R.id.submitDatabase);
        submitDatabase.setVisibility(View.INVISIBLE);

        final String todayDate = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        final String todayTime = sdf.format(d);

        final String userName = Common.currentUser.getName();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ProductDetails1").child(userName);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        getUserDataReference = FirebaseDatabase.getInstance().getReference("PermanentGps").child(Common.currentUser.getPhone());

        textView = (TextView) findViewById(R.id.text_address);
        mTextAddress = (TextView) findViewById(R.id.text_address_geolocation);

        getUserDataReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    lat = dataSnapshot.child("lat").getValue().toString();
                    lng = dataSnapshot.child("lng").getValue().toString();

                    textView.setText("lat :" + String.valueOf(lat) + "\n" + "lng :" + String.valueOf(lng));

                    latitude = Double.parseDouble(lat);
                    longitude = Double.parseDouble(lng);
                }

                Geocoder gcd = new Geocoder(Product1.this, Locale.getDefault());
                List<Address> addresses = null;
                try
                {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && addresses.size() > 0)
                    {
                        // If any additional address line present than only, check with max available address lines by
                        // getMaxAddressLineIndex()

                        address = addresses.get(0).getAddressLine(0);
                        city = addresses.get(0).getLocality();
                        state = addresses.get(0).getAdminArea();
                        country = addresses.get(0).getCountryName();
                        postalCode = addresses.get(0).getPostalCode();
                        knownName = addresses.get(0).getFeatureName();

                        mTextAddress.setText(address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName);

                        getUserDataReference.keepSynced(true);
                    }
                    else
                    {
                        //         Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    //           Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        }) ;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        p1_salesPrice = (EditText) findViewById(R.id.p1_salesPrice);
        p1_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p1_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = null;
                String input = p1_quantity.getText().toString();
                String salesPrice = p1_salesPrice.getText().toString() ;
                if (!s.toString().isEmpty())
                {
                    if (salesPrice.length() > 0 && input.length() > 0)
                    {
                        int d = Integer.parseInt(salesPrice);
                        int e = Integer.parseInt(input);
                        int f = d * e;
                        p1_pay.setText(String.valueOf(f));
                    }


                }
                else
                {
                    p1_pay.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        p1_quantity = (EditText) findViewById(R.id.p1_quantity);
        p1_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p1_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = null;
                String input = p1_quantity.getText().toString();
                String salesPrice = p1_salesPrice.getText().toString() ;
                if (!s.toString().isEmpty())
                {
                    if (salesPrice.length() > 0 && input.length() > 0)
                    {
                        int d = Integer.parseInt(salesPrice);
                        int e = Integer.parseInt(input);
                        int f = d * e;
                        p1_pay.setText(String.valueOf(f));
                    }
                }
                else
                {
                    p1_pay.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p2_salesPrice = (EditText) findViewById(R.id.p2_salesPrice);
        p2_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p2_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = null;
                String input = p2_quantity.getText().toString();
                String salesPrice = p2_salesPrice.getText().toString() ;
                if (!s.toString().isEmpty())
                {
                    if (salesPrice.length() > 0 && input.length() > 0)
                    {
                        int d = Integer.parseInt(salesPrice);
                        int e = Integer.parseInt(input);
                        int f = d * e;
                        p2_pay.setText(String.valueOf(f));
                    }
                }
                else
                {
                    p2_pay.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p2_quantity = (EditText) findViewById(R.id.p2_quantity);
        p2_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p2_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = null;
                String input = p2_quantity.getText().toString();
                String salesPrice = p2_salesPrice.getText().toString() ;
                if (!s.toString().isEmpty())
                {
                    if (salesPrice.length() > 0 && input.length() > 0)
                    {
                        int d = Integer.parseInt(salesPrice);
                        int e = Integer.parseInt(input);
                        int f = d * e;
                        p2_pay.setText(String.valueOf(f));
                    }
                }
                else
                {
                    p2_pay.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p3_salesPrice = (EditText) findViewById(R.id.p3_salesPrice);
        p3_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p3_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = null;
                String input = p3_quantity.getText().toString();
                String salesPrice = p3_salesPrice.getText().toString() ;
                if (!s.toString().isEmpty())
                {
                    if (salesPrice.length() > 0 && input.length() > 0)
                    {
                        int d = Integer.parseInt(salesPrice);
                        int e = Integer.parseInt(input);
                        int f = d * e;
                        p3_pay.setText(String.valueOf(f));
                    }


                }
                else
                {
                    p3_pay.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p3_quantity = (EditText) findViewById(R.id.p3_quantity);
        p3_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p3_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = null;
                String input = p3_quantity.getText().toString();
                String salesPrice = p3_salesPrice.getText().toString() ;
                if (!s.toString().isEmpty())
                {
                    if (salesPrice.length() > 0 && input.length() > 0)
                    {
                        int d = Integer.parseInt(salesPrice);
                        int e = Integer.parseInt(input);
                        int f = d * e;
                        p3_pay.setText(String.valueOf(f));
                    }


                }
                else
                {
                    p3_pay.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p4_salesPrice = (EditText) findViewById(R.id.p4_salesPrice);
        p4_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p4_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p4_quantity.getText().toString();
                String salesPrice = p4_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p4_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p4_quantity = (EditText) findViewById(R.id.p4_quantity);
        p4_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p4_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p4_quantity.getText().toString();
                String salesPrice = p4_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p4_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p5_salesPrice = (EditText) findViewById(R.id.p5_salesPrice);
        p5_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p5_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p5_quantity.getText().toString();
                String salesPrice = p5_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p5_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p5_quantity = (EditText) findViewById(R.id.p5_quantity);
        p5_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p5_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p5_quantity.getText().toString();
                String salesPrice = p5_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p5_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p6_salesPrice = (EditText) findViewById(R.id.p6_salesPrice);
        p6_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p6_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p6_quantity.getText().toString();
                String salesPrice = p6_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p6_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p6_quantity = (EditText) findViewById(R.id.p6_quantity);
        p6_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p6_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p6_quantity.getText().toString();
                String salesPrice = p6_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p6_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p7_salesPrice = (EditText) findViewById(R.id.p7_salesPrice);
        p7_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p7_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p7_quantity.getText().toString();
                String salesPrice = p7_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p7_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p7_quantity = (EditText) findViewById(R.id.p7_quantity);
        p7_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p7_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p7_quantity.getText().toString();
                String salesPrice = p7_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p7_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p8_salesPrice = (EditText) findViewById(R.id.p8_salesPrice);
        p8_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p8_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p8_quantity.getText().toString();
                String salesPrice = p8_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p8_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p8_quantity = (EditText) findViewById(R.id.p8_quantity);
        p8_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p8_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p8_quantity.getText().toString();
                String salesPrice = p8_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p8_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p9_salesPrice = (EditText) findViewById(R.id.p9_salesPrice);
        p9_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p9_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p9_quantity.getText().toString();
                String salesPrice = p9_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p9_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p9_quantity = (EditText) findViewById(R.id.p9_quantity);
        p9_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p9_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p9_quantity.getText().toString();
                String salesPrice = p9_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p9_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p10_salesPrice = (EditText) findViewById(R.id. p10_salesPrice);
        p10_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p10_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input =  p10_quantity.getText().toString();
                String salesPrice =  p10_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p10_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p10_quantity = (EditText) findViewById(R.id. p10_quantity);
        p10_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p10_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input =  p10_quantity.getText().toString();
                String salesPrice =  p10_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p10_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p11_salesPrice = (EditText) findViewById(R.id.p11_salesPrice);
        p11_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p11_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p11_quantity.getText().toString();
                String salesPrice = p11_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p11_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p11_quantity = (EditText) findViewById(R.id.p11_quantity);
        p11_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p11_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p11_quantity.getText().toString();
                String salesPrice = p11_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p11_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p12_salesPrice = (EditText) findViewById(R.id.p12_salesPrice);
        p12_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p12_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p12_quantity.getText().toString();
                String salesPrice = p12_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p12_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p12_quantity = (EditText) findViewById(R.id.p12_quantity);
        p12_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p12_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p12_quantity.getText().toString();
                String salesPrice = p12_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p12_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p13_salesPrice = (EditText) findViewById(R.id.p13_salesPrice);
        p13_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p13_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p13_quantity.getText().toString();
                String salesPrice = p13_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p13_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p13_quantity = (EditText) findViewById(R.id.p13_quantity);
        p13_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p13_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p13_quantity.getText().toString();
                String salesPrice = p13_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p13_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p14_salesPrice = (EditText) findViewById(R.id.p14_salesPrice);
        p14_salesPrice.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p14_salesPrice.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p14_quantity.getText().toString();
                String salesPrice = p14_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p14_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        p14_quantity = (EditText) findViewById(R.id.p14_quantity);
        p14_quantity.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10")});
        p14_quantity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String current = "";
                String input = p14_quantity.getText().toString();
                String salesPrice = p14_salesPrice.getText().toString() ;
                if (!s.toString().equals(current))
                {
                    int d = Integer.parseInt(salesPrice);
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    p14_pay.setText(String.valueOf(f));
                }
                else
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        productName.setVisibility(View.INVISIBLE);
        mRP.setVisibility(View.INVISIBLE);
        salesPrice.setVisibility(View.INVISIBLE);
        quantity.setVisibility(View.INVISIBLE);
        pay.setVisibility(View.INVISIBLE);

        p1_mrp.setVisibility(View.INVISIBLE);
        p1_salesPrice.setVisibility(View.INVISIBLE);
        p1_quantity.setVisibility(View.INVISIBLE);
        p1_pay.setVisibility(View.INVISIBLE);

        p2_mrp.setVisibility(View.INVISIBLE);
        p2_salesPrice.setVisibility(View.INVISIBLE);
        p2_quantity.setVisibility(View.INVISIBLE);
        p2_pay.setVisibility(View.INVISIBLE);

        p3_mrp.setVisibility(View.INVISIBLE);
        p3_salesPrice.setVisibility(View.INVISIBLE);
        p3_quantity.setVisibility(View.INVISIBLE);
        p3_pay.setVisibility(View.INVISIBLE);

        p4_mrp.setVisibility(View.INVISIBLE);
        p4_salesPrice.setVisibility(View.INVISIBLE);
        p4_quantity.setVisibility(View.INVISIBLE);
        p4_pay.setVisibility(View.INVISIBLE);

        p5_mrp.setVisibility(View.INVISIBLE);
        p5_salesPrice.setVisibility(View.INVISIBLE);
        p5_quantity.setVisibility(View.INVISIBLE);
        p5_pay.setVisibility(View.INVISIBLE);

        p6_mrp.setVisibility(View.INVISIBLE);
        p6_salesPrice.setVisibility(View.INVISIBLE);
        p6_quantity.setVisibility(View.INVISIBLE);
        p6_pay.setVisibility(View.INVISIBLE);

        p7_mrp.setVisibility(View.INVISIBLE);
        p7_salesPrice.setVisibility(View.INVISIBLE);
        p7_quantity.setVisibility(View.INVISIBLE);
        p7_pay.setVisibility(View.INVISIBLE);

        p8_mrp.setVisibility(View.INVISIBLE);
        p8_salesPrice.setVisibility(View.INVISIBLE);
        p8_quantity.setVisibility(View.INVISIBLE);
        p8_pay.setVisibility(View.INVISIBLE);

        p9_mrp.setVisibility(View.INVISIBLE);
        p9_salesPrice.setVisibility(View.INVISIBLE);
        p9_quantity.setVisibility(View.INVISIBLE);
        p9_pay.setVisibility(View.INVISIBLE);

        p10_mrp.setVisibility(View.INVISIBLE);
        p10_salesPrice.setVisibility(View.INVISIBLE);
        p10_quantity.setVisibility(View.INVISIBLE);
        p10_pay.setVisibility(View.INVISIBLE);

        p11_mrp.setVisibility(View.INVISIBLE);
        p11_salesPrice.setVisibility(View.INVISIBLE);
        p11_quantity.setVisibility(View.INVISIBLE);
        p11_pay.setVisibility(View.INVISIBLE);

        p12_mrp.setVisibility(View.INVISIBLE);
        p12_salesPrice.setVisibility(View.INVISIBLE);
        p12_quantity.setVisibility(View.INVISIBLE);
        p12_pay.setVisibility(View.INVISIBLE);

        p13_mrp.setVisibility(View.INVISIBLE);
        p13_salesPrice.setVisibility(View.INVISIBLE);
        p13_quantity.setVisibility(View.INVISIBLE);
        p13_pay.setVisibility(View.INVISIBLE);

        p14_mrp.setVisibility(View.INVISIBLE);
        p14_salesPrice.setVisibility(View.INVISIBLE);
        p14_quantity.setVisibility(View.INVISIBLE);
        p14_pay.setVisibility(View.INVISIBLE);

        p1_name = (CheckBox) findViewById(R.id.p1_name);
        p1_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p1_name.isChecked())
                {
                    p1_mrp.setVisibility(View.VISIBLE);
                    p1_salesPrice.setVisibility(View.VISIBLE);
                    p1_quantity.setVisibility(View.VISIBLE);
                    p1_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p1_mrp.setVisibility(View.INVISIBLE);
                    p1_salesPrice.setVisibility(View.INVISIBLE);
                    p1_quantity.setVisibility(View.INVISIBLE);
                    p1_pay.setVisibility(View.INVISIBLE);

                    p1_quantity.setText("0");
                    p1_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p2_name = (CheckBox) findViewById(R.id.p2_name);
        p2_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p2_name.isChecked())
                {
                    p2_mrp.setVisibility(View.VISIBLE);
                    p2_salesPrice.setVisibility(View.VISIBLE);
                    p2_quantity.setVisibility(View.VISIBLE);
                    p2_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p2_mrp.setVisibility(View.INVISIBLE);
                    p2_salesPrice.setVisibility(View.INVISIBLE);
                    p2_quantity.setVisibility(View.INVISIBLE);
                    p2_pay.setVisibility(View.INVISIBLE);

                    p2_quantity.setText("0");
                    p2_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");

                }
            }
        });

        p3_name = (CheckBox) findViewById(R.id.p3_name);
        p3_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p3_name.isChecked())
                {
                    p3_mrp.setVisibility(View.VISIBLE);
                    p3_salesPrice.setVisibility(View.VISIBLE);
                    p3_quantity.setVisibility(View.VISIBLE);
                    p3_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p3_mrp.setVisibility(View.INVISIBLE);
                    p3_salesPrice.setVisibility(View.INVISIBLE);
                    p3_quantity.setVisibility(View.INVISIBLE);
                    p3_pay.setVisibility(View.INVISIBLE);

                    p3_quantity.setText("0");
                    p3_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p4_name = (CheckBox) findViewById(R.id.p4_name);
        p4_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p4_name.isChecked())
                {
                    p4_mrp.setVisibility(View.VISIBLE);
                    p4_salesPrice.setVisibility(View.VISIBLE);
                    p4_quantity.setVisibility(View.VISIBLE);
                    p4_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p4_mrp.setVisibility(View.INVISIBLE);
                    p4_salesPrice.setVisibility(View.INVISIBLE);
                    p4_quantity.setVisibility(View.INVISIBLE);
                    p4_pay.setVisibility(View.INVISIBLE);

                    p4_quantity.setText("0");
                    p4_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p5_name = (CheckBox) findViewById(R.id.p5_name);
        p5_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p5_name.isChecked())
                {
                    p5_mrp.setVisibility(View.VISIBLE);
                    p5_salesPrice.setVisibility(View.VISIBLE);
                    p5_quantity.setVisibility(View.VISIBLE);
                    p5_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p5_mrp.setVisibility(View.INVISIBLE);
                    p5_salesPrice.setVisibility(View.INVISIBLE);
                    p5_quantity.setVisibility(View.INVISIBLE);
                    p5_pay.setVisibility(View.INVISIBLE);

                    p5_quantity.setText("0");
                    p5_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p6_name = (CheckBox) findViewById(R.id.p6_name);
        p6_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p6_name.isChecked())
                {
                    p6_mrp.setVisibility(View.VISIBLE);
                    p6_salesPrice.setVisibility(View.VISIBLE);
                    p6_quantity.setVisibility(View.VISIBLE);
                    p6_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p6_mrp.setVisibility(View.INVISIBLE);
                    p6_salesPrice.setVisibility(View.INVISIBLE);
                    p6_quantity.setVisibility(View.INVISIBLE);
                    p6_pay.setVisibility(View.INVISIBLE);

                    p6_quantity.setText("0");
                    p6_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p7_name = (CheckBox) findViewById(R.id.p7_name);
        p7_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p7_name.isChecked())
                {
                    p7_mrp.setVisibility(View.VISIBLE);
                    p7_salesPrice.setVisibility(View.VISIBLE);
                    p7_quantity.setVisibility(View.VISIBLE);
                    p7_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p7_mrp.setVisibility(View.INVISIBLE);
                    p7_salesPrice.setVisibility(View.INVISIBLE);
                    p7_quantity.setVisibility(View.INVISIBLE);
                    p7_pay.setVisibility(View.INVISIBLE);

                    p7_quantity.setText("0");
                    p7_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p8_name = (CheckBox) findViewById(R.id.p8_name);
        p8_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p8_name.isChecked())
                {
                    p8_mrp.setVisibility(View.VISIBLE);
                    p8_salesPrice.setVisibility(View.VISIBLE);
                    p8_quantity.setVisibility(View.VISIBLE);
                    p8_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p8_mrp.setVisibility(View.INVISIBLE);
                    p8_salesPrice.setVisibility(View.INVISIBLE);
                    p8_quantity.setVisibility(View.INVISIBLE);
                    p8_pay.setVisibility(View.INVISIBLE);

                    p8_quantity.setText("0");
                    p8_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p9_name = (CheckBox) findViewById(R.id.p9_name);
        p9_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p9_name.isChecked())
                {
                    p9_mrp.setVisibility(View.VISIBLE);
                    p9_salesPrice.setVisibility(View.VISIBLE);
                    p9_quantity.setVisibility(View.VISIBLE);
                    p9_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p9_mrp.setVisibility(View.INVISIBLE);
                    p9_salesPrice.setVisibility(View.INVISIBLE);
                    p9_quantity.setVisibility(View.INVISIBLE);
                    p9_pay.setVisibility(View.INVISIBLE);

                    p9_quantity.setText("0");
                    p9_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p10_name = (CheckBox) findViewById(R.id.p10_name);
        p10_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p10_name.isChecked())
                {
                    p10_mrp.setVisibility(View.VISIBLE);
                    p10_salesPrice.setVisibility(View.VISIBLE);
                    p10_quantity.setVisibility(View.VISIBLE);
                    p10_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p10_mrp.setVisibility(View.INVISIBLE);
                    p10_salesPrice.setVisibility(View.INVISIBLE);
                    p10_quantity.setVisibility(View.INVISIBLE);
                    p10_pay.setVisibility(View.INVISIBLE);

                    p10_quantity.setText("0");
                    p10_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p11_name = (CheckBox) findViewById(R.id.p11_name);
        p11_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p11_name.isChecked())
                {
                    p11_mrp.setVisibility(View.VISIBLE);
                    p11_salesPrice.setVisibility(View.VISIBLE);
                    p11_quantity.setVisibility(View.VISIBLE);
                    p11_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p11_mrp.setVisibility(View.INVISIBLE);
                    p11_salesPrice.setVisibility(View.INVISIBLE);
                    p11_quantity.setVisibility(View.INVISIBLE);
                    p11_pay.setVisibility(View.INVISIBLE);

                    p11_quantity.setText("0");
                    p11_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p12_name = (CheckBox) findViewById(R.id.p12_name);
        p12_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p12_name.isChecked())
                {
                    p12_mrp.setVisibility(View.VISIBLE);
                    p12_salesPrice.setVisibility(View.VISIBLE);
                    p12_quantity.setVisibility(View.VISIBLE);
                    p12_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p12_mrp.setVisibility(View.INVISIBLE);
                    p12_salesPrice.setVisibility(View.INVISIBLE);
                    p12_quantity.setVisibility(View.INVISIBLE);
                    p12_pay.setVisibility(View.INVISIBLE);

                    p12_quantity.setText("0");
                    p12_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p13_name = (CheckBox) findViewById(R.id.p13_name);
        p13_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p13_name.isChecked())
                {
                    p13_mrp.setVisibility(View.VISIBLE);
                    p13_salesPrice.setVisibility(View.VISIBLE);
                    p13_quantity.setVisibility(View.VISIBLE);
                    p13_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p13_mrp.setVisibility(View.INVISIBLE);
                    p13_salesPrice.setVisibility(View.INVISIBLE);
                    p13_quantity.setVisibility(View.INVISIBLE);
                    p13_pay.setVisibility(View.INVISIBLE);

                    p13_quantity.setText("0");
                    p13_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        p14_name = (CheckBox) findViewById(R.id.p14_name);
        p14_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (p14_name.isChecked())
                {
                    p14_mrp.setVisibility(View.VISIBLE);
                    p14_salesPrice.setVisibility(View.VISIBLE);
                    p14_quantity.setVisibility(View.VISIBLE);
                    p14_pay.setVisibility(View.VISIBLE);

                    productName.setVisibility(View.VISIBLE);
                    mRP.setVisibility(View.VISIBLE);
                    salesPrice.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                }
                else
                {
                    p14_mrp.setVisibility(View.INVISIBLE);
                    p14_salesPrice.setVisibility(View.INVISIBLE);
                    p14_quantity.setVisibility(View.INVISIBLE);
                    p14_pay.setVisibility(View.INVISIBLE);

                    p14_quantity.setText("0");
                    p14_salesPrice.setText("0");

                    totalAmount.setText("0");
                    paymentReceivedAmount.setText("0");
                    outstandingAmount.setText("0");
                }
            }
        });

        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String one = p1_pay.getText().toString();
                String two = p2_pay.getText().toString();
                String three = p3_pay.getText().toString();
                String four = p4_pay.getText().toString();
                String five = p5_pay.getText().toString();
                String six = p6_pay.getText().toString();
                String seven = p7_pay.getText().toString();
                String eight = p8_pay.getText().toString();
                String nine = p9_pay.getText().toString();
                String ten = p10_pay.getText().toString();
                String eleven = p11_pay.getText().toString();
                String twelve = p12_pay.getText().toString();
                String thirteen = p13_pay.getText().toString();
                String fourteen = p14_pay.getText().toString();

                String paymentReceived = paymentReceivedAmount.getText().toString();

                if (p1_name.isChecked() || p2_name.isChecked() || p3_name.isChecked() || p4_name.isChecked() || p5_name.isChecked() ||
                        p6_name.isChecked() || p7_name.isChecked() || p8_name.isChecked() || p9_name.isChecked() ||
                        p10_name.isChecked() || p11_name.isChecked() || p12_name.isChecked() || p13_name.isChecked() || p14_name.isChecked())
                {
                    if (p1_name.isChecked() )
                    {
                        if (one.length() < 2 )
                        {
                            Toast.makeText(Product1.this , "Enter Details of 0.5W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p1_finalResult.setText(p1_name.getText().toString() +  "             " + p1_mrp.getText().toString() + "         "
                                    + p1_salesPrice.getText().toString() + "         " + p1_quantity.getText().toString() + "         "
                                    + p1_pay.getText().toString() + "\n");
                        }
                    }

                    if (p2_name.isChecked())
                    {
                        if (two.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 5W Led Candle Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p2_finalResult.setText(p2_name.getText().toString() +  "             " + p2_mrp.getText().toString() + "         "
                                    + p2_salesPrice.getText().toString() + "         " + p2_quantity.getText().toString() + "         "
                                    + p2_pay.getText().toString() + "\n");
                        }
                    }

                    if (p3_name.isChecked())
                    {
                        if (three.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 3W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p3_finalResult.setText(p3_name.getText().toString() +  "             " + p3_mrp.getText().toString() + "         "
                                    + p3_salesPrice.getText().toString() + "         " + p3_quantity.getText().toString() + "         "
                                    + p3_pay.getText().toString() + "\n");
                        }
                    }

                    if (p4_name.isChecked())
                    {
                        if (four.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 5W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p4_finalResult.setText(p4_name.getText().toString() +  "             " + p4_mrp.getText().toString() + "         "
                                    + p4_salesPrice.getText().toString() + "         " + p4_quantity.getText().toString()+ "         "
                                    + p4_pay.getText().toString() + "\n");
                        }
                    }

                    if (p5_name.isChecked())
                    {
                        if (five.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 7W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p5_finalResult.setText(p5_name.getText().toString() + "             " + p5_mrp.getText().toString() + "         " +
                                    p5_salesPrice.getText().toString() + "         " + p5_quantity.getText().toString() + "         " +
                                    p5_pay.getText().toString() + "\n");
                        }
                    }

                    if (p6_name.isChecked())
                    {
                        if (six.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 9W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p6_finalResult.setText(p6_name.getText().toString() + "            " + p6_mrp.getText().toString() + "         "
                                    + p6_salesPrice.getText().toString() + "         " + p6_quantity.getText().toString() + "         "
                                    + p6_pay.getText().toString() + "\n");
                        }
                    }

                    if (p7_name.isChecked())
                    {
                        if (seven.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 12W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p7_finalResult.setText(p7_name.getText().toString() + "             " + p7_mrp.getText().toString() + "         "
                                    + p7_salesPrice.getText().toString() + "         " + p7_quantity.getText().toString() + "         "
                                    + p7_pay.getText().toString() + "\n");
                        }
                    }

                    if (p8_name.isChecked())
                    {
                        if (eight.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 15W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p8_finalResult.setText(p8_name.getText().toString() + "              " + p8_mrp.getText().toString() + "         "
                                    + p8_salesPrice.getText().toString() + "         " + p8_quantity.getText().toString() + "         "
                                    + p8_pay.getText().toString() + "\n");
                        }
                    }

                    if (p9_name.isChecked())
                    {
                        if (nine.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 20W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p9_finalResult.setText(p9_name.getText().toString() + "              " + p9_mrp.getText().toString() + "         "
                                    + p9_salesPrice.getText().toString() + "         " + p9_quantity.getText().toString() + "         "
                                    + p9_pay.getText().toString() + "\n");
                        }
                    }

                    if (p10_name.isChecked())
                    {
                        if (ten.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 26W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p10_finalResult.setText(p10_name.getText().toString() + "             " + p10_mrp.getText().toString() + "         "
                                    + p10_salesPrice.getText().toString() + "         " + p10_quantity.getText().toString() + "         "
                                    + p10_pay.getText().toString() + "\n");
                        }
                    }

                    if (p11_name.isChecked())
                    {
                        if (eleven.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 36W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p11_finalResult.setText(p11_name.getText().toString() + "             " + p11_mrp.getText().toString() + "         "
                                    + p11_salesPrice.getText().toString() + "         " +p11_quantity.getText().toString() + "         " +
                                    p11_pay.getText().toString() + "\n");

                        }
                    }

                    if (p12_name.isChecked())
                    {
                        if (twelve.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 45W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p12_finalResult.setText(p12_name.getText().toString() + "             " + p12_mrp.getText().toString() + "         "
                                    + p12_salesPrice.getText().toString() + "         " + p12_quantity.getText().toString() + "         "
                                    + p12_pay.getText().toString() + "\n");
                        }
                    }

                    if (p13_name.isChecked())
                    {
                        if (thirteen.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 9W Led Lamp" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p13_finalResult.setText(p13_name.getText().toString() + "             " + p13_mrp.getText().toString() + "         " +
                                    p13_salesPrice.getText().toString() + "         " + p13_quantity.getText().toString() + " "+ "         "  +
                                    p13_pay.getText().toString() + "\n");


                        }
                    }

                    if (p14_name.isChecked())
                    {
                        if (fourteen.length() < 2)
                        {
                            Toast.makeText(Product1.this , "Enter Details of 45W T Bulb" , Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            p14_finalResult.setText(p14_name.getText().toString() + "             " + p14_mrp.getText().toString() + "         "
                                    + p14_salesPrice.getText().toString() + "         " + p14_quantity.getText().toString() + "         "
                                    + p14_pay.getText().toString()+ "\n");
                        }
                    }

                    if (paymentReceivedAmount.length() < 2)
                    {
                        Toast.makeText(Product1.this , "Enter Amount Received" , Toast.LENGTH_SHORT).show();

                        submitDatabase.setVisibility(View.INVISIBLE);
                    }

                    int amount1 = Integer.parseInt(one);
                    int amount2 = Integer.parseInt(two);
                    int amount3 = Integer.parseInt(three);
                    int amount4 = Integer.parseInt(four);
                    int amount5 = Integer.parseInt(five);
                    int amount6 = Integer.parseInt(six);
                    int amount7 = Integer.parseInt(seven);
                    int amount8 = Integer.parseInt(eight);
                    int amount9 = Integer.parseInt(nine);
                    int amount10 = Integer.parseInt(ten);
                    int amount11 = Integer.parseInt(eleven);
                    int amount12 = Integer.parseInt(twelve);
                    int amount13 = Integer.parseInt(thirteen);
                    int amount14 = Integer.parseInt(fourteen);

                    int totalMoney = amount1 + amount2 + amount3 + amount4 + amount5 + amount6 + amount7 + amount8 + amount9 + amount10
                            + amount11 + amount12 + amount13 + amount14 ;

                    String moneyReceived = paymentReceivedAmount.getText().toString();

                    int amountReceived = Integer.parseInt(moneyReceived);

                    int balance = totalMoney - amountReceived ;

                    totalAmount.setText(String.valueOf(totalMoney));

                    outstandingAmount.setText(String.valueOf(balance));

                    String total_database = totalAmount.getText().toString();
                    String payment_database = paymentReceivedAmount.getText().toString();
                    String outstanding_database = outstandingAmount.getText().toString();

                    if (total_database.length() > 1 && payment_database.length() > 1)
                    {
                        submitButton.setVisibility(View.INVISIBLE);
                        submitDatabase.setVisibility(View.VISIBLE);

                        submitDatabase.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {

                                String item1 = p1_finalResult.getText().toString();
                                String item2 = p2_finalResult.getText().toString();
                                String item3 = p3_finalResult.getText().toString();
                                String item4 = p4_finalResult.getText().toString();
                                String item5 = p5_finalResult.getText().toString();
                                String item6 = p6_finalResult.getText().toString();
                                String item7 = p7_finalResult.getText().toString();
                                String item8 = p8_finalResult.getText().toString();
                                String item9 = p9_finalResult.getText().toString();
                                String item10 = p10_finalResult.getText().toString();
                                String item11 = p11_finalResult.getText().toString();
                                String item12 = p12_finalResult.getText().toString();
                                String item13 = p13_finalResult.getText().toString();
                                String item14 = p14_finalResult.getText().toString();

                                ProductDatabase1 productDatabase1 = new ProductDatabase1
                                        (
                                                Common.currentUser.getName(),
                                                Common.currentUser.getPhone(),
                                                todayTime,
                                                todayDate,
                                                item1 ,
                                                item2 ,
                                                item3 ,
                                                item4 ,
                                                item5 ,
                                                item6 ,
                                                item7 ,
                                                item8 ,
                                                item9 ,
                                                item10 ,
                                                item11 ,
                                                item12 ,
                                                item13 ,
                                                item14 ,
                                                totalAmount.getText().toString(),
                                                paymentReceivedAmount.getText().toString(),
                                                outstandingAmount.getText().toString(),
                                                address + " " + city + " " + state + " " + country + " "
                                                        + postalCode + " "+ knownName,
                                                lat,
                                                lng
                                        );

                                String order_number = String.valueOf(System.currentTimeMillis());

                                mDatabaseRef.child(order_number).setValue(productDatabase1);

                                Toast.makeText(Product1.this , "Details Sent Successfully" , Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Product1.this , Product1.class);
                                startActivity(intent);
                            }
                        });
                    }
                }

                else
                {
                    Toast.makeText(Product1.this , "Enter Details" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
