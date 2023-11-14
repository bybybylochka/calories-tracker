package by.bsuir.caloriestracker.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private AuthorizationData authorizationData;
    @OneToOne
    private PersonalData personalData;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ConsumedProduct> consumptionHistory;
    @ManyToMany(mappedBy = "likedUserList", fetch = FetchType.EAGER)
    private List<Recipe> favouriteRecipes;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ConsumedWater> waterConsumptionHistory;

    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return authorizationData.getPassword();
    }

    @Override
    public String getUsername() {
        return authorizationData.getLogin();
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
        return enabled;
    }
}
