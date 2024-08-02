//package ru.myproj.projectlenar.controller;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.empty;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import ru.myproj.projectlenar.model.Client;
//import ru.myproj.projectlenar.services.implementor.ClientServiceImpl;
//import ru.myproj.projectlenar.services.SendService;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//@ExtendWith(MockitoExtension.class)
//public class UsersControllerTest {
//    @Mock
//    private ClientServiceImpl clientServiceImpl;
//    @InjectMocks
//    private UsersController usersController;
//    private MockMvc mockMvc;
//    @Mock
//    private SendService sender;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
//    }
//
//    @Test
//    public void shouldGetAllUsers() throws Exception {
//        when(clientServiceImpl.getAllClients()).thenReturn(new ArrayList<>());
//        mockMvc.perform(get("/api/v2/getClients"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", empty()));
//    }
//
//    @Test
//    public void shouldGetByIdUsers() throws Exception {
//        int id = 1;
//        LocalDate birthday = LocalDate.of(1999, 11, 4);
//        Client client = new Client(id, "test", "phone", birthday, true);
//        when(clientServiceImpl.getClientById(id)).thenReturn(client);
//        mockMvc.perform(get("/api/v2/getClient/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(id)))
//                .andExpect(jsonPath("$.fullName", is("test")))
//                .andExpect(jsonPath("$.phone", is("phone")))
//                .andExpect(jsonPath("$.birthday", is(birthday.toString())))
//                .andExpect(jsonPath("$.messageSend", is(true)));
//    }
//
//    @Test
//    public void shouldSendKafkaMessage() throws Exception {
//        mockMvc.perform(post("/api/v2/kafka/send"))
//                .andExpect(status().isOk());
//        verify(sender).send();
//    }
//}
//
//
//
//
//
