package other.fund;

public class Buy {
    public static void  main(String[] arg){
        Double money=0.0;
        //可投金额
        Double num1=5000.0;
        //每月新增金额
        Double num2=1500.0;
        //期望投入月份
        Double month=6.0;
        for(int i=0;i<month;i++){
            num1+=num2;
        }
        money=num1/month/4;
        System.out.println("每周定投"+money);
    }
}
