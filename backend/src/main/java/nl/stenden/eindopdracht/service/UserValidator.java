package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass){
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("userName", "Size.userForm.userName");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

    }
}
