import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class management {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<Orders> lists = new ArrayList<>();
        Orders order1=new Orders(1001,1001,"apple",10,"2021-1-1",10);
        Orders order2=new Orders(1002,1002,"peach",11,"2021-1-2",20);
        Orders order3=new Orders(1003,1003,"banana",12,"2021-1-3",30);
        lists.add(order1);
        lists.add(order2);
        lists.add(order3);
        label:
        while (true) {
            System.out.println("--------欢迎来到订单管理系统--------");
            System.out.println("1 订单更新");
            System.out.println("2 订单查询");
            System.out.println("3 订单删除");
            System.out.println("4 订单修改");
            System.out.println("5 退出");
            System.out.println("请输入您的选择:");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    addOrders(lists);
                    break;
                case "2":
                    queryOrders(lists);
                    break;
                case "3":
                    deleteOrders(lists);
                    break;
                case "4":
                    updateOrders(lists);
                    break;
                case "5":
                    System.out.println("感谢您的使用");
                    break label;
                default:
                    System.out.println("您的输入有误");
                    break;
            }
        }
    }
    //修改订单的方法
    private static void updateOrders(ArrayList<Orders> lists) {
        System.out.println("请输入您要修改的订单编号:");
        Scanner sc=new Scanner(System.in);
        int updateNum=sc.nextInt();
        int index=getIndex(lists,updateNum);
        if(index==-1){
            System.out.println("查无信息，请重新输入");
        }
        else {
            System.out.println("请输入新的商品编号");
            int gNum= sc.nextInt();
            System.out.println("请输入新的商品名称");
            String name= sc.next();
            System.out.println("请输入新的商品价格");
            double price=sc.nextDouble();
            System.out.println("请输入新的下单时间");
            String time= sc.next();
            System.out.println("请输入新的商品数量");
            int sum= sc.nextInt();
            //封装为新的订单对象
            Orders orders=new Orders(updateNum,gNum,name,price,time,sum);
            lists.set(index,orders);
            System.out.println("修改成功");
        }
    }

    //查找订单的方法
    public static void queryOrders(ArrayList<Orders> lists){
        if(lists.size()==0){
            System.out.println("无信息，请添加后查询");
            return;
        }
        System.out.println("订单编号\t\t商品编号\t\t商品名\t\t商品价格\t\t下单时间\t\t商品数量");
        for (Orders orders : lists) {
            System.out.println(orders.getONum() + "\t\t" + orders.getGNum() + "\t\t" + orders.getName() + "\t\t" +
                    orders.getPrice() + "\t\t" + orders.getTime() + "\t\t" + orders.getSum());
        }
    }
    //删除订单的方法
    public static void deleteOrders(ArrayList<Orders> lists){
        System.out.println("请输入您要删除的订单编号");
        Scanner sc=new Scanner(System.in);
        int delOrder= sc.nextInt();
        int index= getIndex(lists,delOrder);//根据getIndex方法，查找该学号在集合中出现的索引位置
        if(index==-1){
            System.out.println("查无信息，请重新输入");
        }
        else{
            lists.remove(index);
            System.out.println("删除成功");
        }
    }
    //查找这个订单在集合中出现的索引位置
    public static int getIndex(ArrayList<Orders> lists, int num){
        int index=-1;//假设传入编号在集合中不存在
        //遍历集合，获取每一个对象,准备进行查找
        for(int i=0;i< lists.size();i++){
            Orders orders= lists.get(i);
            int nums=orders.getONum();
            if(nums==num){
                index=i;//记录正确索引位置
                break;
            }
        }
        return index;
    }
    //添加订单的方法
    public static void addOrders(ArrayList<Orders> lists){
        Scanner sc=new Scanner(System.in);
        int nums;
        while(true){
            System.out.println("请输入订单编号");
            nums= sc.nextInt();
            int index=getIndex(lists,nums);
            if(index==-1){
                break;//编号不存在，可以使用
            }
        }
        Goods goods=new Goods();
        System.out.println("请输入商品编号");
        goods.gNum= sc.nextInt();
        System.out.println("请输入商品名");
        goods.name= sc.next();
        System.out.println("请输入商品价格");
        goods.price= sc.nextDouble();
        System.out.println("请输入下单时间");
        String time= sc.next();
        System.out.println("请输入商品数量");
        int sum= sc.nextInt();
        Orders orders=new Orders(nums,goods.gNum, goods.name, goods.price,time,sum);
        lists.add(orders);
        System.out.println("添加成功");
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            //初始化驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hgc?characterEncoding=UTF-8","root", "hgc201808");
            //该类就在 mysql-connector-java-5.0.8-bin.jar中,如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
