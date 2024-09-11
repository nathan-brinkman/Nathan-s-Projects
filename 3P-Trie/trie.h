/*
 * trie.h
 *
 * Declaration of the trie class.
 * 
 * Author: <your name here>
 */

#ifndef _TRIE_H
#define _TRIE_H

#include <string>
#include <vector>

using namespace std;

class trie {
public:
    void insert(const string &s);
    bool contains(const string &s);
    bool is_prefix(const string &s);
    void extend(const string &prefix, vector<string> &result);

private:

    class trie_node {
    public:

        trie_node();

        bool is_terminal;
        trie_node* children[26];

        void extend_helper(string prefix, vector<string> &result);    
    };

    trie_node root_node;

};

#endif
