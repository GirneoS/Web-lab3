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
public class RDrawValidator implements Validator {
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages",new Locale("ru"));
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double r = (double) o;
            if (r < 2 || r > 5) {
                throw new ValidatorException(new FacesMessage(bundle.getString("main_page_set_r_error_range")));
            }
        }catch(NumberFormatException e){
            throw new ValidatorException(new FacesMessage(bundle.getString("main_page_set_r_error")));
        }
    }
}
