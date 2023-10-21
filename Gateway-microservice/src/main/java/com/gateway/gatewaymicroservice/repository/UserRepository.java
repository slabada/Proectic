package com.gateway.gatewaymicroservice.repository;

import com.gateway.gatewaymicroservice.models.UserModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserModel, Long> {
    Mono<UserModel> findByLogin(String login);
}
