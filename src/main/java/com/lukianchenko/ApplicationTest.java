package com.lukianchenko;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class ApplicationTest {

    String one = "wer";
    String two = "wer";

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
//        InputStream serviceAccount = new FileInputStream("C:\\GCPfirestore\\FirestoreTest2-103474bfee56.json");
//        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//        FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
//        FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
//                .setProjectId("firestoretest2-272106")

//                .setCredentials(credentials)
//                .build();
//        Firestore db = firestoreOptions.getService();
//        Firestore db = FirestoreOptions.getDefaultInstance().getService();

        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("firestoretest2-272106")
                        .build();
        Firestore db = firestoreOptions.getService();

        DocumentReference docRef = db.collection("users").document("appEngine2");
//         Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Ada");
        data.put("last", "Lovelace");
        data.put("born", 1815);
//        asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
//         ...
//         result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());


        DocumentReference docRef1 = db.collection("users").document("aturing");
//      Add document data with an additional field ("middle")
        Map<String, Object> data1 = new HashMap<>();
        data1.put("first", "Alan");
        data1.put("middle", "Mathison");
        data1.put("last", "Turing");
        data1.put("born", 1912);

        ApiFuture<WriteResult> result1 = docRef1.set(data1);
        System.out.println("Update time : " + result1.get().getUpdateTime());


        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
//      ...
//      query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last"));
            System.out.println("Born: " + document.getLong("born"));


        }
        
    }






}
