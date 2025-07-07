package dasturlash.uz.config;

import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileService profileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileService.findByUsernameAndVisibleIsTrue(username);
        if (optional.isEmpty()) throw new UsernameNotFoundException("User not found");

        ProfileEntity profile = optional.get();
        ProfileRole role = profile.getRole();
        return new CustomUserDetails(
                profile.getId(),
                profile.getEmail(),
                profile,
                profile.getPassword(),
                profile.getStatus(),
                role);
    }
}
