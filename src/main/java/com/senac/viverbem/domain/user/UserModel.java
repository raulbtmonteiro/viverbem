package com.senac.viverbem.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.address.AddressModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String dateofbirth;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    @JsonIgnore
    private String password;

    private String gender;
    private String cpf;
    private String phone;
    private String medications;
    private String emergencycontact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private AddressModel address;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ActivityModel> activities;

    public UserModel(UserRequestDTO data, AddressModel address){
        this.firstname = data.firstname();
        this.lastname = data.lastname();
        this.email = data.email();
        this.password = data.password();
        this.dateofbirth = data.dateofbirth();
        this.gender = data.gender();
        this.cpf = data.cpf();
        this.phone = data.phone();
        this.medications = data.medications();
        this.emergencycontact = data.emergencycontact();
        this.address = address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
