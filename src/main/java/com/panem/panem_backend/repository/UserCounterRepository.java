package com.panem.panem_backend.repository;

import com.panem.panem_backend.models.UserCounter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface UserCounterRepository extends CrudRepository<UserCounter, Long> {


    UserCounter findByAccountAndName(String account, String name);
    UserCounter findByName(String name);



//    /**
//     * Используется для подстановки параметра YV2021.Banknotes.Counter
//     * в случае его отсутствия
//     * @param account - аккаунт пользователя
//     * @param name
//     */
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO user_counter VALUES (account, name)", nativeQuery = true)
//    //INSERT INTO Products VALUES (1, 'Galaxy S9', 'Samsung', 4, 63000)
//    void insertBanknoteAttributeByAccount(@Param("account") String account, @Param("name") String name);

//    @Modifying
//    @Query(value = "INSERT INTO journals (messageid, oldowner) select m.id, m.owner from message m where m.id = ?1",
//            nativeQuery = true)
//    void updateJournalOldowner(Long id);

    /*
    Метод обновления банкнот, по аккаунту
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserCounter uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = 'YV2021.Banknotes.Counter'")
    void updateBanknoteValueByAccount(@Param("account") String account, @Param("value") int value);

    /*
    Метод обновления уровня, по аккаунту
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserCounter uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = 'YV2021.Level.Counter'")
    void updateLevelValueByAccount(@Param("account") String account, @Param("value") int value);

    /*
    Метод обновления монет, по аккаунту
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserCounter uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = 'YV2021.Coins.Counter'")
    void updateCoinValueByAccount(@Param("account") String account, @Param("value") int value);


}
