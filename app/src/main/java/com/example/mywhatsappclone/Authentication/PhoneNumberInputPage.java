package com.example.mywhatsappclone.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mywhatsappclone.MainActivity;
import com.example.mywhatsappclone.R;
import com.example.mywhatsappclone.databinding.ActivityPhoneNumberInputPageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class PhoneNumberInputPage extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText phoneNumEt;
    Button getOtpBtn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phone_number_input_page);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){ // to check if user is already signed in or not
            Intent intent= new Intent(PhoneNumberInputPage.this, MainActivity.class);
            startActivity(intent);
        }
     phoneNumEt=(EditText) findViewById(R.id.phoneNumberEt);
     ccp=(CountryCodePicker)findViewById(R.id.ccp);
     ccp.registerCarrierNumberEditText(phoneNumEt);

     getOtpBtn=(Button)findViewById(R.id.getOtpBtn);
     getOtpBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(PhoneNumberInputPage.this,OtpManage.class);
             //intent.putExtra("mobileNum",ccp.getFullNumberWithPlus().replace(" ",""));

             intent.putExtra("mobileNum",ccp.getFullNumberWithPlus().replace(" ",""));
             startActivity(intent);
         }
     });
    }
}