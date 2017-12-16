#include <iostream>
#include <map>
#define MAXSIZE 10
using namespace std;

class Permutation{
public:
    explicit Permutation() = default;
    explicit Permutation(int size):theSize(size), Count(0){
        if (size > MAXSIZE || size <= 0){
            cerr << "输入过大或过小!" << endl;
            exit(1);
        }
        theMembers = (char*) malloc(sizeof(char)*size);
        for (int i = 0; i < size; ++i) {
            initialMembers.insert(pair<char, bool>(static_cast<const char &>(i + 65), false));
            //使用map存储初始集合，以及其时候已被取到（即value的bool值）
        }
    };
    ~Permutation() = default;
    void getPermutations(ostream &out){
        getOnePerm(theMembers, theSize, 0, out);
    }
    void getCount(ostream &out){
        out << theSize << "个元素共有" << Count << "全排列!" << endl;
    }
protected:
    int theSize;
    int Count;
    char* theMembers;
    //存储全排列，每次存满将会输出
    map<char, bool> initialMembers;
    //参考值map，key值是数列中的元素（本题为ABC…），初始状态所有key标记均为false
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
                //  当操作位置到达最后一个元素时，此时map中一定仅有一个key的value为false，
                //此时将数列输出，若未到达最后一个元素，这value为false的参考值数量与theMembers
                //中未定值的数量必定相等，如此选择任意一个，而后对下一个位置递归操作
                initialMembers[i+65] = true;
                theMembers[index] = static_cast<char>(i + 65);
                if (index == size-1){
                    printOnePerm(theMembers, size, out);
                    Count++;
                }else{
                    getOnePerm(members, size, index+1, out);
                }
                initialMembers[i+65] = false;
                //回退时将已标记的参考值取消标记，让其能被后面循环中的元素取道
            }
        }
    }
};

int main() {
    cout << "请输入全排列的数量（介于0-10之间）：";
    int amount;
    cin >> amount;
    clock_t start = clock();
    //计时器开始
    Permutation permutation(amount);
    permutation.getPermutations(cout);
    permutation.getCount(cout);
    //创建Permutation对象，并获取全排列
    clock_t finish = clock();
    //计时器结束
    double duration = (double)(finish-start)/CLOCKS_PER_SEC;
    cout << "共花费了" << duration << "s!" << endl;
    return 0;
}