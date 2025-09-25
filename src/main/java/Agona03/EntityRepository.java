package Agona03;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityRepository {
    private Connection connection;

    private static final String SQL_INSERT = "INSERT INTO entity (name, age, salary, active) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE entity SET name = ?, age = ?, salary = ?, active = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM entity WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT id, name, age, salary, active FROM entity";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, age, salary, active FROM entity WHERE id = ?";

   public EntityRepository(String username, String password, String url) throws SQLException {
       connection = DriverManager.getConnection(url, username, password);
   }

   List<Entity> getAllEntities() throws SQLException {
       List<Entity> listEntities =  new ArrayList<>();
       try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
               ResultSet resultSet = preparedStatement.executeQuery()) {
           while (resultSet.next()) {
               listEntities.add(getEntity(resultSet));
           }
       }
       return listEntities;
   }

   Optional<Entity> getEntityById(int id) throws SQLException {
       try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
           preparedStatement.setInt(1, id);
           try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Entity entity = getEntity(resultSet);
                    return Optional.of(entity);
                }
           }
       }
       return Optional.empty();
   }

   public boolean deleteEntityById(int id) throws SQLException {
       try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
           preparedStatement.setInt(1, id);
           return preparedStatement.executeUpdate() > 0;
       }
   }

   public void createEntity(Entity entity) throws SQLException {
       try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
           preparedStatement.setString(1, entity.getName());
           preparedStatement.setInt(2, entity.getAge());
           preparedStatement.setDouble(3, entity.getSalary());
           preparedStatement.setBoolean(4, entity.isActive());
           preparedStatement.execute();
       }
   }

   public boolean updateEntity(Entity entity) throws SQLException {
       try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
           preparedStatement.setString(1, entity.getName());
           preparedStatement.setInt(2, entity.getAge());
           preparedStatement.setDouble(3, entity.getSalary());
           preparedStatement.setBoolean(4, entity.isActive());
           preparedStatement.setInt(5, entity.getId());
           return preparedStatement.execute();
       }
   }

   public void crudInOneTransaktion(Entity entity, int id) throws SQLException {
       connection.setAutoCommit(false);
       createEntity(entity);
       updateEntity(entity);
       getEntityById(id);
       deleteEntityById(id);
       connection.commit();
       connection.setAutoCommit(true);
   }

    private Entity getEntity(ResultSet resultSet) throws SQLException {
        Entity entity = new Entity();
        entity.setId(resultSet.getInt("id"));
        entity.setName(resultSet.getString("name"));
        entity.setAge(resultSet.getInt("age"));
        entity.setSalary(resultSet.getInt("salary"));
        entity.setActive(resultSet.getBoolean("active"));
        return entity;
    }
}
