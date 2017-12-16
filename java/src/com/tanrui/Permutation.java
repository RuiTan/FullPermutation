package com.tanrui;

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
