package Agona02;

public class User {
    private String password;
    private Integer age;

    public String name;
    public String email;

    public User() {
    }

    public User(String name, String email, String password, Integer age) {
        this.password = password;
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    private void setPassword(String password) {
        this.password = password;
    }
    private String getPassword() {
        return password;
    }
}
