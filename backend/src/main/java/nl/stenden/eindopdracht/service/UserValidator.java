package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void validate(Object o, Errors errors){
        User user = (User) o;
        logger.info(user.getEmail() + " " + user.getPassword());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
//        String upperCaseChars = "(.*[A-Z].*)";
//        if (!user.getPassword().contains(upperCaseChars )) {
//            errors.rejectValue("password", "upperCase.userForm.password");
//        }
//        String lowerCaseChars = "(.*[a-z].*)";
//        if (!user.getPassword().contains(lowerCaseChars )) {
//            errors.rejectValue("password", "lowerCase.userForm.password");
//        }
//        String numbers = "(.*[0-9].*)";
//        if (!user.getPassword().contains(numbers)) {
//            errors.rejectValue("password", "number.userForm.password");
//        }
//        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
//        if (user.getPassword().contains(specialChars )) {
//            errors.rejectValue("password", "specialChar.userForm.password");
//        }
    }
}
