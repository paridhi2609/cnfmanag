package com.btp_iitj.cnfmanag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewCOnference extends Fragment {
    private EditText name, venue, date, description;
    private Button save;
    private static final String TAG = "Suppport";
    private FirebaseFirestore db;

    public NewCOnference() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_conference, container, false);
        name=view.findViewById(R.id.conf_name);
        venue=view.findViewById(R.id.venue);
        date=view.findViewById(R.id.date);
        description=view.findViewById(R.id.description);
        save=view.findViewById(R.id.SaveConf);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                final String name1=name.getText().toString();
                final String venue1=venue.getText().toString();
                final String date1=date.getText().toString();
                final String description1=description.getText().toString();
                Map<String, Object> conference = new HashMap<>();
                db=FirebaseFirestore.getInstance();
                conference.put("name",name1);
                conference.put("venue",date1);
                conference.put("date",date1);
                conference.put("venue",venue1);
                conference.put("description",description1);


                db.collection("CONFERENCE").document()
                        .set(conference)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "saved!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
            }
        });

        return view;
    }

}
