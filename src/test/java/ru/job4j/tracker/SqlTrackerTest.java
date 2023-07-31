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
     * � ������ initConnection() ����������� ������������� �����������.
     * ������ ����� ��������� ���������� @BeforeClass, �.�. ����� ����������� ���� ��� �� ������ ������;
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
    � ������ closeConnection() ����������� �������� �����������.
     ������ ����� ��������� ���������� @AfterClass, �.�. ����� ����������� ���� ��� ����� ������;
     */
    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     *  - � ������ wipeTable() �� ������ ������� items ����� ��������� ���������.
     *  �������� ��� ����������, ����� ��������� ������������, ����� ���������,
     *  ��������� ���� ������, ����� ����� �������. ������ ����� ��������� ���������� @After,
     *  �.�. ����� ����������� ����� ������� �����;
     */
    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    /**
     *  ����� whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() ������������ ����� ��� ����.
     *  �������� ��������, ��� ����� ������� �� ������� item �� ���������������� �� �����.
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