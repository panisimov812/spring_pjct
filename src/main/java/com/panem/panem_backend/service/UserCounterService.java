package com.panem.panem_backend.service;

import com.panem.panem_backend.models.UserCounter;
import com.panem.panem_backend.repository.UserCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //чтобы Spring мог обнаружить его как сервис.
public class UserCounterService {

  //  private final UserCounterRepository userCounterRepository;

//    @Autowired
//    public UserCounterService(UserCounterRepository userCounterRepository) {
//        this.userCounterRepository = userCounterRepository;
//    }

//    public List<UserCounter> findByAccount(String account) {
//        return userCounterRepository.findByAccount(account);
//    }


}
/*
В этом классе UserCounterService:

Мы аннотировали класс @Service, чтобы Spring мог обнаружить его как сервис.
Мы внедрили зависимость UserCounterRepository через конструктор.
Мы определили метод findByAccount, который делегирует запрос к репозиторию UserCounterRepository для поиска записей по полю account.
Теперь ваш сервис готов к использованию в вашем контроллере или других компонентах вашего приложения.
 */
