package limited.pvt.global.stachi.glisteruser.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import limited.pvt.global.stachi.glisteruser.app.Model.InputFilterMinMax;

public class RoughLayoutRoughLayout extends AppCompatActivity
{
    TextView productName , mRP , salesPrice , quantity , pay ;
    TextView p1_mrp , p2_mrp , p3_mrp , p4_mrp , p5_mrp ;
    TextView p1_pay , p2_pay , p3_pay , p4_pay , p5_pay ;
    CheckBox p1_name , p2_name , p3_name , p4_name , p5_name ;
    EditText p1_salesPrice , p2_salesPrice , p3_salesPrice , p4_salesPrice , p5_salesPrice ;
    EditText p1_quantity , p2_quantity , p3_quantity , p4_quantity , p5_quantity ;
    TextView databaseLogic , databaeNames ;
    TextView total , paymentReceived , outstanding , totalAmount , paymentReceivedAmount , outstandingAmount ;
    Button submitButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rough_layout);



    }
}
