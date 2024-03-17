package com.panem.panem_backend.controller;

import com.panem.panem_backend.DTO.BanknoteUpdateDTO;
import com.panem.panem_backend.DTO.LevelUpdateDTO;
import com.panem.panem_backend.DTO.Response;
import com.panem.panem_backend.exeption.PanemException;
import com.panem.panem_backend.models.UserCounter;
import com.panem.panem_backend.repository.UserCounterRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController //говорит Spring, что этот класс является контроллером, который будет обрабатывать HTTP запросы.
@RequestMapping("/api/user-counter") // Базовый URL для всех запросов к контроллеру
public class UserCounterController {
    final int MAX_LEVEL = 15;
    final int MAX_VALUE_BANKNOTES = 9999;
    final int INCORRECT_LEVEL = 0;
    final int INCORRECT_BANKNOTES = 0;
    final String BANKNOTES_ATTRIBUTE = "YV2021.Banknotes.Counter";

    private final UserCounterRepository userCounterRepository;


    @Autowired
    public UserCounterController(UserCounterRepository userCounterRepository) {
        this.userCounterRepository = userCounterRepository;
    }

    // Определяем REST эндпоинт для обновления банкнот по аккаунту
    @PutMapping("/banknotes/{account}")
    public Response updateBanknoteValueByAccount(@PathVariable String account,
                                                 @RequestBody BanknoteUpdateDTO banknoteUpdateDTO)
            throws PanemException {

        if (userCounterRepository.findByAccountAndName(account, BANKNOTES_ATTRIBUTE) == userCounterRepository.findByName(null)) {
            addCounter(account, BANKNOTES_ATTRIBUTE, 0);
        }
        if (banknoteUpdateDTO.getValue() > MAX_VALUE_BANKNOTES || banknoteUpdateDTO.getValue() < INCORRECT_BANKNOTES)
            throw new PanemException("Incorrect banknotes value: " + banknoteUpdateDTO.getValue() + " for account: " + account);
        userCounterRepository.updateBanknoteValueByAccount(account, banknoteUpdateDTO.getValue());
        return new Response("OK");
    }

    @PutMapping("/level/{account}")
    public Response updateLevelValueByAccount(@PathVariable String account, @RequestBody LevelUpdateDTO levelUpdateDTO)
            throws PanemException {
        if (levelUpdateDTO.getValue() > MAX_LEVEL || levelUpdateDTO.getValue() <= INCORRECT_LEVEL)
            throw new PanemException("Incorrect level value for account : " + account);
        userCounterRepository.updateLevelValueByAccount(account, levelUpdateDTO.getValue());
        return new Response("OK");
    }

    @ExceptionHandler(PanemException.class)
    public Response handleException(PanemException e) {
        return new Response(e.getMessage());
    }

    public void addCounter(String account, String name, int value) {
        UserCounter userCounter = new UserCounter();
        userCounter.setAccount(account);
        userCounter.setName(name);
        userCounter.setValue(value);
        userCounterRepository.save(userCounter);
    }
}
/**
 * В этом примере:
 *
 * @RestController говорит Spring, что этот класс является контроллером, который будет обрабатывать HTTP запросы.
 * @RequestMapping("/api/user-counter") определяет базовый URL для всех эндпоинтов этого контроллера.
 * @PutMapping("/banknotes/{account}") указывает, что метод updateBanknoteValueByAccount будет обрабатывать PUT запросы по пути /api/user-counter/banknotes/{account}, где {account} - это переменная часть URL, содержащая аккаунт.
 * @PathVariable String account говорит Spring, что значение {account} из URL должно быть привязано к аргументу account метода.
 * @RequestBody int value указывает Spring на то, что значение из тела запроса должно быть привязано к аргументу value метода.
 * userCounterRepository.updateBanknoteValueByAccount(account, value) вызывает метод из вашего репозитория для обновления значения банкнот по аккаунту.
 * Теперь, когда вы отправите PUT запрос на /api/user-counter/banknotes/{account}, передавая в теле запроса новое значение банкнот, оно будет обновлено в базе данных.
 */