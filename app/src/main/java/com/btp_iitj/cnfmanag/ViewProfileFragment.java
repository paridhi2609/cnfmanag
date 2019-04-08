package com.btp_iitj.cnfmanag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProfileFragment extends Fragment {

    public static TextView name, designation, dob, mobile, email;
    public static Button save;
    public static FragmentManager fragmentManager;
    private static final String TAG = "Suppport";
    private FirebaseFirestore db;
    public ViewProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_profile, container, false);
        name=view.findViewById(R.id.vname);
        designation=view.findViewById(R.id.vdesignation);
        dob=view.findViewById(R.id.vdob);
        mobile= view.findViewById(R.id.vphone);
        email=view.findViewById(R.id.vemail);
        save=view.findViewById(R.id.edit_profile);

        name.setText("name: ");
        designation.setText("designation: ");
        dob.setText("dob: ");
        mobile.setText("mobile: ");
        email.setText("email: ");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new EditProfileFragment()).commit();
            }
        });

        return view;
    }

}
