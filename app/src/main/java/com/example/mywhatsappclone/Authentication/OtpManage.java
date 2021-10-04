package com.example.mywhatsappclone.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mywhatsappclone.MainActivity;
import com.example.mywhatsappclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OtpManage extends AppCompatActivity {
    EditText otpEt;
    Button verifyBtn;
    String phoneNumber;
    FirebaseAuth mAuth;
    String otpId;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_manage);
        phoneNumber = getIntent().getStringExtra("mobileNum");
        otpEt = (EditText) findViewById(R.id.otpInputET);
        verifyBtn = (Button) findViewById(R.id.verifyOtpBtn);
        mAuth = FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.circular_progress_bar);
        Log.i("Error Tracing","Inside OtpManage");
        initiateOtp(); // we called this before for the cases when sim in same phone or error in verification in both cases we dont need to press the verify button it should happen automatically

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpEt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Otp", Toast.LENGTH_LONG).show();
                }
                else if(otpEt.getText().toString().length()!=6){
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Otp", Toast.LENGTH_LONG).show();
                }
                else{
                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otpId,otpEt.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }

    private void initiateOtp() {
        Log.i("Error tracing","start of initateotp");
        //the method below is used to send otp
     //   progressBar.setVisibility(ProgressBar.INVISIBLE);

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {// when sim is not in the same device
                                // super.onCodeSent(s, forceResendingToken);
                               // progressBar.setVisibility(ProgressBar.INVISIBLE);
                                Log.i("Error tracing","Inside Code Sent");
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                otpId = s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {// when sim is in the same device
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        })        // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        Log.i("Error tracing","end of initateotp");

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        Log.i("Error tracing","inside signInWithPhone");

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                           // progressBar.setVisibility(ProgressBar.INVISIBLE);
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");

                            //FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            startActivity(new Intent(OtpManage.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error occured During Automatic Otp Verification", Toast.LENGTH_LONG).show();

//                            // Sign in failed, display a message and update the UI
//                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                // The verification code entered was invalid
//                            }
                        }
                    }
                });
    }


}