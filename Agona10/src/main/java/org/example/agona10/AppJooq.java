package org.example.agona10;

import org.example.agona10.config.JooqConfig;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.example.agona10.jooq.tables.Authors;
import org.example.agona10.jooq.tables.Books;
import org.example.agona10.jooq.tables.People;
import org.example.agona10.jooq.tables.Passports;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class AppJooq {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JooqConfig.class)) {
            DSLContext dsl = ctx.getBean(DSLContext.class);

            UUID a1 = UUID.randomUUID();
            UUID a2 = UUID.randomUUID();
            dsl.insertInto(Authors.AUTHORS, Authors.AUTHORS.ID, Authors.AUTHORS.NAME)
                    .values(a1, "Marina")
                    .values(a2, "Alex")
                    .execute();

            UUID b1 = UUID.randomUUID();
            UUID b2 = UUID.randomUUID();
            UUID b3 = UUID.randomUUID();
            dsl.insertInto(Books.BOOKS, Books.BOOKS.ID, Books.BOOKS.AUTHOR_ID, Books.BOOKS.TITLE)
                    .values(b1, a1, "A-Java")
                    .values(b2, a1, "A-SQL")
                    .values(b3, a2, "B-Algorithms")
                    .execute();

            UUID p1 = UUID.randomUUID();
            UUID s1 = UUID.randomUUID();
            dsl.insertInto(People.PEOPLE, People.PEOPLE.ID, People.PEOPLE.NAME)
                    .values(p1, "Gena")
                    .execute();
            dsl.insertInto(Passports.PASSPORTS, Passports.PASSPORTS.ID, Passports.PASSPORTS.PERSON_ID, Passports.PASSPORTS.NUMBER)
                    .values(s1, p1, "8020143211")
                    .execute();

            var authorsPage = dsl.select(Authors.AUTHORS.ID, Authors.AUTHORS.NAME)
                    .from(Authors.AUTHORS)
                    .orderBy(Authors.AUTHORS.NAME.asc())
                    .limit(1)
                    .offset(1)
                    .fetch();

            System.out.printf("Authors page (limit 1, offset 1, by name asc): %s%n", authorsPage.format());

            var joinAB = dsl.select(Authors.AUTHORS.NAME, Books.BOOKS.TITLE)
                    .from(Authors.AUTHORS)
                    .join(Books.BOOKS).on(Books.BOOKS.AUTHOR_ID.eq(Authors.AUTHORS.ID))
                    .orderBy(Authors.AUTHORS.NAME.asc(), Books.BOOKS.TITLE.asc())
                    .fetch();

            System.out.printf("Author-Book pairs: %s%n", joinAB.format());

            var joinPP = dsl.select(People.PEOPLE.NAME, Passports.PASSPORTS.NUMBER)
                    .from(People.PEOPLE)
                    .leftJoin(Passports.PASSPORTS).on(Passports.PASSPORTS.PERSON_ID.eq(People.PEOPLE.ID))
                    .fetch();

            System.out.printf("People-Passport: %s%n", joinPP.format());

            var booksPerAuthor = dsl.select(Authors.AUTHORS.NAME, DSL.count().as("books_count"))
                    .from(Authors.AUTHORS)
                    .leftJoin(Books.BOOKS).on(Books.BOOKS.AUTHOR_ID.eq(Authors.AUTHORS.ID))
                    .groupBy(Authors.AUTHORS.NAME)
                    .orderBy(Authors.AUTHORS.NAME.asc())
                    .fetch();

            System.out.printf("Books per author: %s%n", booksPerAuthor.format());
        }
    }
}
