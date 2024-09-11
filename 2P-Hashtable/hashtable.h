/*
 * hashtable.h
 *
 * A basic hashtable implementation.  This hashtable uses vectors,
 * rather than linked lists, to implement chaining.
 * 
 * Author: <your name here>
 */

#ifndef _HASHTABLE_PROJECT_H
#define _HASHTABLE_PROJECT_H

#include <functional>   // for std::hash
#include <vector>

using namespace std;

template <class T, class H = std::hash<T>>
class hashtable {
public:
    // constructor
    hashtable();

    // basic hashset operations
    void insert(const T& key);
    void remove(const T& key);
    bool contains(const T& key);
    size_t size();
    int count_comparisons(const T& key);

    // diagnostic functions
    double load_factor();

    // convenience method, invokes the "hash" template parameter
    // function object to get a hash code
    static size_t hash(const T &key) {
        H h;
        return h(key);
    }

    size_t _size;
    vector<vector<T>> hashVector;

private:
 
};

template <typename T, class H>
hashtable<T,H>::hashtable(){
    hashVector.resize(4);
    _size = 0;
}

template <typename T, class H>
void hashtable<T,H>::insert(const T& key){

    if(contains(key)){
        return;
    }

    _size++;
    size_t hash_code;
    if(((double)_size)/hashVector.size() > 0.75){
        //cout << "resize" << endl;
        vector<vector<T>> newHashVector;
        newHashVector.resize(hashVector.size()*2);
        for(unsigned i=0; i<hashVector.size(); i++){
            for(unsigned j=0; j<hashVector[i].size(); j++){
                hash_code = hash(hashVector[i][j]);
                newHashVector[hash_code%newHashVector.size()].push_back(hashVector[i][j]);
            }
        }

        // cout << "newHashVector" << endl;
        // for(unsigned i=0; i<newHashVector.size(); i++){
        //     for(unsigned j=0; j<newHashVector[i].size(); j++){
        //         cout << newHashVector[i][j] << " ";
        //     }
        //     cout << ", ";
        // }
        // cout << endl;

        hashVector.resize(newHashVector.size());

        for(unsigned i=0; i<newHashVector.size(); i++){
            hashVector[i].clear();
            for(unsigned j=0; j<newHashVector[i].size(); j++){
                hashVector[i].push_back(newHashVector[i][j]);
                // cout << hashVector[i][j] << endl;
            }
        }

        // cout << "hashVector" << endl;
        // for(unsigned i=0; i<hashVector.size(); i++){
        //     for(unsigned j=0; j<hashVector[i].size(); j++){
        //         cout << hashVector[i][j] << " ";
        //     }
        //     cout << ", ";
        // }
        // cout << endl;

        

    }
    hash_code = hash(key);
    hashVector[hash_code%hashVector.size()].push_back(key);

    // cout << "hashVector" << endl;
    // for(unsigned i=0; i<hashVector.size(); i++){
    //     for(unsigned j=0; j<hashVector[i].size(); j++){
    //         cout << hashVector[i][j] << " ";
    //     }
    //     cout << ", ";
    // }
    // cout << endl;


}

template <typename T, class H>
void hashtable<T,H>::remove(const T& key){
    size_t hash_code = hash(key);
    for(unsigned i=0; i<hashVector[hash_code%hashVector.size()].size(); i++){
        if(hashVector[hash_code%hashVector.size()][i] == key){
            hashVector[hash_code%hashVector.size()].erase(hashVector[hash_code%hashVector.size()].begin() + i);
            _size--;
            break;
        }
    }
    

    // for(unsigned i=0; i<hashVector.size(); i++){
    //     for(unsigned j=0; j<hashVector[i].size(); j++){
    //         cout << hashVector[i][j] << " ";
    //     }
    //     cout << ", ";
    // }
    // cout << endl;
}

template <typename T, class H>
bool hashtable<T,H>::contains(const T& key){
    size_t hash_code = hash(key);
    for(unsigned i=0; i<hashVector[hash_code%hashVector.size()].size(); i++){
        if(hashVector[hash_code%hashVector.size()][i] == key){
            return true;
        }
    }
    return false;
}

template <typename T, class H>
size_t hashtable<T,H>::size(){
    return _size;
}

template <typename T, class H>
double hashtable<T,H>::load_factor(){
    //cout << (double)_size / hashVector.size() << endl;
    return (double)_size / hashVector.size();
}

template <typename T, class H>
int hashtable<T,H>::count_comparisons(const T& key){
    size_t hash_code = hash(key);
    int comparisons = 0;
    for(unsigned i=0; i<hashVector[hash_code%hashVector.size()].size(); i++){
        comparisons++;
        if(hashVector[hash_code%hashVector.size()][i] == key){
            return comparisons;
        }
    }
    return -1;

}

#endif
