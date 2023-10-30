package com.penguin.esms.security;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.constant.SystemConstant;
import com.penguin.esms.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomStaffDetailService implements UserDetailsService {
    @Autowired
    private StaffRepository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StaffEntity user = userRespository.findOneByEmailAndStatus(username, SystemConstant.ACTIVE_STATUS);
        if (user == null)
            throw new UsernameNotFoundException("User NOT found");
        return new CustomStaffDetail(user.getId(), user.getEmail(), user.getPassword());
    }
}
