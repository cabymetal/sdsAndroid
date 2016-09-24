package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.sdsandroid.sds.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Carlos Murillo on 24/06/2016.
 * Personal ASUS
 */
public class ListCheckActivity extends Fragment {
    private ContentAdapter mContentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        View view = inflater.inflate(R.layout.list_check_fragment, container, false);
        ButterKnife.bind(this, view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_fragment_recycler);
        mContentAdapter = new ContentAdapter(recyclerView.getContext());

        recyclerView.setAdapter(mContentAdapter);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    /*
    * this method reads the confirm button and displays a message with the selected options
     */
    @OnClick(R.id.button_confirm)
    public void confirmSelection(){
        StringBuffer stringBuffer = new StringBuffer();
        for (String c : mContentAdapter.mListChecked) {
            stringBuffer.append(c);
            stringBuffer.append("\n");
        }

        Toast.makeText(getContext(), stringBuffer.toString(), Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("checkboxes", mContentAdapter.mListChecked);
        this.setArguments(bundle);

        getFragmentManager().popBackStackImmediate();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface ItemClickListener{
        public void onItemSelected(View v, int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView listName;
        public TextView listDescription;
        public CheckBox isSelected;
        public ItemClickListener listener;

        public ViewHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item, viewGroup, false));
            listName = (TextView) itemView.findViewById(R.id.list_name);
            listDescription = (TextView) itemView.findViewById(R.id.list_desc);
            isSelected = (CheckBox) itemView.findViewById(R.id.checkbox);
            isSelected.setChecked(true);
            isSelected.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic){
            this.listener = ic;
        }

        @Override
        public void onClick(View view){
            this.listener.onItemSelected(view, getLayoutPosition());
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{

        private String[] mListName;
        private String[] mListDescription;
        private ArrayList<String> mListChecked = new ArrayList<>();
        private ArrayList<String> mObject= new ArrayList<>();



        public ContentAdapter(Context context){
            Resources resources = context.getResources();
            mListName = resources.getStringArray(R.array.list_names);
            mListDescription= resources.getStringArray(R.array.list_descriptions);

            for (String listName: mListName) {
                mObject.add(listName);
            }

            if(mObject.size() != 0 && mListChecked.isEmpty()) {
                this.mListChecked = mObject;
            }

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


            holder.setItemClickListener((v, pos) -> {
                CheckBox checkBox = (CheckBox) v;
                if(checkBox.isChecked()){
                    mListChecked.add(mObject.get(pos));
                }else if(!checkBox.isChecked()){
                    mListChecked.remove(mObject.get(pos));
                }
            });
        }

        @Override
        public int getItemCount(){
            return mListName.length;
        }
    }
}


