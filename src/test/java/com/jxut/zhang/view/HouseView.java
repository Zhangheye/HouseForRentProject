package com.jxut.zhang.view;

import com.jxut.zhang.domain.House;
import com.jxut.zhang.service.HouseService;

import java.util.Scanner;

/**
 * @界面层
 * 1.显示界面
 * 2.接收用户的输入
 * 2.调用HouseService完成对房屋信息的各种操作
 * */
public class HouseView {

    private boolean loop = true; //控制显示菜单
    private int key; //接收用户选择
    private HouseService houseService = new HouseService(10);//设置数组大小为10

    //显示主菜单
    public void mainMenu(){
        do {
            System.out.println("======================房屋出租系统======================");
            System.out.println("\t\t\t1 新 增 房 屋");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退       出");

            Scanner s = new Scanner(System.in);
            System.out.print("请输入你的选择(1-6):");
            this.key = s.nextInt();
            switch (key){
                case 1 :
                    addHouse();
                    break;
                case 2 :
                    findHouse();
                    break;
                case 3 :
                    delHouse();
                    break;
                case 4 :
                    amendHouse();
                    break;
                case 5 :
                    listHouses();
                    break;
                case 6 :
                    quitHouse();
                    break;
            }
        }while (loop);

    }

    //显示房屋列表
    public void listHouses(){
        System.out.println("\n======================房屋列表======================");
        System.out.println("编号\t房主\t电话\t地址\t月租\t状态(未出租/已出租)");
        House[] houses = houseService.list();//得到所有房屋信息
        for (House h : houses){
            if (h == null){
                break;
            }
            System.out.println(h);
        }
        System.out.println("==================房屋列表显示完毕==================\n");
    }

    //添加房屋信息
    public void addHouse(){
        Scanner s = new Scanner(System.in);
        System.out.println("======================添加房屋======================\n");
        System.out.print("姓名：");
        String name = s.nextLine();
        System.out.print("电话: ");
        String phone = s.nextLine();
        System.out.print("地址：");
        String address = s.nextLine();
        System.out.print("月租：");
        int rent = s.nextInt();

        System.out.print("");
        String s1 = s.nextLine();

        System.out.print("状态(未出租/已出租): ");
        String state = s.nextLine();

        //创建一个新的House对象,注意id是系统分配的
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)){
            System.out.println("===============添加成功=============");
        }else {
            System.out.println("===============添加失败=============");
        }
    }

    //查找房屋
    public void findHouse() {
        System.out.println("========查找房屋========");
        Scanner s = new Scanner(System.in);
        System.out.print("请输入要查找的房屋编号: ");
        int idHouse = s.nextInt();
        House house = houseService.find(idHouse);
        if (house != null){
            System.out.println("编号\t房主\t电话\t地址\t月租\t状态(未出租/已出租)");
            System.out.println(house);
        }else {
            System.out.println("======没有找到此编号的房屋信息=======");
        }
    }

    //删除房屋信息
    public void delHouse(){
        System.out.println("=================删除房屋==================");
        Scanner s = new Scanner(System.in);
        System.out.print("请选择待删除的房屋编号(-1退出): ");
        int id = s.nextInt();
        if (id == -1){
            System.out.println("=========放弃删除成功！！！=========");
            return;
        }
        Scanner s1 = new Scanner(System.in);
        System.out.print("请确认是否删除(Y/N): ");
        String line = s1.nextLine();
        if (line.equals("y")){
            if (houseService.del(id)){
                System.out.println("=========删除成功！！！=========");
            }else {
                System.out.println("=========房屋信息不存在,删除失败=========");
            }
        }else {
            System.out.println("=========放弃删除成功！！！=========");
            return;
        }
    }

    //修改房屋信息
    public void amendHouse(){
        System.out.println("===================修改房屋信息===================\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要修改的房屋编号(-1退出): ");
        int idHouse = sc.nextInt();
        if (idHouse==-1){
            System.out.println("=======退出修改成功=======\n\n");
            return;
        }

        House house = houseService.find(idHouse);

        if (house==null){
            System.out.println("=====没有找到该编号对应的房屋信息=====");
            return;
        }

        System.out.print("姓名("+house.getName()+")：");
        String name = sc.next();
        if (!"".equals(name)){
            house.setName(name);
        }else {
            house.setName(house.getName());
        }

        System.out.print("");
        String s1 = sc.nextLine();

        System.out.print("电话("+house.getPhone()+")：");
        String phone = sc.nextLine();
        if (!"".equals(phone)){
            house.setPhone(phone);
        }else {
            house.setPhone(house.getPhone());
        }

        System.out.print("地址("+house.getAddress()+")：");
        String address = sc.nextLine();
        if (!"".equals(address)){
            house.setAddress(address);
        }else {
            house.setAddress(house.getAddress());
        }

        System.out.print("月租("+house.getRent()+")：");
        int rent = sc.nextInt();
        if (rent!=0){
            house.setRent(rent);
        }else {
            house.setRent(house.getRent());
        }

        System.out.print("状态("+house.getState()+")：");
        String state = sc.nextLine();
        if (!"".equals(state)){
            house.setState(state);
        }else if ("".equals(state)){
            house.setState(house.getState());
        }
        System.out.print("");
        String s2 = sc.nextLine();

        System.out.println("编号\t房主\t电话\t地址\t月租\t状态(未出租/已出租)");
        System.out.println(house);
    }

    //退出
    public void quitHouse(){
        boolean b = true;
        do {
            System.out.print("请确认是否退出(y/n): ");
            Scanner s = new Scanner(System.in);
            String s1 = s.nextLine();
            if (s1.equals("y") || s1.equals("n")){
                if (s1.equals("y")){
                    System.out.println("===========退出房屋出租系统成功==========\n\n");
                    this.loop=false;
                    return;
                }else if (s1.equals("n")){
                    System.out.println("=============取消退出=============\n");
                    break;
                }
            }else {
                System.out.println("=====你的输入有误，只能输入y/n!!!=====");
            }

        }while (b);
    }
}
