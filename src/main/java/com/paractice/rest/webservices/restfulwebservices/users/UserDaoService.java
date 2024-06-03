package com.paractice.rest.webservices.restfulwebservices.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;


    static {
        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount, "jim", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "Shivam", LocalDate.now().minusYears(15)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {

     /*   for (User user : users) {

            if (user.getId() == id) {
                return user;
            }
        }
        return null;*/

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {

        user.setId(++userCount);
        users.add(user);
        return user;

    }

    public void deleteById(int id) {

     /*   Iterator<User> itr = users.iterator();
        while (itr.hasNext()){
            User user = itr.next();
            if (user.getId() == id){
                itr.remove();
            }
        }*/

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
