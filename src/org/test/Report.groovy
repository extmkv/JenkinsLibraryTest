package org.test

interface Report {

    def getUserByEmail(String email)

    def sendMessage(String title, String message, String color)
}
