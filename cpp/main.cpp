#include <iostream>
#include <map>
#include <stdlib.h>
#include <time.h>
#define MAXSIZE 10
using namespace std;

class Permutation{
public:
    explicit Permutation(){};
    explicit Permutation(int size):theSize(size), Count(0){
        if (size > MAXSIZE || size <= 0){
            cerr << "Bad input!" << endl;
            exit(1);
        }
        theMembers = (char*) malloc(sizeof(char)*size);
        for (int i = 0; i < size; ++i) {
            initialMembers.insert(pair<char, bool>(static_cast<const char &>(i + 65), false));
            //Using map to store the initial members, every element marks as gettable.
        }
    };
    ~Permutation(){};
    void getPermutations(ostream &out){
        getOnePerm(theMembers, theSize, 0, out);
    }
    void getCount(ostream &out){
        out << theSize << " elements have " << Count << " permutations!" << endl;
    }
protected:
    int theSize;
    int Count;
    char* theMembers;
    //Store a kind of permutation
    map<char, bool> initialMembers;
    //The keys are letters in this question, and the values are all initialized as false.
    void printOnePerm(char* members, int size, ostream &out){
        out << "(";
        for (int i = 0; i < size; ++i) {
            out << members[i];
            if (i != size-1){
                out << ",";
            }
        }
        out << ")" << endl;
    }
    void getOnePerm(char* members, int size, int index, ostream &out){
        for (int i = 0; i < size; ++i) {
            if (!initialMembers[i+65]){
                //  When the operation comes to the last element, there is only one element in this
                // map which value is false, and print the array at the moment. But if it doesn't, 
                // then the amount of elements which values are false must be the same as the amount
                // of unkown value in TheMembers array. And then choose a valid member, and operate to 
                // next position recurisivly. 
                initialMembers[i+65] = true;
                theMembers[index] = static_cast<char>(i + 65);
                if (index == size-1){
                    printOnePerm(theMembers, size, out);
                    Count++;
                }else{
                    getOnePerm(members, size, index+1, out);
                }
                initialMembers[i+65] = false;
                //  When back to the upper operation, the mark of the member should be canceled, 
                // making sure that the next operation can reach it.
            }
        }
    }
};

int main() {
    cout << "Please input the amount of permutation between 0 and 10 : ";
    int amount;
    cin >> amount;
    clock_t start = clock();
    Permutation permutation(amount);
    permutation.getPermutations(cout);
    permutation.getCount(cout);
    clock_t finish = clock();
    double duration = (double)(finish-start)/CLOCKS_PER_SEC;
    // get the cost of this algorithm.
    cout << "It costs " << duration << " seconds!" << endl;
    return 0;
}