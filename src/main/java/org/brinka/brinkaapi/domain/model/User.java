package org.brinka.brinkaapi.domain.model;

import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private Address address;
    private String senha;
    private Boolean ehAdmin;
    private List<Review> avaliacoes;
}
