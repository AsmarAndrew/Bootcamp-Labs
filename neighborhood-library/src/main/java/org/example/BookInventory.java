package org.example;

public class BookInventory {

    public Book[] inventory(){
        Book[] books = new Book[20];
        books[0] = new Book(1, "978-3-16-148410-0", "Java Programming");
        books[1] = new Book(2, "978-1-23-456789-0", "Introduction to Algorithms");
        books[2] = new Book(3, "978-0-12-345678-9", "Data Structures and Algorithms in Java");
        books[3] = new Book(4, "978-0-99-829560-8", "Clean Code: A Handbook of Agile Software Craftsmanship");
        books[4] = new Book(5, "978-0-13-235088-4", "Head First Design Patterns");
        books[5] = new Book(6, "978-0-321-34970-8", "Effective Java");
        books[6] = new Book(7, "978-0-672-33618-7", "Learning Python");
        books[7] = new Book(8, "978-0-07-802212-8", "Operating System Concepts");
        books[8] = new Book(9, "978-0-321-87978-9", "Cracking the Coding Interview");
        books[9] = new Book(10, "978-0-201-63385-6", "Design Patterns: Elements of Reusable Object-Oriented Software");
        books[10] = new Book(11, "978-0-672-32955-9", "Python Crash Course");
        books[11] = new Book(12, "978-1-59327-552-9", "Automate the Boring Stuff with Python");
        books[12] = new Book(13, "978-0-596-52068-7", "Learning Perl");
        books[13] = new Book(14, "978-0-13-595705-9", "Computer Networking: A Top-Down Approach");
        books[14] = new Book(15, "978-1-449-34205-4", "Learning JavaScript Design Patterns");
        books[15] = new Book(16, "978-1-59327-614-4", "Eloquent JavaScript");
        books[16] = new Book(17, "978-0-321-58361-0", "Introduction to the Theory of Computation");
        books[17] = new Book(18, "978-0-201-36131-1", "Artificial Intelligence: A Modern Approach");
        books[18] = new Book(19, "978-1-4842-4213-1", "Deep Learning");
        books[19] = new Book(20, "978-0-672-33490-9", "C Programming Absolute Beginner's Guide");
        return books;
    }

}
