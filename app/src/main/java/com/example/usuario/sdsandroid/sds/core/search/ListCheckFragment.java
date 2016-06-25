package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.usuario.sdsandroid.sds.R;

/**
 * Created by Carlos Murillo on 24/06/2016.
 * Personal ASUS
 */
public class ListCheckFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.list_check_fragment, parent, false);
        ContentAdapter contentAdapter = new ContentAdapter(recyclerView.getContext());

        recyclerView.setAdapter(contentAdapter);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView listName;
        public TextView listDescription;
        public CheckBox isSelected;

        public ViewHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item, viewGroup, false));
            listName = (TextView) itemView.findViewById(R.id.list_name);
            listDescription = (TextView) itemView.findViewById(R.id.list_desc);
            isSelected = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{

        private String[] mListName;
        private String[] mListDescription;
        private CheckBox[] mListChecked;

        public ContentAdapter(Context context){
            Resources resources = context.getResources();
            mListName = resources.getStringArray(R.array.list_names);
            mListDescription= resources.getStringArray(R.array.list_descriptions);

        }
        @Override
        public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            holder.listDescription.setText(mListDescription[position]);
            holder.listName.setText(mListName[position]);
            holder.isSelected.setTag(position);
            holder.isSelected.setChecked(true);
        }

        @Override
        public int getItemCount(){
            return mListName.length;
        }
    }
}


