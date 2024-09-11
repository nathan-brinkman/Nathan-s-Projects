/*
 * sorter.h
 *
 * Declares a template function:
 *    
 *   sorter() : k-way merge sort.
 * 
 * 
 * You may add other functions if you wish, but this template function
 * is the only one that need to be exposed for the testing code.
 * 
 * Authors: C. Painter-Wakefield & Tolga Can
 */

#ifndef _SORTER_H
#define _SORTER_H

#include <string>
#include <cstddef>
#include <vector>
#include <iostream>
#include <limits>

using namespace std;

template <typename T>
void merge(vector<T> &vec, vector<vector<T>> vecOfVec, vector<int> voi){

	T smallest;
	//cout << "smallest: " << smallest << endl;

	//index of smallest value in the vector
	unsigned indencyIndexNumber = 0;
	unsigned index;

	bool firstloop = true;

	//loop through vec
	for(unsigned k=0; k<vec.size(); k++){
		//cout << smallest << endl;
		index = 0;

		firstloop = true;

		//loop through vec of vec
		for(unsigned j = 0; j<vecOfVec.size(); j++){

			// for(unsigned i=0; i<vecOfVec.size(); i++){
			// 	for(unsigned j=0; j<vecOfVec[i].size(); j++){
			// 		cout << vecOfVec[i][j] << " ";
			// 	}
			// 	cout << ", ";
			// }
			// cout << endl;

			if(voi[j] < vecOfVec[j].size() && (vecOfVec[j][voi[j]] < smallest || firstloop)){
				smallest = vecOfVec[j][voi[j]];
				//cout << "first loop: " << firstloop << endl;
				//cout << "smallest: " << smallest << endl;
				indencyIndexNumber = voi[j] + 1;
				index = j;
				firstloop = false;
			}


		}

		voi[index] = indencyIndexNumber;
		vec[k] = smallest;

		// cout << "voi: " << endl;
		// for(unsigned i=0; i<voi.size(); i++){
		// 	cout << voi[i] << " ";
		// }
		// cout << endl;

		// cout << "vec: " << endl;
		// for(unsigned i=0; i<vec.size(); i++){
		// 	cout << vec[i] << " ";
		// }
		// cout << endl << endl;
	
	}

}

template <typename T>
void improved_merge(vector<T> &vec, vector<vector<T>> vecOfVec){

	while(vecOfVec.size() > 1){

		// for(unsigned i=0; i<vecOfVec.size(); i++){
		// 	for(unsigned j=0; j<vecOfVec[i].size(); j++){
		// 		cout << vecOfVec[i][j] << " ";
		// 	}
		// 	cout << ", ";
		// }
		// cout << endl;

		unsigned smallestIndex = 0;
		unsigned smallestSize = vecOfVec[smallestIndex].size();
		unsigned smallestIndex2 = 1;
		unsigned smallestSize2 = vecOfVec[smallestIndex2].size();
		if(vecOfVec[vecOfVec.size()-1].size() < vecOfVec[0].size()){
			smallestIndex = vecOfVec.size()-1;
			smallestSize = vecOfVec[smallestIndex].size();
			smallestIndex2 = vecOfVec.size()-2;
			smallestSize2 = vecOfVec[smallestIndex2].size();
		}

		// for(unsigned i=0; i<vecOfVec.size(); i++){
		// 	if(vecOfVec[i].size() < smallestSize){
		// 		smallestSize2 = smallestSize;
		// 		smallestIndex2 = smallestIndex;
		// 		smallestSize = vecOfVec[i].size();
		// 		smallestIndex = i;
		// 	}
		// 	else if(vecOfVec[i].size() < smallestSize2 && vecOfVec[i].size() >= smallestSize){
		// 		smallestSize2 = vecOfVec[i].size();
		// 		smallestIndex2 = i;
		// 	}
		// }

		//cout << smallestIndex << endl;
		//cout << smallestIndex2 << endl;

		vector<T> newVector(smallestSize + smallestSize2);
		vector<int> voi = {0,0};
		for(unsigned i=0; i<smallestSize + smallestSize2; i++){
			// cout << vecOfVec[smallestIndex][voi[0]] << endl;
			// cout << vecOfVec[smallestIndex2][voi[1]] << endl;
			if(voi[1] >= vecOfVec[smallestIndex2].size() || (voi[0] < vecOfVec[smallestIndex].size() && vecOfVec[smallestIndex][voi[0]] < vecOfVec[smallestIndex2][voi[1]])){
				newVector[i] = vecOfVec[smallestIndex][voi[0]];
				voi[0]++;
			}
			else{ 
				newVector[i] = vecOfVec[smallestIndex2][voi[1]];
				voi[1]++;
			}

			// cout << "newVector: ";
			// for(unsigned i=0; i<newVector.size(); i++){
			// 	cout << newVector[i] << " ";
			// }
			// cout << endl;

		}

		// cout << smallestIndex << endl;
		// cout << smallestIndex2 << endl;
		vecOfVec.erase(vecOfVec.begin() + smallestIndex);
		if(smallestIndex < smallestIndex2){
			vecOfVec.erase(vecOfVec.begin() + smallestIndex2 - 1);
		}
		else{
			vecOfVec.erase(vecOfVec.begin() + smallestIndex2);
		}
		vecOfVec.push_back(newVector);

	}

	vec = vecOfVec[0];

}


template <typename T>
void sorter(vector<T> &vec, unsigned k) {  
	// write your solution for k-way merge sort below

	if(vec.size() <= 1){	
		return;
	}

	if(k > vec.size()){
		k = vec.size();
	}

	vector<vector<T>> vecOfVec(k);

	//sorts item into different vectors
	for(unsigned i=0; i<vec.size(); i++){
		vecOfVec[i%k].push_back(vec[i]);
	}

	// for(unsigned i=0; i<k; i++){
	// 	for(unsigned j=0; j<vecOfVec[i].size(); j++){
	// 		cout << vecOfVec[i][j] << " ";
	// 	}
	// 	cout << ", ";
	// }
	// cout << endl;

	//calls sorter method for each subvector
	for(unsigned i=0; i<vecOfVec.size(); i++){
		sorter(vecOfVec[i], k);
	}

	//vector of indecies and instantiates each value to 0
	vector<int> voi(k);
	for(unsigned i=0; i<k; i++){
		voi[i] = 0;
	}

	// cout << endl;
	// for(unsigned i=0; i<k; i++){
	// 	for(unsigned j=0; j<vecOfVec[i].size(); j++){
	// 		cout << vecOfVec[i][j] << " ";
	// 	}
	// 	cout << ", ";
	// }
	// cout << endl;
	
	// looping thru vector of vectors
	//merge(vec, vecOfVec, voi);

	improved_merge(vec, vecOfVec);


}
#endif
