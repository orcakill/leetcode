package fund;

public class SellOut {
    public static void main(String[] args) {
        double  a=481.35; /*持有份额*/
        double  b=580.89; /*持有金额*/
        double  c=100;    /*保留金*/
        System.out.println(sell(a,b,c));

    }
   public  static  double sell(double a,double b,double c){
        double d=c/b;
        double e=a*(1-d);

        return  e;
   }
}
