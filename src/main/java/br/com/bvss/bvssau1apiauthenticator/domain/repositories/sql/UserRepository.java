package br.com.bvss.bvssau1apiauthenticator.domain.repositories.sql;

import br.com.bvss.bvssau1apiauthenticator.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

}