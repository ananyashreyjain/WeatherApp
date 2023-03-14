package com.Sapient.WeatherApp.data;

import com.Sapient.WeatherApp.data.Heap;
import com.Sapient.WeatherApp.data.Trie;
import com.Sapient.WeatherApp.models.Request;
import com.Sapient.WeatherApp.models.RawResponse;

import java.time.LocalDateTime;

/**
 * Class that caches the responses for incoming requests
 */
public class Cache {
    private final Heap heap;
    private final Trie trie;
    private static Cache cache;

    private Cache(){
        trie = new Trie();
        heap = new Heap(trie);
    }

    /**
     * Function to return the instance of singleton class
     */
    public static Cache getCache(){
        if(Cache.cache == null){
            Cache.cache = new Cache();
        }
        return Cache.cache;
    }

    /**
     * Function to check the incoming request with the Cache memory
     * @return RawResponse The Cached response for the request
     */
    public RawResponse getHistory(final Request request){
        heap.cleanUp(LocalDateTime.now());
        return trie.findString(request.location);
    }

    /**
     * Function to store add new data in the cache
     */
    public void storeHistory(final Request request, final RawResponse rawResponse){
        heap.cleanUp(LocalDateTime.now());
        heap.addNew(request.location, LocalDateTime.now());
        trie.addString(request.location, rawResponse);
    }
}
