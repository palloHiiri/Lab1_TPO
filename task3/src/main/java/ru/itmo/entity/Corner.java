package ru.itmo.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated
public class Corner {
    private  double x;
    private double y;
    private boolean isFilled;

    public Corner(double x, double y){
        this.x = x;
        this.y = y;
        this.isFilled = false;
    }

}
