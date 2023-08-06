package com.application.blog.entities;


//import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="User")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = false, length = 225)
    private String name;

    @Column(name = "user_email", nullable = false, length = 225)
    private String email;

    @Column(name  = "user_password", nullable = false, length = 225)
    private String password;

    @Column(name = "user_about", nullable = false, length = 225)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    public void User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return simpleGrantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
