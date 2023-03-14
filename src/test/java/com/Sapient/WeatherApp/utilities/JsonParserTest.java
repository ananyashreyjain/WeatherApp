package com.Sapient.WeatherApp.Parser;

import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.utilites.JsonParser;
import com.Sapient.WeatherApp.models.RawResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class JsonParserTest {

    @Test
    void testParser(){
        String json = "{\"cod\":\"200\",\"message\":0,\"cnt\":3,\"list\":[{\"dt\":1678806000,"
        + "\"main\":{\"temp\":281.1,\"feels_like\":278.23,\"temp_min\":280.93,\"temp_max\":281.1,\"pressure\":1005,\"sea_level\":1005,\"grnd_level\":1006,\"humidity\":59,\"temp_kf\":0.17},"
        + "\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],"
        + "\"clouds\":{\"all\":99},\"wind\":{\"speed\":4.87,\"deg\":288,\"gust\":7.62},\"visibility\":10000,\"pop\":0.29,\"rain\":{\"3h\":0.22},"
        + "\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-03-14 15:00:00\"},{\"dt\":1678816800,"
        + "\"main\":{\"temp\":279.83,\"feels_like\":277.06,\"temp_min\":279.15,\"temp_max\":279.83,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1007,\"humidity\":67,\"temp_kf\":0.68},"
        + "\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.02,\"deg\":263,\"gust\":9.02},"
        + "\"visibility\":10000,\"pop\":0.71,\"rain\":{\"3h\":0.54},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-03-14 18:00:00\"},{\"dt\":1678827600,"
        + "\"main\":{\"temp\":278.35,\"feels_like\":274.68,\"temp_min\":278.35,\"temp_max\":278.35,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1008,\"humidity\":75,\"temp_kf\":0},"
        + "\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":5.12,\"deg\":255,\"gust\":11.8},"
        + "\"visibility\":10000,\"pop\":0.37,\"rain\":{\"3h\":0.1},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-03-14 21:00:00\"}],\"city\":{\"id\":2643743,\"name\":\"London\",\"coord\":{\"lat\":51.5085,\"lon\":-0.1257},"
        + "\"country\":\"GB\",\"population\":1000000,\"timezone\":0,\"sunrise\":1678774674,\"sunset\":1678816913}}";
        RawResponse rawResponse = JsonParser.parse(json);
        assert rawResponse.maxTemp.size() == 3;
        assert rawResponse.maxTemp.get(0).compareTo("281.1") == 0;
        assert rawResponse.minTemp.get(1).compareTo("279.15") == 0;
        assert rawResponse.maxTemp.get(2).compareTo("278.35") == 0;
        assert rawResponse.weather.size() == 3;
        assert rawResponse.weather.get(0).compareTo("Rain") == 0;
    }
}
