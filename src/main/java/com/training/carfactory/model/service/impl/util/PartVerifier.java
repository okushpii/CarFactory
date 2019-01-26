package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.model.entity.Part;
import com.training.carfactory.model.exception.IncorrectResembleOrderException;
import com.training.carfactory.model.exception.PartIsMissingException;

public class PartVerifier {

    public void verifyPartsPresent(Part... parts){
        for (Part part : parts){
            verifyPartPresent(part, "Some part is missing");
        }
    }

    public void verifyPartPresent(Part part, String errorMessage) {
        if (part == null){
            throw new PartIsMissingException(errorMessage);
        }
    }

    public void verifyPartAbsent(Part part, String errorMessage){
        if (part != null){
            throw new IncorrectResembleOrderException(errorMessage);
        }
    }
}
