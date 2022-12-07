package com.foxminded.ums.security;

import com.foxminded.ums.entities.Person;
import com.foxminded.ums.entities.Student;
import com.foxminded.ums.entities.Teacher;
import com.foxminded.ums.repository.StudentRepository;
import com.foxminded.ums.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryUserDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Teacher teacher = teacherRepository.findByLogin(username);
        if (teacher != null) {
            return personToUser(teacher);
        }

        Student student = studentRepository.findByLogin(username);
        if (student != null) {
            return personToUser(student);
        }

        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

    private User personToUser(Person person){
        User user = new User();
        user.setId(person.getId());
        user.setLogin(person.getLogin());
        user.setHashedPassword(person.getHashedPassword());
        user.setRole(person.getRole());

        return user;
    }

}
