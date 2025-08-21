package org.example.pointvisualizer.repository;

import org.example.pointvisualizer.model.Point;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Класс для работы с базой данных PostgreSQL
public class PointRepository {
    private Connection connection;

    // Конструктор, устанавливающий соединение с БД
    public PointRepository() {
        try {
            // Параметры подключения к PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/points_db";
            String user = "postgres";
            String password = "Pfghelby";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL database!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    // Метод для получения всех точек из БД
    public List<Point> getAllPoints() {
        List<Point> points = new ArrayList<>();
        String sql = "SELECT id, x, y FROM points ORDER BY id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                points.add(new Point(id, x, y));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching points: " + e.getMessage());
        }
        return points;
    }

    // Метод для добавления новой точки в БД
    public void addPoint(Point point) {
        String sql = "INSERT INTO points (x, y) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, point.getX());
            pstmt.setDouble(2, point.getY());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding point: " + e.getMessage());
        }
    }

    // Метод для закрытия соединения с БД
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
