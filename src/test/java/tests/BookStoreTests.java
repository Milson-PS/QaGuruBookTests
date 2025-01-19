package tests;

import io.restassured.response.Response;
import models.books.AddBookRequestBodyModel;
import models.books.Isbn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.ApiSteps.*;

public class BookStoreTests extends TestBase {
    @Tag("Simple")
    @Tag("Smoke")
    @DisplayName("Удаление книги из списка")
    @Test
    void deleteBookFromList() {
        Response responseLogin = login(testData.bookStoreLogin, testData.bookStorePassword);
        String token = responseLogin.path("token");
        String userId = responseLogin.path("userId");
        String expires = responseLogin.path("expires");
        clearListOfUserBooks(token, userId);
        Isbn isbn = new Isbn();
        isbn.setIsbn(testData.isbn);
        List<Isbn> listIsbns = List.of(isbn);
        AddBookRequestBodyModel bookData = new AddBookRequestBodyModel(userId, listIsbns);
        addBooks(token, bookData);
        profileBooksPage.openUserBooksPage(userId, expires, token);
        profileBooksPage.findBookByName(testData.bookName);
        profileBooksPage.deleteBookByName(testData.bookName);
        profileBooksPage.findNotBookByName(testData.bookName);
    }
}