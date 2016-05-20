package com.example.usuario.sdsandroid.sds.core.search;

import com.example.usuario.api.pojo.ResponseList;

/**
 * Created by Carlos Murillo on 08/05/2016.
 * Personal ASUS
 */
public interface Contract {
    interface SearchToolView{
        void setUserError(String error);
        void setIdError(String error);
        void setMinPercentage();

        void backToLogin();
    }

    interface SearchPresenter{
        void onDestroy();
        void onSearchButtonSubmitClick(String loggedUser, String loggedPassword, String searchUser,
                                       String searchId, String docType, int percentage);

    }

    interface SearchInteractor{
        void onSearchSuccess(ResponseList responseList);
        void onSearchError(Throwable error);

    }
}
