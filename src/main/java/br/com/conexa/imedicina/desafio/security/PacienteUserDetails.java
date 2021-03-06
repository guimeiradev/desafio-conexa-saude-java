package br.com.conexa.imedicina.desafio.security;


import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PacienteUserDetails implements UserDetailsService {

    @Autowired
    private PacienteRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Paciente user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .accountExpired(false)
                .authorities((GrantedAuthority) () -> "Paciente")
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}