--
-- File generated with SQLiteStudio v3.4.4 on Fri Sep 1 06:32:14 2023
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: forecast
CREATE TABLE IF NOT EXISTS forecast (
    forecast_id   INTEGER PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    country_name  TEXT    NOT NULL,
    city_name     TEXT    NOT NULL,
    zip_code      TEXT    NOT NULL,
    forecast_date DATE    NOT NULL,
    temperature   REAL    NOT NULL
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
