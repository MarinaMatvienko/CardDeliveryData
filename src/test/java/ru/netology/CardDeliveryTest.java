package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.*;
import static ru.netology.DataGenerator.Registration.generateDate;
import static ru.netology.DataGenerator.Registration.generateUserInfo;


class CardDeliveryTest {

    @BeforeEach
    void SetUp() {
        open("http://localhost:9999");

    }

    @Test
    void ValidForm() {
        UserInfo user = generateUserInfo();
        $(By.cssSelector("[data-test-id=city] input")).setValue(user.getCity());
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $(By.cssSelector(".calendar-input input")).setValue(generateDate(3));
        $(By.cssSelector("[data-test-id=name] input")).setValue(user.getName());
        $(By.cssSelector("[data-test-id=phone] input")).setValue(user.getPhone());
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(1000));
        $(By.cssSelector("[data-test-id=success-notification]")).shouldHave(visible, Duration.ofSeconds(1000))
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(3)));
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $(By.cssSelector(".calendar-input input")).setValue(generateDate(4));
        $(By.cssSelector(".button")).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(1500));
        $(".button__text").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(1500)).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(4)));
    }

    @Test
    void NoName() {
        UserInfo user = generateUserInfo();
        $(By.cssSelector("[data-test-id=city] input")).setValue(user.getCity());
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $(By.cssSelector(".calendar-input input")).setValue(generateDate(3));
        $(By.cssSelector("[data-test-id=name] input")).setValue(" ");
        $(By.cssSelector("[data-test-id=phone] input")).setValue(user.getPhone());
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(1000));

    }

    @Test
    void NoNumber() {
        UserInfo user = generateUserInfo();
        $(By.cssSelector("[data-test-id=city] input")).setValue(user.getCity());
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $(By.cssSelector(".calendar-input input")).setValue(generateDate(3));
        $(By.cssSelector("[data-test-id=name] input")).setValue(user.getName());
        $(By.cssSelector("[data-test-id=phone] input")).setValue(" ");
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(1000));

    }

    @Test
    void NoCity() {
        UserInfo user = generateUserInfo();
        $(By.cssSelector("[data-test-id=city] input")).setValue("Ереван");
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $(By.cssSelector(".calendar-input input")).setValue(generateDate(3));
        $(By.cssSelector("[data-test-id=name] input")).setValue(user.getName());
        $(By.cssSelector("[data-test-id=phone] input")).setValue(" ");
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(1000));

    }

    @Test
    void NoDate() {
        UserInfo user = generateUserInfo();
        $(By.cssSelector("[data-test-id=city] input")).setValue(user.getCity());
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id=date] input").doubleClick().sendKeys("10.05.2001");
        $(By.cssSelector("[data-test-id=name] input")).setValue(user.getName());
        $(By.cssSelector("[data-test-id=phone] input")).setValue(" ");
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(withText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(1000));

    }

    @Test
    void NoAgreement() {
        UserInfo user = generateUserInfo();
        $(By.cssSelector("[data-test-id=city] input")).setValue(user.getCity());
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $(By.cssSelector(".calendar-input input")).setValue(generateDate(3));
        $(By.cssSelector("[data-test-id=name] input")).setValue(user.getName());
        $(By.cssSelector("[data-test-id=phone] input")).setValue(user.getPhone());
        $(By.cssSelector(".button")).click();
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).shouldBe(visible, Duration.ofSeconds(1000));

    }

}





