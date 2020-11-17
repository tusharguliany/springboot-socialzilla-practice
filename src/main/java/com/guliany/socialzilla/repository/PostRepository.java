package com.guliany.socialzilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guliany.socialzilla.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
