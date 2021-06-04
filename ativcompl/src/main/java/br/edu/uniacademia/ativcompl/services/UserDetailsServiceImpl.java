package br.edu.uniacademia.ativcompl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.User;
import br.edu.uniacademia.ativcompl.repositories.UserRepository;
import br.edu.uniacademia.ativcompl.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String registration) throws UsernameNotFoundException {
		User user = repo.findByRegistration(registration);
		if (user == null) {
			throw new UsernameNotFoundException(registration);
		}
		return new UserSS(user.getId(), user.getRegistration(), user.getPassword(), user.getProfiles());
	}

}
