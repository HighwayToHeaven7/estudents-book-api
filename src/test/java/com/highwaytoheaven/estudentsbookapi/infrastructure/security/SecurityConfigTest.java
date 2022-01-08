package com.highwaytoheaven.estudentsbookapi.infrastructure.security;

import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities="ADMIN")
    public void getUsersNewTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

//    @Test
//    @WithMockUser(authorities="ADMIN")
//    public void postUsersTest() throws Exception {
//        JsonObject json = new JsonObject();
//        json.addProperty("account_status", "ACTIVE");
//        json.addProperty("name", "Name");
//        json.addProperty("surname", "Surname");
//        json.addProperty("role", "STUDENT");
//        json.addProperty("email", "test@student.com");
//        json.addProperty("password", "test");
//        json.addProperty("phone_number", "000000000");
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users")
//                        .content(json.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//    }

    @Test
    @WithMockUser(authorities="PROFESSOR")
    public void getStudentsInGroupsTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/students/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    @WithMockUser
    public void getStudentsTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/students/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(403)).andReturn();
    }

    @Test
    @WithMockUser
    public void getStudentSubjectCardBySemesterTest() throws Exception {
        String semesterNumber = "7";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/students/subject-cards/semesters/" + semesterNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(403)).andReturn();
    }
}
