-- TODO: drop table and remove this script if not used
DROP TABLE IF EXISTS NASHVILLE_NUMBERS;

CREATE TABLE IF NOT EXISTS NASHVILLE_NUMBERS (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  NN_ID INTEGER,
  DESCRIPTION VARCHAR(20)
);

INSERT INTO NASHVILLE_NUMBERS (NN_ID, DESCRIPTION) VALUES
  (1, 'root'),
  (2, 'two'),
  (3, 'three'),
  (4, 'four'),
  (5, 'five'),
  (6, 'six'),
  (7, 'seven'),
  (8, 'octave'),
  (9, 'nine')
;