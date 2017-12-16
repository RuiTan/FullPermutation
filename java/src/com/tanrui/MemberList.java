package com.tanrui;

import java.util.ArrayList;

public class MemberList extends ArrayList {
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("(");
        for (int i = 0; i < size(); i++){
            string.append(this.get(i)).append(",");
        }
        string.delete(string.length()-1, string.length());
        string.append(")");
        return string.toString();
    }
}
