package ru.lbarbaris.webservice.dto.dataService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDataListResponse {

    @JsonProperty("_embedded")
    private  Embedded embedded;

    @Getter
    @Setter
    public static class Embedded{
        @JsonProperty("userDatas")
        private List<UserData> userDataList;
    }




}
