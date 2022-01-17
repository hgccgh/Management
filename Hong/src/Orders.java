public class Orders extends Goods{
    private int oNum;
    private String time;
    private int sum;

    public Orders(int oNum,int gNum,String name,double price,String time,int sum){
        this.oNum=oNum;
        this.gNum=gNum;
        this.name=name;
        this.price=price;
        this.time=time;
        this.sum=sum;
    }
    public int getONum() {
        return oNum;
    }

    public String getTime() {
        return time;
    }

    public int getSum() {
        return sum;
    }

   @Override
    public String toString(){
        return "订单编号:"+oNum+",商品编号:"+gNum+",商品名:"+name+",商品价格:"+price+",下单时间:"+time+",商品数量+"+sum;
   }

}
