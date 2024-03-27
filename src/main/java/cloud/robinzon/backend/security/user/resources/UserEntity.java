/*

Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package cloud.robinzon.backend.security.user.resources;

import cloud.robinzon.backend.data.client.resources.ClientEntity;
import cloud.robinzon.backend.common.EntityTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * the main entity
 *
 * @author Anton Kkuzmin
 * @since 2024.03.26
 */

@Entity
@Getter
@NoArgsConstructor
public class UserEntity
        extends EntityTemplate
        implements UserDetails {

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String username;

    @Size(min = 60, max = 60)
    @Column(nullable = false, length = 60)
    private String password;

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String fullName;

    @Setter
    @Column(nullable = false)
    private boolean admin;

    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    @JoinTable
    private Set<ClientEntity> clients = new HashSet<>();

    final public UserEntity update(String username,
                                   String password,
                                   String fullName,
                                   String title,
                                   String description) {

        if (Objects.equals(this.username, username)
                && Objects.equals(this.password, password)
                && Objects.equals(this.fullName, fullName)
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description))
            return null;

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        return this;
    }

    final public String toString() {
        //noinspection StringBufferReplaceableByString
        final StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][username=").append(username);
        sb.append("][password=").append(password);
        sb.append("][fullName=").append(fullName);
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    final public Map<String, Object> toMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("password", password);
        map.put("fullName", fullName);
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }

    // user details implements:

    @Override
    final public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(
                isAdmin() ? "ROLE_ADMIN" : "ROLE_USER"
        ));

        for (ClientEntity client : getClients()) {
            String role = String.format("ROLE_CLIENT_%d", client.getId());
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    final public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    final public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    final public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    final public boolean isEnabled() {
        return !this.deleted;
    }

}