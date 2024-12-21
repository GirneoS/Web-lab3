package com.ozhegov.laba3.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("numberValidator")
public class NumberValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try{
            double param = (double)o;
            String id = uiComponent.getId();
            if(id.equals("set-y")){
                if(param < -3 || param > 3)
                    throw new ValidatorException(
                            new FacesMessage("Координата Y должна находится в диапазоне [-3; 3]"));
            }else {
                if(param < 2 || param > 5)
                    throw new ValidatorException(
                            new FacesMessage("Радиус должен находится в диапазоне [2; 5]"));
            }

        }catch(NumberFormatException e){
            throw new ValidatorException(new FacesMessage("В поле должно быть указано именно число!"));
        }
    }
}
