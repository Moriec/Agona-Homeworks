package Agona02;

public class User {
    private Integer password;
    private String age;

    public String name;
    public String email;

    public User() {
    }

    public User(String name, String email, Integer password, String age) {
        this.password = password;
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    private void setPassword(Integer password) {
        this.password = password;
    }
    private Integer getPassword() {
        return password;
    }
}
