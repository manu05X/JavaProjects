package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Restaurant;
import org.example.evaluations.evaluation.services.NearbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NearbyServiceTest {

    @InjectMocks
    private NearbyService nearbyService;

    @Mock
    private RedisTemplate<String,Object> redisTemplate;

   @Mock
    private RedisConnectionFactory redisConnectionFactory;

   @Mock
    private RedisConnection redisConnectionMock;

   @Mock
   private GeoOperations<String,Object> geoOperations;

   @Mock
   private HashOperations hashOperations;


    @BeforeEach
    public void setUp() {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        when(redisConnectionFactory.getConnection()).thenReturn(redisConnectionMock);
        redisTemplate.afterPropertiesSet();

        when(redisTemplate.opsForGeo()).thenReturn(geoOperations);
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
    }

    @Test
    public void testAddLocation() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setLatitude(40.7128);
        restaurant.setLongitude(-74.0060);

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(redisTemplate.opsForGeo()).thenReturn(geoOperations);

        Boolean result = nearbyService.addLocation(restaurant);

        verify(geoOperations, times(1)).add(eq("nearby_restaurants"), any(Point.class), eq(restaurant.getId()));
        verify(redisTemplate.opsForHash(), times(1)).put(eq("nearby_restaurants"), eq(restaurant.getId()), eq(restaurant));
        assertTrue(result);
    }

    @Test
    public void testFindNearbyLocations() {
        double userLatitude = 40.7128;
        double userLongitude = -74.0060;
        double radius = 5.0;

        Distance distance = new Distance(5.0);
        RedisGeoCommands.GeoLocation<Object> geoLocation = new RedisGeoCommands.GeoLocation<>(1L, new Point(userLongitude, userLatitude));
        GeoResult<RedisGeoCommands.GeoLocation<Object>> geoResult = new GeoResult<>(geoLocation, distance);
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> geoResults = Collections.singletonList(geoResult);

        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResultsWrapper = new GeoResults<>(geoResults,distance);

        when(geoOperations.radius(any(String.class), any(Point.class), anyDouble()))
                .thenReturn(geoResultsWrapper);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setLatitude(40.7128);
        restaurant.setLongitude(-74.0060);

        when(hashOperations.get(any(String.class), any())).thenReturn(restaurant);

        List<Restaurant> nearbyRestaurants = nearbyService.findNearbyLocations(userLatitude, userLongitude, radius);

        assertNotNull(nearbyRestaurants);
        assertFalse(nearbyRestaurants.isEmpty());
        assertEquals(1, nearbyRestaurants.size());
        assertEquals("Test Restaurant", nearbyRestaurants.get(0).getName());

        verify(geoOperations, times(1)).radius(eq("nearby_restaurants"), any(Point.class), eq(radius));
    }
}

