package biz.kaim.restaurantservice.otrs.controller;

import biz.kaim.restaurantservice.otrs.domain.Restaurant;
import biz.kaim.restaurantservice.otrs.repository.RestaurantRepository;
import biz.kaim.restaurantservice.otrs.service.RestaurantService;
import biz.kaim.restaurantservice.otrs.service.RestaurantServiceImpl;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RestaurantControllerTests extends AbstractRestaurantControllerTests {

    protected static final Restaurant restaurantStaticInstance = new Restaurant(RESTAURANT,
            RESTAURANT_NAME, RESTAURANT_ADDRESS, null);
    protected TestRestaurantRepository testRestaurantRepository = new TestRestaurantRepository();
    protected RestaurantService restaurantService = new RestaurantServiceImpl(testRestaurantRepository);

    @Before

    public void setup() {

        restaurantController = new RestaurantController(restaurantService);

    }

    protected static class TestRestaurantRepository implements RestaurantRepository<Restaurant, String> {
        private Map<String, Restaurant> entities;

        public TestRestaurantRepository() {
            entities = new HashMap();
            Restaurant restaurant = new Restaurant(RESTAURANT_NAME, RESTAURANT, RESTAURANT_ADDRESS, null);
            entities.put("1", restaurant);
            restaurant = new Restaurant("O Restaurant", "2", "Address of O Restaurant", null);
            entities.put("2", restaurant);
        }

        @Override
        public boolean containsName(String name) {
            try {
                return this.findByName(name).size() > 0;
            } catch (Exception ex) {
                //Exception Handler
            }
            return false;
        }

        /**
         * @param entity
         */
        @Override
        public void add(Restaurant entity) {
            entities.put(entity.getId(), entity);
        }

        @Override
        public void remove(String id) {
            if (entities.containsKey(id)) {
                entities.remove(id);
            }
        }


        @Override
        public void update(Restaurant entity) {
            if (entities.containsKey(entity.getId())) {
                entities.put(entity.getId(), entity);
            }
        }

        @Override
        public Collection<Restaurant> findByName(String name) throws Exception {
            Collection<Restaurant> restaurants = new ArrayList();
            int noOfChars = name.length();
            entities.forEach((k, v) -> {
                if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                    restaurants.add(v);
                }
            });
            return restaurants;
        }

        @Override
        public boolean contains(String id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Restaurant get(String id) {
            return entities.get(id);
        }

        @Override
        public Collection<Restaurant> getAll() {
            return entities.values();
        }
    }

}