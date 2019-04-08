package com.btp_iitj.cnfmanag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class Conference_Adapter extends FirestoreRecyclerAdapter<conference_1,Conference_Adapter.conference_holder> {

    private onItemCLickListener listener;

    public Conference_Adapter(@NonNull FirestoreRecyclerOptions<conference_1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull conference_holder holder, final int position, @NonNull conference_1 model) {
        holder.cname.setText(model.getName());
        holder.cdate.setText(model.getDate());
        holder.cvenue.setText(model.getVenue());

        Log.d("suthar", "Model: " + model.toString());

    }

    @NonNull
    @Override
    public conference_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view,viewGroup,false);
        return new conference_holder(v);
    }
    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class conference_holder extends RecyclerView.ViewHolder{
    TextView cname, cdate, cvenue;
        public conference_holder(@NonNull View itemView) {
            super(itemView);
            cname=itemView.findViewById(R.id.tv1);
            cdate=itemView.findViewById(R.id.tv2);
            cvenue=itemView.findViewById(R.id.tv3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listener!=null)
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                }
            });

        }

    }
    public interface onItemCLickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemCLickLIstener(onItemCLickListener listener){
        this.listener=listener;
    }
}
