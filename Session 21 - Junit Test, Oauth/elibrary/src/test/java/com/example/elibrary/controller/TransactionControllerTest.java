package com.example.elibrary.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.example.elibrary.models.MyUser;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.enums.TransactionType;

import static com.example.elibrary.constants.ApplicationConstants.STUDENT_AUTHORITY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void transact_test() throws Exception {
        // Initialize MockMvc with security filters
        MockMvc mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        // Setup mocked user with student
        Student student = Student.builder().id(1).name("Test Student").build();
        MyUser user = MyUser.builder()
                .id(1)
                .username("kishan")
                .password("kishan123")
                .authority(STUDENT_AUTHORITY)
                .student(student)
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(user, "kishan123", user.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);

        int bookId = 1;

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("bookId", String.valueOf(bookId));

        // Set security context before test
        SecurityContextHolder.setContext(context);

        ResultActions response = mockMvc.perform(post("/transaction/" + TransactionType.ISSUE.name())
                .contentType(MediaType.APPLICATION_JSON)
                .params(params)
                .principal(auth));

        // Verify the response
        response.andDo(print()).andExpect(status().isCreated());
    }
}
