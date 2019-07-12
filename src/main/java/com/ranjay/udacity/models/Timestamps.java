package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timestamps extends StoredObject {
    private String start_time;
    private int hour;
    private int day;
    private int week;
    private int month;
    private int year;
    private String weekday;

    public Timestamps() {
    };

    public Timestamps(Long time) {
        
        Date date = new Date(time);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.day = calendar.get(Calendar.DAY_OF_WEEK);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
        this.week = calendar.get(Calendar.WEEK_OF_MONTH);
        this.weekday = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).toString();
        this.start_time = localDateFormat.format(date);
    }

    public Timestamps(TStamps ts) {
    }

    @Override
    public String toString() {
        return "{" + " start_time='" + getStart_time() + "'" + ", hour='" + getHour() + "'" + ", day='" + getDay() + "'"
                + ", week='" + getWeek() + "'" + ", month='" + getMonth() + "'" + ", year='" + getYear() + "'"
                + ", weekday='" + getWeekday() + "'" + "}";
    }

    // @Override
    // public <T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> dataStream) {
    //     List<Timestamps> storedObjectList = new ArrayList<>();
    //     Gson converter = new Gson();
    //     for (String line : dataStream.collect(Collectors.toList())) {
    //         TStamps ts = converter.fromJson(line, TStamps.class);
    //         Timestamps timestamp = new Timestamps(ts.getTs());
    //         storedObjectList.add(timestamp);
    //     }
    //     return (Stream<T>) storedObjectList.stream();
        
    // }
    @Override
    public Stream<StoredObject> mapPojoToFileData(Stream<String> dataStream) {
        List<StoredObject> storedObjectList = new ArrayList<>();
        Gson converter = new Gson();
        for (String line : dataStream.collect(Collectors.toList())) {
            TStamps ts = converter.fromJson(line, TStamps.class);
            Timestamps timestamp = new Timestamps(ts.getTs());
            storedObjectList.add(timestamp);
        }
        return storedObjectList.stream();
        
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        // try {
        //     // statement = connection.prepareStatement(insertTableSQL);
        //     statement.setString(1, this.getStart_time());
        //     statement.setInt(2, this.getHour());
        //     statement.setInt(3, this.getDay());
        //     statement.setInt(4, this.getWeek());
        //     statement.setInt(5, this.getMonth());
        //     statement.setInt(6, this.getYear());
        //     statement.setString(7, this.getWeekday());
        //     statement.addBatch();

        // } catch (SQLException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return null;
    }

    

}
