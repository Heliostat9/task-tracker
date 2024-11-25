package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfig {
    public static Gson createGson()
    {
        return new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat(DateUtil.format)
                .create();
    }
}
