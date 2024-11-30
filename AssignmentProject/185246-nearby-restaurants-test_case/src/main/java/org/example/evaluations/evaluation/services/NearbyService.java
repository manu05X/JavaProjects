package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NearbyService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String LOCATION_KEY = "nearby_restaurants";


    public Boolean addLocation(Restaurant restaurant) {
        GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();
        Point point = new Point(restaurant.getLongitude(), restaurant.getLatitude());
        geoOps.add(LOCATION_KEY, point, restaurant.getId());

        redisTemplate.opsForHash().put(LOCATION_KEY, restaurant.getId(), restaurant);
        return true;
    }

    public List<Restaurant> findNearbyLocations(double userLatitude, double userLongitude, double radius) {
        GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();
        Point userLocation = new Point(userLongitude, userLatitude);

        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> geoResults = geoOps.radius(LOCATION_KEY, userLocation, radius).getContent();

        return geoResults.stream()
                .map(geoResult -> geoResult.getContent().getName())
                .map(id -> (Restaurant) redisTemplate.opsForHash().get(LOCATION_KEY, id))
                .filter(restaurant -> restaurant != null)
                .collect(Collectors.toList());
    }
}
