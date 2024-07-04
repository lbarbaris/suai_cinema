package ru.lbarbaris.dataservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "userdata")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "username")
    private String username;

    @Column(name = "cinemacount")
    private int cinemaCount;

    @JsonManagedReference
    @OneToMany(mappedBy = "userdata", cascade = CascadeType.ALL)
    private List <Movie> movies;

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cinemaCount=" + cinemaCount +
                '}';
    }
}