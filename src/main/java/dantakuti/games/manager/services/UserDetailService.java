package dantakuti.games.manager.services;

import dantakuti.games.manager.dao.PlayerDao;
import dantakuti.games.manager.entity.Player;
import dantakuti.games.manager.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author adarshbhattarai on 2020-05-23
 * @project LeagueManager
 */
@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    PlayerDao playerDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player;
        if(playerDao.existsByEmail(username)){
             player = playerDao.findByEmail(username);
        }else{
            player = playerDao.findByPlayerName(username);
        }
        List<GrantedAuthority> authorities = buildAuthority(player.getRoles());
        return buildPlayerForAuthentication(player, authorities);
    }

    private List<GrantedAuthority> buildAuthority(Collection<Roles> roles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (Roles userRole : roles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getName().name()));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

        return result;
    }

    private UserDetails buildPlayerForAuthentication(Player player, List<GrantedAuthority> authorities) {
        return new User(player.getPsId(), player.getPassword(),
                player.isRegistered(), true, true, true, authorities);
    }


    public boolean userPresent(String username){
        return playerDao.existsByPsId(username) || playerDao.existsByEmail(username);
    }

    public void save(Player player){
         playerDao.save(player);
    }

}
