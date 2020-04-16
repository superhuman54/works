package kr.co.tabling.works.service;

import kr.co.tabling.works.persist.entity.Message;

public interface MessagesService {

    String get(Integer id);

    String remove(Integer id);

    Message update(Message message);
}
