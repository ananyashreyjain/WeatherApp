package com.Sapient.WeatherApp.data;

import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.data.Trie;
import com.Sapient.WeatherApp.data.Heap;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

public class HeapTest {

    @Test
    void testHeapAddition(){
        Trie trie = mock(Trie.class);
        doNothing().when(trie).removeString(anyString());
        Heap heap = new Heap(trie);
        String location1 = "Lucknow";
        String location2 = "Ludhiana";
        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t2 = t1.plusHours(1);
        heap.addNew(location1, t1);
        heap.addNew(location2, t2);
        assert heap.getTop().location == location1;
        assert heap.getTop().timeStamp == t1;
    }

    @Test
    void testHeapDeletion(){
        Trie trie = mock(Trie.class);
        doNothing().when(trie).removeString(anyString());
        Heap heap = new Heap(trie);
        String location1 = "Lucknow";
        String location2 = "Ludhiana";
        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t2 = t1.plusHours(2);
        heap.addNew(location1, t1);
        heap.addNew(location2, t2);
        heap.cleanUp(t2);
        assert heap.getTop().location == location2;
        assert heap.getTop().timeStamp == t2;
    }
}
