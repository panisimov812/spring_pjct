package com.panem.panem_backend.controller;

import com.panem.panem_backend.DTO.Response;
import com.panem.panem_backend.DTO.userAttributeDTO.CarouselCompensationUpdateDTO;
import com.panem.panem_backend.exeption.PanemException;
import com.panem.panem_backend.repository.UserAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Репозитоирй с методами выпполнения sql запросов к бд
 * @Autowired используется для автоматического связывания компонентов бина между собой
 * @author Anisimov Petr (tg: @petr_anisimov)
 * @Since 24.03.2024
 */
@RestController
@RequestMapping("/api/user-counter")
public class UserAttributeController {

    private final UserAttributeRepository userAttributeRepository;

    @Autowired
    public UserAttributeController(UserAttributeRepository userAttributeRepository) {
        this.userAttributeRepository = userAttributeRepository;
    }

    /**
     * Данный метод обновляет количество банкнот в счетчике YV2021.Banknotes.Counter
     * по дефолту счетик == 0
     *
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     * @param carouselCompensationUpdateDTO - кол-во банкнот переданное с фронта
     */
    @CrossOrigin("*")
    @PostMapping("/compensation/{account}")
    public Response updateBanknoteValueByAccount(@PathVariable String account,
                                                 @RequestBody CarouselCompensationUpdateDTO carouselCompensationUpdateDTO)
            throws PanemException {
        userAttributeRepository.updateCompinsationValueByAccount(account, carouselCompensationUpdateDTO.getValue());

        return new Response("OK");
    }
}
