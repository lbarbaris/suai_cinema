package ru.lbarbaris.telegramservice.service.dataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.lbarbaris.telegramservice.dto.*;
import ru.lbarbaris.telegramservice.dto.dataService.UserData;
import ru.lbarbaris.telegramservice.dto.dataService.UserDataListResponse;

import java.util.List;

@Service
public class UserDataService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<UserData> getUserDataList(){
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(links.allUsers.getDescription())
                .retrieve()
                .bodyToMono(UserDataListResponse.class)
                .block()
                .getEmbedded()
                .getUserDataList();
    }

    public UserData getUserData(String username){
        WebClient webClient = webClientBuilder.build();

        return webClient.get()
                .uri(links.userDataByUsername.getDescription() + username)
                .retrieve()
                .bodyToMono(UserData.class)
                .block();

    }




}
