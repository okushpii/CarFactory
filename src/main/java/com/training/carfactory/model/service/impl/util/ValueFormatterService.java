package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;

public class ValueFormatterService {

    public String formatBody(Body body){
        return body.getName() + ", " + body.getType();
    }

    public String formatEngine(Engine engine){
        return engine.getName() + "," +
                " " + engine.getVolume() + " m3," +
                " " + engine.getPower() + " ktn";
    }

    public String formatWheels(Wheels wheels){
        return wheels.getName() + ", " + wheels.getSize() + " inches";
    }
}
