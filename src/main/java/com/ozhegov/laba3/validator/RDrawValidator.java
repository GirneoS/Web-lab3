package com.ozhegov.laba3.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("numberValidator")
public class RDrawValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double r = (double) o;
            if (r < 2 || r > 5) {
                throw new ValidatorException(new FacesMessage("Радиус может быть в диапазоне [2,5]"));
            }
        }catch(NumberFormatException e){
            throw new ValidatorException(new FacesMessage("Радиус может быть только числом!!!"));
        }
    }
}
