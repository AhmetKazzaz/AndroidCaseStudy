package com.example.casestudy.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Gson date serializer, supports multiple date formats
 */
public class GsonDateFormatter implements JsonDeserializer<Date> {


    private DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
    private DateFormat format2 = new SimpleDateFormat("HH:mm:ss",Locale.US);
    private DateFormat format3 = new SimpleDateFormat("yyy-MM-dd", Locale.US);

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String j = json.getAsJsonPrimitive().getAsString();
            return parseDate(j);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        if (dateString != null && dateString.trim().length() > 0) {
            try {
                return format1.parse(dateString);
            } catch (ParseException pe) {
                try {
                    return format2.parse(dateString);
                } catch (ParseException pe2) {
                    return format3.parse(dateString);
                }
            }
        } else {
            return null;
        }
    }

}