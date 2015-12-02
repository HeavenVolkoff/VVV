/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.pojos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "user", schema = "vvv")
public class User implements Serializable {

	private static final long serialVersionUID = 537533178;

	@JsonIgnore
	private Integer   id;

	@NotNull
	@Size(max = 255)
	private String    firstName;

	@NotNull
	@Size(max = 255)
	private String    lastName;

	@Email
	private String    email;

	@NotNull
	@Size(min = 6)
	private String password;

	@JsonIgnore
	private byte[]    passwordHash;

	@JsonIgnore
	private Timestamp createdAt;

	@JsonIgnore
	private Timestamp updatedAt;

	public User() {}

	public User(User value) {
		this.id = value.id;
		this.firstName = value.firstName;
		this.lastName = value.lastName;
		this.email = value.email;
		this.password = value.password;
		this.passwordHash = BCrypt.hashpw(value.password, BCrypt.gensalt(12)).getBytes();
		this.createdAt = value.createdAt;
		this.updatedAt = value.updatedAt;
	}

	public User(
		Integer   id,
		String    firstName,
		String    lastName,
		String    email,
		byte[]    passwordHash,
		Timestamp createdAt,
		Timestamp updatedAt
	) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12)).getBytes();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false, length = 255)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 255)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", unique = true, nullable = false, length = 255)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password_hash", nullable = false, length = 60)
	public byte[] getPasswordHash() {
		return this.passwordHash;
	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12)).getBytes();
	}

	@Column(name = "created_at", nullable = false)
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
		this.updatedAt = createdAt;
	}

	@Column(name = "updated_at", nullable = false)
	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("User (");

		sb.append(id);
		sb.append(", ").append(firstName);
		sb.append(", ").append(lastName);
		sb.append(", ").append(email);
		sb.append(", ").append(createdAt);
		sb.append(", ").append(updatedAt);

		sb.append(")");
		return sb.toString();
	}
}
