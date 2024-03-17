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

    UserCounter findByAccount(String account);
    UserCounter findByAccountAndName(String account, String name);
    UserCounter findByName(String name);

    /**
     * Запрос пополнения баланса банкнот

     */
    @Modifying
    @Transactional
    @Query("UPDATE UserCounter uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = :attributeName")
    void updateBanknoteValueByAccount(@Param("value") int value,
                                      @Param("account") String account,
                                      @Param("attributeName") String attributeName);

    /**
     * Запрос на обновление уровня игрока
     * @param account - аккаунт пользовтаеля передаваемый с фронта
     * @param value - уровень игрокапередаваемый с фронта
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserCounter uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = 'YV2021.Level.Counter'")
    void updateLevelValueByAccount(@Param("account") String account, @Param("value") int value);

    /**
     * Запрос для обновления количества монет
     * @param account - аккаунт пользовтаеля передаваемый с фронта
     * @param value - количеств монет передаваемое с фронта
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserCounter uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = 'YV2021.Coins.Counter'")
    void updateCoinValueByAccount(@Param("account") String account, @Param("value") int value);


}
