package cloud.tests;

import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.PressEnter;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import cloud.autotests.utils.FileUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;



@Epic("Rent in Hand")
@Story("Rentinhand tests")
@Tag("RentInHandTests")
public class RentInHandTests extends TestBase {

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));

        if (System.getProperty("selenoid_url") != null) {
            Configuration.remote = "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub";
        }

    }

    @Test
    @Description("Проверка входа на сайт проекта")
    void checkSiteCanBeFound() {

        step("Заходим на сайт", () -> {
            open("https://staging.rentinhand.ru/");
        });
        step("Проверяем наличие текста Rent in hand", () -> {
            $("html").shouldHave(text("Rent in Hand"));
        });

    }

    @Test
    @Description("Проверяем работоспособность кнопок навигационной панели")
    void checkButtons() {
        step("Заходим на сайт", () -> {
            open("https://staging.rentinhand.ru/");
        });
        step("Проверяем наличие текста Rent in hand", () -> {
            $("html").shouldHave(text("Rent in Hand"));
        });
        step("Нажимаем на кнопку: Возможности", () -> {
            $(byText("Возможности")).click();
        });
        step("Проверяем скролл до раздела Возможности", () -> {
            $(".col-lg-12").shouldHave(text("это единая система управления вашим прокатным бизнесом"));
        });
        step("Нажимаем на кнопку: Тарифы", () -> {
            $(byText("Тарифы")).click();
        });
        step("Проверяем наличие раздела Тарифы", () -> {
            $("html").shouldHave(text("Мы предлагаем минимальные цены для наших пользователей"));
        });
        step("Нажимаем на кнопку: Расширения", () -> {
            $(byText("Расширения")).click();
        });
        step("Проверяем наличие раздела Расширения", () -> {
            $("#modules").shouldHave(text("мы разработали несколько необходимых функциональных модулей"));
        });
        step("Нажимаем на кнопку: Контакты", () -> {
            $(byText("Контакты")).click();
        });
        step("Проверяем наличие раздела Контакты", () -> {
            $("#contacts").shouldHave(text("Контакты"));
        });

        step("Проверяем работоспособность кнопок Подробнее в разделе Расширения", () -> {
            $(byText("Расширения")).click();
            $$(byText("Подробнее")).get(0).click();
            sleep(2000);
            $("html").shouldHave(text("Магазин"));
            $(byText("Расширения")).click();
            $$(byText("Подробнее")).get(1).click();
            sleep(2000);
            $("html").shouldHave(text("Мастерская"));

        });

        step("Проверяем работоспособность кнопок выбора срока использования продукта в разделе Тарифы", () -> {
            $(byText("Тарифы")).click();
            $$(".nav-link").findBy(text("1 месяц")).click();
            $$(".col-lg-4").get(0).shouldHave(text("1000"));
            $$(".nav-link").findBy(text("6 месяцев")).click();
            $$(".col-lg-4").get(1).shouldHave(text("750"));
            $$(".nav-link").findBy(text("12 месяцев")).click();
            $$(".col-lg-4").get(2).shouldHave(text("500"));
        });

        step("Проверяем работоспособность кнопок Попробовать бесплатно 14 дней", () -> {
            $(".navbar-brand").click();
            $$(byText("Попробовать бесплатно 14 дней")).get(1).click();
            sleep(1000);
            $("#page-login").shouldHave(text("Регистрация"));
            $(".navbar-brand").click();
            $$(byText("Попробовать бесплатно 14 дней")).get(2).click();
            sleep(1000);
            $("#page-login").shouldHave(text("Регистрация"));
            $(".navbar-brand").click();
            $$(byText("Попробовать бесплатно 14 дней")).get(3).click();
            sleep(1000);
            $("#page-login").shouldHave(text("Регистрация"));
            $(".navbar-brand").click();
        });

    }

    @Test
    @Description("Проверка ссылок на сайте проекта")
    void checkLinks() {

        step("Заходим на сайт", () -> {
            open("https://staging.rentinhand.ru/");
        });
        step("Проверяем работоспособность ссылок Подробнее", () -> {
            $(byText("Административная веб-панель")).scrollIntoView(true);
            $$(byPartialLinkText("Подробнее")).get(0).click();
            $("#page-top").shouldHave(text("веб-панель которая открывается из любого браузера"));
            $(".navbar-brand").click();
            $(byText("Мобильное приложение")).scrollIntoView(true);
            $$(byPartialLinkText("Подробнее")).get(1).click();
            $("#page-top").shouldHave(text("один из способов облегчить работу на точках проката и продаж"));
        });

        step("Проверяем работоспособность ссылок в разделе Информация", () -> {
            $(".navbar-brand").click();
            $(byText("Справочное руководство")).click();
            sleep(3000);
            $("#features").shouldHave(text("Справочное руководство"));
            $(".navbar-brand").click();
            $(byText("Помощь")).click();
            $("#features").shouldHave(text("Лицензионный договор-офферта"));
            $(".navbar-brand").click();
            $(byText("Пользовательское соглашение")).click();
            $("h3").shouldHave(text("Пользовательское соглашение"));
            $(".navbar-brand").click();
            $(byText("Политика конфиденциальности")).click();
            $("h3").shouldHave(text("Политика конфиденциальности"));
            $(".navbar-brand").click();
        });
    }

    @Test
    @Description("Проверка авторизации на сайте проекта")
    void checkAuthorizationForm() {

        step("Заходим в раздел авторизации", () -> {
            open("https://staging.rentinhand.ru/");
            $(byText("Логин")).click();
            $(".custom-form").shouldHave(text("Авторизация"));
        });
        step("Заполняем форму авторизации", () -> {
            $("#input-login").setValue("test");
            $("#input-password").setValue("123123").pressEnter();
        });
        step("Проверяем успешную авторизацию", () -> {
            $("h2").shouldHave(text("Александр Швидченко"));
        });

    }

    @Test
    @Description("Проверка навигационной панели личного кабинета")
    void checkNavigationPanel() {

        step("Заходим в раздел авторизации", () -> {
            open("https://lk-staging.rentinhand.ru/ru/login");
            $(".custom-form").shouldHave(text("Авторизация"));
        });
        step("Заполняем форму авторизации", () -> {
            $("#input-login").setValue("test");
            $("#input-password").setValue("123123").pressEnter();
        });
        step("Проверяем успешную авторизацию", () -> {
            $("h2").shouldHave(text("Александр Швидченко"));
        });
        step("Проверяем вкладку Расходы", () -> {
            $(byText("Расходы")).click();
            $("html").shouldHave(text("Вид расходов"));
        });
        step("Проверяем вкладку Клиенты", () -> {
            $(byText("Клиенты")).click();
            $("html").shouldHave(text("Фио"));
        });
        step("Проверяем вкладку Сотрудники", () -> {
            $(byText("Сотрудники")).click();
            $("html").shouldHave(text("Фио"));
        });
        step("Проверяем вкладку Инвентарь", () -> {
            $(byText("Инвентарь")).click();
            $(".navbar-static-side").shouldHave(text("Список"));
            $(byText("Список")).click();
            $("html").shouldHave(text("Всего"));
            $(byText("Бронирование")).click();
            $("html").shouldHave(text("Сегодня"));
        });
        step("Проверяем вкладку Аренды", () -> {
            $(byText("Аренды")).click();
            $(".navbar-static-side").shouldHave(text("Создать"));
            $(byText("Создать")).click();
            $("html").shouldHave(text("Укажите время аренды"));
            $(byText("Список")).click();
            $("html").shouldHave(text("Всего"));
            $(byText("Бронирование")).click();
            $("html").shouldHave(text("Сегодня"));
        });
        step("Проверяем вкладку Магазин", () -> {
            $(byText("Магазин")).click();
            $(".navbar-static-side").shouldHave(text("Создать"));
            $(byText("Создать")).click();
            $("html").shouldHave(text("Укажите клиента"));
            $(byText("Продажи")).click();
            $("html").shouldHave(text("Всего"));
            //$(byText("Товар")).click();
            //$("html").shouldHave(text("Столбцы"));
        });
        step("Проверяем вкладку Мастерсткая", () -> { // МАСТЕРСКАЯ
            $(byText("Мастерсткая")).click();    // МАСТЕРСКАЯ
            $(".navbar-static-side").shouldHave(text("Создать"));
            $(byText("Создать")).click();
            $("html").shouldHave(text("Укажите клиента"));
            $(byText("Заявки")).click();
            $("html").shouldHave(text("Всего"));
            //$(byText("Услуги")).click();
            //$("html").shouldHave(text("Столбцы"));
        });
        step("Проверяем вкладку Настройки", () -> {
            $(byText("Настройки")).click();
            $(".navbar-static-side").shouldHave(text("Пункты проката"));
            $$(byText("Настройки")).get(1).click();
            $("html").shouldHave(text("Основные настройки"));
            $(byText("Пункты проката")).click();
            $("html").shouldHave(text("Всего"));
        });
        step("Проверяем вкладку Поддержка", () -> {
            $(byText("Поддержка")).click();
            $(".navbar-static-side").shouldHave(text("Что нового"));
            $(byText("Обращения")).click();
            $("html").shouldHave(text("Всего"));
            $(byText("Справочник")).click();
            $("html").shouldHave(text("Всего"));
            $(byText("Что нового")).click();
            $("#page-wrapper").shouldBe(visible);
        });

    }
}

