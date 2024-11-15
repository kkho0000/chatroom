package space.kkho.chatroom.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames={"username"})
})
public class User {
    
    @Id
    @Column(nullable = false, name="username")
    private String username;
    @Column(nullable = false, name="password")
    private String password;
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
