package org.example.pointvisualizer.controller;

import org.example.pointvisualizer.model.Point;
import org.example.pointvisualizer.service.PointService;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.List;

// Контроллер для управления UI и взаимодействия с сервисом
public class MainController {
    @FXML
    private Canvas canvas;          // Холст для рисования точек
    @FXML
    private Button refreshButton;   // Кнопка обновления
    @FXML
    private Label statusLabel;      // Метка для статуса

    private PointService pointService;
    private GraphicsContext gc;

    // Инициализация контроллера
    @FXML
    public void initialize() {
        pointService = new PointService();
        gc = canvas.getGraphicsContext2D();
        refreshPoints();  // Загружаем точки при запуске

        // Обработчик клика по холсту для добавления новых точек
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleCanvasClick);
    }

    // Обработчик клика по холсту
    private void handleCanvasClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        // Добавляем точку в БД
        pointService.addPoint(x, y);
        statusLabel.setText("Added point at: " + (int)x + ", " + (int)y);

        // Обновляем отображение
        refreshPoints();
    }

    // Обновление точек на холсте
    @FXML
    private void refreshPoints() {
        clearCanvas();
        List<Point> points = pointService.getAllPoints();

        if (points.isEmpty()) {
            statusLabel.setText("No points found in database");
            return;
        }

        statusLabel.setText("Loaded " + points.size() + " points");

        // Рисуем каждую точку
        for (Point point : points) {
            drawPoint(point);
        }
    }

    // Отрисовка одной точки
    private void drawPoint(Point point) {
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);

        // Рисуем круг (точку)
        gc.fillOval(point.getX() - 5, point.getY() - 5, 10, 10);
        gc.strokeOval(point.getX() - 5, point.getY() - 5, 10, 10);

        // Подписываем ID точки
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(point.getId()), point.getX() + 8, point.getY() - 8);
    }

    // Очистка холста
    private void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    // Закрытие ресурсов при выходе
    public void shutdown() {
        if (pointService != null) {
            pointService.close();
        }
    }
}