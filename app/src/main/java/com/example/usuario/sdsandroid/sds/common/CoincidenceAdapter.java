package com.example.usuario.sdsandroid.sds.common;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.api.repositories.person.pojo.Coincidence;
import com.example.usuario.api.repositories.person.pojo.Telephone;
import com.example.usuario.sdsandroid.sds.R;

import java.util.List;

/**
 * Created by Carlos Murillo on 18/05/2016.
 * Personal ASUS
 */
public class CoincidenceAdapter extends RecyclerView.Adapter<CoincidenceAdapter.CoincidenceHolder> {

    private List<Coincidence> mCoincidences;

    public CoincidenceAdapter(List<Coincidence> coincidences){
        mCoincidences = coincidences;
    }

    public static class CoincidenceHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView personId;
        private TextView personName;
        private TextView personNacionality;
        private TextView personPhone;

        public CoincidenceHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_view);
            personId = (TextView) itemView.findViewById(R.id.id_coincidence);
            personName = (TextView)itemView.findViewById(R.id.name_coincidence);
            personNacionality = (TextView)itemView.findViewById(R.id.nacionality_coincidence);
            personPhone = (TextView) itemView.findViewById(R.id.phone_numbers_coincidence);

        }

        public TextView getPersonId(){
            return personId;
        }

        public TextView getPersonName(){
            return personName;
        }

        public TextView getPersonNacionality(){
            return personNacionality;
        }

        public TextView getPersonPhone(){
            return personPhone;
        }
    }

    @Override
    public CoincidenceAdapter.CoincidenceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent,false);
        CoincidenceAdapter.CoincidenceHolder coincidenceHolder = new CoincidenceHolder(view);
        return coincidenceHolder;
    }

    @Override
    public void onBindViewHolder(CoincidenceAdapter.CoincidenceHolder holder, int position) {
        String phoneNumbers = "";
        List<Telephone> telephones = mCoincidences.get(position).getPhoneNumbers();
        for (Telephone telephone : telephones){
            phoneNumbers+= telephone.getItem() +", ";
        }
        holder.getPersonId().setText(mCoincidences.get(position).getId());
        holder.getPersonName().setText(mCoincidences.get(position).getName());
        holder.getPersonNacionality().setText(mCoincidences.get(position).getNacionality());
        holder.getPersonPhone().setText(phoneNumbers);
    }

    @Override
    public int getItemCount() {
        return mCoincidences.size();
    }
}
