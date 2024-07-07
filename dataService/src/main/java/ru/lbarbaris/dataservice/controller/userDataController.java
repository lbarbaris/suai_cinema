package ru.lbarbaris.dataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.MovieResponse;
import ru.lbarbaris.dataservice.entity.UserData;
import ru.lbarbaris.dataservice.entity.UserDataResponse;
import ru.lbarbaris.dataservice.service.UserDataService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userDatas")
public class userDataController {

        @Autowired
        private UserDataService userDataService;

    @PostMapping
    public UserData saveMovie(@RequestBody UserDataResponse userDataResponse){
        return userDataService.save(userDataResponse);

    }

    @GetMapping
    public List<UserData> getAll() {
        return userDataService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userDataService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<UserData> getById(@PathVariable("id") Long id) {
        return userDataService.findById(id);
    }

    @GetMapping("/search/getUserDataByUsername")
    public UserData getAllByUsername(@RequestParam("username") String username){
        return userDataService.getAllByUserdataUsername(username);
    }
}
