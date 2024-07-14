package ru.lbarbaris.telegramservice.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lbarbaris.telegramservice.dto.dataService.Movie;
import ru.lbarbaris.telegramservice.dto.dataService.UserData;
import ru.lbarbaris.telegramservice.service.dataService.MovieService;
import ru.lbarbaris.telegramservice.service.dataService.UserDataService;
import ru.lbarbaris.telegramservice.service.site.SiteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CinemaBot extends TelegramLongPollingBot {

    private HashMap<String, Movie> movieToSaveStorage;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private SiteService siteService;
    public CinemaBot(@Value("${bot.token}") String botToken) {
        super(botToken);
        movieToSaveStorage = new HashMap<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if ((!update.hasMessage() || !update.getMessage().hasText()) && !update.hasCallbackQuery()){
            return;
        }
        if (update.hasCallbackQuery()){
            addOrDelete(update);
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


        if (moviesFromSite != null && !moviesFromSite.isEmpty()){
            moviesFromSite.removeIf(movie -> moviesFromUser.stream().anyMatch(movie1 -> movie1.getName().equals(movie.getName())));
            sendMovies(moviesFromSite, update, false);
        }
        else {
            sendSorry(update, true);
        }
    }




    private void myMovies(Update update){
        var username = update.getMessage().getChat().getUserName();
        List<Movie> movies = movieService.getMoviesList(username);
        if (!movies.isEmpty()){
            sendMovies(movies, update, true);
        }
        else {
            sendSorry(update, false);
        }
    }




    private void sendMovies(List<Movie> movies, Update update, boolean addOrDelete){
        UserData userData = userDataService.getUserData(update.getMessage().getChat().getUserName());

        for (Movie movie : movies) {
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(update.getMessage().getChatId().toString());
            sendPhotoRequest.setPhoto(new InputFile(movie.getImageurl()));
            sendPhotoRequest.setCaption("*" + movie.getName() + "*" + '\n' +
                    "*Rating*" + " : " + movie.getRating() + '\n' +
                    movie.getDescription());
            sendPhotoRequest.setParseMode("Markdown");

            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton movieButton = new InlineKeyboardButton();

            if (addOrDelete){
                movieButton.setText("Удалить из просмотренного");
                userData.setCinemaCount(userData.getCinemaCount() - 1);
                movieButton.setCallbackData("1" + movie.getName());
            }
            else {
                movieButton.setText("Я посмотрел");
                userData.setCinemaCount(userData.getCinemaCount() + 1);
                movieToSaveStorage.put(movie.getName(), movie);
                movieButton.setCallbackData("0" + movie.getName());
            }


            row.add(movieButton);
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            sendPhotoRequest.setReplyMarkup(keyboardMarkup);





            try {
                execute(sendPhotoRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void addOrDelete(Update update){
        String data = update.getCallbackQuery().getData();
        boolean aod = (data.charAt(0) == '1');
        String name = data.substring(1);


        if (aod){ // если надо удалить, то мы фильм на удаление ищем среди тех, которые у нас уже есть
            List<Movie> movies = movieService.getMoviesList(update.getCallbackQuery().getFrom().getUserName());
            for (Movie movie : movies) {
                if (movie.getName().equals(name)) {
                    movieService.deleteMovie(movie.getId());
                    break;
                }
            }

        }
        else {
            movieService.saveMovie(
                    movieToSaveStorage.get(name),
                    userDataService.getUserData(update.getCallbackQuery().getFrom().getUserName())
            );
            movieToSaveStorage.remove(name);

        }
    }


    public void sendSorry(Update update, boolean isFromAll){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        if (isFromAll){
            sendMessage.setText("Извините, фильмов не найдено, приходите позже");
        }
        else {
            sendMessage.setText("Извините, фильмов не найдено");
        }

        try {
            execute(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "suaiPracticeBot";
    }
}
