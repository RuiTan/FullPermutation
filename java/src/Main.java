import com.tanrui.Permutation;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入全排列的数量（介于0-10之间）：");
        size = scanner.nextInt();
        if (size <= 0 || size >= 10){
            System.out.println("输入有误！");
            return;
        }
        long start = System.currentTimeMillis();
        Permutation permutation = new Permutation(size);
        permutation.getPermutation();
        System.out.println(size+"个元素的全排列"+"共有" + permutation.getCount()+"个！");
        long finish = System.currentTimeMillis();
        System.out.println("共花费了"+(finish-start)/1000.0+"秒！");
    }
}
