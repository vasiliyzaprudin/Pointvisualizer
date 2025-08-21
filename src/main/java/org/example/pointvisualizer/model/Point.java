package org.example.pointvisualizer.model;

// Класс модели, представляющий точку с координатами и ID
public class Point {
    private int id;         // Уникальный идентификатор точки
    private double x;       // Координата X
    private double y;       // Координата Y

    // Конструктор
    public Point(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{id=" + id + ", x=" + x + ", y=" + y + '}';
    }
}