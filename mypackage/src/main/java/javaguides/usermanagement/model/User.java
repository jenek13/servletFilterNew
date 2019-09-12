package javaguides.usermanagement.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "newuser")//todo вот здесь имя таблицы а было неверное
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	@Column(name="name")
	private String name;

	@Column(name="age")
	private int age;

	//todo добавить классу User поле role типа String, которое будет принимать значения user или admin
	@Column(name="role")
	private String role;


    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;


	public User(String name, int age, String role) {
		this.name = name;
		this.age = age;
		this.role = role;
	}

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

    public User(int id, String login, String password) {
        this.id = id;
	    this.login = login;
        this.password = password;
    }

	public User(int id, String name, int age, String role) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.role = role;
	}

	public User(String login, String password, String name, int age, String role) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.age = age;
		this.role = role;
	}

	public User(int id, String name, int age, String role, String login, String password) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.role = role;
		this.login = login;
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getId() == user.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}
}

/*
сущность должна обладать
1)путстым конструктором @NoArgsConstructor это что будет пустой конструктор
2)гетерами и сетерами, это выоплняет аннтация @Data
3)должен иметь превичный ключ
4) класс не должен быть финальным
иначе не подружится с гибернейт
*/
