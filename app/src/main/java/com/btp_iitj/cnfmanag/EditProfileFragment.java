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
public class EditProfileFragment extends Fragment {
    public static EditText name, designation, dob, mobile, email;
    public static Button save;
    private static final String TAG = "Suppport";
    private FirebaseFirestore db;
    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        name=view.findViewById(R.id.uname);
        designation=view.findViewById(R.id.udesignation);
        dob=view.findViewById(R.id.udob);
        mobile= view.findViewById(R.id.uphone);
        email=view.findViewById(R.id.uemail);
        save=view.findViewById(R.id.save_user);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                final String name1=name.getText().toString();
                final String designation1=designation.getText().toString();
                final String dob1=dob.getText().toString();
                final String mobile1=mobile.getText().toString();
                final String email1=email.getText().toString();
                Map<String, Object> conference_user = new HashMap<>();
                db=FirebaseFirestore.getInstance();
                conference_user.put("name",name1);
                conference_user.put("designation",designation1);
                conference_user.put("dob",dob1);
                conference_user.put("email",email1);
                conference_user.put("mobile",mobile1);

                Toast.makeText(getActivity(), "Data successfully Edited", Toast.LENGTH_SHORT).show();


                db.collection("CONFERENCE_USER").document()
                        .set(conference_user)
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
