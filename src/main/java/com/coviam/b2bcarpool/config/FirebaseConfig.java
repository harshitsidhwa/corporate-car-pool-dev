package com.coviam.b2bcarpool.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class FirebaseConfig {

    @Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() throws IOException {
        String databaseUrl = environment.getProperty("firebase.database.url");
        String configPath = environment.getProperty("firebase.config.path");
        log.info("databaseUrl-->" + databaseUrl);
        FileInputStream serviceAccount = new FileInputStream(configPath);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();
        FirebaseApp.initializeApp(options);
    }
}