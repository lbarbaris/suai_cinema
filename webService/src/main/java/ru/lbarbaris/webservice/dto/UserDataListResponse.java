package ru.lbarbaris.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataListResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("_embedded")
    private  Embedded embedded;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded{
        @JsonProperty("userDatas")
        private List<UserData> userDataList;
    }




}
