package com.example.baggagecalc2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inf {


    String tickettype;
    String seattype;
    String card;
    String region;
    List<Baggage> myList;
    List<specialBaggage> myList2;


}
