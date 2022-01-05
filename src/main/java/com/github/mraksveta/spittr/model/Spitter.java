package com.github.mraksveta.spittr.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: mraksveta
 * Date: 03.11.21
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Spitter {
    @Id
    @Column(name="spitter_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{firstName.blank}")
    @Size(min = 2, max = 30, message = "{firstName.size}")
    @Column
    private String firstName;    // not null, from 2 to 30 characters

    @NotBlank(message = "{lastName.blank}")
    @Size(min = 2, max = 30, message = "{lastName.size}")
    @Column
    private String lastName;  // not null, from 2 to 30 characters

    @NotBlank(message = "{username.blank}")
    @Size(min = 5, max = 16, message = "{username.size}")
    @Column
    private String username;  // not null, from 5 to 16 characters

    @NotBlank(message = "{password.blank}")
    @Size(min = 5, max = 128, message = "{password.size}")
    @Column
    private String password;   // not null, from 5 to 25 characters

    @NotBlank(message = "{email.blank}")
    @Email(message = "{email.valid}")
    @Column
    private String email;

    @Column
    private Boolean updateByEmail;

    @ManyToMany
    @JoinTable(
            name = "Spitter_role",
            joinColumns = @JoinColumn(name="spitter_id", referencedColumnName = "spitter_id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    public Spitter() {
    }

    public Spitter(Long id, String firstName, String lastName, String username, String password, String email, Boolean updateByEmail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.updateByEmail = updateByEmail;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUpdateByEmail() {
        return updateByEmail;
    }

    public void setUpdateByEmail(Boolean updateByEmail) {
        this.updateByEmail = updateByEmail;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role authority) {
        roles.add(authority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spitter)) return false;

        Spitter spitter = (Spitter) o;

        return Objects.equals(firstName, spitter.firstName) &&
                Objects.equals(id, spitter.id) &&
                Objects.equals(lastName, spitter.lastName) &&
                Objects.equals(password, spitter.password) &&
                Objects.equals(username, spitter.username);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
