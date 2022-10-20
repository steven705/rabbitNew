package com.example.baggagecalc2.controller;


import com.example.baggagecalc2.entity.Baggage;
import com.example.baggagecalc2.entity.Inf;
import com.example.baggagecalc2.entity.specialBaggage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class getListTest {


    @Autowired
    private menuController myConTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    //函数都已经通过测试

    @Test
    public void getList()
    {


        //制造一个假的案列而已
        //就是说你先如果测试一个功能但是需要用到另外一个函数的功能 这个函数还没有写好 你弄个假的出来
        //Mockito.when(userDao.getUserById(3)).thenReturn(new User(200, "Aritisan")



        List<Baggage> bg;
        List<specialBaggage> spBg;
        HashMap<String,Object> ret;


        //待比较数据
        List<Baggage> arrT1= new ArrayList<>();
        List<specialBaggage> arrT2= new ArrayList<>();

        //开始定义测试数据
        Inf myInf=new Inf();
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");
        myInf.setMyList(null);
        myInf.setMyList2(null);
        myInf.setMyList(arrT1);
        myInf.setMyList2(arrT2);






        //测试数据1
        ret =myConTest.getList(myInf);
        bg= (List<Baggage>) ret.get("1");
        spBg= (List<specialBaggage>) ret.get("2");


        Assertions.assertEquals(bg,arrT1);
        Assertions.assertEquals(spBg,arrT2);


        //测试数据2
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");
        arrT1.clear();
        arrT2.clear();
        arrT1.add(new Baggage(30.,30.,30.,20.));
        myInf.setMyList(arrT1);
        myInf.setMyList2(arrT2);
        ret =myConTest.getList(myInf);
        bg= (List<Baggage>) ret.get("1");
        spBg= (List<specialBaggage>) ret.get("2");
        Assertions.assertEquals(bg,arrT1);
        Assertions.assertEquals(spBg,arrT2);


        //测试数据3
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");
        arrT1.clear();
        arrT2.clear();
        arrT1.add(new Baggage(0.,0.,0.,0.));
        myInf.setMyList(arrT1);
        myInf.setMyList2(arrT2);
        ret =myConTest.getList(myInf);
        bg= (List<Baggage>) ret.get("1");
        spBg= (List<specialBaggage>) ret.get("2");
        arrT1.clear();
        Assertions.assertEquals(bg,arrT1);
        Assertions.assertEquals(spBg,arrT2);


        //测试数据4
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");
        arrT1.clear();
        arrT2.clear();
        arrT1.add(new Baggage(30.,30.,30.,20.));
        arrT2.add(new specialBaggage("t1",20.));
        myInf.setMyList(arrT1);
        myInf.setMyList2(arrT2);
        ret =myConTest.getList(myInf);
        bg= (List<Baggage>) ret.get("1");
        spBg= (List<specialBaggage>) ret.get("2");
        Assertions.assertEquals(bg,arrT1);
        Assertions.assertEquals(spBg,arrT2);


        //测试数据5
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");
        arrT1.clear();
        arrT2.clear();
        arrT1.add(new Baggage(30.,30.,30.,20.));
        arrT2.add(new specialBaggage("t1"));
        myInf.setMyList(arrT1);
        myInf.setMyList2(arrT2);
        ret =myConTest.getList(myInf);
        bg= (List<Baggage>) ret.get("1");
        spBg= (List<specialBaggage>) ret.get("2");
        arrT2.clear();
        Assertions.assertEquals(bg,arrT1);
        Assertions.assertEquals(spBg,arrT2);



    }
}
