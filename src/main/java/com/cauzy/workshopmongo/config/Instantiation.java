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
import com.cauzy.workshopmongo.dto.CommentDTO;
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
		
		Post post1 = new Post(null, sdf.parse("21/03/2018") , "Partiu viagem", "VOu viajar para sao paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018") , "bom dia", "acordei feliz hoje", new AuthorDTO(maria));
		
		post1.getComment().add(new CommentDTO("Boa viagem mano", sdf.parse("21/03/2018"), new AuthorDTO(alex)));
		post1.getComment().add(new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob)));
		post2.getComment().add(new CommentDTO("Tenha um otimo dia !", sdf.parse("23/03/2018"), new AuthorDTO(alex)));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		repo.save(maria);
	}

}
