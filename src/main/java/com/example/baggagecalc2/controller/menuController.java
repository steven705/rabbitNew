package com.example.baggagecalc2.controller;


import com.example.baggagecalc2.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin//允许跨域 只有这个控制器可以
public class menuController {

    //计重制度 输入信息 输出重量
    public Double weightSys(Inf myInf,List<Baggage> bg, List<specialBaggage> spBg)
    {
        Double totalW=0.;
        //根据不同的票来计算可托运的最大重量
        if(myInf.getTickettype().equals("成人/儿童客票"))
        {
            if(myInf.getSeattype().equals("头等舱"))
            {
                totalW=40.0;
            } else if (myInf.getSeattype().equals("公务舱")) {
                totalW=30.0;
            }else {
                totalW=20.0;
            }
        }
        else {
            totalW=10.0;
        }
        //看看有没有会员卡
        if(myInf.getCard().equals("凤凰知音终身白金卡/白金卡旅客")) {
            totalW+=30.0;
        } else if (myInf.getCard().equals("凤凰知音金卡/银卡旅客")) {
            totalW+=20.0;
        } else if (myInf.getCard().equals("星空联盟金卡旅客")) {
            totalW+=20.0;
        }

        //得到了总的重量
        //现在开始统计所有行李的重量
        Double realWeight=0.0;
        //特殊行李总的数量
        Double spWeight=0.0;
        for (int i=0;i< bg.size();i++)
        {
            realWeight+=bg.get(i).getWeight();
        }
        for (int i=0;i< spBg.size();i++)
        {
            if(spBg.get(i).getSptype().equals("t2")||spBg.get(i).getSptype().equals("t4"))
            {
                realWeight+=spBg.get(i).getSpweight();
            }
            else {
                spWeight+=spBg.get(i).getSpweight();
            }
        }
        Double pOfSpBg=spWeight*100;
        if(realWeight<=totalW)
        {
            return pOfSpBg;

        }
        else {
            return pOfSpBg+(realWeight-totalW)*500*0.015;

        }
    }


    //计件制度
    public Double pieceWorkSys(Inf myInf,List<Baggage> bg, List<specialBaggage> spBg)
    {

        HashMap RP2 = new HashMap();
        RP2.put("t3.1", 0); // 将学生信息键值对存储到Map中
        RP2.put("t3.2", 1);
        RP2.put("t5.1", 2);
        RP2.put("t5.2", 3);
        RP2.put("t5.3", 4);
        RP2.put("t5.4", 5);


        //特殊行李的重量限制
        List<List<Integer>> spWRestrict=new ArrayList<>();
        List<Integer> tmp0= new ArrayList<>();
        tmp0.add(2);tmp0.add(23);tmp0.add(32);tmp0.add(45);
        List<Integer> tmp1= new ArrayList<>();
        tmp1.add(2);tmp1.add(23);tmp1.add(32);tmp1.add(45);
        List<Integer> tmp2= new ArrayList<>();
        tmp2.add(2);tmp2.add(23);tmp2.add(32);
        List<Integer> tmp3= new ArrayList<>();
        tmp3.add(2);tmp3.add(23);tmp3.add(32);
        List<Integer> tmp4= new ArrayList<>();
        tmp4.add(2);tmp4.add(23);
        List<Integer> tmp5= new ArrayList<>();
        tmp5.add(2);tmp5.add(23);tmp5.add(32);
        spWRestrict.add(tmp0);
        spWRestrict.add(tmp1);
        spWRestrict.add(tmp2);
        spWRestrict.add(tmp3);
        spWRestrict.add(tmp4);
        spWRestrict.add(tmp5);
        //特殊行李各个段位的价格
        List<List<Integer>> spPrice=new ArrayList<>();
        List<Integer> tmpP0= new ArrayList<>();
        tmpP0.add(2600);tmpP0.add(3900);tmpP0.add(5200);
        List<Integer> tmpP1= new ArrayList<>();
        tmpP1.add(1300);tmpP1.add(2600);tmpP1.add(3900);
        List<Integer> tmpP2= new ArrayList<>();
        tmpP2.add(490);tmpP2.add(3900);
        List<Integer> tmpP3= new ArrayList<>();
        tmpP3.add(1300);tmpP3.add(2600);
        List<Integer> tmpP4= new ArrayList<>();
        tmpP4.add(1300);
        List<Integer> tmpP5= new ArrayList<>();
        tmpP5.add(3900);tmpP5.add(5200);tmpP5.add(7800);
        spPrice.add(tmpP0);
        spPrice.add(tmpP1);
        spPrice.add(tmpP2);
        spPrice.add(tmpP3);
        spPrice.add(tmpP4);
        spPrice.add(tmpP5);



        //国外的人的花费
        Double totalPrice=0.0;
        List<Double> free=new ArrayList<>();
        //先看婴儿票持有者
        if(myInf.getTickettype().equals("持婴儿客票旅客")) {free.add(23.0);}
        //其他人
        else {
            if(myInf.getTickettype().equals("头等舱")||myInf.getTickettype().equals("公务舱"))
            {
                free.add(32.0);free.add(32.0);

            }else if(myInf.getTickettype().equals("悦享经济舱超级经济舱")) {
                free.add(23.0);free.add(23.0);
            }
            else {
                //这里有两个不同的区域
                if(myInf.getRegion().equals("r1")||myInf.getRegion().equals("r2"))
                {
                    free.add(23.0);free.add(23.0);
                }else {
                    free.add(23.0);
                }
            }
        }
        //看看有没有SVIP
        if(myInf.getCard().equals("凤凰知音终身白金卡/白金卡旅客")||myInf.getCard().equals("凤凰知音金卡/银卡旅客")) {
            if(myInf.getTickettype().equals("头等舱")||myInf.getTickettype().equals("公务舱"))
            {
                free.add(32.0);
            }else {
                free.add(23.0);
            }
        }
        else if (myInf.getCard().equals("星空联盟金卡旅客")) {
            free.add(23.0);
        }
        //按照降序排个序列

        //可以不用排序
        //Collections.sort(free, Collections.reverseOrder());

        //先把特殊的行李给安排了
        for (int i=0;i< spBg.size();i++)
        {
            if(!(spBg.get(i).getSptype().equals("t2")||spBg.get(i).getSptype().equals("t4"))) {
                //行李的下标
                int pos= (int) RP2.get(spBg.get(i).getSptype());
                int itrationN=spWRestrict.get(pos).size()-1;//过几次循环

                for (int j=0;j<itrationN;j++)
                {
                    //看看具体符合什么段位
                    if(j==0)
                    {
                        if(spWRestrict.get(pos).get(j)<=spBg.get(i).getSpweight()&&spBg.get(i).getSpweight()<spWRestrict.get(pos).get(j+1)){
                            totalPrice+=spPrice.get(pos).get(j);
                            break;
                        }
                    }
                    else {
                        if(spWRestrict.get(pos).get(j)<spBg.get(i).getSpweight()&&spBg.get(i).getSpweight()<spWRestrict.get(pos).get(j+1)){
                            totalPrice+=spPrice.get(pos).get(j);
                            break;
                        }
                    }

                }
            }

        }



        List<myBg> bgPos=new ArrayList<>();
        for (int i=0;i< bg.size();i++)
        {
            myBg tmp=new myBg(bg.get(i).getWeight(),i);
            bgPos.add(tmp);
        }
        for (int i=0;i< spBg.size();i++)
        {
            if(spBg.get(i).getSptype().equals("t2")||spBg.get(i).getSptype().equals("t4"))
            {
                myBg tmp=new myBg(spBg.get(i).getSpweight(),-100+i);
                bgPos.add(tmp);
            }
        }
        Collections.sort(bgPos, Collections.reverseOrder());


        //计算普通行李
        Double ret=pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos);

        return totalPrice+ret;
    }


    public Double pieceWorkSys2(Inf myInf,List<Baggage> bg,List<specialBaggage> spBg, Double totalPrice, List<Double> free, List<myBg> bgPos){
        HashMap RP = new HashMap();
        RP.put("r1", 0); // 将学生信息键值对存储到Map中
        RP.put("r2", 1);
        RP.put("r3", 2);
        RP.put("r4", 3);
        RP.put("r5", 4);


        Double[][] price1= {
                {380.0,980.0,980.0,1400.0},
                {280.0,690.0,690.0,1100.0},
                {520.0,520.0,520.0,520.0,},
                {690.0,1040.0,1040.0,2050.0},
                {210.0,520.0,520.0,830.0}};
        Double[][] price2={
                {1400.0,2000.0,3000.0},
                {1100.0,1100.0,1590.0},
                {1170.0,1170.0,1590.0},
                {1380.0,1380.0,1590.0},
                {830.0,2000.0,1590.0},
        };


        //开始进行计算了
        int free_index=0;//记录下标
        //普通的行李 可以计入免费的行李
        for (int i=0;i<bgPos.size();i++)
        {
            Double w_sure=0.;
            Double w_real=0.;
            Double s_real=0.;
            //免费托运的件数
            if(free_index<free.size())
            {
                w_sure=free.get(free_index);
            }
            else {
                //超过了就取最大的呗
                w_sure=free.get(0);
                //超出的行李多花的托运费
                int t=0;
                if(free_index-free.size()>=2){
                 t=2;
                }
                else {
                    t=free_index-free.size();
                }
                totalPrice+=price2[(int) RP.get(myInf.getRegion())][t];
            }
            int tmpPos=bgPos.get(i).getPos();
            if(tmpPos>=0)
            {
                w_real=bg.get(tmpPos).getWeight();
                s_real=bg.get(tmpPos).getLength()+bg.get(tmpPos).getWidth()+
                        bg.get(tmpPos).getHeight();
            }
            else {
                w_real=spBg.get(tmpPos+100).getSpweight();
                //设置一个不超尺寸的值
                s_real=60.0;
            }
            //特殊的行李超尺寸是不收费的
            //要么32 要么23
            if(w_sure.equals(32.0)&&tmpPos>0)
            {
                //普通的行李超尺寸才收费 特殊行李限制输入
                //免费
                if(s_real<=158.0)
                {
                    totalPrice+=0.;
                }
                else {
                    totalPrice+=price1[(int) RP.get(myInf.getRegion())][3];
                }
            }//这里就有四种情况
            else if(w_sure.equals(23.0)) {

                //特殊的行李就只判断是不是超重就行了
                if(tmpPos<0)
                {
                    if(w_real<=23.0)
                    {
                        totalPrice+=0.0;
                    }
                    //超重但是不超尺寸
                    else if(23.0<w_real&&w_real<=28.0)
                    {
                        totalPrice+=price1[(int) RP.get(myInf.getRegion())][0];
                    }
                    else if(28.0<w_real&&w_real<=32.0){
                        totalPrice+=price1[(int) RP.get(myInf.getRegion())][1];
                    }
                }
                //普通行李就按照所给的规则去算就行了
                else {
                    if(w_real<=23.0&&60<=s_real&&s_real<=158.0)
                    {
                        totalPrice+=0.0;
                    }
                    //超重但是不超尺寸
                    else if(23.0<w_real&&w_real<=28.0&&
                            60<=s_real&&s_real<=158.0)
                    {
                        totalPrice+=price1[(int) RP.get(myInf.getRegion())][0];
                    }
                    else if(28.0<w_real&&w_real<=32.0&&
                            60<=s_real&&s_real<=158.0){
                        totalPrice+=price1[(int) RP.get(myInf.getRegion())][1];
                    }
                    else if(2<w_real&&w_real<=23&&
                            158.0<s_real&&s_real<=203.0){
                        totalPrice+=price1[(int) RP.get(myInf.getRegion())][2];
                    } else if (23.0<w_real&&w_real<=32.0&&
                            158.0<s_real&&s_real<=203.0) {
                        totalPrice+=price1[(int) RP.get(myInf.getRegion())][3];
                    }
                }

            }
            free_index++;
        }
        return totalPrice;
    }




    public HashMap<String,Object> getList(Inf myInf)
    {
        List<Baggage> bg=new ArrayList<>();
        List<specialBaggage> spBg=new ArrayList<>();

        //在这里要先对数据处理一遍 用户在那里随便乱点 产生了无效数据
        if(myInf.getMyList()!=null)
        {
            for (int i=0;i<myInf.getMyList().size();i++)
            {
                if(
                        myInf.getMyList().get(i).getLength()!=0&&
                                myInf.getMyList().get(i).getWidth()!=0&&
                                myInf.getMyList().get(i).getHeight()!=0&&
                                myInf.getMyList().get(i).getWidth()!=0
                ){
                    bg.add(myInf.getMyList().get(i));
                }
            }
        }
        if(myInf.getMyList2()!=null)
        {
            for (int i=0;i<myInf.getMyList2().size();i++)
            {
                if(
                        ((myInf.getMyList2().get(i).getSptype()!=null)||(!myInf.getMyList2().get(i).getSptype().equals("")))&&
                                myInf.getMyList2().get(i).getSpweight()!=null
                ){
                    spBg.add(myInf.getMyList2().get(i));
                }
            }
        }

        HashMap<String,Object>myRet=new HashMap<>();
        myRet.put("1",bg);
        myRet.put("2",spBg);
        return myRet;
    }


    //根据所发送的数据计算结果
    @RequestMapping("/getPrice")
    @ResponseBody
    public FrontData getPrice(@RequestBody Inf myInf)
    {

        //得到所需的信息了 开始分析价格
        List<Baggage> bg=new ArrayList<>();
        List<specialBaggage> spBg=new ArrayList<>();

        //处理行李
        HashMap<String,Object> ret1=getList(myInf);
        bg= (List<Baggage>) ret1.get("1");
        spBg= (List<specialBaggage>) ret1.get("2");

        if(
                (bg.size()==0&&spBg.size()==0)||
                (bg.size()==0&&spBg.size()==1&&spBg.get(0).getSptype().equals("t1"))
        )
        {
            FrontData myData=new FrontData(0.0);
            return myData;
        }
        else
        {
            //超重的统一按照500*1.5%来算
            //按照区域来划分 国内是计重制
            if(myInf.getRegion().equals("domestic"))
            {
                Double ret=weightSys(myInf,bg,spBg);
                FrontData retData=new FrontData(ret);
                return retData;
            }
           else {
                Double ret=pieceWorkSys(myInf,bg,spBg);
                FrontData retData=new FrontData(ret);
                return retData;
            }
        }
    }
}
