package com.Sapient.WeatherApp.data;

import com.Sapient.WeatherApp.models.RawResponse;
import org.junit.jupiter.api.Test;

public class TrieTest {
    @Test
    void testTrieInsertion(){
        Trie trie = new Trie();
        RawResponse rawResponse = new RawResponse();
        trie.addString("London", rawResponse);
        assert trie.findString("London") == rawResponse;
    }

    @Test
    void testTrieDeletion(){
        Trie trie = new Trie();
        RawResponse rawResponsePune = new RawResponse();
        RawResponse rawResponseLucknow = new RawResponse();
        RawResponse rawResponseLudhiana = new RawResponse();
        trie.addString("Pune", rawResponsePune);
        trie.addString("Lucknow", rawResponseLucknow);
        trie.addString("Ludhiana",rawResponseLudhiana);
        trie.removeString("Lucknow");
        assert trie.findString("Pune") == rawResponsePune;
        assert trie.findString("Lucknow") == null;
        assert trie.findString("Ludhiana") == rawResponseLudhiana;
    }
}
