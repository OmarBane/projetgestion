package com.example.gestionabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    Button crash;
    TextView titre;
    FirebaseAuth nFirebase;
    FirebaseAuth.AuthStateListener nAuth;
    private FirebaseFirestore   mF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nFirebase=FirebaseAuth.getInstance();
            email=findViewById(R.id.email);
            password=findViewById(R.id.password);
            login=findViewById(R.id.login);
            titre=findViewById(R.id.titre);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mail=email.getText().toString();
                    String pass=password.getText().toString();

                   if(mail.isEmpty()){
                        email.setError("Hot email ya bro");
                        email.requestFocus();
                    }else if(pass.isEmpty()){
                        password.setError("Hot pass ya bro");
                        password.requestFocus();
                    }
                    else if (mail.isEmpty() && pass.isEmpty()){
                        Toast.makeText(MainActivity.this,"fields are empty",Toast.LENGTH_LONG).show();
                    }
                    else if(!(mail.isEmpty() && pass.isEmpty())){
                        nFirebase.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Login error try again",Toast.LENGTH_LONG).show();

                                }else{

                                    mF=FirebaseFirestore.getInstance();
                                    final String mm=email.getText().toString();
                                    mF.collection("users").document(mm).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.isSuccessful()) {
                                                DocumentSnapshot DS = task.getResult();
                                                String type = DS.getString("type");
                                                String nom = DS.getString("Nom");
                                                if (type.equals("Prof")){
                                                    Intent inttoprof=new Intent(MainActivity.this,prof.class);
                                                    inttoprof.putExtra("nom", nom);
                                                    startActivity(inttoprof);
                                                }else{

                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }

                }
            });
    }
}
