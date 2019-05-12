
DROP TABLE IF EXISTS songplays;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS artists;
DROP TABLE IF EXISTS time;

CREATE TABLE IF NOT EXISTS songplays(
    songplay_id varchar(30) NOT NULL UNIQUE,
    start_time int,
    user_id VARCHAR(3),
    level VARCHAR(10),
    song_id VARCHAR(30),
    session_id int,
    location VARCHAR(255),
    user_agent VARCHAR(255),

    PRIMARY KEY (songplay_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (song_id) REFERENCES songs(song_id)
);

CREATE TABLE IF NOT EXISTS users(
    user_id VARCHAR(3) NOT NULL UNIQUE,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    gender VARCHAR(1),
    level VARCHAR(10),

    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS songs(
    song_id VARCHAR(30) NOT NULL UNIQUE,
    title VARCHAR(30),
    artist_id VARCHAR(30),
    year int,
    duration float(10),

    PRIMARY KEY (song_id)
);

CREATE TABLE IF NOT EXISTS artists(
    artist_id VARCHAR(30) NOT NULL UNIQUE ,
    name varchar(60),
    location VARCHAR(255),
    lattitude FLOAT(10),
    longitude FLOAT(10),

    PRIMARY KEY (artist_id)
);

CREATE TABLE IF NOT EXISTS logtimes(
    start_time VARCHAR(60),
    hour int,
    day int,
    week int,
    month int,
    year int,
    weekday VARCHAR(15),
    id int NOT NULL AUTO_INCREMENT,

    PRIMARY KEY (id)
);

