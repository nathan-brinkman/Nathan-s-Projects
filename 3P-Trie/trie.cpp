/*
 * trie.cpp
 *
 * Method definitions for the trie class.
 *
 * Author: <your name here>
 */

#include <iostream>

#include "trie.h"

void trie::insert(const string &s){

    //cout << "hi" << endl;

    trie_node* node_pointer = &root_node;

    for(unsigned i=0; i<s.length(); i++){
        if(node_pointer->children[s.at(i)-97] == nullptr){
            trie_node* new_node = new trie_node();
            node_pointer->children[s.at(i)-97] = new_node;
        }

        //cout << s.at(i);

        node_pointer = node_pointer->children[s.at(i)-97];
    }

    node_pointer->is_terminal = true;

    //cout << endl;

}

bool trie::contains(const string &s){

    trie_node* node_pointer = &root_node;

    for(unsigned i=0; i<s.length(); i++){
        if(node_pointer->children[s.at(i)-97] == nullptr){
            return false;
        }
        node_pointer = node_pointer->children[s.at(i)-97];
    }

    return node_pointer->is_terminal;
}

bool trie::is_prefix(const string &s){

    trie_node* node_pointer = &root_node;

    for(unsigned i=0; i<s.length(); i++){
        if(node_pointer->children[s.at(i)-97] == nullptr){
            return false;
        }
        node_pointer = node_pointer->children[s.at(i)-97];
    }

    return true;

}

void trie::trie_node::extend_helper(string prefix, vector<string> &result){
    if(is_terminal == true){
        result.push_back(prefix);
        //cout << prefix << endl;
    }

    for(unsigned i=0; i<26; i++){
        if(children[i] != nullptr){
           children[i]->extend_helper(prefix + (char)(97 + i), result);
        }
    }

    
}

void trie::extend(const string &prefix, vector<string> &result){

    trie_node* node_pointer = &root_node;

    for(unsigned i=0; i<prefix.length(); i++){
        if(node_pointer->children[prefix.at(i)-'a'] == nullptr){
            return;
        }
        node_pointer = node_pointer->children[prefix.at(i)-'a'];
    }

    node_pointer->extend_helper(prefix, result);

}

trie::trie_node::trie_node(){

    is_terminal = false;

    for(unsigned i=0; i<26; i++){
        children[i] = nullptr;
    }

}
