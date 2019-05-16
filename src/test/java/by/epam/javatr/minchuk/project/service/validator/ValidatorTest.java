package by.epam.javatr.minchuk.project.service.validator;

import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.validator.Validator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidatorTest {

    private Validator validator = Validator.getInstance();

    @Test
    public void testValidateNameOrSurnamePositive() {
        String trueName = "ИмяName";
        boolean actual = validator.validateNameOrSurname(trueName);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateNameOrSurnameNegative() {
        String wrongName = "Name12";
        boolean actual = validator.validateNameOrSurname(wrongName);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateLoginOrPasswordPositive() {
        String trueLogin = "Login123";
        boolean actual = validator.validateLoginOrPassword(trueLogin);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateLoginOrPasswordNegative() {
        String wrongLogin = "Login123_";
        boolean actual = validator.validateLoginOrPassword(wrongLogin);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateEmailPositive() {
        String trueEmail = "email@mail.ru";
        boolean actual = validator.validateEmail(trueEmail);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateEmailNegative() {
        String wrongEmail = "email@mail55.ru";
        boolean actual = validator.validateEmail(wrongEmail);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateDatePositive() {
        String trueData = "2019-05-09";
        boolean actual = validator.validateDate(trueData);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateDateNegative() {
        String trueData = "2019-77-09";
        boolean actual = validator.validateDate(trueData);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateManey() {
        Double money = 500.25;
        boolean actual = validator.validateMoney(money);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateUniqeLogin_notExist() throws TravelAgencyServiceException {
        String login = "newLogin";
        boolean actual = validator.validateUniqeLogin(login);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateUniqeLogin_isExist() throws TravelAgencyServiceException {
        String login = "login1";
        boolean actual = validator.validateUniqeLogin(login);
        boolean expected = false;
        assertEquals(actual, expected);
    }
}