package com.tanrui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MySet<AnyType> implements Set<AnyType> {

    private ArrayList<AnyType> arrayList;

    public MySet(){
        arrayList = new ArrayList<>();
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(Object o) {
        for (AnyType elem : arrayList){
            if (elem.equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return arrayList.iterator();
    }

    @Override
    public Object[] toArray() {
        return arrayList.toArray();
    }

    @Override
    public boolean add(Object o) {
        if (!contains(o)){
            arrayList.add((AnyType) o);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)){
            arrayList.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean ok = false;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()){
            if (add(iterator.next()))
                ok = true;
        }
        return ok;
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean ok = false;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()){
            if (remove(iterator.next()))
                ok = true;
        }
        return ok;
    }

    @Override
    public boolean retainAll(Collection c) {
        return arrayList.removeIf(o -> !c.contains(o));
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c){
            if (!arrayList.contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
