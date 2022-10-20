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
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class weightSysTest {

    @Autowired
    private menuController myConTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    //计重制度
    @Test
    public void weightSysTest(){


        List<Baggage> bg=new ArrayList<>();
        List<specialBaggage> spBg=new ArrayList<>();
        //用户的数据
        Inf myInf=new Inf();


        //测试数据1
        myInf.setTickettype("持婴儿客票旅客");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("凤凰知音终身白金卡/白金卡旅客");
        myInf.setMyList(null);
        myInf.setMyList2(null);

        bg.add(new Baggage(30.,30.,30.,20.));
        spBg.add(new specialBaggage("t2",20.));
        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),0.0);


        //测试数据2
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("头等舱");
        myInf.setRegion("demostic");
        myInf.setCard("凤凰知音终身白金卡/白金卡旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        //spBg.add(new specialBaggage("t2",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),0.0);

        //测试数据3
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("公务舱");
        myInf.setRegion("demostic");
        myInf.setCard("凤凰知音终身白金卡/白金卡旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        //spBg.add(new specialBaggage("t2",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),0.0);

        //测试数据4
        myInf.setTickettype("成人/儿童客票");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("凤凰知音终身白金卡/白金卡旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        //spBg.add(new specialBaggage("t2",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),0.0);

        //测试数据5
        myInf.setTickettype("持儿童客票旅");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("凤凰知音金卡/银卡旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        //spBg.add(new specialBaggage("t2",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),75.0);

        //测试数据6
        myInf.setTickettype("持儿童客票旅");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("星空联盟金卡旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        //spBg.add(new specialBaggage("t2",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),75.0);

        //测试数据7
        myInf.setTickettype("持儿童客票旅");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        //spBg.add(new specialBaggage("t2",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),225.0);


        //测试数据8
        myInf.setTickettype("持儿童客票旅");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");


        //bg.add(new Baggage(30.,30.,30.,20.));
        spBg.clear();
        spBg.add(new specialBaggage("t1",20.));

        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),2075.0);


        //测试数据9
        myInf.setTickettype("持儿童客票旅");
        myInf.setSeattype("经济舱");
        myInf.setRegion("demostic");
        myInf.setCard("普通旅客");


        bg.add(new Baggage(30.,30.,30.,20.));
        bg.add(new Baggage(30.,30.,30.,20.));
        bg.add(new Baggage(30.,30.,30.,20.));


        Assertions.assertEquals(myConTest.weightSys(myInf,bg,spBg),2525.0);





    }

}
