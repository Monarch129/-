import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeleniumTest {
    public static WebDriver driver;

    @BeforeEach
    void Driver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\antip\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://trendoptom.ru/");
    }

    @Test
    void InputProduct() {
        String expected = "Фонарик";

        WebElement inputElement = driver.findElement(By.xpath("//input[@name='search']"));
        inputElement.sendKeys(expected);
        String actual = inputElement.getAttribute("value");

        assertEquals(expected, actual);
    }

    @Test
    void ButtonSearch() {
        String expected = "https://trendoptom.ru/search/";

        WebElement buttonElement = driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/div[3]/div/span/button"));
        buttonElement.click();
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }

    @Nested
    public class PositiveTests {
        @Test
        void CheckReviewsButton() {
            String expected = "https://trendoptom.ru/otzivy";

            WebElement buttonElement = driver.findElement(By.xpath("/html/body/header/div[2]/div/div[1]/nav/div/ul/li[6]/a"));
            buttonElement.click();
            String actual = driver.getCurrentUrl();
            assertEquals(expected, actual);
        }

        @Test
        public void CheckNewPositionsButton() {
            String expected = "https://trendoptom.ru/novie-postupleniay-tovara/";

            WebElement ButtonElement1 = driver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/nav/button/i"));
            ButtonElement1.click();
            WebElement ButtonElement2 = driver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/nav/ul/li[2]/a"));
            ButtonElement2.click();
            String actual = driver.getCurrentUrl();
            assertEquals(expected, actual);
        }

        @Test
        void CheckSearchProduct() {
            String expected = "https://trendoptom.ru/search/?search=%D0%A4%D0%BE%D0%BD%D0%B0%D1%80%D0%B8%D0%BA";

            WebElement searchElement = driver.findElement(By.xpath("//input[@name='search']"));
            searchElement.sendKeys("Фонарик");
            WebElement buttonElement = driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/div[3]/div/span/button"));
            buttonElement.click();
            String actual = driver.getCurrentUrl();
            assertEquals(expected, actual);
        }

        @Test
        void CheckAuthorization() {
            String expected = "Антон Попов";

            WebElement element1 = driver.findElement(By.xpath("/html/body/footer/div/div[2]/div/div[2]/ul[1]/li[8]/a"));
            element1.click();
            WebElement buttonLkElement = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/ul/li[2]/a"));
            buttonLkElement.click();
            WebElement element2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/div[1]/input"));
            element2.sendKeys("abiklabon@mail.ru");
            WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/div[2]/input"));
            element3.sendKeys("nrivporad12");
            WebElement element4 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/input[1]"));
            element4.click();
            WebElement element5 = driver.findElement(By.xpath("/html/body/div[2]/div/column/div/a[2]"));
            element5.click();
            String actual = driver.findElement(By.xpath("/html/body/nav/div/div[2]/div[1]/div/button/b")).getText();
            assertEquals(expected, actual);
        }

        @Test
        void CheckForgotPasswordButton() {
            String expected = "https://trendoptom.ru/forgot-password/";

            WebElement signInElement = driver.findElement(By.xpath("/html/body/footer/div/div[2]/div/div[2]/ul[1]/li[8]/a"));
            signInElement.click();
            WebElement buttonLk = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/ul/li[2]/a"));
            buttonLk.click();
            WebElement passworForgotButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/div[2]/a"));
            passworForgotButton.click();
            String actual = driver.getCurrentUrl();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class NegativeTests {
        @Test
        void CheckSignInWithInvalidCredentials() {
            String expected = "Неправильно заполнены поля E-Mail и/или пароль!";

            WebElement element1 = driver.findElement(By.xpath("/html/body/footer/div/div[2]/div/div[2]/ul[1]/li[8]/a"));
            element1.click();
            WebElement buttonLkElement = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/ul/li[2]/a"));
            buttonLkElement.click();
            WebElement element2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/div[1]/input"));
            element2.sendKeys("abdsfsdfin@mail.ru");
            WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/div[2]/input"));
            element3.sendKeys("nriad12");
            WebElement element4 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/input[1]"));
            element4.click();
            String actual = driver.findElement(By.xpath("/html/body/div[2]/div[1]")).getText();
            assertEquals(expected, actual);
        }

        @Test
        public void CheckSubscribers() {
            String expected = "Пожалуйста, введите действующий адрес электронной почты!";

            WebElement element1 = driver.findElement(By.xpath("/html/body/footer/div/div[1]/div[1]/div/div[2]/input"));
            element1.sendKeys("dvsv");
            WebElement element12 = driver.findElement(By.xpath("/html/body/footer/div/div[1]/div[1]/div/div[3]/button"));
            element12.click();
            String actual = driver.findElement(By.xpath("/html/body/footer/div/div[1]/div[1]/div/div[2]/span")).getText();
            assertEquals(expected, actual);
        }

        @Test
        public void CheckSearch() {
            String expected = "Нет товаров, соответствующих критериям поиска.";

            WebElement element1 = driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/div[3]/div/input"));
            element1.sendKeys("Fonarik");
            WebElement element2 = driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/div[3]/div/span/button"));
            element2.click();
            String actual = driver.findElement(By.xpath("/html/body/div[2]/div/div/p[2]")).getText();
            assertEquals(expected, actual);
        }

        // По какой то причине этот тест проходит только в развернутом на весь экран окне браузера.
//        @Test
//        public void CheckBasket() {
//            String expected = "Для регистрации вы должны быть согласны с документом Политика безопасности!";
//
//            WebElement element1 = driver.findElement(By.xpath("/html/body/nav/div/div[2]/div[1]/div/button"));
//            element1.click();
//            WebElement element2 = driver.findElement(By.xpath("/html/body/nav/div/div[2]/div[1]/div/ul[2]/li[1]/a"));
//            element2.click();
//            WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]"));
//            element3.click();
//
//            String actual = driver.findElement(By.xpath("/html/body/div[2]/div[1]")).getText();
//            System.out.println(actual);
//            assertEquals(expected, actual);
//
//
//        }

// По какой то причине не кликантся кнопка, что только сделать не пытался, никак не хочет нажиматься.
//        @Test
//        void CheckSignUpWithInvalidEmail() {
//            String emailValue = "123456";
//            String expected = "E-Mail адрес не найден, проверьте и попробуйте ещё раз!";
//
//            WebElement buttonElement = driver.findElement(By.xpath("/html/body/nav/div/div[2]/div[1]/div/button/span[1]"));
//            buttonElement.click();
//            WebElement emailFieldElement = driver.findElement(By.xpath("/html/body/nav/div/div[2]/div[1]/div/ul[2]/li[1]/a"));
//            emailFieldElement.click();
//            WebElement emailFieldElement1 = driver.findElement(By.xpath("/html/body/div[2]/div/column/div/a[3]"));
//            emailFieldElement1.click();
//            WebElement emailFieldElement2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div/div/input"));
//            emailFieldElement2.sendKeys("jhjjhj");
//            WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div"));
//            WebElement element4 = element3.findElement(By.className("pull-right"));
//            Actions actions = new Actions(driver);
//            actions.moveToElement(element4).click().perform();
//            String actual = driver.findElement(By.xpath("/html/body/div[6]/div/div")).getText();
//            System.out.println(actual == null ? "nothing" : actual);
//            assertEquals(expected, actual);
//        }


        @AfterEach
        public void QuitWebDriver() {
            driver.quit();
        }
    }
}


