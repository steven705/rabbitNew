package com.example.baggagecalc2.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class myBg implements Comparable<myBg>{


    Double weight;
    int pos;

    @Override
    public int compareTo(myBg o) {
        int temp = this.weight.compareTo(o.weight);
        return temp == 0 ? this.pos - o.pos : temp ;
    }
}
