package api.nonWorkingDays.security.service;

import api.nonWorkingDays.security.model.MyUserDetails;
import api.nonWorkingDays.security.repo.AppUserRepo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        val user = appUserRepo.findByUsername(s);

        user.orElseThrow(()->new UsernameNotFoundException("User not found"));

        return user.map(MyUserDetails::new).get();
    }
}
