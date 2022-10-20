package com.example.baggagecalc2.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Baggage {


    Double length;
    Double width;
    Double height;
    Double weight;
}
