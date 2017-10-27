package com.randomizer.android.webapi;

import com.randomizer.android.model.response.RandomUsersListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static com.randomizer.android.constants.AppConstants.DEFAULT_PAGE_SIZE;

public interface UsersApiService {

    //    https://randomuser.me/api/?
    //    results=20&
    //    seed=seed
    //    page=1&

    @GET("?results="+DEFAULT_PAGE_SIZE+"&seed=seed")
    Observable<RandomUsersListResponse> getUsers(@Query("page") String pageNumber);

}
