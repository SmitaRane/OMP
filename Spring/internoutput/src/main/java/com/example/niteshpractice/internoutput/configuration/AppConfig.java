package com.example.niteshpractice.internoutput.configuration;

import com.example.niteshpractice.internoutput.DB;
import com.example.niteshpractice.internoutput.DevDB;
import com.example.niteshpractice.internoutput.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Bean
//    @ConditionalOnProperty(name = "project.mode", havingValue = "development")
//    public DB getDevBean(){
//        return new DevDB();
//    }
//
//    @Bean
//    @ConditionalOnProperty(name = "project.mode", havingValue = "production")
//    public DB getProdBean(){
//        return new ProdDB();
//    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }


}
