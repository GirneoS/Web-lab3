package com.ozhegov.laba3.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.Locale;
import java.util.ResourceBundle;

@FacesValidator("numberValidator")
public class NumberValidator implements Validator {
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages",new Locale("ru"));
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try{
            double param = (double)o;
            String id = uiComponent.getId();
            if(id.equals("set-y")){
                if(param < -3 || param > 3)
                    throw new ValidatorException(
                            new FacesMessage(bundle.getString("main.page.set.y.error.range")));
            }else {
                if(param < 2 || param > 5)
                    throw new ValidatorException(
                            new FacesMessage(bundle.getString("main.page.set.r.error.range")));
            }

        }catch(NumberFormatException e){
            throw new ValidatorException(new FacesMessage(bundle.getString("main_page_number_exception")));
        }
    }
}
