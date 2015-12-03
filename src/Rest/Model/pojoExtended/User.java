package Rest.Model.pojoExtended;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


public class User extends Rest.Model.tables.pojos.User {

    @NotNull(message = "NOT_NULL")
    @Size(min = 6, message = "TOO_SHORT")
    private String password;

    public User(){}

    public User(
            Integer   id,
            String    firstName,
            String    lastName,
            String    email,
            String    password,
            Timestamp createdAt,
            Timestamp updatedAt,
            Byte      type
    ) {
        super(id, firstName, lastName, email, BCrypt.hashpw(password, BCrypt.gensalt(12)).getBytes(), createdAt, updatedAt, type);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt(12)).getBytes());
    }

    @Override
    public void setCreatedAt(Timestamp createdAt) {
        super.setCreatedAt(createdAt);
        this.setUpdatedAt(createdAt);
    }
}
