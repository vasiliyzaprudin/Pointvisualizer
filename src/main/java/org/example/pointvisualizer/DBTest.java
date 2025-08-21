package org.example.pointvisualizer;

import org.example.pointvisualizer.repository.PointRepository;
import org.example.pointvisualizer.model.Point;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing database connection...");

            // Создаем репозиторий
            PointRepository repo = new PointRepository();

            // Получаем точки из БД
            List<Point> points = repo.getAllPoints();
            System.out.println("Points from DB: " + points.size());

            // Выводим информацию о точках
            for (Point point : points) {
                System.out.println("Point: ID=" + point.getId() +
                        ", X=" + point.getX() +
                        ", Y=" + point.getY());
            }

            // Закрываем соединение
            repo.close();
            System.out.println("Test completed successfully!");

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}