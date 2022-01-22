package com.bootdo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaoyuzhe
 * @date 2017/12/14.
 */
@Configuration
public class DateConverConfig {
    @Bean
    public Converter<String, Date> stringDateConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                if(source==null||source.equals("")){return null;}
                if(source.contains("T")){
                    source=source.replace('T',' ');
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(source);
                } catch (Exception e) {
                    try {
                        SimpleDateFormat  sdfMinute=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        date=sdfMinute.parse(source);
                    }catch (Exception e1){
                        SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date = sdfday.parse(source);
                        } catch (ParseException e2) {
                            e1.printStackTrace();
//                        System.out.println("unparsebleDate:"+source);
                            return null;
                        }
                    }
                }
                return date;
            }
        };
    }
    //string to Time
    @Bean
    public Converter<String, Time> stringTimeConvert(){
        return new Converter<String,Time>(){

            @Override
            public Time convert(String source) {
                System.out.println("stringToTime Converter");
                if(source!=null&&!source.equals(""))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    try {
                        Date date=sdf.parse(source);
                        Time time=new Time(date.getTime());
                        return time;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }

                }
                else{return null;}
            }
        };
    }
    //string to sql.Date
    @Bean
    public Converter<String, java.sql.Date> stringSQLDateConvert() {
        return new Converter<String, java.sql.Date>() {
            @Override
            public java.sql.Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                java.sql.Date sqlDate = null;
                try {
                    date = sdf.parse(source);
                    sqlDate = new java.sql.Date(date.getTime());
                } catch (Exception e) {
                    SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = sdfday.parse(source);
                        sqlDate = new java.sql.Date(date.getTime());
                    } catch (ParseException e1) {
//                        System.out.println("unparsebleDate:"+source);
                        SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
                        try {
                            date = sdfMonth.parse(source);
                            sqlDate = new java.sql.Date(date.getTime());
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    }
                }
                return sqlDate;
            }
        };
    }

}
