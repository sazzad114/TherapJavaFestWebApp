package net.therap.validator;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.faces.Validator;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/31/12 4:35 PM
 */
@Name("checkBoxValidator")
@Validator
@BypassInterceptors
@Scope(ScopeType.CONVERSATION)
public class CheckBoxValidator implements javax.faces.validator.Validator {
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof Boolean && ((Boolean) value).equals(Boolean.FALSE)) {
            String clientId = component.getClientId(context);
            String requiredMessage = MessageFormat.format(UIInput.REQUIRED_MESSAGE_ID, clientId);
            throw new ValidatorException(new FacesMessage(requiredMessage));
        }
    }
}
