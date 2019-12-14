package com.coviam.b2bcarpool.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
public class FirebaseConfig {

    /*@Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }*/

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() throws IOException {
        String databaseUrl = environment.getProperty("firebase.database.url");
        String configPath = environment.getProperty("firebase.config.path");
        log.info("databaseUrl-->" + databaseUrl);
//        FileInputStream serviceAccount = new FileInputStream(configPath);
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl(databaseUrl)
//                .build();
//        FirebaseApp.initializeApp(options);
    }
}
