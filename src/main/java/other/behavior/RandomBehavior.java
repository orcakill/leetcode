package other.behavior;

import java.util.*;

/**
 * @author orcakill
 * @date 2021/1/12  9:36
 **/
public class RandomBehavior {
    public static void main(String[] args){
        Integer num=100;
        Map<String, Double>  map=new TreeMap<>();
        map.put("踩回去",40.00);
        map.put("退让",30.00);
        map.put("大骂",20.00);
        map.put("打架",9.00);
        map.put("杀人",1.00);
        List<String> list=new ArrayList<>();
        for(int i=0;i<num;i++){
          list.add(compute(map));
        }
        List<String> list1=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            String  ss=list.get(i);
            if(!list1.contains(ss)){
                list1.add(list.get(i));
            }
        }
        List<Integer> list2=new ArrayList<>();
        for(int i=0;i<list1.size();i++){
            String s1=list1.get(i);
            list2.add(0);
            int x=0;
            for(int j=0;j<list.size();j++){
                String s2=list.get(j);
                if(s1.equals(s2)){
                    x+=1;
                    list2.set(i,x);
                }
            }
        }
        String s="";
        for(int i=0;i<list1.size();i++){
           s=s+list1.get(i)+":"+(double)list2.get(i)*100.0/num+"% ";
        }
        System.out.println("当你被踩一脚，你可能会"+s);
    }
    public  static String compute(Map<String, Double> map){
        String  s="";
        List<String> list1=new ArrayList<>();
        List<Double> list2=new ArrayList<>();
        double d=0;
        list1.add("无行为");
        list2.add(d);
        for(String key: map.keySet()){
            list1.add(key);
            d+=map.get(key);
            list2.add(d);
        }
        double random=Math.random()*100;
        for(int i=1;i<list2.size();i++){
            if(list2.get(i) >= random){
                s=list1.get(i);
                break;
            }
        }
        return  s;
    }
}
