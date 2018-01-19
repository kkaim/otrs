package biz.kaim.restaurantservice.otrs.service;

import biz.kaim.restaurantservice.otrs.domain.Restaurant;
import biz.kaim.restaurantservice.otrs.repository.RestaurantRepository;

public class BaseService<T, T1> {
    public BaseService(RestaurantRepository<Restaurant, String> restaurantRepository) {

    }

    public void add(T restaurant) throws Exception {

    }
}
