package com.marcobehler.myfancypdfinvoices.service;

// tag::atComponent[]
import com.marcobehler.myfancypdfinvoices.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {
// end::atComponent[]

    public User findById(String id) {
        String randomName = UUID.randomUUID().toString();
        // always "finds" the user, every user has a random name
        return new User(id, randomName);
    }
}