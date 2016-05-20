package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.usuario.api.pojo.ResponseList;
import com.example.usuario.sdsandroid.sds.R;
import com.example.usuario.sdsandroid.sds.common.TextResourceManager;
import com.example.usuario.sdsandroid.sds.common.Validator;
import com.example.usuario.sdsandroid.sds.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

/**
 * Created by Carlos Murillo on 06/05/2016.
 * Personal ASUS
 *
 * Important thing to notice here is that the fragment must instantiate the presenter in MVP Pattern
 */
public class SearchFragment  extends Fragment implements Contract.SearchToolView{
    private static final String EXTRA_USER = "search.user";
    private static final String EXTRA_PASSWORD = "search.password";
    private final int MIN_PERCENTAGE = 80;

    //UI attributes
    private SeekBar mSeekBar;
    private TextView mTextView;
    private Spinner mSpinner;
    @Bind(R.id.search_name)public EditText mSearchUser;
    @Bind(R.id.search_id)  public EditText mSearchId;
    @Bind(R.id.search_status_viewgroup) public ViewGroup mSearchStatus;
    private int percentage;

    //Presenter
    private Contract.SearchPresenter mSearchPresenter;

    //Logged parameters
    private String loggedUser;
    private String loggedPassword;

    public static SearchFragment newInstance(String user, String password){
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_USER, user);
        bundle.putString(EXTRA_PASSWORD, password);

        SearchFragment searchFragment= new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence> (this.getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tool_menu_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mTextView = (TextView) view.findViewById(R.id.seekbar_progress);

        mSearchStatus.setVisibility(View.VISIBLE);



        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                percentage = progress;
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

        //initialize the presenter
        mSearchPresenter = new SearchPresenterImpl(this, new Validator(), new TextResourceManager(getResources()), new SearchInteractorImpl());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        loggedUser = bundle.getString(EXTRA_USER);
        loggedPassword = bundle.getString(EXTRA_PASSWORD);
    }

    @OnClick(R.id.form_search_submit)
    public void searchPersonList(){
        //instantiate parameters to presenter
        String searchUser = mSearchUser.getText().toString();
        String searchId = mSearchId.getText().toString();
        String docType = mSpinner.getSelectedItem().toString();
        if( percentage < MIN_PERCENTAGE)
            setMinPercentage();

        //call the method
        mSearchPresenter.onSearchButtonSubmitClick(loggedUser, loggedPassword, searchUser, searchId, docType, percentage);
        mSearchStatus.setVisibility(View.VISIBLE);
    }


    @Override
    public void setUserError(String error) {
        mSearchUser.setError(error);
    }

    @Override
    public void setIdError(String error) {
        mSearchId.setError(error);
    }

    @Override
    public void setMinPercentage() {
        percentage = MIN_PERCENTAGE;
    }

    @Override
    public void backToLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
    //this function recieves te list and passit as parameter to the next view
    public void startDetailActivity(ResponseList responseList) {
        Intent intent = new Intent(getActivity(), SearchDetailActivity.class);
        intent.putExtra("XML_RESPONSE", responseList);
        startActivity(intent);
    }
}
