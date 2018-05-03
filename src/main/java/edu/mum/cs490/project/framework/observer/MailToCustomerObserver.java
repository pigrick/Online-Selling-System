package edu.mum.cs490.project.framework.observer;

import java.util.Observable;
import java.util.Observer;

public class MailToCustomerObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Send an email to customer");
    }
}
