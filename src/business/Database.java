package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.SortedSet;
import java.util.TreeSet;


public class Database {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private PreparedStatement preparedStatement;

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }
    
   
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }
    
    
    
    
    public void connect_database(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/stairsdatabase?zeroDateTimeBehavior=convertToNull","root",DontDeleteDB.Pass);
            //connection= DriverManager.getConnection("jdbc:mysql://host.evatixcloud.com:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            //connection= DriverManager.getConnection("jdbc:mysql:https://bsldu.org.bd.com:2083/3rdparty/phpMyAdmin/index.php:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            statement = connection.createStatement();
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("database connected_imran");
    }
    
    public void close_database(){
        
            try
            {
                if(resultSet!=null){
                    resultSet.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                
                
                System.out.println("database closed_imran");
            } 
            catch ( Exception exception )
            {
                exception.printStackTrace();
            } 
    }
    
    
    
    public SortedSet<String> getSuggestionList(String query,String column_name){
        SortedSet<String> sortedSet=new TreeSet<>();
        try{
           
            resultSet=statement.executeQuery(query);
            metaData=resultSet.getMetaData();
            
            
            while(resultSet.next()){
               
                String name=resultSet.getString(column_name);
                
               
                sortedSet.add(name);
                
            }
        }
        catch(Exception e){
            
        }
        
        return sortedSet;
    }
    
    
    
    public void insert_in_database(String query){
        try{
            resultSet=statement.executeQuery(query);
            metaData=resultSet.getMetaData();
           
        }
        catch(Exception e){
            
        }
        
        
    }
    
    
}
