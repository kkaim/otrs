package biz.kaim.otrs.restaurantservice.service;

import biz.kaim.otrs.restaurantservice.domain.Restaurant;
import biz.kaim.otrs.restaurantservice.repository.RestaurantRepository;

public class BaseService<T, T1> {
    public BaseService(RestaurantRepository<Restaurant, String> restaurantRepository) {

    }

    public void add(T restaurant) throws Exception {

    }
}
