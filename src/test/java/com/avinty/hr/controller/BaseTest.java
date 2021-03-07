package com.avinty.hr.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Base test.
 *
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Szo
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("unit-test")
@AutoConfigureMockMvc
@Transactional
class BaseTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Put method mvc result.
     *
     * @param url        the url
     * @param jsonObject the json object
     * @return the mvc result
     * @throws Exception the exception
     */
    MvcResult putMethod(final String url, final JSONObject jsonObject) throws Exception {
        return this.mockMvc.perform(
                MockMvcRequestBuilders.put(url)
                        .secure(true)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Post method mvc result.
     *
     * @param url        the url
     * @param jsonObject the json object
     * @return the mvc result
     * @throws Exception the exception
     */
    MvcResult postMethod(final String url, final JSONObject jsonObject) throws Exception {
        return this.mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
                        .secure(true))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Gets method.
     *
     * @param url the url
     * @return the method
     * @throws Exception the exception
     */
    MvcResult getMethod(final String url) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                .secure(true))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Delete method mvc result.
     *
     * @param url the url
     * @return the mvc result
     * @throws Exception the exception
     */
    MvcResult deleteMethod(final String url) throws Exception {
        return this.mockMvc.perform(
                MockMvcRequestBuilders.delete(url)
                        .secure(true))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
