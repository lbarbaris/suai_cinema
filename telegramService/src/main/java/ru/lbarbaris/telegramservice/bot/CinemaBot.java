package ru.lbarbaris.telegramservice.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lbarbaris.telegramservice.dto.dataService.Movie;
import ru.lbarbaris.telegramservice.dto.dataService.UserData;
import ru.lbarbaris.telegramservice.service.dataService.MovieService;
import ru.lbarbaris.telegramservice.service.dataService.UserDataService;
import ru.lbarbaris.telegramservice.service.site.SiteService;

import java.util.List;

@Component
public class CinemaBot extends TelegramLongPollingBot {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private SiteService siteService;
    public CinemaBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }
        switch (update.getMessage().getText()){
            case "/allMovies" -> allMovies(update);
            case "/myMovies" -> myMovies(update);

        }
    }

    private void allMovies(Update update){
        var username = update.getMessage().getChat().getUserName();

        List<Movie> moviesFromUser = movieService.getMoviesList(username);
        List<Movie> moviesFromSite = siteService.getMoviesList();

        moviesFromSite.removeIf(movie -> moviesFromUser.stream().anyMatch(movie1 -> movie1.getName().equals(movie.getName())));

        sendMovies(moviesFromSite, update);
    }




    private void myMovies(Update update){
        var username = update.getMessage().getChat().getUserName();
        List<Movie> movies = movieService.getMoviesList(username);

        sendMovies(movies, update);
    }




    private void sendMovies(List<Movie> movies, Update update){
        for (Movie movie : movies) {
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(update.getMessage().getChatId().toString());
            sendPhotoRequest.setPhoto(new InputFile(movie.getImageurl()));
            sendPhotoRequest.setCaption("*" + movie.getName() + "*" + '\n' +
                    "*Rating*" + " : " + movie.getRating() + '\n' +
                    movie.getDescription());
            sendPhotoRequest.setParseMode("Markdown");

            try {
                execute(sendPhotoRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "suaiPracticeBot";
    }
}
