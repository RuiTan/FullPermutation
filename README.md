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
            cerr << "Bad input!" << endl;
            exit(1);
        }
        theMembers = (char*) malloc(sizeof(char)*size);
        for (int i = 0; i < size; ++i) {
            initialMembers.insert(pair<char, bool>(static_cast<const char &>(i + 65), false));
            //Using map to store the initial members, every element marks as gettable.
        }
    };
    ~Permutation() = default;
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
</code></pre>

参照上文中的算法分析，很轻松就用C++实现出来，递归调用getOnePerm函数，递归结束条件为已经找到一次排列，而后返回查找下一次排列，直到所有排列已全部列出。
##### Linux上编译及运行
编写好的cpp文件在Linux系统下可直接生成.out文件而后运行，由于Linux自带gcc，因此直接在命令行输入命令如下：
> #`g++ main.cpp -o main`<br/>
> #`./main`<br/>

第一行产生一个`main.out`文件，第二行运行之，当然这里的`main.out`文件以及创建好，直接来到项目路径下，而后输入第二条命令即可。<br/>
![C++编译界面]()
##### Windows上编译及运行
跨平台编译一直是个头疼的问题，在写数据结构项目的时候就遇到了"\n\r"与"\n"的问题，这里出现了Linux里面UTF-8中文字符拿到Windows上面就自动转换为乱码，在VScode里面转换编码都改不过来，因此放弃了中文，所有注释用蹩脚的英语写了。
Windows上由于没有gcc，这里采用MinGW，其支持gcc一样的语法，用起来很顺。
> #`g++ main.cpp -o main`<br/>
> #`main`<br/>

同样，第一行产生一个`main.exe`文件，第二行运行之，注意这里运行时不需要加`“./”`，因为Windows下会在当前目录下查找可执行文件，而后在从path中找。
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

使用java编写此程序时不如C++那样轻松，原因在于我在C++与Java中都是用了Map，C++中自带Map类，而Java中仅提供Map接口，比较幸运的是前几日刚好看了下Java中Map和Set的实现方式，且自己也实现了这两个接口，这里直接拷贝过来用，美滋滋。实现的MyMap和MySet类代码就不在这里贴出来了，在我的[github](https://github.com/RuiTan/FullPermutation/)中找到所有文件，关于实现这两个接口的更多细节，有时间再写一篇博文分析之。
##### Linux上编译及运行
在使用Java编译时由于是第一次尝试用命令行编译所以遇到了一些障碍。这里整理一下遇到的问题。

> - Java环境变量的设置<br/>由于Linux中自带了openJDK，显然不能时刻与Java官方版本一致，首先在下载最新版Java，这里用到的是[Java 9.0.1]()，下载后解压，在terminal中输入命令：<br/>` # vim /etc/profile `<br/>使用vim编辑器在文件最后插入：<br/><pre><code>export JAVA_HOME=/home/tanrui/jdk-9.0.1
//这里是解压后的jdk路径
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HOME/bin</code></pre>
而后使profile文件生效：<br/>` # source /etc/profile `<br/>查看Java版本：<br/>` # java -version `<br/>可看到：<pre><code>java version "9.0.1"
Java(TM) SE Runtime Environment (build 9.0.1+11)
Java HotSpot(TM) 64-Bit Server VM (build 9.0.1+11, mixed mode)</code></pre>说明配置成功。<br/><br/>
> - 对java文件编译<br/>进入项目目录下，进行如下操作：<br/>` # javac Main.java `<br/>` # java Main `<br/>![Java编译界面]()<br/>同C++，class这里已经打包好，可直接进入class文件目录下使用第二条命令运行。
##### Windows上编译及运行
坑待填

### 