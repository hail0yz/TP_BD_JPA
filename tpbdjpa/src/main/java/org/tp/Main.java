/*
LAGRANGE Victoria
TP DE BASE DE DONNEES A L UGA 
SUR UNE BASE DE CODE PAR PR. FABRICE JOUANOT */


package org.tp;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.tp.service.*;
import org.tp.data.*;

public class Main {
    public static void main(String[] args) {
        // Initialisation de l'EntityManagerFactory (utilisez H2 pour les tests et Oracle pour le déploiement)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

        // Création d'une catégorie
        CategoryService categoryService = new CategoryServiceImpl(emf);
        Category fictionCategory = new Category();
        fictionCategory.setName("Fiction");
        categoryService.createCategory(fictionCategory);

        // ToDo: Création de deux auteurs de nom Author1 et Author2
        AuthorService authorService = new AuthorServiceImpl(emf);
        Author author1 = new Author();
        author1.setName("Author 1");
        author1.setId(100001);
        authorService.createAuthor(author1);

        Author author2 = new Author();
        author2.setName("Author 2");
        author2.setId(100002);
        authorService.createAuthor(author2);

        BookService bookService = new BookServiceImpl(emf);
        // ToDo: Création d'un PaperBook de titre PaerperBook1 et de 200 pages
        // Ajouter les auteurs Author1 et Author2 comme auteurs de ce livre
        PaperBook paperbook1 = new PaperBook();
        paperbook1.setId(200001);
        paperbook1.setTitle("PaerperBook1");
        paperbook1.setPageCount(200);
        Set<Author> authors = new Set<Author>;
        authors.add(author1);
        authors.add(author2);
        paperbook1.setAuthors(authors);
        bookService.createbook(paperbook1);
        Ebook ebook1 = new Ebook();
        ebook1.setId(300001);
        ebook1.setTitle("Ebook1");
        ebook1.setFormat("PDF");
        paperbook1.setAuthors(authors);
        bookService.createbook(ebook1);

       
        Book foundBook = bookService.findBookById(paperbook1.getId());
        Category foundCategory = foundBook.getCategory(); // Assuming the Book has a getCategory() method
        System.out.println("Category of PaperBook 1: " + foundCategory.getName());

        // Recherche d'auteurs d'un livre
        Set<Author> foundAuthors = foundBook.getAuthors();
        System.out.println("Authors of PaperBook 1:");
        for (Author author : foundAuthors) {
            System.out.println("Author Name: " + author.getName());
        }

        // Recherche de livres par catégorie (Fiction par exemple)
        List<Book> booksByCategory = bookService.findBooksByCategory(fictionCategory); // Replace with actual method
        System.out.println("Books in Fiction category:");
        for (Book book : booksByCategory) {
            System.out.println("Book Title: " + book.getTitle());
        }

        // Recherche de livres par auteur (par exemple Author 1)
        List<Book> booksByAuthor = bookService.findBooksByAuthor(author1); // Replace with actual method
        System.out.println("Books by Author 1:");
        for (Book book : booksByAuthor) {
            System.out.println("Book Title: " + book.getTitle());
        }

        // Fermeture de l'EntityManagerFactory
        emf.close();
    }
}