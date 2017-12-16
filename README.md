# FullPermutation  
## 组合数学小项目——获取全排列
使用C++、Java实现，在Java中采用Map、Set接口重载，以及继承ArrayList
### 算法分析
> 要获取给定数量元素的全排列，必然是需要用暴力搜索来将每一个可能的排列方式都输出出来。这里的想法是采取标记取舍，即前面元素已经取过的值想办法将其标记为已被取过，在本次排列序列搜索中此值不可再被用，而在此次排列结束被标记的元素又会被依次取消标记，这依赖于函数中的递归，而递归结束的条件是函数中代表位置的参数index恰为序列末尾，这时输出一种排列，而后返回到上一层函数接着搜索其他可能的情况，直到所有可能的全排列都被输出为止。
### 程序分析
#### C++
##### 代码片段
 <pre><code>class Permutation{
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
                //  当操作位置到达最后一个元素时，此时map中一定仅有一个key的value为false，此时将数列输出，若未到达最后一个元素，这value为false的参考值数量与theMembers中未定值的数量必定相等，如此选择任意一个，而后对下一个位置递归操作
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
    cout << "共花费了" << duration << "s!" << endlfds
    return 0;
}
</code></pre>

参照上文中的算法分析，很轻松就用C++实现出来，递归调用getOnePerm函数，递归结束条件为已经找到一次排列，而后返回查找下一次排列，直到所有排列已全部列出。
##### 运行阶段
编写好的cpp文件在Linux系统下可直接生成.out文件而后运行。在命令行输入命令r
> #`g++ main.cpp -o main`<br/>
> #`./main`

![](暂时空着)
#### Java
##### 代码片段
<pre><code>package com.tanrui;

public class Permutation {
    private int theSize;
    private int Count = 0;
    private MemberList theMembers;
    private MyMap<Character, Boolean> initialMembers;

    public Permutation(int theSize) {
        this.theSize = theSize;
        theMembers = new MemberList();
        initialMembers = new MyMap<>();
        for (int i = 0; i < theSize; i++ ){
            initialMembers.put((char) (i+65), false);
            theMembers.add((char)(64));
        }
    }

    public void getPermutation(){
        getOnePerm(0);
    }

    private void getOnePerm(int index){
        for (int i = 0; i < theSize; i++){
            Character flag = (char) (i+65);
            if (initialMembers.get(flag).equals(false)){
                initialMembers.changeValue(flag, true);
                theMembers.set(index, flag);
                if (index == theSize-1){
                    printOnePerm();
                    Count++;
                }else {
                    getOnePerm(index+1);
                }
                initialMembers.changeValue(flag, false);
            }
        }
    }

    public int getCount(){
        return Count;
    }

    private void printOnePerm(){
        System.out.println(theMembers.toString());
    }

    @Override
    public String toString(){
        return theMembers.toString();
    }
}
</code></pre>

使用java编写此程序时不如C++那样轻松，原因在于我在C++与Java中都是用了Map，C++中自带Map类，而Java中仅提供Map接口，比较幸运的是前几日刚好看了下Java中Map和Set的实现方式，且自己也实现了这两个接口，这里直接拷贝过来用，美滋滋。<br/>实现的MyMap和MySet类代码就不在这里贴出来了，可在[github](https://github.com/RuiTan/FullPermutation/)