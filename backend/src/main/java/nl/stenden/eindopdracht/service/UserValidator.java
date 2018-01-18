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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!user.getPassword().matches(upperCaseChars )) {
            errors.rejectValue("password", "upperCase.userForm.password");
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!user.getPassword().matches(lowerCaseChars )) {
            errors.rejectValue("password", "lowerCase.userForm.password");
        }
        String numbers = "(.*[0-9].*)";
        if (!user.getPassword().matches(numbers)) {
            errors.rejectValue("password", "number.userForm.password");
        }
        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (user.getPassword().matches(specialChars )) {
            errors.rejectValue("password", "specialChar.userForm.password");
        }
    }
}
