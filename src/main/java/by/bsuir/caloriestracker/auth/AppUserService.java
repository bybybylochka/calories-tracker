package by.bsuir.caloriestracker.auth;

import by.bsuir.caloriestracker.models.Admin;
import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.repository.AdminRepository;
import by.bsuir.caloriestracker.repository.EditorRepository;
import by.bsuir.caloriestracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final EditorRepository editorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        Optional<Editor> optionalEditor = editorRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else if(optionalAdmin.isPresent()){
            return optionalAdmin.get();
        }  else if(optionalEditor.isPresent()) {
            return optionalEditor.get();
        }
        else return null;
    }
}
