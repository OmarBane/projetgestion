package com.example.gestionabs;

import android.os.Bundle;
import android.util.Log;


import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class prof extends AppCompatActivity {

        private RecyclerView nMainlist;
        private FirebaseFirestore mf ;
        private  static  final  String TAG ="firelog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        nMainlist =(RecyclerView) findViewById(R.id.main_list);
        mf = FirebaseFirestore.getInstance();
mf.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

if (e!= null){
    Log.d(TAG ,"error : " + e.getMessage());


}
for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
    if (doc.getType() == DocumentChange.Type.ADDED){
            String name =doc.getDocument().getString("Nom");
            Log.d(TAG,"name : "+name);



    }


}


    }
});

    }



}
