package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

@Owner("katz42")
@Feature("Работа с задачами")

public class issueTest {
    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 12;

    @Test
    @DisplayName("Пользователь должен иметь возможность найти Issue по номеру")
    public void shouldFindIssueByNumber() {
        link("GitHub", String.format("%s/%s", BASE_URL, REPOSITORY));
        parameter("Репозиторий", REPOSITORY);
        parameter("Номер Issue", ISSUE);

        step("Открываем главную страницу", ()-> {
            open(BASE_URL);
        });

        step("Ищем репозиторий " + REPOSITORY, ()-> {
            $(".input-sm").sendKeys(REPOSITORY);
            $(".input-sm").click();
            $(".input-sm").submit();
        });

        step("Переходим по ссылке " + REPOSITORY, ()-> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });

        step("Открываем с задачами и сортируем от старых к новым", ()-> {
            $(withText("Issues")).click();
            $(byTitle("Sort")).click();
            $(withText("Oldest")).click();
        });

        step("Проверяем наличие Issue " + ISSUE, ()-> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }
}