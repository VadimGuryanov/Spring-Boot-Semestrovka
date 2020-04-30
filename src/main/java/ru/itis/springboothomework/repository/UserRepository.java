package ru.itis.springboothomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.springboothomework.models.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

//    @Modifying
//    @Query("update User u set u.name = ?2, u.last_name = ?3, u.country = ?4, u.sity = ?5, u.gender = ?6," +
//            "u.age = ?7, u.study = ?8, u.work = ?9, u.mobile = ?10, u.social = ?11, u.photoUrl = ?12, u.description = ?13 " +
//            "WHERE u.id = :id")
//    void updateUser(
//            long id,
//            String name,
//            String lastName,
//            String country,
//            String sity,
//            String gender,
//            Integer age,
//            String study,
//            String work,
//            String mobile,
//            String social,
//            String photoUrl,
//            String description);

}
