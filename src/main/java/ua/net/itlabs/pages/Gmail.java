package ua.net.itlabs.pages;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Gmail {
    public static void vizit() {
        open("http://gmail.com");
    }

    public static void logIn(String email, String pass) {
        $("#Email").setValue(email);
        $("#next").click();
        $("#Passwd").setValue(pass);
        $("#signIn").click();
    }
}
