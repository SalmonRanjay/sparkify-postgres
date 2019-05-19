use udacity;


DROP TABLE IF EXISTS songplays;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS artists;
DROP TABLE IF EXISTS time;


CREATE TABLE IF NOT EXISTS users(
    user_id VARCHAR(90) NOT NULL UNIQUE,
    first_name TEXT,
    last_name TEXT,
    gender TEXT,
    level TEXT,

    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS songs(
    song_id VARCHAR(90) NOT NULL UNIQUE,
    title TEXT,
    artist_id TEXT,
    year int,
    duration float(10),

    PRIMARY KEY (song_id)
);

CREATE TABLE IF NOT EXISTS artists(
    artist_id VARCHAR(90) NOT NULL UNIQUE ,
    name TEXT,
    location TEXT,
    lattitude FLOAT(10),
    longitude FLOAT(10),

    PRIMARY KEY (artist_id)
);

CREATE TABLE IF NOT EXISTS logtimes(
    start_time TEXT,
    hour int,
    day int,
    week int,
    month int,
    year int,
    weekday TEXT,
    id int NOT NULL AUTO_INCREMENT,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS songplays(
    songplay_id VARCHAR(90) NOT NULL UNIQUE,
    start_time TEXT,
    user_id VARCHAR(90),
    level TEXT,
    song_id VARCHAR(90),
    session_id int,
    location TEXT,
    user_agent TEXT,
    artist_id VARCHAR(90),
   
    PRIMARY KEY (songplay_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (song_id) REFERENCES songs(song_id),
    FOREIGN KEY (artist_id) REFERENCES artists(artist_id)
);
