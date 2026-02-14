# Agona-Homeworks

Учебный репозиторий с домашними заданиями: каждое ДЗ вынесено в отдельную ветку.

## Навигация по веткам

В репозитории есть ветки: `Agona01`–`Agona10`, `Agona11`, `Agona11Part2`, а также `develop` и `master`.  
`master` — ветка с общей документацией (этот файл), `develop` — вспомогательная ветка разработки.

## Что я делал в каждой ветке

Ниже — описание по фактическому содержимому веток с указанием ключевых технологий.

| Ветка | Что я делал | Ключевые файлы/папки |
|---|---|---|
| `Agona01` | **Git / GitHub Flow**: Настроил структуру репозитория `master/develop`, базовый README и `.gitignore`, практиковал работу с ветками и merge request’ами, плюс настройки защиты `master`. | `Readme.md` (описание задания и структуры). |
| `Agona02` | **Java Reflection API**: Реализовал модели (`User`, `AbstractUser1`, `AbstractUser2`) и `ReflectionService` для анализа классов в runtime. | `src/main/java/Agona02/ReflectionService.java`, `User.java`, `AbstractUser1.java`, `AbstractUser2.java`. |
| `Agona03` | **JDBC / SQL**: Реализовал репозиторий под таблицу `entity`: CRUD через `PreparedStatement`, `ResultSet`, работа с транзакциями (`connection.commit()`). | `src/main/java/Agona03/EntityRepository.java`, `Entity.java`, `Main.java`. |
| `Agona04` | **SQL (DDL/DML)**: Практика написания raw SQL скриптов: создание БД, `INSERT`, `JOIN`, `GROUP BY`, временные таблицы. | `src/main/java/Agona04/part1-CreatingDB.sql`, `part2-FillTables.sql`, `part2-join.sql`, `part3-createTempTable.sql`. |
| `Agona05` | **Build Systems (Maven/Gradle)**: Подготовка материалов по плагинам сборки, инструкция по подключению плагинов к проекту. | `src/main/java/Agona05/gradle_plagin/`, `src/main/java/Agona05/maven-plagin/`. |
| `Agona06` | **Hibernate (ORM)**: Настройка `SessionFactory`, маппинг сущностей `Student` и `Course` (Many-to-Many), реализация репозитория на Hibernate API. | `src/main/java/Agona06/Student.java`, `Course.java`, `HibernateUtil.java`, `StudentRepository.java`. |
| `Agona07` | **Spring Core (Context)**: Конфигурация через Java-классы (`@Configuration`, `@Bean`), `BeanPostProcessor`, реализация `WebAppInitializer`. | `src/main/java/com/vinogradov/Agona07/...`, `AppConfig.java`, `CustomBeanPostProcessor.java`, `WebAppInitializer.java`. |
| `Agona08` | **Spring Data JPA**: Использование `JpaRepository`, `@Entity`, JPQL-запросы, проекции и JOIN-ы через интерфейсы репозиториев. | `DemoApplication.java`, `JpaConfig.java`, папки `model/`, `repository/`. |
| `Agona09` | **Spring Data JPA (Advanced)**: Сложная доменная модель (`User`, `Profile`, `Category`, `Product`, `Order`), транзакционность, связи One-to-One, One-to-Many. | `Agona09/src/main/java/org/example/agona09/App.java`, папки `config/`, `model/`, `repository/`. |
| `Agona10` | **jOOQ**: Type-safe SQL запросы в Java. Генерация метамодели БД, использование `DSLContext` для выборок, пагинации и агрегации. | `Agona10/src/main/java/org/example/agona10/AppJooq.java`, папка `config/`. |
| `Agona11` | **Spring Boot Starter / Spring AOP**: Реализация собственного стартера `logging-starter`, автоконфигурация (`@ConditionalOnProperty`), аспекты (`@Aspect`, `@Around`) для логирования. | `logging-starter/.../LoggingStarterAutoConfiguration.java`, `aop/*.java`. |
| `Agona11Part2` | **Spring AOP / SLF4J (MDC)**: Расширенное логирование с контекстом (MDC), замер времени выполнения методов, уточнение Pointcut-выражений. | `logging-starter/.../aop/ControllerLoggingAspect.java`, родительский `pom.xml`. |

## Технологический стек

- **Язык**: Java 23
- **Сборка**: Maven, Gradle
- **Базы данных**: PostgreSQL, SQL, JDBC
- **ORM / Data Access**: Hibernate, Spring Data JPA, jOOQ
- **Frameworks**: Spring Framework, Spring Boot
- **Aspects**: Spring AOP, AspectJ
