package com.cauzy.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cauzy.workshopmongo.domain.Post;
import com.cauzy.workshopmongo.domain.User;
import com.cauzy.workshopmongo.dto.AuthorDTO;
import com.cauzy.workshopmongo.repository.PostRepository;
import com.cauzy.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		repo.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		repo.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("12/03/2018") , "Partiu viagem", "VOu viajar para sao paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018") , "bom dia", "acordei feliz hoje", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		repo.save(maria);
	}

}
