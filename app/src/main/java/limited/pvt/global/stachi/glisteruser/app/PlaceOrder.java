package limited.pvt.global.stachi.glisteruser.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;

import limited.pvt.global.stachi.glisteruser.app.Model.InputFilterMinMax;

public class PlaceOrder extends AppCompatActivity
{
    CheckBox p1_name , p2_name , p3_name , p4_name , p5_name , p6_name , p7_name , p8_name , p9_name , p10_name , p11_name , p12_name ,
             p13_name , p14_name , p15_name ;

    Button submit_button ;

    EditText p1_quantity , p2_quantity , p3_quantity , p4_quantity , p5_quantity , p6_quantity , p7_quantity , p8_quantity ,
             p9_quantity , p10_quantity , p11_quantity , p12_quantity , p13_quantity , p14_quantity , p15_quantity ;

    TextView productName , mRP , salesPrice , quantity , pay ;

    TextView p1_mrp , p2_mrp , p3_mrp , p4_mrp , p5_mrp , p6_mrp , p7_mrp , p8_mrp , p9_mrp , p10_mrp , p11_mrp , p12_mrp ,
             p13_mrp , p14_mrp , p15_mrp ;

    TextView p1_salesPrice , p2_salesPrice , p3_salesPrice , p4_salesPrice , p5_salesPrice , p6_salesPrice , p7_salesPrice ,
             p8_salesPrice , p9_salesPrice , p10_salesPrice , p11_salesPrice , p12_salesPrice , p13_salesPrice , p14_salesPrice ,
             p15_salesPrice ;

    TextView vegetable_price1, vegetable_price2, vegetable_price3, vegetable_price4, vegetable_price5, vegetable_price6,
            vegetable_price7, vegetable_price8, vegetable_price9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

 /*       quant1 = (EditText) findViewById(R.id.editText1);
        quant1.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant1.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 100;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost1.setText(String.valueOf(f));
                } else {
                    cost1.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant2 = (EditText) findViewById(R.id.editText2);
        quant2.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant2.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 130;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost2.setText(String.valueOf(f));
                } else {
                    cost2.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant3 = (EditText) findViewById(R.id.editText3);
        quant3.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant3.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 120;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost3.setText(String.valueOf(f));
                } else {
                    cost3.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        quant4 = (EditText) findViewById(R.id.editText4);
        quant4.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant4.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 120;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost4.setText(String.valueOf(f));
                } else {
                    cost4.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant5 = (EditText) findViewById(R.id.editText5);
        quant5.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant5.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 150;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost5.setText(String.valueOf(f));
                } else {
                    cost5.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant6 = (EditText) findViewById(R.id.editText6);
        quant6.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant6.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 160;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost6.setText(String.valueOf(f));
                } else {
                    cost6.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant7 = (EditText) findViewById(R.id.editText7);
        quant7.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant7.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 170;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost7.setText(String.valueOf(f));
                } else {
                    cost7.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant8 = (EditText) findViewById(R.id.editText8);
        quant8.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant8.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 180;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost8.setText(String.valueOf(f));
                } else {
                    cost8.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quant9 = (EditText) findViewById(R.id.editText9);
        quant9.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10")});
        quant9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = "";
                String input = quant9.getText().toString();
                if (!s.toString().equals(current)) {
                    int d = 190;
                    int e = Integer.parseInt(input);
                    int f = d * e;
                    cost9.setText(String.valueOf(f));
                } else {
                    cost9.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        cost1 = (TextView) findViewById(R.id.pricetext1);
        cost2 = (TextView) findViewById(R.id.pricetext2);
        cost3 = (TextView) findViewById(R.id.pricetext3);
        cost4 = (TextView) findViewById(R.id.pricetext4);
        cost5 = (TextView) findViewById(R.id.pricetext5);
        cost6 = (TextView) findViewById(R.id.pricetext6);
        cost7 = (TextView) findViewById(R.id.pricetext7);
        cost8 = (TextView) findViewById(R.id.pricetext8);
        cost9 = (TextView) findViewById(R.id.pricetext9);

        vegetable_price1 = (TextView) findViewById(R.id.price_bakchoy);
        vegetable_price2 = (TextView) findViewById(R.id.price_spinach);
        vegetable_price3 = (TextView) findViewById(R.id.price_lettucelollo);
        vegetable_price4 = (TextView) findViewById(R.id.price_lettucemarvel);
        vegetable_price5 = (TextView) findViewById(R.id.price_kale);
        vegetable_price6 = (TextView) findViewById(R.id.price_mustardgreens);
        vegetable_price7 = (TextView) findViewById(R.id.price_beetgreen);
        vegetable_price8 = (TextView) findViewById(R.id.price_swisschard);
        vegetable_price9 = (TextView) findViewById(R.id.price_chienesecabbage);

        text_vegetable = (TextView) findViewById(R.id.textview_vegetable);
        text_price = (TextView) findViewById(R.id.textview_price);
        text_quantity = (TextView) findViewById(R.id.textview_quantity);
        text_payment = (TextView) findViewById(R.id.textview_payment);

        text_price.setVisibility(View.INVISIBLE);
        text_quantity.setVisibility(View.INVISIBLE);
        text_payment.setVisibility(View.INVISIBLE);

        quant1.setVisibility(View.INVISIBLE);
        cost1.setVisibility(View.INVISIBLE);
        vegetable_price1.setVisibility(View.INVISIBLE);

        quant2.setVisibility(View.INVISIBLE);
        cost2.setVisibility(View.INVISIBLE);
        vegetable_price2.setVisibility(View.INVISIBLE);

        quant3.setVisibility(View.INVISIBLE);
        cost3.setVisibility(View.INVISIBLE);
        vegetable_price3.setVisibility(View.INVISIBLE);

        quant4.setVisibility(View.INVISIBLE);
        cost4.setVisibility(View.INVISIBLE);
        vegetable_price4.setVisibility(View.INVISIBLE);

        quant5.setVisibility(View.INVISIBLE);
        cost5.setVisibility(View.INVISIBLE);
        vegetable_price5.setVisibility(View.INVISIBLE);

        quant6.setVisibility(View.INVISIBLE);
        cost6.setVisibility(View.INVISIBLE);
        vegetable_price6.setVisibility(View.INVISIBLE);

        quant7.setVisibility(View.INVISIBLE);
        cost7.setVisibility(View.INVISIBLE);
        vegetable_price7.setVisibility(View.INVISIBLE);

        quant8.setVisibility(View.INVISIBLE);
        cost8.setVisibility(View.INVISIBLE);
        vegetable_price8.setVisibility(View.INVISIBLE);

        quant9.setVisibility(View.INVISIBLE);
        cost9.setVisibility(View.INVISIBLE);
        vegetable_price9.setVisibility(View.INVISIBLE);

        button_Submit = (Button) findViewById(R.id.submit_order_button);

        check1 = (CheckBox) findViewById(R.id.check1);
        check1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (check1.isChecked())
                {
                    quant1.setVisibility(View.VISIBLE);
                    cost1.setVisibility(View.VISIBLE);
                    vegetable_price1.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                }
                else
                {
                    quant1.setVisibility(View.INVISIBLE);
                    cost1.setVisibility(View.INVISIBLE);
                    vegetable_price1.setVisibility(View.INVISIBLE);
                    quant1.setText("");
                }
            }
        });
        check2 = (CheckBox) findViewById(R.id.check2);
        check2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (check2.isChecked())
                {
                    quant2.setVisibility(View.VISIBLE);
                    cost2.setVisibility(View.VISIBLE);
                    vegetable_price2.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                }
                else
                {
                    quant2.setVisibility(View.INVISIBLE);
                    cost2.setVisibility(View.INVISIBLE);
                    vegetable_price2.setVisibility(View.INVISIBLE);
                    quant2.setText("");
                }
            }
        });
        check3 = (CheckBox) findViewById(R.id.check3);
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check3.isChecked()) {
                    quant3.setVisibility(View.VISIBLE);
                    cost3.setVisibility(View.VISIBLE);
                    vegetable_price3.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                } else {
                    quant3.setVisibility(View.INVISIBLE);
                    cost3.setVisibility(View.INVISIBLE);
                    vegetable_price3.setVisibility(View.INVISIBLE);
                    quant3.setText("");
                }
            }
        });
        check4 = (CheckBox) findViewById(R.id.check4);
        check4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (check4.isChecked())
                {
                    quant4.setVisibility(View.VISIBLE);
                    cost4.setVisibility(View.VISIBLE);
                    vegetable_price4.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                }
                else
                {
                    quant4.setVisibility(View.INVISIBLE);
                    cost4.setVisibility(View.INVISIBLE);
                    vegetable_price4.setVisibility(View.INVISIBLE);
                    quant4.setText("");
                }
            }
        });
        check5 = (CheckBox) findViewById(R.id.check5);
        check5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check5.isChecked()) {
                    quant5.setVisibility(View.VISIBLE);
                    cost5.setVisibility(View.VISIBLE);
                    vegetable_price5.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                } else {
                    quant5.setVisibility(View.INVISIBLE);
                    cost5.setVisibility(View.INVISIBLE);
                    vegetable_price5.setVisibility(View.INVISIBLE);
                    quant5.setText("");
                }
            }
        });
        check6 = (CheckBox) findViewById(R.id.check6);
        check6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check6.isChecked()) {
                    quant6.setVisibility(View.VISIBLE);
                    cost6.setVisibility(View.VISIBLE);
                    vegetable_price6.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                } else {
                    quant6.setVisibility(View.INVISIBLE);
                    cost6.setVisibility(View.INVISIBLE);
                    vegetable_price6.setVisibility(View.INVISIBLE);
                    quant6.setText("");
                }
            }
        });
        check7 = (CheckBox) findViewById(R.id.check7);
        check7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check7.isChecked()) {
                    quant7.setVisibility(View.VISIBLE);
                    cost7.setVisibility(View.VISIBLE);
                    vegetable_price7.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                } else {
                    quant7.setVisibility(View.INVISIBLE);
                    cost7.setVisibility(View.INVISIBLE);
                    vegetable_price7.setVisibility(View.INVISIBLE);
                    quant7.setText("");
                }
            }
        });
        check8 = (CheckBox) findViewById(R.id.check8);
        check8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check8.isChecked()) {
                    quant8.setVisibility(View.VISIBLE);
                    cost8.setVisibility(View.VISIBLE);
                    vegetable_price8.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                } else {
                    quant8.setVisibility(View.INVISIBLE);
                    cost8.setVisibility(View.INVISIBLE);
                    vegetable_price8.setVisibility(View.INVISIBLE);
                    quant8.setText("");
                }
            }
        });
        check9 = (CheckBox) findViewById(R.id.check9);
        check9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check9.isChecked()) {
                    quant9.setVisibility(View.VISIBLE);
                    cost9.setVisibility(View.VISIBLE);
                    vegetable_price9.setVisibility(View.VISIBLE);

                    text_price.setVisibility(View.VISIBLE);
                    text_quantity.setVisibility(View.VISIBLE);
                    text_payment.setVisibility(View.VISIBLE);
                } else {
                    quant9.setVisibility(View.INVISIBLE);
                    cost9.setVisibility(View.INVISIBLE);
                    vegetable_price9.setVisibility(View.INVISIBLE);
                    quant9.setText("");
                }
            }
        });  */
    }
}
