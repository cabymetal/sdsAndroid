package com.example.usuario.sdsandroid.sds.core.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.usuario.sdsandroid.sds.R;

;

/**
 * Created by Carlos Murillo on 06/05/2016.
 * Personal ASUS
 */
public class SearchFragment  extends Fragment {
    private SeekBar mSeekBar;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        View view = inflater.inflate(R.layout.search_fragment, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence> (this.getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tool_menu_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mTextView = (TextView) view.findViewById(R.id.seekbar_progress);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                mTextView.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //mTextView.setVisibility(View.GONE); not at the moment better visibility
            }
        });
        return view;

    }


}
