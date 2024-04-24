package com.cauzy.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cauzy.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
