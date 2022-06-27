import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    private static final String Url = "https://www.saucedemo.com/";

    @BeforeEach
    void setUP() {
        open(Url);
        $x("//input[@id='user-name']").setValue("performance_glitch_user");
        $x("//input[@id='password']").setValue("secret_sauce").pressEnter();
    }

    /**
     * ElementsCollection получает данные по xpath //button[@class='btn btn_primary btn_small btn_inventory'], затем
     * происходит цикл, который нажимает на эту кнопку столько раз сколько имеет размер эта коллекция, далее мы ищем
     * кол-во товаров в корзине и проверяем чтобы размер с ним совпадал.
     */
    @Test
    @DisplayName("ex1")
    public void AddAllThingsProduct() {
        ElementsCollection elementsCollectionResult = $$x("//button[@class='btn btn_primary btn_small btn_inventory']");
        for (SelenideElement element : elementsCollectionResult) element.click();
        $x("//*[@id=\"shopping_cart_container\"]/a").click();
        $$(By.className("inventory_item_name")).shouldHave(size(6));
    }


    /**
     * Функция получает коллекцию всех эл-тов корзины, далее проверяет их кол-во с тем которое было добавлено.
     */
    @Test
    @DisplayName("ex2")
    public void AddThingsProductBubble() {
        int i = 0;
        ElementsCollection elementsCollectionResult = $$x("//button[@class='btn btn_primary btn_small btn_inventory']");
        for (SelenideElement element : elementsCollectionResult) {
            element.click();
            i++;
            $(By.cssSelector(".shopping_cart_badge")).shouldHave(Condition.exactText(String.valueOf(i)));
        }
    }

    /**
     * Функция проверяет название товара на главной странице сайта и на странице странице товара.
     */
    @Test
    @DisplayName("ex4")
    public void TestOpenEachProduct() {
        int numberThings = $$(By.xpath("//div[@class='inventory_item_name']")).size();
        for (int i = 0; i < numberThings; i++) {
            SelenideElement element = elements(By.xpath("//div[@class='inventory_item_name']")).get(i);
            String nameThings = element.getText();
            element.click();
            $(By.xpath("//div[@class = 'inventory_details_name large_size']")).shouldBe(Condition.text(nameThings));
            back();

        }
    }
}