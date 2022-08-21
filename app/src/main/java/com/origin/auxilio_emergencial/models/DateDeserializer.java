package com.origin.auxilio_emergencial.models;

import android.service.autofill.DateValueSanitizer;
import android.util.Log;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Date date = null;
        try {
            date = new SimpleDateFormat("MM/yyyy").parse( json.getAsJsonPrimitive().getAsString() );
        } catch (ParseException e) {
            Log.i("MENSAGEM: ",e.getMessage());
            e.printStackTrace();
            return null;
        }
        return date;
    }
}