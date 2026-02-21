package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionResume implements Action {

        @Override
        public void happen(String name) throws IncorrectActionParticipantException {
            if(name.equals("Бомбардировка")){

                System.out.println(name + " возобновилась");
            }else {
                throw new IncorrectActionParticipantException();
            }
        }
}
