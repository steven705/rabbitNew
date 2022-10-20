package com.example.baggagecalc2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrontData {
    private String msg="successfully";

    private Double data;

    public FrontData(Double data)
    {
        this.data=data;
    }

}
