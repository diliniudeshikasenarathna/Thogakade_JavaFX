import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

public class Starter extends Application {
    public static void main(String[] args) {
        String password="Dilini@1134";
        String key="5586#APr";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        basicTextEncryptor.setPassword(key);
        String encrypt = basicTextEncryptor.encrypt(password);

        System.out.println("Encrpyt password : "+encrypt);

        String decrypt = basicTextEncryptor.decrypt(encrypt);

        System.out.println("Decrpyt password : "+decrypt);

        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/login_form.fxml"))));
        stage.show();
    }
}
