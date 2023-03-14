package com.Sapient.WeatherApp.data;

import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.models.TrieNode;

/**
 * Class store to store RawResponses against given keys
 */
public class Trie{
    public final TrieNode root;
    
    public Trie(){
        root = new TrieNode(null);
    }
    
    /**
     * Publicly exposed wrapper to retrieve RawResponse against a input key
     * @return RawResponse    
     */
    public RawResponse findString(final String location){
        return findString(location.toLowerCase(), 0, root);
    }
    
    /** 
     * Publicly exposed wrapper to add a new entry in the trie
    */
    public void addString(final String location, 
                          final RawResponse rawResponse){
        addString(location.toLowerCase(), 0, root, rawResponse);
    }
    
    /**
     * Publicly exposed wrapper to remove an entry in the trie
     */
    public void removeString(final String location){
        removeString(location.toLowerCase(), 0, root);
    }
    
    /**
     * Recursive function to retrieve `Response` against a given key
     * @return RawResponse
     */
    private RawResponse findString(final String location, 
                               final Integer index,
                               final TrieNode root){
    
        if(index==location.length()){
            return root.rawResponse;
        }
        
        Integer pos = (int)location.charAt(index) - (int)'a';
        pos = pos<0?location.length()-1:pos;
        
        if(root.children[pos] == null){
            return null;
        }
        
        return findString(location, index+1, root.children[pos]);
    }
    
    /**
     * Recursive function to add an entry in the trie
     */
    private void addString(final String location, 
                           final Integer index,
                           final TrieNode root,
                           final RawResponse rawResponse){
    
        if(index>=location.length()){
            return;
        }
        
        Integer pos = (int)location.charAt(index) - (int)'a';
        pos = pos<0?location.length()-1:pos;

        if(root.children[pos] == null){
            if(index==location.length()-1){
                root.children[pos] = new TrieNode(rawResponse);
            } else {
                root.children[pos] = new TrieNode(null);
            }
        }
        
        addString(location, index+1, root.children[pos], rawResponse);
    }

    /**
     * Recursive function to remove a entry in the trie
     */
    private Boolean removeString(final String location, 
                             final Integer index,
                             final TrieNode root){
        
        if(index>=location.length()){
            return true;
        }
        
        Integer pos = (int)location.charAt(index) - (int)'a';
        pos = pos<0?location.length()-1:pos;

        if(root.children[pos] == null){
            return true;
        }
        
        if(removeString(location, index+1, root.children[pos])){
            root.children[pos] = null;
        }
        
        if(!root.hasChildren()){
            return true;
        }
        
        return false;
    }
}