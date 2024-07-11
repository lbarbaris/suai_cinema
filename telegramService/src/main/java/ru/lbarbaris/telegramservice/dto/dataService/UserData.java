package ru.lbarbaris.telegramservice.dto.dataService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class UserData {

        private long id;

        private String username;

        private int cinemaCount;

        /*private List<Movie> movies;*/


}
