package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.tp.data.Book;

public class BookDAO implements CRUDRepository<Book> {
    private final EntityManagerFactory emf;

    public BookDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public void create(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction.commit;
        em.close();
    }
    @Override
    public Book findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Book.class,id);
    }

    @Override
    public List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT a From Book a",Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    @Override
    public void update(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction.commit();
        em.close();
    }

    @Override
    public void delete(Book book) {
        EntityManager em = emf.createEntityManager();
        // ToDo
        em.close();
    }

    public List<Book> findBooksByAuthorId(Long authorId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT a From Book b JOIN b.authors a WHERE a.ID = :authorID",Book.class);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<Book> findBooksByCategoryName(String categoryName) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT a From Book b WHERE b.category = :categoryName",Book.class);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }
}
