package com.example.api.Repository;import com.example.api.Entity.Comment;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;@Repositorypublic interface CommentRepository extends JpaRepository<Comment,Long> {}