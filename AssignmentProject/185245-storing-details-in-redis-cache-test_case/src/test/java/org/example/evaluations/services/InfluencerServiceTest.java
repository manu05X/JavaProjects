package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Influencer;
import org.example.evaluations.evaluation.services.InfluencerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InfluencerServiceTest {

    @InjectMocks
    private InfluencerService influencerService;

    @Mock
    private RedisTemplate<String,Object> redisTemplate;

   @Mock
    private RedisConnectionFactory redisConnectionFactory;

   @Mock
    private RedisConnection redisConnectionMock;

   @Mock
   private HashOperations hashOperations;



    @BeforeEach
    public void setUp() {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        when(redisConnectionFactory.getConnection()).thenReturn(redisConnectionMock);
        redisTemplate.afterPropertiesSet();
    }

    @Test
    public void testAddInfluencerToCache_WhenInfluencerIsPopular_ShouldReturnTrue() {
        Influencer influencer = new Influencer();
        influencer.setId(1L);
        influencer.setFollowers(600000L);
        influencer.setTotalPostReach(4000000L);

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);

        Boolean result = influencerService.addInfluencerToCache(influencer);

        assertTrue(result);
        verify(redisTemplate.opsForHash(), times(1)).put("influencer", influencer.getId(), influencer);
    }

    @Test
    public void testAddInfluencerToCache_WhenInfluencerIsNotPopular_ShouldReturnFalse() {
        Influencer influencer = new Influencer();
        influencer.setId(2L);
        influencer.setFollowers(300000L);
        influencer.setTotalPostReach(4000000L);

        Boolean result = influencerService.addInfluencerToCache(influencer);

        assertFalse(result);
        verify(hashOperations, never()).put(any(), any(), any());
    }

    @Test
    public void testGetInfluencerDetails_WhenExists_ShouldReturnInfluencer() {
        Influencer influencer = new Influencer();
        influencer.setId(1L);
        influencer.setName("John Doe");

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.get("influencer", 1L)).thenReturn(influencer);

        Influencer result = influencerService.getInfluencerDetails(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testGetInfluencerDetails_WhenNotExists_ShouldReturnNull() {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.get("influencer", 1L)).thenReturn(null);

        Influencer result = influencerService.getInfluencerDetails(1L);

        assertNull(result);
    }

    @Test
    public void testGetAllViralInfluencers() {
        Influencer influencer1 = new Influencer();
        influencer1.setId(1L);
        influencer1.setName("John Doe");

        Influencer influencer2 = new Influencer();
        influencer2.setId(2L);
        influencer2.setName("Jane Smith");

        List<Object> values = new ArrayList<>();
        values.add(influencer1);
        values.add(influencer2);

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);

        when(hashOperations.values("influencer")).thenReturn(values);

        List<Influencer> result = influencerService.getAllViralInfluencers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
    }
}

