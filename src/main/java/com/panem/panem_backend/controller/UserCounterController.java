package com.panem.panem_backend.controller;

import com.panem.panem_backend.DTO.BanknoteUpdateDTO;
import com.panem.panem_backend.DTO.CoinsUpdateDTO;
import com.panem.panem_backend.DTO.LevelUpdateDTO;
import com.panem.panem_backend.DTO.Response;
import com.panem.panem_backend.exeption.PanemException;
import com.panem.panem_backend.models.UserCounter;
import com.panem.panem_backend.repository.UserCounterRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Контроллеры для обработки
 * В контроллере используетс аннотация CrossOrigin("*")
 * ("*") В этом случае запросы будут разрешены со всех источников.
 *
 * @author Anisimov Petr (tg: @petr_anisimov)
 * @Since 18.03.2024
 */

@RestController //говорит Spring, что этот класс является контроллером, который будет обрабатывать HTTP запросы.
@RequestMapping("/api/user-counter") // Базовый URL для всех запросов к контроллеру
public class UserCounterController {
    static final int MAX_LEVEL = 15;
    static final int MAX_VALUE_BANKNOTES = 100000;
    static final int MAX_VALUE_COINS = 100000;
    static final int INCORRECT_LEVEL = 0;
    static final int INCORRECT_BANKNOTES = 0;
    static final int INCORRECT_COINS = -1;
    static final String YV_2021_BANKNOTES_COUNTER = "YV2021.Banknotes.Counter";

    private final UserCounterRepository userCounterRepository;

    @Autowired
    public UserCounterController(UserCounterRepository userCounterRepository) {
        this.userCounterRepository = userCounterRepository;
    }

    /**
     * Данный метод обновляет количество банкнот в счетчике YV2021.Banknotes.Counter
     * В случае если счетчик отсутствует, то метод добавляет его вызовом метода ensureBanknotesCounterExists
     *
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     * @param banknoteUpdateDTO - кол-во банкнот переданное с фронта
     * @throws PanemException - исключение
     */
    @CrossOrigin("*")
    @PostMapping("/banknotes/{account}")
    public Response updateBanknoteValueByAccount(@PathVariable String account,
                                                 @RequestBody BanknoteUpdateDTO banknoteUpdateDTO) throws PanemException {
        ensureBanknotesCounterExists(account);
        validateBanknoteValue(banknoteUpdateDTO.getValue());

        userCounterRepository.updateBanknoteValueByAccount(banknoteUpdateDTO.getValue(), account, YV_2021_BANKNOTES_COUNTER);

        return new Response("OK");
    }

    /**
     * Метод для обновления уровня
     *
     * @param account        - номер кошелька пользовтаеля передаваемый с фронта
     * @param levelUpdateDTO - получение уровня
     */
    @CrossOrigin("*")
    @PostMapping("/level/{account}")
    public Response updateLevelValueByAccount(@PathVariable String account, @RequestBody LevelUpdateDTO levelUpdateDTO)
            throws PanemException {
        validationLevelValue(levelUpdateDTO.getValue(), account);

        userCounterRepository.updateLevelValueByAccount(account, levelUpdateDTO.getValue());

        return new Response("OK");
    }

    /**
     * Метод для обнавления количества монет
     *
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     * @param coinsUpdateDTO - получение монет
     */
    @CrossOrigin("*")
    @PostMapping("/coins/{account}")
    public Response updateCoinsValueByAccount(@PathVariable String account,
                                              @RequestBody CoinsUpdateDTO coinsUpdateDTO) throws PanemException {
        validationCoinValue(coinsUpdateDTO.getValue(), account);
        userCounterRepository.updateCoinValueByAccount(account, coinsUpdateDTO.getValue());

        return new Response("OK");
    }

    @ExceptionHandler(PanemException.class)
    public Response handleException(PanemException e) {
        return new Response(e.getMessage());
    }

    /**
     * Метод для добавления отсутствующей записи в бд
     *
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     * @param name    - передаваемый параметр для его записи в таблицу user_counter
     * @param value   - передаваемое значение для его записи в таблицу user_counter
     */
    public void addBanknotesAttribute(String account, String name, int value) {
        UserCounter userCounter = new UserCounter();
        userCounter.setAccount(account);
        userCounter.setName(name);
        userCounter.setValue(value);
        userCounterRepository.save(userCounter);
    }

    /**
     * Проверка на присутсвие у пользователя аттрибута 'YV2021.Banknotes.Counter'
     *
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     */
    private void ensureBanknotesCounterExists(String account) {
        if (userCounterRepository.findByAccountAndName(account, YV_2021_BANKNOTES_COUNTER) == userCounterRepository.findByName(null)) {
            addBanknotesAttribute(account, YV_2021_BANKNOTES_COUNTER, 0);
        }
    }

    /**
     * @param value - кол-во банкнот переданное с фронта
     * @throws PanemException - исключение при невалидных данных
     *                        кол-во банкнот не может быть ниже нуля
     *                        кол-во банкнот не может привыщать значение 100000
     */
    private void validateBanknoteValue(int value) throws PanemException {
        if (value > MAX_VALUE_BANKNOTES || value < INCORRECT_BANKNOTES) {
            throw new PanemException("Incorrect banknotes value: " + value);
        }
    }

    /**
     * Метод валидации уровня игрока (на данный момент в игре 15 уровень максимальный)
     *
     * @param levelValue - уровень устанавливаемый пользователем
     * @param account    - номер кошелька пользовтаеля передаваемый с фронта
     */
    private void validationLevelValue(int levelValue, String account) {
        if (levelValue > MAX_LEVEL || levelValue <= INCORRECT_LEVEL)
            throw new PanemException("Incorrect level value for account: " + account);
    }

    /**
     * Метод валидации манет
     *
     * @param value - кол-во монет переданное с фронта
     * @param account - номер кошелька пользовтаеля передаваемый с фронта
     */
    private void validationCoinValue(int value, String account) throws PanemException {
        if (value <=INCORRECT_COINS || value > MAX_VALUE_COINS )
            throw new PanemException("Incorrect coins value for account: " + account);
    }

    //TODO в дальнейшем следует реализовать единый метод валидации для сравенения условий

}
