package com.coviam.b2bcarpool.helper;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

@Component("com.coviam.b2bcarpool.helper.DbHelper")
public class DbHelper {

    private Firestore firestoreDb;

    public DbHelper() {
        this.firestoreDb = FirestoreClient.getFirestore();
    }

    public Firestore getFirestoreDb() {
        return firestoreDb;
    }
}