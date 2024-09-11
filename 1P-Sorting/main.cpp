/*
 * main.cpp
 *
 * Includes the main() function for the sorting project. 
 *
 * This code is included in the build target "run-main"
*/

#include <iostream>
#include <cstddef>
#include <vector>
#include <cstdlib>
#include <ctime>

#include "chrono"

#include "sorter.h"

using namespace std;

double time_k_merge_sort(vector<int> vec, unsigned k){

    // get starting clock value
    auto start_time = chrono::system_clock::now();

    sorter(vec, k);

    // get ending clock value
    auto stop_time = chrono::system_clock::now();

    // compute elapsed time in seconds
    chrono::duration<double> elapsed = stop_time - start_time;
    return elapsed.count();

}

int main() {
    // You can use this main() to run your own analysis or testing code.
    cout << "If you are seeing this, you have successfully run main!" << endl;

    srand(time(0));
    rand();

    for(unsigned j=1; j<6; j++){
        vector<int> vec(j * 100000);
        for(unsigned i=0; i<vec.size(); i++){
            vec[i] = rand() % 100 + 1;
        }

        unsigned k = 200;

        cout << time_k_merge_sort(vec, k) << endl;
    }

    // vector<int> vec = {5,2,3,1,4,7,8,9,4,7,1,3,5,8,5,0,9,7,5,78};
    // sorter(vec, 2);

    // for(unsigned i=0; i<vec.size(); i++){
    //     cout << vec[i] << " ";
    // }

    // return 0;
}


