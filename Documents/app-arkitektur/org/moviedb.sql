PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE movies(movie_id INTEGER PRIMARY KEY NOT NULL, title TEXT,
unique COLLATE NOCASE, genre TEXT COLLATE NOCASE CHECK(
genre IN ('Action','Comedy','Drama','Horror')), year INTEGER,
regi TEXT, origin TEXT);
/*Här lägger du in testdata för filmer på formen:
INSERT INTO "movies" VALUES(movie_id, 'title', 'genre', 'year', 'regi', 'origin');
"movie_id" behöver ej anges, en INTEGER PRIMARY KEY
kommer sqlite att själv nästa lediga värde. Men
för att enklare kunna koppla film till skådespelare
i vår testdata gör vi det här!!*/
INSERT INTO "movies" VALUES(1, 'Rogue One', 'Sci-Fi', '2016', '', 'USA');
INSERT INTO "movies" VALUES(2, 'The Theory of Everything', 'Drama', '', '', '');
CREATE TABLE actors(actor_id INTEGER PRIMARY KEY NOT NULL, name TEXT COLLATE NOCASE,
sex TEXT collate NOCASE CHECK(sex IN ('male','female')), nationality TEXT
collate NOCASE, born datetime
check(born is strftime('%Y-%m-%d', datetime(born))));
/*Se info för movies.
collate NOCASE = ignorera gemen/versal
INSERT INTO "actors" VALUES(actor_id, 'name', 'sex');
*/
INSERT INTO "actors" VALUES(1, 'Felicity Jones', 'female', '', '');
INSERT INTO "actors" VALUES(2, 'Diego Luna', 'male', '', '');
INSERT INTO "actors" VALUES(3, 'Eddie Redmayne', 'male', '', '');
CREATE TABLE actors_movies(actor_id INTEGER, movie_id INTEGER);
/*Håll tungan i rätt mun och koppla film till en eller flera
skådespelare.
INSERT INTO "actors_movies" VALUES(actor_id, movie_id);
*/
INSERT INTO "actors_movies" VALUES(1, 1);
INSERT INTO "actors_movies" VALUES(2, 1);
INSERT INTO "actors_movies" VALUES(1, 2);
INSERT INTO "actors_movies" VALUES(3, 2);
CREATE TABLE admins(admin_id integer primary key, username text unique,
password text unique check(password not like username));
INSERT INTO "admins" VALUES('admin', 'admin');
COMMIT;
