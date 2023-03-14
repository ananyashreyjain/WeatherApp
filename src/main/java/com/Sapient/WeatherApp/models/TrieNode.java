package com.Sapient.WeatherApp.models;

import com.Sapient.WeatherApp.models.RawResponse;

/**
 * Model for the Trie nodes
 */
public class TrieNode{
    public static Integer SIZE = 27;
    public final TrieNode[] children;
    public final RawResponse rawResponse;
    public TrieNode(final RawResponse rawResponse){
        children = new TrieNode[26];
        this.rawResponse = rawResponse;
    }
    public Boolean hasChildren(){
        for(int i=0;i<26;i++){
            if(children[i]!=null){
                return true;
            }
        }
        return false;
    }
}