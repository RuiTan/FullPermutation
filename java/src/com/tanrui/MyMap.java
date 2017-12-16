package com.tanrui;

import java.util.*;

public class MyMap<Key, Value> implements Map,Iterable{

    public MyMap() {
        this.elements = new MySet<>();
    }

    @Override
    public Iterator iterator() {
        return elements.iterator();
    }

    public static class Element<Key, Value>{
        public Element(){
            this.key = null;
            this.value = null;
        }

        public Element(Key key, Value value){
            this.key = key;
            this.value = value;
        }

        private Key key;
        private Value value;

        public Key getKey() {
            return key;
        }

        private void setKey(Key key) {
            this.key = key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }
    }

    public MySet<Element> getElements() {
        return elements;
    }

    private MySet<Element> elements;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Element elem : elements){
            stringBuilder.append("<").append(elem.key).append(":").append(elem.value).append(">").append(",");
        }
        return stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length()).toString();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (Object elem : elements) {
            if (((Element)elem).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Object elem : elements) {
            if (((Element)elem).getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        for (Object elem : elements) {
            if (((Element)elem).getKey().equals(key)) {
                return ((Element)elem).getValue();
            }
        }
        return null;
    }

    public Object changeValue(Object key, Object value){
        for (Object elem : elements){
            if (((Element) elem).key.equals((key))){
                ((Element) elem).setValue(value);
                return value;
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        for (Object elem : elements) {
            if (((Element)elem).getKey().equals(key)) {
                return ((Element)elem).getValue();
            }
        }
        elements.add(new Element(key, value));
        return value;
    }

    @Override
    public Object remove(Object key) {
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()){
            Element elem = (Element) iterator.next();
            if (elem.getKey().equals(key)){
                iterator.remove();
                return elem.getValue();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
        putAll((MyMap) m, this);
    }

    private void putAll(MyMap m, MyMap myMap){
        for (Object aM : m) {
            Element elem = (Element) aM;
            myMap.put(elem.key, elem.value);
        }
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public Set keySet() {
        MySet keys = new MySet();
        for (Object elem : elements){
            keys.add(((Element)elem).getKey());
        }
        return keys;
    }

    @Override
    public Collection values() {
        ArrayList values = new ArrayList();
        for (Object elem : elements){
            values.add(((Element)elem).getValue());
        }
        return values;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
