package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


class MealRestControllerTest extends AbstractControllerTest {

    @Autowired
    MealService mealService;

    private static final String REST_URL = MealRestController.REST_URL + "/";

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MealTestData.MEALS));
    }

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(REST_URL + MealTestData.MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MealTestData.MEAL1));
    }

    @Test
    void deleteTest() throws Exception {
                mockMvc.perform(delete(REST_URL + MealTestData.MEAL1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(mealService.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    void updateTest() throws Exception {
        final Meal updated = mealService.get(MEAL1_ID, USER_ID);
        updated.setCalories(100);
        updated.setDescription("new Description");

        mockMvc.perform(put(REST_URL + MealTestData.MEAL1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(mealService.get(MealTestData.MEAL1_ID, USER_ID), updated);
    }

    @Test
    void createsTest() throws Exception {
        final Meal expected = getCreated();

        final ResultActions saved = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        final Meal result = TestUtil.readFromJson(saved, Meal.class);
        assertMatch(mealService.getAll(USER_ID), result, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    void getBetweenTest() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&startTime=&endDate=&endTime="))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(JsonUtil.writeValue(MealsUtil.getWithExceeded(MEALS, USER.getCaloriesPerDay()))));
    }
}
