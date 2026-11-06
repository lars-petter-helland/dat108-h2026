DROP SCHEMA IF EXISTS f22_personer CASCADE;
CREATE SCHEMA f22_personer;
SET search_path TO f22_personer;

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    fornavn VARCHAR,
    etternavn VARCHAR
);