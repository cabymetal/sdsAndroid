package com.example.usuario.sdsandroid.sds.core.search;

import com.example.usuario.sdsandroid.sds.R;
import com.example.usuario.sdsandroid.sds.common.TextResourceManager;
import com.example.usuario.sdsandroid.sds.common.Validator;

/**
 * Created by Carlos Murillo on 08/05/2016.
 * Personal ASUS
 */
public class SearchPresenterImpl implements Contract.SearchPresenter, Contract.SearchInteractor {
    private SearchFragment mSearchFragment;
    private TextResourceManager mTextResourceManager;
    private Validator mValidator;
    private SearchInteractorImpl mSearchInteractorListener;

    public SearchPresenterImpl(SearchFragment fragment, Validator validator, TextResourceManager textResourceManager, SearchInteractorImpl searchInteractorListener){
        mSearchFragment = fragment;
        mValidator = validator;
        mTextResourceManager = textResourceManager;
        mSearchInteractorListener = searchInteractorListener;
    }

    @Override
    public void onDestroy() {
        mSearchFragment = null;
    }

    @Override
    public void onSearchButtonSubmitClick(String loggedUser, String loggedPassword, String searchUser, String searchId, String docType, int percentage) {
        if(mValidator.isEmpty(searchUser) && mValidator.isEmpty(searchId)){
            mSearchFragment.setUserError(mTextResourceManager.get(R.string.error_search_empty_user));
            mSearchFragment.setIdError(mTextResourceManager.get(R.string.error_search_empty_id));
            return;
        }

        mSearchInteractorListener.performSearch(loggedUser,loggedPassword, this);
    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchError() {

    }
}
