package ru.lbarbaris.webservice.service.dataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.lbarbaris.webservice.dto.*;
import ru.lbarbaris.webservice.dto.dataService.*;

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

    public void save(UserData userData, List<Movie> movies){
        UserDataRequest userDataRequest = new UserDataRequest(userData.getId(), userData.getUsername(), userData.getCinemaCount(), movies);
        WebClient webClient = webClientBuilder.build();
        webClient.post()
                .uri(links.allUsers.getDescription())
                .body(Mono.just(userDataRequest), UserDataRequest.class)
                .retrieve()
                .bodyToMono(UserDataRequest.class)
                .block();
    }


}
