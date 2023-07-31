package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.tracker.model.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


import static org.junit.Assert.assertEquals;

public class SqlTrackerTest {

    private static Connection connection;

    /**
     * в методе initConnection() выполняется инициализация подключения.
     * Данный метод обозначен аннотацией @BeforeClass, т.е. метод выполняется один раз до начала тестов;
     */
    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
    в методе closeConnection() выполняется закрытие подключения.
     Данный метод обозначен аннотацией @AfterClass, т.е. метод выполняется один раз после тестов;
     */
    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     *  - в методе wipeTable() мы чистим таблицу items после внесенных изменений.
     *  Делается это специально, чтобы облегчить тестирование, иначе изменения,
     *  внесенные один тестом, будут видны другому. Данный метод обозначен аннотацией @After,
     *  т.е. метод выполняется после каждого теста;
     */
    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    /**
     *  метод whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() представляет собой уже тест.
     *  Обратите внимание, что после вставки мы находим item по сгенерированному БД ключу.
     */
    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertEquals(tracker.findById(item.getId()), item);
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSam() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertEquals(tracker.findById(item.getId()), item);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}