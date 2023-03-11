package com.Sapient.WeatherApp.data;

import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.models.TrieNode;

public class Trie{
    public final TrieNode root;
    
    public Trie(){
        root = new TrieNode(null);
    }
    
    public RawResponse findString(final String location){
        return findString(location.toLowerCase(), 0, root);
    }
    
    public void addString(final String location, 
                          final RawResponse rawResponse){
        addString(location.toLowerCase(), 0, root, rawResponse);
    }
    
    public void removeString(final String location){
        removeString(location.toLowerCase(), 0, root);
    }
    
    private RawResponse findString(final String location, 
                               final Integer index,
                               final TrieNode root){
    
        if(index==location.length()){
            return root.rawResponse;
        }
        
        Integer pos = (int)location.charAt(index) - (int)'a';
        if(root.children[pos] == null){
            return null;
        }
        
        return findString(location, index+1, root.children[pos]);
    }
    
    private void addString(final String location, 
                           final Integer index,
                           final TrieNode root,
                           final RawResponse rawResponse){
    
        if(index>=location.length()){
            return;
        }
        
        Integer pos = (int)location.charAt(index) - (int)'a';

        if(root.children[pos] == null){
            if(index==location.length()-1){
                root.children[pos] = new TrieNode(rawResponse);
            } else {
                root.children[pos] = new TrieNode(null);
            }
        }
        
        addString(location, index+1, root.children[pos], rawResponse);
    }
    
    private Boolean removeString(final String location, 
                             final Integer index,
                             final TrieNode root){
        
        if(index>=location.length()){
            return true;
        }
        
        Integer pos = (int)location.charAt(index) - (int)'a';
        
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