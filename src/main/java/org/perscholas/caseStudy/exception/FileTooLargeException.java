package org.perscholas.caseStudy.exception;

public class FileTooLargeException extends Exception {

    private static final long serialVersionUID = 1896042030312978034L;

    public FileTooLargeException(String s) {
        super(s);
    }
}