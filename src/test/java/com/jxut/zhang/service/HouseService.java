package com.jxut.zhang.service;

import com.jxut.zhang.domain.House;

/**
 * @业务层
 * 1.响应HouseView的调用
 * 2.完成对房屋信息的各种操作（增删改查）
 * */
public class HouseService {

    private House[] houses; //保存House对象
    private int houseNums=1; //记录当前有多少个房屋信息
    private int idCount=1; //记录id

    public HouseService(int size){
        //创建House对象是，指定数组大小
        houses = new House[size];
        //初始化一个House对象
        houses[0] = new House(1,"吴莹","0618","层坑",2000,"未出租");
    }

    //list方法,返回House[]
    public House[] list(){
        return houses;
    }

    //add方法,添加新对象,返回boolean
    public boolean add(House newHouse){
        //判断是否还可以继续添加
        if (houseNums == houses.length){
            System.out.println("数组已满,不能在加了....");
            return false;
        }
        //把新的newHouse对象加入，并且新增了一个房屋
        houses[houseNums++] = newHouse;
        //id自增长机制,然后更新newHouse的id
        newHouse.setId(++idCount);
        return true;
    }

    //del,删除一个房屋信息
    public boolean del(int delId){
        //先找到要删除的房屋信息对应的下标
        int index = -1;
        for (int i = 0;i < houseNums;i++){
            //要删除的房屋信息对应的下标
            if (delId == houses[i].getId()){
                index=i;
            }
        }
        if (index == -1){
            return false;
        }
        //把删除之后的房屋信息全部往前走一个
        for (int i = index; i < houseNums -1; i++) {
            houses[i] = houses[i+1];
        }
        //把当前存在房屋信息的最后一个置空
        houses[--houseNums]=null;
        return true;
    }

    //find,查找一个房屋信息
    public House find(int idHouse){

        for (int i =0;i<houseNums;i++){
            if (idHouse == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
}
