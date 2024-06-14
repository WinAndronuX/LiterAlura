package com.winandronux.LiterAlura.ConsoleApp;

import com.winandronux.LiterAlura.models.Book;
import com.winandronux.LiterAlura.repository.BookRepository;
import com.winandronux.LiterAlura.repository.PersonRepository;
import com.winandronux.LiterAlura.service.GutendexAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final GutendexAPI gutendexAPI = new GutendexAPI();
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PersonRepository personRepository;

    public void run() {
        var running = true;
        while (running) {
            showMenu();
            var opc = getInput("Elija una opcion:");
            switch (opc) {
                case "1":

                    var query = getInput("Ingrese el nombre del libro que desee buscar");
                    var result = gutendexAPI.search(query);
                    if (result != null) {
                        if (!bookRepository.existsByTitle(result.title())) {
                            var newBook = new Book(result, personRepository);
                            bookRepository.save(newBook);
                            System.out.println(newBook);
                        } else System.out.println("No se puede registrar el libro mas de una vez.");
                    } else System.out.println("Libro no encontrado.");

                    break;
                case "2":
                    var listBooks = bookRepository.findAll();
                    for (var book : listBooks) {
                        System.out.println(book);
                    }
                    break;
                case "3":
                    var listPersons = personRepository.findAll();
                    for (var person : listPersons) {
                        System.out.println(person);
                    }
                    break;
                case "4":

                    var year = getInput("Ingrese el año vivo de autor(es) que desea buscar");
                    var listP = personRepository.findAllByYearAlive(Integer.parseInt(year));
                    for (var p : listP) {
                        System.out.println(p);
                    }

                    break;
                case "5":

                    var lang = getInput("Ingrese el language para buscar libros\nes - Español\nen - Ingles\nfr - Frances\npt - Portugués");
                    var listB = bookRepository.findAllByLanguage(lang);
                    for (var b : listB) {
                        System.out.println(b);
                    }

                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("""
                -----------------------
                Bienvenido a LiterAlura
                -----------------------
                
                1 .- Buscar libro por titulo
                2 .- Listar libros registrados
                3 .- Listar autores registrados
                4 .- Listar autores vivos en un determinado año
                5 .- Listar libros por idioma
                6 .- Salir
                """);
    }

    private String getInput(String text) {
        System.out.println(text);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
