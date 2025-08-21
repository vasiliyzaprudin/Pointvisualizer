package org.example.pointvisualizer.service;

import org.example.pointvisualizer.model.Point;
import org.example.pointvisualizer.repository.PointRepository;
import java.util.List;

// Сервисный слой для бизнес-логики
public class PointService {
    private PointRepository repository;

    public PointService() {
        this.repository = new PointRepository();
    }

    // Получение всех точек через репозиторий
    public List<Point> getAllPoints() {
        return repository.getAllPoints();
    }

    // Добавление новой точки
    public void addPoint(double x, double y) {
        // Создаем точку с временным ID (реальный ID присвоит БД)
        Point point = new Point(0, x, y);
        repository.addPoint(point);
    }

    // Закрытие соединения с БД
    public void close() {
        repository.close();
    }
}
