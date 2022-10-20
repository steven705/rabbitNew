package com.example.baggagecalc2.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class specialBaggage {


    String sptype;
    Double spweight;

    public specialBaggage(String s)
    {
        this.sptype=s;
        this.spweight=null;
    }

}
