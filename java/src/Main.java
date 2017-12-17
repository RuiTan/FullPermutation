import com.tanrui.Permutation;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the amount of permutation between 0 and 10 : ");
        size = scanner.nextInt();
        if (size <= 0 || size >= 10){
            System.out.println("Bad input!");
            return;
        }
        long start = System.currentTimeMillis();
        Permutation permutation = new Permutation(size);
        permutation.getPermutation();
        System.out.println(size+" elements has " + permutation.getCount()+" permutations!");
        long finish = System.currentTimeMillis();
        System.out.println("It costs "+(finish-start)/1000.0+" seconds!");
    }
}
