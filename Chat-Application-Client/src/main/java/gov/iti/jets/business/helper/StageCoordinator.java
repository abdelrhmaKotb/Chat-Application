package gov.iti.jets.business.helper;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageCoordinator {

    private static StageCoordinator instance = null;

    private static final Map<String, SceneData> scenes = new HashMap<>();

    private static Stage primaryStage = null;

    private StageCoordinator() {
    }

    public static StageCoordinator getInstance() {
        if (instance == null) {
            return new StageCoordinator();
        }

        return instance;
    }

    public void setStage(Stage stage) {
        if (primaryStage != null) {
            throw new IllegalArgumentException("primary stage already exist");
        }

        primaryStage = stage;
    }

    public void moveToLogin() {
        if (primaryStage == null) {
            throw new RuntimeException("primary stage not set");
        }

        if (!scenes.containsKey("login")) {
            System.out.println("loaded new one");
            System.out.println(scenes);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
                Parent view = loader.load();
                Scene chatScene = new Scene(view);
                SceneData logiSceneData = new SceneData(loader, view, chatScene);
                scenes.put("login", logiSceneData);
                primaryStage.setScene(chatScene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("loaded existing one");
            SceneData logiSceneData = scenes.get("login");
            primaryStage.setScene(logiSceneData.getScene());
        }

        primaryStage.setTitle("login");

    }

    public void moveToSingup() {
        if (primaryStage == null) {
            throw new RuntimeException("primary stage not set");
        }

        if (!scenes.containsKey("signup")) {
            System.out.println("loaded new one");
            System.out.println(scenes);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/signup.fxml"));
                Parent view = loader.load();
                Scene chatScene = new Scene(view);
                SceneData logiSceneData = new SceneData(loader, view, chatScene);
                System.out.println(logiSceneData);
                scenes.put("signup", logiSceneData);
                primaryStage.setScene(chatScene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("loaded existing one");
            SceneData logiSceneData = scenes.get("signup");
            primaryStage.setScene(logiSceneData.getScene());
        }

        primaryStage.setTitle("signup");

    }

    public void moveToChat() {

        if (primaryStage == null) {
            throw new RuntimeException("primary stage not set");
        }

        if (!scenes.containsKey("chat")) {
            System.out.println("loaded new one");
            System.out.println(scenes);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/chatScreen.fxml"));
                Parent view = loader.load();
                Scene chatScene = new Scene(view);
                SceneData logiSceneData = new SceneData(loader, view, chatScene);
                scenes.put("chat", logiSceneData);
                primaryStage.setScene(chatScene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("loaded existing one");
            SceneData logiSceneData = scenes.get("chat");
            primaryStage.setScene(logiSceneData.getScene());
        }

        primaryStage.setTitle("chat");

    }
    public void moveToCharts() {
        if (primaryStage == null) {
            throw new RuntimeException("primary stage not set");
        }

        if (!scenes.containsKey("charts")) {
            System.out.println("loaded new one");
            System.out.println(scenes);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/charts.fxml"));
                Parent view = loader.load();
                Scene chatScene = new Scene(view);
                SceneData logiSceneData = new SceneData(loader, view, chatScene);
                scenes.put("login", logiSceneData);
                primaryStage.setScene(chatScene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("loaded existing one");
            SceneData logiSceneData = scenes.get("charts");
            primaryStage.setScene(logiSceneData.getScene());
        }

        primaryStage.setTitle("Charts");

    }
}
