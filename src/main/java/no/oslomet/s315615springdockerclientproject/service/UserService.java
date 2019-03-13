package no.oslomet.s315615springdockerclientproject.service;

import no.oslomet.s315615springdockerclientproject.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    String BASE_URL = "http://localhost:9090/users";
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> getAllUsers() {
       return  Arrays.stream(restTemplate.getForObject(BASE_URL, User[].class)).collect(Collectors.toList());
    }

    public User getUserById(long id) {
        User user = restTemplate.getForObject(BASE_URL+"/"+id, User.class);
        return user;
    }

    public User saveUser(User newUser) { return restTemplate.postForObject(BASE_URL, newUser, User.class); }

    public void updateUser(long id, User updatedUser) { restTemplate.put(BASE_URL+"/"+id, updatedUser); }

    public void deleteUserById(long id) {
        restTemplate.delete(BASE_URL+"/"+id);
    }
}
