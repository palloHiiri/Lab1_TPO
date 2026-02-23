package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
