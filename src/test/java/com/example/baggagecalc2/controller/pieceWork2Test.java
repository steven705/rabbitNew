package com.example.baggagecalc2.controller;

import com.example.baggagecalc2.entity.Baggage;
import com.example.baggagecalc2.entity.Inf;
import com.example.baggagecalc2.entity.myBg;
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
public class pieceWork2Test {


    @Autowired
    private menuController myConTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    //函数都已经通过测试

    @Test
    public void pieceWorkTest()
    {
        Inf myInf=new Inf();
        myInf.setRegion("r1");
        Double totalPrice=0.;
        List<Baggage> bg=new ArrayList<>();
        List<specialBaggage> spBg=new ArrayList<>();
        List<myBg> bgPos=new ArrayList<>();
        List<Double> free=new ArrayList<>();


        //测试数据1
        free.add(32.);
        free.add(32.);
        bg.add(new Baggage(30.,30.,30.,25.));
        spBg.add(new specialBaggage("t2",20.));
        bgPos.add(new myBg(25.,0));
        bgPos.add(new myBg(20.,-100));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),0.0);

        //测试数据2

        bg.clear();
        bg.add(new Baggage(60.,60.,60.,25.));
        spBg.add(new specialBaggage("t2",20.));
       /* bgPos.clear();
        bgPos.add(new myBg(25.,0));
        bgPos.add(new myBg(20.,-100));*/
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),0.0);

        //测试数据3
        bg.clear();
        bg.add(new Baggage(30.,30.,30.,25.));
        bg.add(new Baggage(30.,30.,30.,25.));
        bgPos.clear();
        bgPos.add(new myBg(25.,0));
        bgPos.add(new myBg(25.,1));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),0.0);

        //测试数据4
        bg.clear();
        bg.add(new Baggage(30.,30.,30.,25.));
        bg.add(new Baggage(30.,30.,30.,25.));
        bg.add(new Baggage(30.,30.,30.,25.));
        bg.add(new Baggage(30.,30.,30.,25.));
        bgPos.clear();
        bgPos.add(new myBg(25.,0));
        bgPos.add(new myBg(25.,1));
        bgPos.add(new myBg(25.,1));
        bgPos.add(new myBg(25.,2));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),3400.0);

        //测试数据5
        free.clear();
        free.add(23.);
        free.add(23.);
        bg.clear();
        spBg.clear();
        spBg.add(new specialBaggage("t2",22.));
        bgPos.clear();
        bgPos.add(new myBg(22.,-100));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),0.0);

        //测试数据6
        spBg.clear();
        spBg.add(new specialBaggage("t2",25.));
        bgPos.clear();
        bgPos.add(new myBg(25.,-100));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),380.0);

        //测试数据7
        spBg.clear();
        spBg.add(new specialBaggage("t2",29.));
        bgPos.clear();
        bgPos.add(new myBg(29.,-100));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),980.0);

        //测试数据8
        bg.clear();
        bg.add(new Baggage(30.,30.,30.,20.));
        spBg.clear();
        bgPos.clear();
        bgPos.add(new myBg(20.,0));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),0.0);

        //测试数据9
        bg.clear();
        bg.add(new Baggage(50.,50.,50.,25.));
        bgPos.clear();
        bgPos.add(new myBg(25.,0));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),380.0);

        //测试数据10
        bg.clear();
        bg.add(new Baggage(50.,50.,50.,30.));
        bgPos.clear();
        bgPos.add(new myBg(30.,0));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),980.0);

        //测试数据11
        bg.clear();
        bg.add(new Baggage(50.,50.,60.,20.));
        bgPos.clear();
        bgPos.add(new myBg(20.,0));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),980.0);

        //测试数据12
        bg.clear();
        bg.add(new Baggage(50.,50.,60.,30.));
        bgPos.clear();
        bgPos.add(new myBg(30.,0));
        Assertions.assertEquals(myConTest.pieceWorkSys2(myInf,bg,spBg,totalPrice,free,bgPos),1400.0);


    }
}
