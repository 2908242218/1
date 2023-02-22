package com.liuwei.java;

import java.util.*;

/**
 * @author MikeCoder
 * @create 2022-10-3115:21
 */
public class DAO<T> {
    Map<String,T> m = new HashMap<>();
    public void save(String id,T entity){
        m.put(id,entity);
    }
    public T get(String id){
        return m.get(id);
    }
    public void update(String id,T entity){
        m.put(id,entity);
    }
    public List<T> list(){
        Collection<T> values = m.values();
        List<T> list = new ArrayList<>();
        for(T e : values){
            list.add(e);
        }
        return list;
    }
    public void delete(String id){
        m.remove(id);
    }
}
