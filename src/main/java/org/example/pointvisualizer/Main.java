package org.example.pointvisualizer;

import org.example.pointvisualizer.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Главный класс приложения JavaFX
public class Main extends Application {
    private MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загрузка FXML файла интерфейса
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();

        // Получаем контроллер для управления shutdown
        controller = loader.getController();

        // Настройка главного окна
        primaryStage.setTitle("Point Visualizer");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Закрываем ресурсы при выходе из приложения
        if (controller != null) {
            controller.shutdown();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}