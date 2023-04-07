package dev.brammie15;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.brammie15.objects.Class;
import dev.brammie15.objects.Student;

import java.sql.SQLException;

public class Data {
    String databaseURL = "jdbc:mysql://localhost:3306/keepscore";
    private JdbcConnectionSource connectionSource = null;
    Dao<Class, Integer> classDao = null;

    Dao<Student, Integer> studentDao = null;

    public Data(){
        init();
    }
    public void init() {
        try {
            connectionSource = new JdbcConnectionSource(databaseURL);
            connectionSource.setUsername("root");
            connectionSource.setPassword("");

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            throw new RuntimeException(e);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, Class.class);
            classDao = DaoManager.createDao(connectionSource, Class.class);

            TableUtils.createTableIfNotExists(connectionSource, Student.class);
            studentDao = DaoManager.createDao(connectionSource, Student.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Class, Integer> getClassDao() {
        return classDao;
    }
    public Dao<Student, Integer> getStudentDao() {
        return studentDao;
    }

    public JdbcConnectionSource getConnection() {
        return connectionSource;
    }
}
