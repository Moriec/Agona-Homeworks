package Agona03;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String username = "postgres";
        String password = "1234";
        String url = "jdbc:postgresql://localhost:5432/agona03";
        EntityRepository entityRepository = new EntityRepository(username, password, url);

        Entity entity = new Entity("aaa", 12, 23.23F, true);
        Entity entity2 = new Entity(1,"bbb", 12, 23.23F, true);

        //Тесты операций
        entityRepository.createEntity(entity);
        System.out.println(entityRepository.getAllEntities());
        System.out.println(entityRepository.getEntityById(1));
        entityRepository.updateEntity(entity2);
        System.out.println(entityRepository.getEntityById(entity2.getId()));

        //Выполнить CRUD в одной операции
        entityRepository.crudInOneTransaktion(entity, 3);
    }
}
