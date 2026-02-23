package ru.itmo.exception;

public class ComputerBankNotDestroyedException extends Exception{
    public ComputerBankNotDestroyedException() {
        super("Компьютерный банк еще не уничтожен");
    }
}
