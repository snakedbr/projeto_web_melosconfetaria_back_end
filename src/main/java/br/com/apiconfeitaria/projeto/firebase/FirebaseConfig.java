package br.com.apiconfeitaria.projeto.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount =
            new FileInputStream("src/main/java/br/com/apiconfeitaria/projeto/firebase/key.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setStorageBucket("meloconfeitaria-c38eb.appspot.com")  // Substitua pelo nome do seu bucket
            .build();

        return FirebaseApp.initializeApp(options);
    }
}