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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class pieceWorkTest {



    @Autowired
    private menuController myConTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    //函数都已经通过测试
    @Test
    public void pieceWorkTest(){
        List<Baggage> bg=new ArrayList<>();
        List<specialBaggage> spBg=new ArrayList<>();


        //开始定义测试数据
        Inf myInf=new Inf();
        myInf.setTickettype("持婴儿客票旅客");
        myInf.setSeattype("经济舱");
        myInf.setRegion("r1");
        myInf.setCard("凤凰知音终身白金卡/白金卡旅客");
        myInf.setMyList(null);
        myInf.setMyList2(null);


        //测试数据1
        bg.add(new Baggage(50.,50.,60.,20.));
        spBg.add(new specialBaggage("t3.1",20.));
        myInf.setMyList(bg);
        myInf.setMyList2(spBg);
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),6180.0);


        //测试数据2
        spBg.clear();
        spBg.add(new specialBaggage("t3.1",30.));
        myInf.setMyList2(spBg);
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),8780.0);

        //测试数据3
        spBg.clear();
        spBg.add(new specialBaggage("t3.1",30.));
        spBg.add(new specialBaggage("t3.1",20.));
        myInf.setMyList2(spBg);
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),13980.0);

        //测试数据4
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setCard("星空联盟金卡旅客");
        spBg.clear();
        spBg.add(new specialBaggage("t2",20.));
        myInf.setMyList2(spBg);
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),980.0);


        //测试数据5
        myInf.setSeattype("悦享经济舱超级经济舱");
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),980.0);


        //测试数据6
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setCard("星空联盟金卡旅客");
        myInf.setRegion("r1");
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),980.0);

        //测试数据7
        myInf.setRegion("r3");
        Assertions.assertEquals(myConTest.pieceWorkSys(myInf,bg,spBg),520.0);

    }
}
