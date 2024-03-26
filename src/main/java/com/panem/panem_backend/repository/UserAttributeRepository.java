package com.panem.panem_backend.repository;

import com.panem.panem_backend.models.UserCounter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Репозитоирй с методами выпполнения sql запросов к бд
 *
 * @author Anisimov Petr (tg: @petr_anisimov)
 * @Since 24.03.2024
 */
@Repository
public interface UserAttributeRepository extends CrudRepository<UserCounter, Long> {

    /**
     * Запрос на обновление уровня игрока
     *
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     * @param value - уровень игрока передаваемый в запрос в качестве аргумента
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserAttribute uc SET uc.value = :value WHERE uc.account = :account " +
            "and uc.name = 'YV2021.CarouselCompensation'")
    void updateCompinsationValueByAccount(@Param("account") String account, @Param("value") int value);
}
