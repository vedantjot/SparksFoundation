package com.example.sparyfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.Invoice;
import com.razorpay.PaymentResultListener;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener
{
   Button donate;
   EditText amount;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());



        donate=findViewById(R.id.idBtnPay);
        amount=findViewById(R.id.idEdtAmount);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });

    }


    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_QhXT0FjEwpAs2S");
        checkout.setImage(R.drawable.logo);

        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Spark Foundation");
            options.put("description", "Reference No. #123456");

            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount.getText().toString()+"00");//pass amount in currency subunits

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("vvv", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s)
    {

        Toast.makeText(this,"Successful",Toast.LENGTH_LONG);


    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this,"Failed",Toast.LENGTH_LONG);
    }
}