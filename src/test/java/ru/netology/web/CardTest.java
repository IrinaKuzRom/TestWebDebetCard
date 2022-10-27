package ru.netology.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static com.codeborne.selenide.Condition.exactText;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {
    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }



    @Test
    void shouldNotReturnIfNameIsInvalid() {
        $("[data-test-id=name] input").setValue("Sidorov Ivan");
        $("[data-test-id=phone] input").setValue("+79250001111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotReturnIfPhoneIsInvalid() {
        $("[data-test-id=name] input").setValue("Сидоров Иван");
        $("[data-test-id=phone] input").setValue("+792500111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotReturnIfNameIsEmpty() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79250001111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldNotReturnIfPhoneIsEmpty() {
        $("[data-test-id=name] input").setValue("Сидоров Иван");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotReturnIfCheckBoxIsEmpty() {
        $("[data-test-id=name] input").setValue("Сидоров Иван");
        $("[data-test-id=phone] input").setValue("+79250001111");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
    @Test
    void shouldRequest() {
        $("[data-test-id=name] input").setValue("Сидоров Иван");
        $("[data-test-id=phone] input").setValue("+79250001111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

}



