package db;

public class DBConnection {

    public static DBConnection instance;

    private DBConnection(){

    }
    public DBConnection getInstance(){
        return instance;
    }

}
