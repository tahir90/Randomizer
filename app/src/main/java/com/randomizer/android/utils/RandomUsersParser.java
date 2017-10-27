package com.randomizer.android.utils;

import com.randomizer.android.model.RandomUser;
import com.randomizer.android.model.response.RandomUsersListResponse;
import com.randomizer.android.model.response.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RandomUsersParser {

    @Inject
    public RandomUsersParser() {
    }

    public List<RandomUser> parseUsers(RandomUsersListResponse response) {
        List<RandomUser> list = new ArrayList<>();

        if (response != null) {
            List<Result> results = response.getResults();
            if (results != null) {
                for (Result result : results) {
                    RandomUser ru = new RandomUser(
                            result.getEmail(),
                            result.getName().getTitle(),
                            result.getName().getFirst(),
                            result.getName().getLast(),
                            result.getPhone(),
                            result.getGender(),
                            result.getPicture().getLarge(),
                            AppUtils.getFormatedAddress(result.getLocation())
                    );
                    list.add(ru);
                }
            }
        }
        return list;
    }
}


