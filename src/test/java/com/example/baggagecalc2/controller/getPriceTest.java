package com.example.baggagecalc2.controller;

import com.example.baggagecalc2.entity.Baggage;
import com.example.baggagecalc2.entity.FrontData;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class getPriceTest {

    @Autowired
    private menuController myConTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    //函数都已经通过测试

    @Test
    public void getPrice() {

        List<Baggage> bg=new ArrayList<>();
        List<specialBaggage> spBg=new ArrayList<>();


        //开始定义测试数据
        Inf myInf=new Inf();
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("domestic");
        myInf.setCard("普通旅客");
        myInf.setMyList(null);
        myInf.setMyList2(null);


        //测试数据1
        bg.add(new Baggage(30.,0.,0.,34.));
        spBg.add(new specialBaggage("t1",20.));
        myInf.setMyList(bg);
        myInf.setMyList2(spBg);
        FrontData test=new FrontData(0.0);
        Assertions.assertEquals(myConTest.getPrice(myInf),test);


        //测试数据2
        bg.clear();spBg.clear();
        bg.add(new Baggage(30.,30.,40.,54.));
        bg.add(new Baggage(30.,30.,40.,34.));
        spBg.add(new specialBaggage("t3.1",20.));
        myInf.setMyList(bg);
        myInf.setMyList2(spBg);
        test.setData(2510.);
        Assertions.assertEquals(myConTest.getPrice(myInf),test);


        //测试数据3
        bg.clear();spBg.clear();
        bg.add(new Baggage(30.,30.,40.,24.));
        bg.add(new Baggage(60.,50.,50.,32.));
        bg.add(new Baggage(60.,50.,50.,18.));
        spBg.add(new specialBaggage("t3.1",20.));
        spBg.add(new specialBaggage("t3.2",20.));
        myInf.setMyList(bg);
        myInf.setMyList2(spBg);
        test.setData(4405.);
        Assertions.assertEquals(myConTest.getPrice(myInf),test);

    }
}
