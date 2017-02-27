PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE movies(movie_id INTEGER PRIMARY KEY NOT NULL, title TEXT,
genre TEXT);
/*Här lägger du in testdata för filmer på formen:
INSERT INTO "movies" VALUES(movie_id, 'title', 'genre');
"movie_id" behöver ej anges, en INTEGER PRIMARY KEY
kommer sqlite att själv nästa lediga värde. Men
för att enklare kunna koppla film till skådespelare
i vår testdata gör vi det här!!*/
INSERT INTO "movies" VALUES(1, 'Rogue One', 'Sci-Fi');
INSERT INTO "movies" VALUES(2, 'The Theory of Everything', 'Drama');
CREATE TABLE actors(actor_id INTEGER PRIMARY KEY NOT NULL, name TEXT,
sex TEXT collate NOCASE CHECK(sex IN ('male','female')));
/*Se info för movies.
collate NOCASE = ignorera gemen/versal
INSERT INTO "actors" VALUES(actor_id, 'name', 'sex');
*/
INSERT INTO "actors" VALUES(1, 'Felicity Jones', 'female');
INSERT INTO "actors" VALUES(2, 'Diego Luna', 'male');
INSERT INTO "actors" VALUES(3, 'Eddie Redmayne', 'male');
CREATE TABLE actors_movies(actor_id INTEGER, movie_id INTEGER);
/*Håll tungan i rätt mun och koppla film till en eller flera
skådespelare.
INSERT INTO "actors_movies" VALUES(actor_id, movie_id);
*/
INSERT INTO "actors_movies" VALUES(1, 1);
INSERT INTO "actors_movies" VALUES(2, 1);
INSERT INTO "actors_movies" VALUES(1, 2);
INSERT INTO "actors_movies" VALUES(3, 2);
COMMIT;
