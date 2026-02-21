package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionResume implements Action {

        @Override
        public String happen(String name) throws IncorrectActionParticipantException {
            String result = "";
            if(name.equals("Бомбардировка")){
                result = name + " возобновилась";
                return result;
            }else {
                throw new IncorrectActionParticipantException();
            }
        }
}
